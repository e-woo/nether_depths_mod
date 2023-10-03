package net.moistti.nether_depths.mixin;

import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class RegistryUnfreezeMixin {
    @Inject(method = "freeze()Lnet/minecraft/registry/Registry;", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Registry<?>> cir) {
        System.out.println("unfreezing...");
        cir.setReturnValue((SimpleRegistry<?>) (Object) this);
    }
}
