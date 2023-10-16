package net.moistti.nether_depths.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.moistti.nether_depths.NetherDepths;
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
            tooltips.add(Text.translatable("gui.gem_tooltip").formatted(Formatting.GRAY).append(Text.translatable("gem." + NetherDepths.MOD_ID + "." + nbt.getString("gem")).formatted(
                    switch (nbt.getString("gem")) {
                        case "ruby" -> Formatting.RED;
                        case "topaz" -> Formatting.YELLOW;
                        case "jade" -> Formatting.GREEN;
                        case "sapphire" -> Formatting.AQUA;
                        default -> Formatting.WHITE;
                    })));
    }
}
