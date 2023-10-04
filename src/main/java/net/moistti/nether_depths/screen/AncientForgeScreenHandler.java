package net.moistti.nether_depths.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.moistti.nether_depths.content.DepthsRecipes;

public class AncientForgeScreenHandler extends AbstractForgeScreenHandler {

    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(DepthsRecipes.ANCIENT_FORGE_SCREEN_HANDLER, DepthsRecipes.FORGING, syncId, playerInventory, inventory);
    }

    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(syncId, playerInventory);
    }
}
