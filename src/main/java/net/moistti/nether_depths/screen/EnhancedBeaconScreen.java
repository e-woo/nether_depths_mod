package net.moistti.nether_depths.screen;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BeaconScreenHandler;
import net.minecraft.text.Text;

public class EnhancedBeaconScreen extends BeaconScreen {
    public EnhancedBeaconScreen(BeaconScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
}
