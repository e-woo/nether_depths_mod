package net.moistti.nether_depths.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public class PiglinEliteEntityRenderer extends PiglinEntityRenderer {
    public PiglinEliteEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer mainLayer, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer, boolean zombie) {
        super(ctx, mainLayer, innerArmorLayer, outerArmorLayer, zombie);
    }

    @Override
    public Identifier getTexture(MobEntity mobEntity) {
        return new Identifier(NetherDepths.MOD_ID, "textures/entity/piglin/piglin_elite.png");
    }
}
