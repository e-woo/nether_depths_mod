package net.moistti.nether_depths.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class IronBoat extends BoatEntity {


    public IronBoat(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
        
    }

//    @Override
//    public void tick() {
//        super.tick();
//        updateLavaVelocity();
//        for (Entity i : this.getPassengerList()) {
//            i.setFireTicks(0);
//        }
//    }

    private void updateLavaVelocity() {
        if (this.isInLava()) {
            Vec3d vec3d2 = this.getVelocity();
            this.setVelocity(vec3d2.x, (vec3d2.y + (this.getBodyY(1.0) - this.getY()) / (double)this.getHeight() * 0.06153846016296973) * 0.75, vec3d2.z);
        }
    }

}
