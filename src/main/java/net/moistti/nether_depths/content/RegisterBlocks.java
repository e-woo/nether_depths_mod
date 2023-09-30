package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.moistti.nether_depths.NetherDepths;

public final class RegisterBlocks {
    // need to create map colors and stuff!!
    public static final Block NETHERSTONE = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK));
    public static final Block NETHERSTONE_GOLD_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_GOLD_ORE), UniformIntProvider.create(0, 1));
    public static final Block NETHERSTONE_QUARTZ_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(2, 5));
    public static final Block NETHERSTONE_BRICKS = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK));
    public static void register() {
        addBlock("netherstone", NETHERSTONE);
        addBlock("netherstone_gold_ore", NETHERSTONE_GOLD_ORE);
        addBlock("netherstone_quartz_ore", NETHERSTONE_QUARTZ_ORE);
        addBlock("netherstone_bricks", NETHERSTONE_BRICKS);
    }

    private static void addBlock(String blockName, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(NetherDepths.MOD_ID, blockName), block);
    }
}
