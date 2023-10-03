package net.moistti.nether_depths.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.moistti.nether_depths.util.DataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
//    @Inject(method = "hasGlint(Lnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
//    private void injectHasGlint(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
//        System.out.println(((DataSaver) (Object) stack).getPersistentData().getBoolean("fireproof"));
//        cir.setReturnValue(stack.hasEnchantments() || ((DataSaver) (Object) stack).getPersistentData().getBoolean("fireproof"));
//    }
}
