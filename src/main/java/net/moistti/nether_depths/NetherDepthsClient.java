package net.moistti.nether_depths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.particle.WaterSuspendParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.moistti.nether_depths.client.HeatHudOverlay;
import net.moistti.nether_depths.block.entity.DepthsBlockEntityTypes;
import net.moistti.nether_depths.block.DepthsBlocks;
import net.moistti.nether_depths.entity.*;
import net.moistti.nether_depths.entity.model.FireSpiritEntityModel;
import net.moistti.nether_depths.entity.render.FireSpiritEntityRenderer;
import net.moistti.nether_depths.entity.render.LavaBoatRenderer;
import net.moistti.nether_depths.particle.DepthsParticles;
import net.moistti.nether_depths.network.Packets;

public class NetherDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(DepthsEntities.LAVA_BOAT, (entityRenderDispatcher) -> new LavaBoatRenderer(entityRenderDispatcher, false));
        EntityRendererRegistry.register(DepthsEntities.FIRE_SPIRIT, FireSpiritEntityRenderer::new);
        Packets.registerS2CPackets();
        HudRenderCallback.EVENT.register(new HeatHudOverlay());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.RUBY_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.TOPAZ_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.JADE_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.SAPPHIRE_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.ENHANCED_BEACON, RenderLayer.getCutout());
        BlockEntityRendererFactories.register(DepthsBlockEntityTypes.ENHANCED_BEACON, BeaconBlockEntityRenderer::new);
        ParticleFactoryRegistry.getInstance().register(DepthsParticles.EMBER, WaterSuspendParticle.CrimsonSporeFactory::new);
        EntityModelLayerRegistry.registerModelLayer(DepthsEntityModelLayers.FIRE_SPIRIT, FireSpiritEntityModel::getTexturedModelData);
    }


}
