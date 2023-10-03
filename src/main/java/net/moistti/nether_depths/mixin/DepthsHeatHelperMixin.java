package net.moistti.nether_depths.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.moistti.nether_depths.util.DataSaver;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)

public abstract class DepthsHeatHelperMixin implements DataSaver {
    private NbtCompound persistentData;

    @Override @NotNull
    public NbtCompound getPersistentData() {
        if (this.persistentData == null)
            this.persistentData = new NbtCompound();
        return this.persistentData;
    }

    @Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;", at = @At("HEAD"))
    private void injectWriteNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (persistentData != null)
            nbt.put("nether_depths.heat", persistentData);
    }

    @Inject(method = "readNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("HEAD"))
    private void injectReadNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("nether_depths.heat"))
            persistentData = nbt.getCompound("nether_depths.heat");
    }
}