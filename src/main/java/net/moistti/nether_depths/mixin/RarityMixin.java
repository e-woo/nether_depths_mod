package net.moistti.nether_depths.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class RarityMixin {
    @Inject(method = "getRarity(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/Rarity;", at = @At("RETURN"), cancellable = true)
    private void injectGetRarity(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
        cir.setReturnValue(stack.getNbt() == null || !stack.getNbt().contains("gem") ? cir.getReturnValue() : switch (cir.getReturnValue()) {
            case COMMON -> Rarity.UNCOMMON;
            case UNCOMMON -> Rarity.RARE;
            default -> Rarity.EPIC;
        });
    }
}
