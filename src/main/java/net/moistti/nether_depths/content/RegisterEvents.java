package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.moistti.nether_depths.events.DepthsHeatEvent;

public final class RegisterEvents {
    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(new DepthsHeatEvent());
    }
}
