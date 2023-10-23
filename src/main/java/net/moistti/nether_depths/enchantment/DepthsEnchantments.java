package net.moistti.nether_depths.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public final class DepthsEnchantments {
    public static final Enchantment FLAME_GUARD = new FlameGuardEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET});
    public static final Enchantment HEATED_TOUCH = new HeatedTouchEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static void register() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(NetherDepths.MOD_ID, "flame_guard"), FLAME_GUARD);
        Registry.register(Registries.ENCHANTMENT, new Identifier(NetherDepths.MOD_ID, "heated_touch"), HEATED_TOUCH);
    }
}
