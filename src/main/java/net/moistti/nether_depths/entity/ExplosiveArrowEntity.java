package net.moistti.nether_depths.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.moistti.nether_depths.item.DepthsItems;

public class ExplosiveArrowEntity extends ArrowEntity {

    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(World world, double x, double y, double z) {
        this(DepthsEntities.EXPLOSIVE_ARROW, world);
        this.setPosition(x, y, z);
    }

    public ExplosiveArrowEntity(World world, LivingEntity owner) {
        this(DepthsEntities.EXPLOSIVE_ARROW, world);
        this.setOwner(owner);
        if (owner instanceof PlayerEntity)
            this.pickupType = PickupPermission.ALLOWED;
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    public void tick() {
        super.tick();
        this.setCritical(false);
        if (this.inGround)
            explode(getX(), getY(), getZ());
        if (this.getWorld().isClient)
            for (int i = 0; i < 3; i++)
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        explode(target.getX(), target.getY(), target.getZ());
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(DepthsItems.EXPLOSIVE_ARROW);
    }

    private void explode(double x, double y, double z) {
        if (!getWorld().isClient) {
            getWorld().createExplosion(this, x, y, z, 2.0f, World.ExplosionSourceType.MOB);
            discard();
        }
    }
}
