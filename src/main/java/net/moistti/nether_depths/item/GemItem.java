package net.moistti.nether_depths.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.moistti.nether_depths.util.GemUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GemItem extends Item {
    public Type type;
    public GemItem(Settings settings, Type type) {
        super(settings);
        this.type = type;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        for (Enchantment enchantment : GemUtil.GEM_ENCHANTS.get(type.toString())) {
            tooltip.add(1, Text.translatable(enchantment.getTranslationKey()).append(Text.literal(" +")).formatted(type.getFormat()));
        }
    }

    public enum Type {
        RUBY("ruby", Formatting.RED, DamageEnchantment.class),
        SAPPHIRE("sapphire", Formatting.AQUA, ProtectionEnchantment.class),
        TOPAZ("topaz", Formatting.YELLOW, LuckEnchantment.class),
        JADE("jade", Formatting.GREEN, EfficiencyEnchantment.class);
        final String name;
        final Formatting format;
        final Class<? extends Enchantment> enchantmentType;
        Type(String name, Formatting format, Class<? extends Enchantment> enchantmentType) {
            this.name = name;
            this.format = format;
            this.enchantmentType = enchantmentType;
        }

        public String toString() {
            return this.name;
        }

        public Formatting getFormat() {
            return this.format;
        }

        public Class<? extends Enchantment> getEnchantmentType() {
            return this.enchantmentType;
        }
    }
}
