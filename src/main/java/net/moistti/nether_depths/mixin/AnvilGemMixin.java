package net.moistti.nether_depths.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;
import net.moistti.nether_depths.util.GemUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilGemMixin {
    @Unique
    private final AnvilScreenHandler self = (AnvilScreenHandler) (Object) this;
    @Redirect(method = "updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int injectGetMaxLevel(Enchantment instance) {
        NbtCompound nbt = self.input.getStack(0).getNbt();
        try {
            if (nbt != null && nbt.contains("gem") && GemUtil.GEM_ENCHANTS.get(nbt.getString("gem")).contains(instance))
                return instance.getMaxLevel() + 1;
            return instance.getMaxLevel();
        } catch (NullPointerException e) {
            return instance.getMaxLevel();
        }
    }
}
