package net.moistti.nether_depths.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.DepthsHeat;
import net.moistti.nether_depths.NetherDepths;

public class DepthsHeatEvent implements ServerTickEvents.EndWorldTick {
    public static final float HEAT_MIN = 0.0f;
    public static final float HEAT_MAX = 10.0f;
    public static final float HEAT_TICK_AMOUNT = 0.05f;
    private static final Identifier NETHER_DEPTHS_BIOME_ID = new Identifier(NetherDepths.MOD_ID, "nether_depths");
    @Override
    public void onEndTick(ServerWorld world) {
        for (LivingEntity p : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !p.isFireImmune())) {
            DepthsHeat q = (DepthsHeat) p;
            if (world.getBiome(p.getBlockPos()).matchesId(NETHER_DEPTHS_BIOME_ID)) {
                if (q.getHeat() <= HEAT_MAX)
                    q.heat(HEAT_TICK_AMOUNT);
            }
            else if (q.getHeat() >= HEAT_MIN)
                q.cool(HEAT_TICK_AMOUNT);

            if (q.getHeat() >= 9.999f)
                p.damage(world.getDamageSources().hotFloor(), 1.0f);
        }
    }
}
