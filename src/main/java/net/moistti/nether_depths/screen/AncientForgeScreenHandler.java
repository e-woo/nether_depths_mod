package net.moistti.nether_depths.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.moistti.nether_depths.content.DepthsScreens;

public class AncientForgeScreenHandler extends AbstractForgeScreenHandler {
    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(DepthsScreens.ANCIENT_FORGE_SCREEN_HANDLER, syncId, playerInventory, inventory, propertyDelegate);
    }

    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(DepthsScreens.ANCIENT_FORGE_SCREEN_HANDLER, syncId, playerInventory);
    }
}
