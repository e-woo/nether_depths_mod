package net.moistti.nether_depths.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public abstract class TopazLevelBoostMixin {
    @Unique
    private final AnvilScreenHandler self = (AnvilScreenHandler) (Object) this;
    @Redirect(method = "updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int injectGetMaxLevel(Enchantment instance) {
        NbtCompound nbt = self.input.getStack(0).getNbt();
        if (nbt != null && nbt.contains("gem") && nbt.getString("gem").equals("topaz") &&
                (instance instanceof DamageEnchantment || instance instanceof ProtectionEnchantment || instance instanceof EfficiencyEnchantment))
            return instance.getMaxLevel() + 1;
        return instance.getMaxLevel();
    }
}
