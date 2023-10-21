package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public final class DepthsParticles {
    public static final DefaultParticleType EMBER = FabricParticleTypes.simple();
    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(NetherDepths.MOD_ID, "ember"), EMBER);
    }
}
