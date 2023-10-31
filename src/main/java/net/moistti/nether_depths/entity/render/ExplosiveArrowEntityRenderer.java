package net.moistti.nether_depths.entity.render;

import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public class ExplosiveArrowEntityRenderer extends ArrowEntityRenderer {
    public static final Identifier TEXTURE = new Identifier(NetherDepths.MOD_ID, "textures/entity/projectiles/explosive_arrow.png");
    public ExplosiveArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return TEXTURE;
    }
}
