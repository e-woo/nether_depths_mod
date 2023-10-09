package net.moistti.nether_depths.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsEntities;
import net.moistti.nether_depths.content.DepthsItems;

public class LavaBoat extends BoatEntity {

    public LavaBoat(EntityType<? extends LavaBoat> entityType, World world) {
        super(entityType, world);
    }

    public LavaBoat(World world, double x, double y, double z) {
        this(DepthsEntities.LAVA_BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public Item asItem() {
        return DepthsItems.LAVA_BOAT;
    }

    @Override
    public Type getVariant() {
        return Type.OAK;
    }
}
