package net.moistti.nether_depths.mixin;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NoiseChunkGenerator.class)
public abstract class LavaOceanTickerMixin {

    @Shadow public abstract boolean matchesSettings(RegistryKey<ChunkGeneratorSettings> settings);

    /**
     * Ticks lava ocean fluids between y = 0 and y = 32 in the nether to pour down into holes and caves underneath.
     * This eliminates "floating lava".
     * */
    @Redirect(method = "populateNoise(Lnet/minecraft/world/gen/chunk/Blender;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/noise/NoiseConfig;Lnet/minecraft/world/chunk/Chunk;II)Lnet/minecraft/world/chunk/Chunk;",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/AquiferSampler;needsFluidTick()Z"))
    private boolean tickLavaOceanFluids(AquiferSampler instance) {
        return this.matchesSettings(ChunkGeneratorSettings.NETHER) || instance.needsFluidTick();
    }

}
