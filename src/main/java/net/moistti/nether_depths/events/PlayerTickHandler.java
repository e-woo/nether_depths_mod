package net.moistti.nether_depths.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.util.EntityDataSaver;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (LivingEntity p : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !p.isFireImmune())) {
                if (((EntityDataSaver) p).getPersistentData().getInt("heat") >= 20)
                    p.damage(world.getDamageSources().hotFloor(), 1.0f);
            }
        }
    }
}
