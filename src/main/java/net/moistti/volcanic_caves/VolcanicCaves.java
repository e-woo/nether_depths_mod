package net.moistti.volcanic_caves;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.moistti.volcanic_caves.blocks.Netherstone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolcanicCaves implements ModInitializer {
	public static final String MOD_ID = "volcanic_caves";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Block NETHERSTONE = new Netherstone(FabricBlockSettings.create().strength(0.4f));
	public static final DefaultParticleType RED_ASH = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		registerBlocks();
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

	private static void registerBlocks() {
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "netherstone"), NETHERSTONE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "netherstone"), new BlockItem(NETHERSTONE, new FabricItemSettings()));
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