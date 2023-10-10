package net.moistti.nether_depths.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ArmorGemTooltipMixin {
    @Unique
    ItemStack self = (ItemStack) (Object) this;
    @Inject(method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/trim/ArmorTrim;appendTooltip(Lnet/minecraft/item/ItemStack;Lnet/minecraft/registry/DynamicRegistryManager;Ljava/util/List;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectTooltip(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, List<Text> tooltips) {
        NbtCompound nbt = self.getNbt();
        if (nbt != null && nbt.contains("gem"))
            tooltips.add(Text.literal("Gem: ").formatted(Formatting.GRAY).append(Text.translatable("gem.nether_depths." + nbt.getString("gem")).formatted(
                    switch (nbt.getString("gem")) {
                        case "ruby" -> Formatting.RED;
                        case "sapphire" -> Formatting.AQUA;
                        case "topaz" -> Formatting.YELLOW;
                        default -> Formatting.WHITE;
                    })));
    }
}
