package net.moistti.nether_depths.item;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.moistti.nether_depths.entity.vehicle.LavaBoat;

public class LavaBoatItem extends BoatItem {
    public LavaBoatItem(boolean chest, BoatEntity.Type type, Settings settings) {
        super(chest, type, settings);
    }

    @Override
    public BoatEntity createEntity(World world, HitResult hitResult) {
        return new LavaBoat(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
    }
}
