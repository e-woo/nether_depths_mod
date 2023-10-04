package net.moistti.nether_depths.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.moistti.nether_depths.screen.AbstractForgeScreenHandler;

public class ForgeGemSlot extends Slot {
    private final AbstractForgeScreenHandler handler;
    public ForgeGemSlot(AbstractForgeScreenHandler handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return this.handler.isGem(stack);
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return 1;
    }
}
