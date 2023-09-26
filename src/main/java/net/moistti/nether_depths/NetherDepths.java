package net.moistti.nether_depths;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.moistti.nether_depths.entities.IronBoat;
import net.moistti.nether_depths.events.DepthsHeat;
import net.moistti.nether_depths.registers.RegisterBlocks;
import net.moistti.nether_depths.registers.RegisterEntities;
import net.moistti.nether_depths.registers.RegisterFeatures;
import net.moistti.nether_depths.registers.RegisterItems;
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
		RegisterBlocks.register();
		RegisterItems.register();
		RegisterFeatures.register();
		registerBiomes();
//		registerEvents();
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "red_ash"), RED_ASH);

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


	private static void registerBiomes() {
		NetherBiomes.addNetherBiome(RegistryKey.of(RegistryKeys.BIOME, new Identifier(MOD_ID, "nether_depths")),
				new MultiNoiseUtil.NoiseHypercube(
						new MultiNoiseUtil.ParameterRange(1, 2),
						new MultiNoiseUtil.ParameterRange(-2, -2),
						new MultiNoiseUtil.ParameterRange(0, 1),
						new MultiNoiseUtil.ParameterRange(1, 2),
						new MultiNoiseUtil.ParameterRange(1, 2),
						new MultiNoiseUtil.ParameterRange(1, 2),0
				));
	}

	private static void registerEvents() {
		ClientTickEvents.END_WORLD_TICK.register(new DepthsHeat());
	}


}