package net.moistti.nether_depths.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.entity.DepthsEntityModelLayers;
import net.moistti.nether_depths.entity.FireSpiritEntity;
import net.moistti.nether_depths.entity.model.FireSpiritEntityModel;

public class FireSpiritEntityRenderer extends MobEntityRenderer<FireSpiritEntity, FireSpiritEntityModel<FireSpiritEntity>> {
    private static final Identifier texture = new Identifier(NetherDepths.MOD_ID, "textures/entity/fire_spirit.png");
    public FireSpiritEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FireSpiritEntityModel<>(context.getPart(DepthsEntityModelLayers.FIRE_SPIRIT)), 0.5f);
    }

    @Override
    public Identifier getTexture(FireSpiritEntity entity) {
        return texture;
    }
}
