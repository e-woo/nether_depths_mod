package net.moistti.nether_depths.entity.render;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

import java.util.Map;

public class LavaBoatRenderer extends BoatEntityRenderer {
    private final Identifier texture = new Identifier(NetherDepths.MOD_ID, "textures/entity/boat/lava_boat.png");
    public LavaBoatRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx, chest);
        this.texturesAndModels = Map.of(BoatEntity.Type.OAK, Pair.of(texture, new BoatEntityModel(ctx.getPart(EntityModelLayers.createBoat(BoatEntity.Type.OAK)))));
    }

    @Override
    public Identifier getTexture(BoatEntity entity) {
        return texture;
    }
}
