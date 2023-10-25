package net.moistti.nether_depths.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.moistti.nether_depths.util.GemUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GemItem extends Item {
    Type type;
    public GemItem(Settings settings, Type type) {
        super(settings);
        this.type = type;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        tooltip.add(1, Text.translatable("gem_description." + NetherDepths.MOD_ID + "." + type.getPath())
//                .formatted(switch (type) {
//                    case RUBY -> Formatting.RED;
//                    case SAPPHIRE -> Formatting.AQUA;
//                    case TOPAZ -> Formatting.YELLOW;
//                    case JADE -> Formatting.GREEN;
//                }));
        for (Enchantment enchantment : GemUtil.GEM_ENCHANTS.get(type.getPath())) {
            tooltip.add(1, Text.translatable(enchantment.getTranslationKey()).append(Text.literal(" +")).formatted(type.getFormat()));
        }
    }

    public enum Type {
        RUBY("ruby", Formatting.RED),
        SAPPHIRE("sapphire", Formatting.AQUA),
        TOPAZ("topaz", Formatting.YELLOW),
        JADE("jade", Formatting.GREEN);
        final String path;
        final Formatting format;
        Type(String path, Formatting format) {
            this.path = path;
            this.format = format;
        }

        private String getPath() {
            return this.path;
        }

        private Formatting getFormat() {
            return this.format;
        }
    }
}
