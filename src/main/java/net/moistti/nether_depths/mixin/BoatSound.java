package net.moistti.nether_depths.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.moistti.nether_depths.NetherDepths;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Adds sounds to boats in lava.*/
@Mixin(BoatEntity.class)
public abstract class BoatSound {
    /** Modifies the pitch of the sound to be lower if the boat is in lava. */
    @ModifyArg(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"), index = 7)
    private float modifyPitch(float pitch) {
        return ((Entity) (Object) this).isInLava() ? pitch - 1.0f : pitch;
    }

    /** Ensures the water play sound occurs when the boat is in lava as well. */
    @Inject(method = "getPaddleSoundEvent()Lnet/minecraft/sound/SoundEvent;", at = @At("HEAD"), cancellable = true)
    private void soundEvent(CallbackInfoReturnable<SoundEvent> cir) {
        if (((Entity) (Object) this).isInLava())
            cir.setReturnValue(SoundEvents.ENTITY_BOAT_PADDLE_WATER);
    }
}
