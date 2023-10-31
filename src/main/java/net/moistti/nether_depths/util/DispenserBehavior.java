package net.moistti.nether_depths.util;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.moistti.nether_depths.entity.ExplosiveArrowEntity;
import net.moistti.nether_depths.item.DepthsItems;

public class DispenserBehavior {
    public static void registerDispenserBehavior() {
        DispenserBlock.registerBehavior(DepthsItems.EXPLOSIVE_ARROW, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                ExplosiveArrowEntity explosiveArrowEntity = new ExplosiveArrowEntity(world, position.getX(), position.getY(), position.getZ());
                explosiveArrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                return explosiveArrowEntity;
            }
        });
    }
}
