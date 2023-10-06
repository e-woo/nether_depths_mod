package net.moistti.nether_depths.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.moistti.nether_depths.screen.AbstractForgeScreenHandler;

public class ForgeGemSlot extends Slot {
    public ForgeGemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return AbstractForgeScreenHandler.isGem(stack);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
