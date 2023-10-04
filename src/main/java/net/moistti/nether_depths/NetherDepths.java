package net.moistti.nether_depths;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.content.*;
import net.moistti.nether_depths.network.Packets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetherDepths implements ModInitializer {
	public static final String MOD_ID = "nether_depths";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType RED_ASH = FabricParticleTypes.simple();
//	public static final EntityType<IronBoat> IRON_BOAT =
//			Registry.register(Registries.ENTITY_TYPE, new Identifier(NetherDepths.MOD_ID, "iron_boat"), FabricEntityTypeBuilder.create(
//							SpawnGroup.MISC, IronBoat::new).dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).fireImmune()
//					.build()
//			);
	@Override
	public void onInitialize() {
		DepthsBlocks.register();
		DepthsItems.register();
		DepthsFeatures.register();
		DepthsBiomes.register();
		DepthsEvents.register();
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "red_ash"), RED_ASH);
		new DepthsRecipes();
		new DepthsBlockEntities();
		Packets.registerC2SPackets();
		Packets.registerS2CPackets();

//		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
//				new SimpleSynchronousResourceReloadListener() {
//					@Override
//					public Identifier getFabricId() {
//						return new Identifier(MOD_ID, "resources");
//					}
//
//					@Override
//					public void reload(ResourceManager manager) {
//						manager.findResources("", path -> { return path.getPath().endsWith(".json");}).forEach(
//								(id, resource) -> {
//									try (InputStream stream = manager.getResourceOrThrow(id).getInputStream()) {
//
//									}
//									catch (Exception e) {
//										LOGGER.info("An error occured while loading the JSON " + id.toString() + e);
//									}
//								}
//						);
//					}
//				}
//		);
	}
}