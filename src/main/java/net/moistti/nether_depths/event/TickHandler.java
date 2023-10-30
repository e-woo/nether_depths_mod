package net.moistti.nether_depths.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.util.DataSaver;
import net.moistti.nether_depths.util.DepthsHeat;

public class TickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (LivingEntity entity : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !DepthsHeat.isImmune(p))) {
                int heat = ((DataSaver) entity).getPersistentData().getInt("heat");
                if (heat >= 20)
                    entity.setOnFireFor(1);
                if (heat > 0 && entity.age % 60 == 0)
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, heat / 15, false, false, false));
//                    entity.damage(world.getDamageSources().onFire(), 1.0f);
            }
        }
    }
}
