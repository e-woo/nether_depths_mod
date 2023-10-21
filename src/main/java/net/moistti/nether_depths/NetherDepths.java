package net.moistti.nether_depths;

import net.fabricmc.api.ModInitializer;

import net.moistti.nether_depths.content.*;
import net.moistti.nether_depths.network.Packets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetherDepths implements ModInitializer {
	public static final String MOD_ID = "nether_depths";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		DepthsBlocks.register();
		DepthsItems.register();
		DepthsFeatures.register();
		DepthsBiomes.register();
		DepthsEvents.register();
		DepthsParticles.register();
		DepthsScreens.register();
		DepthsBlockEntities.register();
		DepthsEnchantments.register();
		DepthsEntities.registerAttributes();
		Packets.registerC2SPackets();
		Packets.registerS2CPackets();
	}
}