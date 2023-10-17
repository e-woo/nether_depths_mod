package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.moistti.nether_depths.events.TickHandler;
import net.moistti.nether_depths.network.Packets;

public final class DepthsEvents {
    public static void register() {
        ClientTickEvents.END_WORLD_TICK.register(world -> ClientPlayNetworking.send(Packets.HEAT_ID, PacketByteBufs.create()));
        ClientTickEvents.END_WORLD_TICK.register(world -> ClientPlayNetworking.send(Packets.FIREPROOF_ID, PacketByteBufs.create()));
        ServerTickEvents.START_SERVER_TICK.register(new TickHandler());
    }
}
