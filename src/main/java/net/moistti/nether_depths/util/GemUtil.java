package net.moistti.nether_depths.util;

import net.minecraft.enchantment.*;
import net.minecraft.registry.Registries;
import net.moistti.nether_depths.item.GemItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GemUtil {
    public static final Map<String, List<Enchantment>> GEM_ENCHANTS = new HashMap<>();
    private static final Set<EnchantmentTarget> acceptableTargetItems = Set.of(EnchantmentTarget.ARMOR, EnchantmentTarget.WEAPON, EnchantmentTarget.DIGGER);
    static {
        for (GemItem.Type type : GemItem.Type.values())
            GEM_ENCHANTS.put(type.toString(), getEnchantments(type.getEnchantmentType()));
    }

    private static List<Enchantment> getEnchantments(Class<? extends Enchantment> type) {
        return Registries.ENCHANTMENT.stream().filter(
                enchantment -> type.isInstance(enchantment) && acceptableTargetItems.contains(enchantment.target))
                .collect(Collectors.toList());
    }
}
