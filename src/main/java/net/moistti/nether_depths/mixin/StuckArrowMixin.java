package net.moistti.nether_depths.mixin;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.moistti.nether_depths.entity.ExplosiveArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PersistentProjectileEntity.class)
public abstract class StuckArrowMixin {
    @ModifyArg(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setStuckArrowCount(I)V"))
    private int injectSetStuckArrowCount(int stuckArrowCount) {
        return ((PersistentProjectileEntity) (Object) (this)) instanceof ExplosiveArrowEntity ? stuckArrowCount - 1 : stuckArrowCount;
    }
}
