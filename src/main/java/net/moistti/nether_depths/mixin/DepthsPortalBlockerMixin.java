package net.moistti.nether_depths.mixin;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.dimension.DimensionTypes;
import net.moistti.nether_depths.NetherDepths;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalForcer.class)
public abstract class DepthsPortalBlockerMixin {

    @Shadow @Final private ServerWorld world;

    @Inject(method = "isValidPortalPos(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos$Mutable;Lnet/minecraft/util/math/Direction;I)Z",
    at = @At("HEAD"), cancellable = true)
    private void checkPortalValid(BlockPos pos, BlockPos.Mutable temp, Direction portalDirection, int distanceOrthogonalToPortal, CallbackInfoReturnable<Boolean> cir) {
        if (this.world.getDimensionKey().equals(DimensionTypes.THE_NETHER) && (pos.getY() <= 32
                || this.world.getBiome(pos).matchesKey(RegistryKey.of(RegistryKeys.BIOME, new Identifier(NetherDepths.MOD_ID, "nether_depths"))))) {
            NetherDepths.LOGGER.info("blocking portal from nether depths!");
            cir.setReturnValue(false);
        }
    }
}