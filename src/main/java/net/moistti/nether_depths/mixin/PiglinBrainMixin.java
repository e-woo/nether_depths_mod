package net.moistti.nether_depths.mixin;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.entities.PiglinEliteEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(method = "consumeOffHandItem(Lnet/minecraft/entity/mob/PiglinEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinBrain;acceptsForBarter(Lnet/minecraft/item/ItemStack;)Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    private synchronized static void injectBarterItems(PiglinEntity piglin, boolean barter, CallbackInfo ci, ItemStack itemStack) {
        barterSuccess = (itemStack.isOf(Items.GOLD_INGOT) && !(piglin instanceof PiglinEliteEntity)) || (itemStack.isOf(Items.GOLD_BLOCK) && piglin instanceof PiglinEliteEntity);
    }
    @Unique
    private static boolean barterSuccess;

    @Inject(method = "acceptsForBarter(Lnet/minecraft/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private synchronized static void injectAcceptsForBarter(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(barterSuccess);
    }

    @Inject(method = "getBarteredItem(Lnet/minecraft/entity/mob/PiglinEntity;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private static void injectLootTable(PiglinEntity piglin, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (piglin instanceof PiglinEliteEntity && piglin.getWorld().getServer() != null)
            cir.setReturnValue(piglin.getWorld().getServer().getLootManager().getLootTable(new Identifier(NetherDepths.MOD_ID, "gameplay/piglin_elite_bartering"))
                    .generateLoot(new LootContextParameterSet.Builder((ServerWorld)piglin.getWorld()).add(LootContextParameters.THIS_ENTITY, piglin).build(LootContextTypes.BARTER))
            );
    }
}
