package net.moistti.nether_depths.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.util.DataSaver;
import net.moistti.nether_depths.util.DepthsHeat;

public class TickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (LivingEntity p : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !DepthsHeat.isImmune(p))) {
                if (((DataSaver) p).getPersistentData().getInt("heat") >= 20)
                    p.damage(world.getDamageSources().onFire(), 1.0f);
            }
        }
    }
}
