package net.moistti.nether_depths.mixin;

import net.minecraft.entity.LivingEntity;
import net.moistti.nether_depths.DepthsHeat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public class DepthsHeatMixin implements DepthsHeat {
    @Unique float heatCounter = 0.0f;
    @Override
    public float getHeat() {
        return heatCounter;
    }

    @Override
    public void heat(float amt) {
        heatCounter = Math.min(10.0f, heatCounter + amt);
    }

    @Override
    public void cool(float amt) {
        heatCounter = Math.max(0.0f, heatCounter - amt);
    }
}
