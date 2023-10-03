package net.moistti.nether_depths.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.moistti.nether_depths.util.DataSaver;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.xml.crypto.Data;

@Mixin(ItemStack.class)

public abstract class ArmorHelperMixin implements DataSaver {

    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (this.persistentData == null) {
            this.persistentData = new NbtCompound();
            if (!persistentData.contains("tag"))
                persistentData.put("tag", new NbtCompound());
            persistentData = persistentData.getCompound("tag");
        }
        return this.persistentData;
    }

    @Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;", at = @At("HEAD"))
    private void injectWriteNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (persistentData != null)
            nbt.put("tag", persistentData);
    }
    @Inject(method = "hasGlint()Z", at = @At("RETURN"), cancellable = true)
    private void injectHasGlint(CallbackInfoReturnable<Boolean> cir) {
        ItemStack item = (ItemStack) (Object) this;
        cir.setReturnValue(item.getItem().hasGlint(item) || ((DataSaver) (Object) item).getPersistentData().getBoolean("fireproof"));
    }
}