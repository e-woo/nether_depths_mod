package net.moistti.nether_depths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.moistti.nether_depths.entities.IronBoatRenderer;

public class NetherDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(NetherDepths.IRON_BOAT, (entityRenderDispatcher) -> new IronBoatRenderer(entityRenderDispatcher, false));
    }


}
