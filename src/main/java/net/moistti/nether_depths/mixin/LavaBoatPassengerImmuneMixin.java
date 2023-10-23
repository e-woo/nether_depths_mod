package net.moistti.nether_depths.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.moistti.nether_depths.entity.vehicle.LavaBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/**
 * Entities riding the lava boat will normally burn due to their hitbox being in the lava. To prevent this, this mixin
 * will grant them immunity from these cases, as long as the boat is not submerged.*/
@Mixin(Entity.class)
public abstract class LavaBoatPassengerImmuneMixin {
    @Unique
    private final Entity self = (Entity) (Object) this;
    @Unique
    private final static Set<BoatEntity.Location> lavaSpots = Set.of(BoatEntity.Location.UNDER_WATER, BoatEntity.Location.UNDER_FLOWING_WATER);
    @Inject(method = "setOnFireFromLava()V", at = @At("HEAD"), cancellable = true)
    private void injectLavaImmune(CallbackInfo ci) {
        Entity vehicle = self.getVehicle();
        if (vehicle instanceof LavaBoat && ((LavaBoat) vehicle).location != null && !lavaSpots.contains(((LavaBoat) vehicle).location))
            ci.cancel();
    }
}
