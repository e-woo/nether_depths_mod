package net.moistti.nether_depths.entities;

import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public class IronBoatRenderer extends BoatEntityRenderer {

    public IronBoatRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx, chest);
    }

    @Override
    public Identifier getTexture(BoatEntity entity) {
        return new Identifier(NetherDepths.MOD_ID, "textures/entity/boat/iron_boat.png");
    }
}
