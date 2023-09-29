package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.moistti.nether_depths.NetherDepths;

public final class RegisterBiomes {
    public static void register() {
        NetherBiomes.addNetherBiome(RegistryKey.of(RegistryKeys.BIOME, new Identifier(NetherDepths.MOD_ID, "nether_depths")),
                new MultiNoiseUtil.NoiseHypercube(
                        new MultiNoiseUtil.ParameterRange(1, 2),
                        new MultiNoiseUtil.ParameterRange(-2, -2),
                        new MultiNoiseUtil.ParameterRange(0, 1),
                        new MultiNoiseUtil.ParameterRange(1, 2),
                        new MultiNoiseUtil.ParameterRange(1, 2),
                        new MultiNoiseUtil.ParameterRange(1, 2),0
                ));
    }
}
