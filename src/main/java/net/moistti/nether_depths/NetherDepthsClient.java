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
import net.moistti.nether_depths.entity.render.ExplosiveArrowEntityRenderer;
import net.moistti.nether_depths.entity.render.FireSpiritEntityRenderer;
import net.moistti.nether_depths.entity.render.LavaBoatRenderer;
import net.moistti.nether_depths.particle.DepthsParticles;
import net.moistti.nether_depths.network.Packets;

public class NetherDepthsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEntityRenderers();
        registerEntityModelLayers();
        registerBlockEntityRenderers();
        registerBlockRenderLayers();
        registerParticles();
        Packets.registerS2CPackets();
        HudRenderCallback.EVENT.register(new HeatHudOverlay());
    }

    public void registerEntityRenderers() {
        EntityRendererRegistry.register(DepthsEntities.LAVA_BOAT, (entityRenderDispatcher) -> new LavaBoatRenderer(entityRenderDispatcher, false));
        EntityRendererRegistry.register(DepthsEntities.FIRE_SPIRIT, FireSpiritEntityRenderer::new);
        EntityRendererRegistry.register(DepthsEntities.EXPLOSIVE_ARROW, ExplosiveArrowEntityRenderer::new);
    }

    public void registerEntityModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(DepthsEntityModelLayers.FIRE_SPIRIT, FireSpiritEntityModel::getTexturedModelData);
    }

    public void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(DepthsBlockEntityTypes.ENHANCED_BEACON, BeaconBlockEntityRenderer::new);
    }

    public void registerBlockRenderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.RUBY_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.TOPAZ_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.JADE_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.SAPPHIRE_CRYSTAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DepthsBlocks.ENHANCED_BEACON, RenderLayer.getCutout());
    }

    public void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(DepthsParticles.EMBER, WaterSuspendParticle.CrimsonSporeFactory::new);
    }
}
