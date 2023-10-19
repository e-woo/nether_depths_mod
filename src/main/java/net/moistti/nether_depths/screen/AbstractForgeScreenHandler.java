package net.moistti.nether_depths.screen;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsItems;
import net.moistti.nether_depths.items.GemItem;
import net.moistti.nether_depths.screen.slot.ForgeFuelSlot;
import net.moistti.nether_depths.screen.slot.ForgeGemSlot;
import net.moistti.nether_depths.screen.slot.ForgeOutputSlot;

public class AbstractForgeScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;

    protected AbstractForgeScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        AbstractForgeScreenHandler.checkSize(inventory, 3);
        this.world = playerInventory.player.getWorld();
        this.addSlot(new ForgeFuelSlot(this.inventory, 0, 40, 47)); // fuel slot
        this.addSlot(new Slot(this.inventory, 1, 31, 21)); // input slot
        this.addSlot(new ForgeGemSlot(this.inventory, 2, 49, 21)); // gem slot
        this.addSlot(new ForgeOutputSlot(this.inventory, 3, 116, 35)); // output slot
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

    public AbstractForgeScreenHandler(ScreenHandlerType<?> screenHandlerType, int syncId, PlayerInventory playerInventory) {
        this(screenHandlerType, syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(4));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        Slot sourceSlot = this.slots.get(slot);
        if (!sourceSlot.hasStack())
            return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyStack = sourceStack.copy();
        if (slot == 3) {
            if (!this.insertItem(sourceStack, 4, 40, true))
                return ItemStack.EMPTY;
            sourceSlot.onQuickTransfer(sourceStack, copyStack);
        } else if (slot == 1 || slot == 0 || slot == 2 ? !this.insertItem(sourceStack, 4, 40, false) : // move item from forge to inventory
                isIngredient(sourceStack) ? !this.insertItem(sourceStack, 1, 2, false) : // move ingredient to forge
                isFuel(sourceStack) ? !this.insertItem(sourceStack, 0, 1, false) : // move fuel to forge
                isGem(sourceStack) && !this.insertItem(sourceStack, 2, 3, false)) // move gem to forge
            return ItemStack.EMPTY;
        if (sourceStack.isEmpty())
            sourceSlot.setStack(ItemStack.EMPTY);
        else
            sourceSlot.markDirty();
        if (sourceStack.getCount() == copyStack.getCount())
            return ItemStack.EMPTY;
        sourceSlot.onTakeItem(player, sourceStack);
        return copyStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        if (isForging() && !validIngredients()) {
            this.propertyDelegate.set(0, 0);
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getCookProgress() {
        int forgingTime = this.propertyDelegate.get(0);
        int totalTime = 400;
        return forgingTime * 24 / totalTime;
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.isOf(DepthsItems.FIRE_SHARD);
    }

    public static boolean isGem(ItemStack stack) {
        return stack.getItem() instanceof GemItem;
    }

    public static boolean isIngredient(ItemStack stack) {
        for (Enchantment enchantment : EnchantmentHelper.get(stack).keySet()) {
            if (EnchantmentHelper.get(stack).get(enchantment) > enchantment.getMaxLevel())
                return false;
        }
        Item item = stack.getItem();
        return (item instanceof MiningToolItem || item instanceof SwordItem || item instanceof ArmorItem);
    }

    public static boolean validIngredients(Inventory inventory) {
        Item inputItem = inventory.getStack(1).getItem();
        ItemStack gem = inventory.getStack(2);
        return isFuel(inventory.getStack(0)) && isIngredient(inventory.getStack(1)) && (
                (inputItem instanceof SwordItem && (gem.isOf(DepthsItems.RUBY) || gem.isOf(DepthsItems.TOPAZ))) ||
                (inputItem instanceof AxeItem && (gem.isOf(DepthsItems.RUBY) || gem.isOf(DepthsItems.JADE) || gem.isOf(DepthsItems.TOPAZ))) ||
                ((inputItem instanceof PickaxeItem || inputItem instanceof ShovelItem || inputItem instanceof HoeItem) && (gem.isOf(DepthsItems.JADE) || gem.isOf(DepthsItems.TOPAZ))) ||
                (inputItem instanceof ArmorItem && gem.isOf(DepthsItems.SAPPHIRE)));
    }

    public boolean validIngredients() {
        return validIngredients(inventory);
    }
    public boolean isForging() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean onButtonClick(PlayerEntity player, int id) {
        propertyDelegate.set(0, 1);
        return true;
    }
}
