package net.moistti.nether_depths.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.content.DepthsEnchantments;
import net.moistti.nether_depths.util.DataSaver;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (LivingEntity p : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !p.isFireImmune() && EnchantmentHelper.getEquipmentLevel(DepthsEnchantments.FLAME_GUARD, p) <= 0)) {
                if (((DataSaver) p).getPersistentData().getInt("heat") >= 20)
                    p.damage(world.getDamageSources().hotFloor(), 1.0f);
                if (((DataSaver) (Object) (p.getEquippedStack(EquipmentSlot.HEAD))).getPersistentData().getBoolean("fireproof")) {
                    p.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20, 1, false, false, true));
                }
            }
        }
    }
}
