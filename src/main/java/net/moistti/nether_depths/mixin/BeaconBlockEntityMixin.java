package net.moistti.nether_depths.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.moistti.nether_depths.blocks.EnhancedBeaconBlockEntity;
import net.moistti.nether_depths.screen.EnhancedBeaconScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {
    @Unique
    BeaconBlockEntity self = (BeaconBlockEntity) (Object) this;
    @Inject(method = "createMenu(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/screen/ScreenHandler;",
    at = @At("RETURN"), cancellable = true)
    private void injectCreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity, CallbackInfoReturnable<ScreenHandler> cir) {
        if (cir.getReturnValue() != null && self instanceof EnhancedBeaconBlockEntity) {System.out.println("test");
            cir.setReturnValue(new EnhancedBeaconScreenHandler(i, playerInventory, self.propertyDelegate, ScreenHandlerContext.create(self.world, self.getPos())));}
    }

    @Redirect(method = "applyPlayerEffects(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/entity/effect/StatusEffect;Lnet/minecraft/entity/effect/StatusEffect;)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private static boolean injectApplyPlayerEffects(PlayerEntity instance, StatusEffectInstance effect) {
        return instance.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), effect.getDuration(), effect.getAmplifier() + 1, true, true));
    }


}
