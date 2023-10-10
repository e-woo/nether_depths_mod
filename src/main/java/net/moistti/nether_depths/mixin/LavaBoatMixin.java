package net.moistti.nether_depths.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Vec3d;
import net.moistti.nether_depths.entities.LavaBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
/** For a lava boat. Responsible for adding on to vanilla boat physics to accommodate for lava as well.*/
@Mixin(BoatEntity.class)
public abstract class LavaBoatMixin {
    @Unique
    private final BoatEntity self = (BoatEntity) (Object) this;
    @Redirect(method = "getUnderWaterLocation()Lnet/minecraft/entity/vehicle/BoatEntity$Location;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean injectGetUnderwaterLocation(FluidState instance, TagKey<Fluid> tag) {
        return checkIsInLava(instance, tag);
    }

    @Redirect(method = "checkBoatInWater()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean injectCheckBoatInWater(FluidState instance, TagKey<Fluid> tag) {
        return checkIsInLava(instance, tag);
    }

    @Redirect(method = "getWaterHeightBelow()F", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean injectGetWaterHeightBelow(FluidState instance, TagKey<Fluid> tag) {
        return checkIsInLava(instance, tag);
    }

    @Redirect(method = "fall(DZLnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean injectFall(FluidState instance, TagKey<Fluid> tag) {
        return checkIsInLava(instance, tag);
    }

    @Inject(method = "updateVelocity()V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectUpdateVelocity(CallbackInfo ci, double d, double e, double f) {
        if (!(self instanceof LavaBoat))
            return;
        Vec3d velocity = self.getVelocity();
        if ((self.location == BoatEntity.Location.IN_WATER || self.location == BoatEntity.Location.UNDER_WATER) && f > -0.35)
            ((BoatEntity)(Object) this).setVelocity(velocity.x, (velocity.y + (f + 0.35) * 0.06153846016296973) * 0.75, velocity.z);
    }
    @Unique
    private boolean checkIsInLava(FluidState instance, TagKey<Fluid> tag) {
        if (self instanceof LavaBoat)
            return instance.isIn(tag) || instance.isIn(FluidTags.LAVA);
        return instance.isIn(tag);
    }
}
