package net.moistti.nether_depths.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.mojang.serialization.Lifecycle;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com/mojang/serialization/Lifecycle", remap = false)
public abstract class ExperimentalScreenDisableMixin {
    @Inject(method = "experimental()Lcom/mojang/serialization/Lifecycle;", at = @At("RETURN"), cancellable = true)
    private static void disableExperimentalScreen(CallbackInfoReturnable<Lifecycle> cir) {
        cir.setReturnValue(Lifecycle.stable());
    }
}