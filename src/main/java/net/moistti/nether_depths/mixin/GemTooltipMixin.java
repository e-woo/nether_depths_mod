package net.moistti.nether_depths.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.util.GemUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Map;

@Mixin(ItemStack.class)
public abstract class GemTooltipMixin {
    @Unique
    private final Map<Class<? extends Enchantment>, Formatting> formattingMap = Map.of(DamageEnchantment.class, Formatting.RED, LuckEnchantment.class, Formatting.YELLOW, EfficiencyEnchantment.class, Formatting.GREEN, ProtectionEnchantment.class, Formatting.AQUA);

    @Inject(method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/trim/ArmorTrim;appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/registry/DynamicRegistryManager;Ljava/util/List;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectTooltip(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, List<Text> tooltips) {
        NbtCompound nbt = ((ItemStack) (Object) this).getNbt();
        if (nbt != null && nbt.contains("gem"))
            tooltips.add(Text.translatable("gui.gem_tooltip").formatted(Formatting.GRAY).append(Text.translatable("gem." + NetherDepths.MOD_ID + "." + nbt.getString("gem")).formatted(
                    switch (nbt.getString("gem")) {
                        case "ruby" -> Formatting.RED;
                        case "topaz" -> Formatting.YELLOW;
                        case "jade" -> Formatting.GREEN;
                        case "sapphire" -> Formatting.AQUA;
                        default -> Formatting.WHITE;
                    })));
    }

    @Redirect(method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendEnchantments(Ljava/util/List;Lnet/minecraft/nbt/NbtList;)V"))
    private void injectAppendEnchantments(List<Text> tooltip, NbtList enchantments) {
        NbtCompound nbt = ((ItemStack) (Object) this).getNbt();
        if (nbt != null && nbt.contains("gem"))
            appendGemEnchantments(tooltip, enchantments, nbt.getString("gem"));
        else
            ItemStack.appendEnchantments(tooltip, enchantments);
    }

    @Unique
    private void appendGemEnchantments(List<Text> tooltip, NbtList enchantments, String gem) {
        for (int i = 0; i < enchantments.size(); ++i) {
            NbtCompound nbtCompound = enchantments.getCompound(i);
            Registries.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt(nbtCompound)).ifPresent(e -> formatText(e, tooltip, nbtCompound, gem));
        }
    }

    @Unique
    private void formatText(Enchantment enchantment, List<Text> tooltip, NbtCompound nbtCompound, String gem) {
        Class<? extends Enchantment> enchantmentClass = enchantment.getClass();
        Text text = enchantment.getName(EnchantmentHelper.getLevelFromNbt(nbtCompound));
        if (GemUtil.GEM_ENCHANTS.containsKey(gem) && GemUtil.GEM_ENCHANTS.get(gem).contains(enchantment))
            text = text.copy().formatted(formattingMap.get(enchantmentClass));
        tooltip.add(text);
    }
}
