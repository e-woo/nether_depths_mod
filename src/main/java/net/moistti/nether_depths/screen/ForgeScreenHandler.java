package net.moistti.nether_depths.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.moistti.nether_depths.content.DepthsScreens;

public class ForgeScreenHandler extends AbstractForgeScreenHandler {
    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(DepthsScreens.FORGE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }

    public ForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(DepthsScreens.FORGE_SCREEN_HANDLER, syncId, playerInventory);
    }
}
