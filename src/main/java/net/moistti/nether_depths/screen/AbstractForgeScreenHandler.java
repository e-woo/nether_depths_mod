package net.moistti.nether_depths.screen;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsItems;
import net.moistti.nether_depths.content.DepthsRecipes;
import net.moistti.nether_depths.screen.slot.ForgeFuelSlot;
import net.moistti.nether_depths.screen.slot.ForgeGemSlot;
import net.moistti.nether_depths.screen.slot.ForgeOutputSlot;

import java.util.Arrays;
import java.util.List;

public class AbstractForgeScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;
    private final List<Item> gems = Arrays.asList(DepthsItems.RUBY, DepthsItems.SAPPHIRE, DepthsItems.TOPAZ);
    private boolean forging = false;

    protected AbstractForgeScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        AbstractForgeScreenHandler.checkSize(inventory, 3);
        this.world = playerInventory.player.getWorld();
        this.addSlot(new ForgeFuelSlot(this, this.inventory, 0, 40, 47)); // fuel slot
        this.addSlot(new Slot(this.inventory, 1, 31, 21)); // input slot
        this.addSlot(new ForgeGemSlot(this, this.inventory, 2, 49, 21)); // gem slot
        this.addSlot(new ForgeOutputSlot(playerInventory.player, this.inventory, 3, 116, 35)); // output slot
        // add player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        this.addProperties(propertyDelegate);

    }

    public AbstractForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(DepthsRecipes.ANCIENT_FORGE_SCREEN_HANDLER, syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(4));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 3) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot == 1 || slot == 0 || slot == 2 ? !this.insertItem(itemStack2, 4, 40, false) : (this.isIngredient(itemStack2)? !this.insertItem(itemStack2, 1, 2, false) : (this.isFuel(itemStack2) ? !this.insertItem(itemStack2, 0, 1, false) : this.isGem(itemStack2) ? !this.insertItem(itemStack2, 2, 3, false) : (slot >= 4 && slot < 31 ? !this.insertItem(itemStack2, 31, 40, false) : slot >= 31 && slot < 40 && !this.insertItem(itemStack2, 4, 31, false))))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot2.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
//        ItemStack fuel = inventory.getStack(0);
//        ItemStack ingredient = inventory.getStack(1);
//        ItemStack gem = inventory.getStack(2);
//        if (isFuel(fuel) && isIngredient(ingredient) && isGem(gem)) {
        if (validIngredients()) {
            System.out.println("correct recipe!");
            propertyDelegate.set(3, 1);
        }
        else {
            propertyDelegate.set(3, 0);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getCookProgress() {
        int i = this.propertyDelegate.get(2);
        int j = this.propertyDelegate.get(3);
        if (j == 0 || i == 0) {
            return 0;
        }
        return i * 24 / j;
    }


    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }

    public boolean isFuel(ItemStack stack) {
        return stack.isOf(DepthsItems.FIRE_CRYSTAL);
    }

    public boolean isGem(ItemStack stack) {
        return gems.contains(stack.getItem());
    }

    public boolean isIngredient(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof MiningToolItem || item instanceof SwordItem || item instanceof ArmorItem;
    }

    public boolean validIngredients() {
        ItemStack fuel = inventory.getStack(0);
        ItemStack ingredient = inventory.getStack(1);
        ItemStack gem = inventory.getStack(2);
        return isFuel(fuel) && isIngredient(ingredient) && isGem(gem);
    }

    public boolean isForging() {
        return forging;
    }

    public void setForging(boolean forging) {
        System.out.println("forging: " + forging);
        this.forging = forging;
    }

    public boolean onButtonClick(PlayerEntity player, int id) {
        propertyDelegate.set(0, 1);
        System.out.println("set property!");
        return true;
    }
}
