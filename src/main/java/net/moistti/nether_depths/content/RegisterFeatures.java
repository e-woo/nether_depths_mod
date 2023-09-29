package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moistti.nether_depths.NetherDepths;

public final class RegisterFeatures {
    public static final RegistryKey<PlacedFeature> NETHERSTONE_GOLD_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_netherstone_gold"));
    public static final RegistryKey<PlacedFeature> NETHERSTONE_QUARTZ_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_netherstone_quartz"));
    public static final RegistryKey<PlacedFeature> ANCIENT_DEBRIS_DEPTHS_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(NetherDepths.MOD_ID, "ore_ancient_debris_depths"));
    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, NETHERSTONE_GOLD_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, NETHERSTONE_QUARTZ_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, ANCIENT_DEBRIS_DEPTHS_PLACED_KEY);
    }
}
