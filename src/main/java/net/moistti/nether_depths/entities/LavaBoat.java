package net.moistti.nether_depths.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;

public class LavaBoat extends BoatEntity {


    public LavaBoat(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Type getVariant() {
        return Type.OAK;
    }
}
