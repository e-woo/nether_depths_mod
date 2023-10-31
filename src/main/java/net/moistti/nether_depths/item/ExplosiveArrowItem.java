package net.moistti.nether_depths.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.moistti.nether_depths.entity.ExplosiveArrowEntity;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ExplosiveArrowEntity explosiveArrowEntity = new ExplosiveArrowEntity(world, shooter);
        explosiveArrowEntity.initFromStack(stack);
        return explosiveArrowEntity;
    }
}
