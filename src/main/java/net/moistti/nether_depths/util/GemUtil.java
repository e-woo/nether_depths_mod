package net.moistti.nether_depths.util;

import net.minecraft.enchantment.*;
import net.minecraft.registry.Registries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GemUtil {
    public static final Map<String, List<Enchantment>> GEM_ENCHANTS = new HashMap<>();
    private static final Set<EnchantmentTarget> acceptableTargetItems = Set.of(EnchantmentTarget.ARMOR, EnchantmentTarget.WEAPON, EnchantmentTarget.DIGGER);
    static {
        GEM_ENCHANTS.put("ruby", getEnchantments(DamageEnchantment.class));
        GEM_ENCHANTS.put("topaz", getEnchantments(LuckEnchantment.class));
        GEM_ENCHANTS.put("jade", getEnchantments(EfficiencyEnchantment.class));
        GEM_ENCHANTS.put("sapphire", getEnchantments(ProtectionEnchantment.class));
    }

    private static List<Enchantment> getEnchantments(Class<? extends Enchantment> type) {
        return Registries.ENCHANTMENT.stream().filter(
                enchantment -> type.isInstance(enchantment) && acceptableTargetItems.contains(enchantment.target))
                .collect(Collectors.toList());
    }
}
