package net.moistti.nether_depths.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.moistti.nether_depths.NetherDepths;
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
        tooltip.add(1, Text.translatable("gem_description." + NetherDepths.MOD_ID + "." + type.getPath())
                .formatted(switch (type) {
                    case RUBY -> Formatting.RED;
                    case SAPPHIRE -> Formatting.AQUA;
                    case TOPAZ -> Formatting.YELLOW;
                    case JADE -> Formatting.GREEN;
                }));
    }

    public enum Type {
        RUBY("ruby"),
        SAPPHIRE("sapphire"),
        TOPAZ("topaz"),
        JADE("jade");
        final String path;
        Type(String path) {
            this.path = path;
        }

        private String getPath() {
            return this.path;
        }
    }
}
