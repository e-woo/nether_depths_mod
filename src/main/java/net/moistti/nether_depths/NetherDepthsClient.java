package net.moistti.nether_depths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.moistti.nether_depths.client.HeatHudOverlay;
import net.moistti.nether_depths.entities.LavaBoatRenderer;
import net.moistti.nether_depths.content.DepthsEntities;
import net.moistti.nether_depths.network.Packets;

public class NetherDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(DepthsEntities.LAVA_BOAT, (entityRenderDispatcher) -> new LavaBoatRenderer(entityRenderDispatcher, false));
        Packets.registerS2CPackets();
        HudRenderCallback.EVENT.register(new HeatHudOverlay());

    }


}
