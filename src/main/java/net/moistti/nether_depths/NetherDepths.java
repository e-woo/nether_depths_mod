package net.moistti.nether_depths;

import net.fabricmc.api.ModInitializer;

import net.moistti.nether_depths.biomes.DepthsBiomes;
import net.moistti.nether_depths.block.DepthsBlocks;
import net.moistti.nether_depths.block.entity.DepthsBlockEntityTypes;
import net.moistti.nether_depths.enchantment.DepthsEnchantments;
import net.moistti.nether_depths.entity.DepthsEntities;
import net.moistti.nether_depths.event.DepthsEvents;
import net.moistti.nether_depths.feature.DepthsFeatures;
import net.moistti.nether_depths.item.DepthsItems;
import net.moistti.nether_depths.network.Packets;
import net.moistti.nether_depths.particle.DepthsParticles;
import net.moistti.nether_depths.screen.DepthsScreens;
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
		DepthsBlockEntityTypes.register();
		DepthsEnchantments.register();
		DepthsEntities.registerAttributes();
		Packets.registerC2SPackets();
		Packets.registerS2CPackets();
	}
}