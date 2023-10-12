package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moistti.nether_depths.NetherDepths;

import java.util.function.Predicate;

public final class DepthsFeatures {
    private static final Predicate<BiomeSelectionContext> NETHER_DEPTH_PREDICATE = BiomeSelectionContext -> BiomeSelectionContext.getBiomeKey().equals(RegistryKey.of(RegistryKeys.BIOME, new Identifier(NetherDepths.MOD_ID, "nether_depths")));
    public static final RegistryKey<PlacedFeature> NETHERSTONE_GOLD_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_netherstone_gold"));
    public static final RegistryKey<PlacedFeature> NETHERSTONE_QUARTZ_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_netherstone_quartz"));
    public static final RegistryKey<PlacedFeature> ANCIENT_DEBRIS_DEPTHS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_ancient_debris_depths"));
    public static final RegistryKey<PlacedFeature> MINERAL_CLUSTER_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_mineral_cluster"));
    public static final RegistryKey<PlacedFeature> MAGMA_DEPTHS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_magma_depths"));
    public static final RegistryKey<PlacedFeature> SPRING_DEPTHS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "spring_depths_open"));
    public static final RegistryKey<PlacedFeature> CRYSTAL_DEPTHS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "crystals_depths"));

    public static void register() {
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, NETHERSTONE_GOLD_ORE_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, NETHERSTONE_QUARTZ_ORE_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, ANCIENT_DEBRIS_DEPTHS_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, MINERAL_CLUSTER_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, MAGMA_DEPTHS_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.FLUID_SPRINGS, SPRING_DEPTHS_PLACED_KEY);
        BiomeModifications.addFeature(NETHER_DEPTH_PREDICATE, GenerationStep.Feature.UNDERGROUND_ORES, CRYSTAL_DEPTHS_PLACED_KEY);
    }


}
