package net.moistti.nether_depths.util;

import net.minecraft.enchantment.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GemUtil {
    public static Map<String, List<Class<? extends Enchantment>>> GEM_ENCHANTS = new HashMap<>();
    static {
        GEM_ENCHANTS.put("ruby", List.of(DamageEnchantment.class));
        GEM_ENCHANTS.put("topaz", List.of(LuckEnchantment.class));
        GEM_ENCHANTS.put("jade", List.of(EfficiencyEnchantment.class));
        GEM_ENCHANTS.put("sapphire", List.of(ProtectionEnchantment.class));
    }
}
