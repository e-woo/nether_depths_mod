package net.moistti.nether_depths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.moistti.nether_depths.entities.IronBoatRenderer;
import net.moistti.nether_depths.content.RegisterEntities;

public class NetherDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RegisterEntities.IRON_BOAT, (entityRenderDispatcher) -> new IronBoatRenderer(entityRenderDispatcher, false));
    }


}
