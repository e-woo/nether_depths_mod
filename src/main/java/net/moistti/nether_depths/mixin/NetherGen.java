package net.moistti.nether_depths.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.moistti.nether_depths.NetherDepths;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public abstract class NetherGen {
    @Inject(method = "createFluidLevelSampler(Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;)Lnet/minecraft/world/gen/chunk/AquiferSampler$FluidLevelSampler;",
    at = @At("RETURN"), cancellable = true)
    private static void inject(ChunkGeneratorSettings settings, CallbackInfoReturnable<AquiferSampler.FluidLevelSampler> cir) {
        NetherDepths.LOGGER.info("injecting mixin!");
        int i = settings.seaLevel();
        if (settings.defaultFluid().equals(Fluids.LAVA.getDefaultState().getBlockState())) {
            NetherDepths.LOGGER.info("in nether!");
            cir.setReturnValue((x, y, z) -> {
                if (y < Math.min(-54, i) || (y > 0 && y < 32)) {
                    return new AquiferSampler.FluidLevel(32, Blocks.LAVA.getDefaultState());
                }
                return new AquiferSampler.FluidLevel(i, settings.defaultFluid());
            });
        }
    }
}
