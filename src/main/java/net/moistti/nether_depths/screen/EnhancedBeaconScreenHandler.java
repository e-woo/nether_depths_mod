package net.moistti.nether_depths.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.BeaconScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.moistti.nether_depths.content.DepthsBlocks;
import net.moistti.nether_depths.content.DepthsScreens;

public class EnhancedBeaconScreenHandler extends BeaconScreenHandler {
    public EnhancedBeaconScreenHandler(int syncId, Inventory inventory) {
        super(syncId, inventory);
        this.type = DepthsScreens.ENHANCED_BEACON_SCREEN_HANDLER_SCREEN_HANDLER;
    }

    public EnhancedBeaconScreenHandler(int syncId, Inventory inventory, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(syncId, inventory, propertyDelegate, context);
        this.type = DepthsScreens.ENHANCED_BEACON_SCREEN_HANDLER_SCREEN_HANDLER;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return EnhancedBeaconScreenHandler.canUse(this.context, player, DepthsBlocks.ENHANCED_BEACON);
    }
}
