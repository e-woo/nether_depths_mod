package net.moistti.nether_depths.mixin;

import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.moistti.nether_depths.block.entity.EnhancedBeaconBlockEntity;
import net.moistti.nether_depths.screen.EnhancedBeaconScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {
    @Unique
    BeaconBlockEntity self = (BeaconBlockEntity) (Object) this;
    @Inject(method = "createMenu(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/entity/player/PlayerEntity;)Lnet/minecraft/screen/ScreenHandler;",
    at = @At("RETURN"), cancellable = true)
    private void injectCreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity, CallbackInfoReturnable<ScreenHandler> cir) {
        if (cir.getReturnValue() != null && self instanceof EnhancedBeaconBlockEntity)
            cir.setReturnValue(new EnhancedBeaconScreenHandler(i, playerInventory, self.propertyDelegate, ScreenHandlerContext.create(self.world, self.getPos())));
    }

    @Inject(method = "applyPlayerEffects(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/entity/effect/StatusEffect;Lnet/minecraft/entity/effect/StatusEffect;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getNonSpectatingEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private synchronized static void injectBoxGetter(World world, BlockPos pos, int beaconLevel, StatusEffect primaryEffect, StatusEffect secondaryEffect, CallbackInfo ci, double d, int i, int j, Box box) {
        BeaconBlockEntityMixin.box = box;
        BeaconBlockEntityMixin.blockEntity = world.getBlockEntity(pos);
    }
    @Unique private static Box box;
    @Unique private static BlockEntity blockEntity;

    @Redirect(method = "applyPlayerEffects(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/entity/effect/StatusEffect;Lnet/minecraft/entity/effect/StatusEffect;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getNonSpectatingEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"))
    private synchronized static List<PlayerEntity> injectGetPlayerList(World instance, Class<PlayerEntity> clazz, Box box) {
        return instance.getNonSpectatingEntities(clazz, box.equals(BeaconBlockEntityMixin.box) && BeaconBlockEntityMixin.blockEntity instanceof EnhancedBeaconBlockEntity ? box.expand(50) : box);
    }
    @Inject(method = "applyPlayerEffects(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/entity/effect/StatusEffect;Lnet/minecraft/entity/effect/StatusEffect;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectEffectAmplifier(World world, BlockPos pos, int beaconLevel, StatusEffect primaryEffect, StatusEffect secondaryEffect, CallbackInfo ci, double d, int i, int j, Box box, List<PlayerEntity> list, Iterator<PlayerEntity> var11, PlayerEntity playerEntity) {
        if (world.getBlockEntity(pos) instanceof EnhancedBeaconBlockEntity) {
            playerEntity.addStatusEffect(new StatusEffectInstance(primaryEffect, j, i + 1, true, true));
            if (beaconLevel >= 4 && primaryEffect != secondaryEffect && secondaryEffect != null)
                playerEntity.addStatusEffect(new StatusEffectInstance(secondaryEffect, j, 1, true, true));
        }
    }

}
