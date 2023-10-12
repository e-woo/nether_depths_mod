package net.moistti.nether_depths.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.moistti.nether_depths.screen.AbstractForgeScreenHandler;

public class ForgeFuelSlot extends Slot {
    public ForgeFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return AbstractForgeScreenHandler.isFuel(stack);
    }
}
