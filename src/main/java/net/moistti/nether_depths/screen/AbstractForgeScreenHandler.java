package net.moistti.nether_depths.screen;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsItems;
import net.moistti.nether_depths.content.DepthsRecipes;
import net.moistti.nether_depths.forging.AbstractForgingRecipe;
import net.moistti.nether_depths.screen.slot.ForgeFuelSlot;
import net.moistti.nether_depths.screen.slot.ForgeGemSlot;
import net.moistti.nether_depths.screen.slot.ForgeOutputSlot;

import java.util.Arrays;
import java.util.List;

public class AbstractForgeScreenHandler extends ScreenHandler {
    private final RecipeType<? extends AbstractForgingRecipe> recipeType;
    private final Inventory inventory;
//    private final PropertyDelegate propertyDelegate;
    protected final World world;
    private final List<Item> gems = Arrays.asList(DepthsItems.RUBY, DepthsItems.SAPPHIRE, DepthsItems.TOPAZ);

    protected AbstractForgeScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends AbstractForgingRecipe> recipeType, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(type, syncId);
        this.recipeType = recipeType;
        this.inventory = inventory;
//        this.propertyDelegate = propertyDelegate;
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

    }

    public AbstractForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(DepthsRecipes.ANCIENT_FORGE_SCREEN_HANDLER, DepthsRecipes.FORGING, syncId, playerInventory, new SimpleInventory(6));//, new ArrayPropertyDelegate(6));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = (Slot)this.slots.get(slot);
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
        ItemStack fuel = inventory.getStack(0);
        ItemStack ingredient = inventory.getStack(1);
        ItemStack gem = inventory.getStack(2);
        if (isFuel(fuel) && isIngredient(ingredient) && isGem(gem)) {
            System.out.println("correct recipe!");
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
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
}
