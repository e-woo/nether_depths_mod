package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.moistti.nether_depths.NetherDepths;

public final class RegisterBlocks {
    private static final BlockSoundGroup NETHERSTONE_BRICK_SOUND = new BlockSoundGroup(1.25f, 0.5f, SoundEvents.BLOCK_DEEPSLATE_BRICKS_BREAK, SoundEvents.BLOCK_DEEPSLATE_BRICKS_STEP, SoundEvents.BLOCK_DEEPSLATE_BRICKS_PLACE, SoundEvents.BLOCK_DEEPSLATE_BRICKS_HIT, SoundEvents.BLOCK_DEEPSLATE_BRICKS_FALL);
    public static final Block NETHERSTONE = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(new BlockSoundGroup(1.25f, 0.75f, SoundEvents.BLOCK_STONE_BREAK, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL)));
    public static final Block NETHERSTONE_GOLD_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_GOLD_ORE), UniformIntProvider.create(0, 1));
    public static final Block NETHERSTONE_QUARTZ_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(2, 5));
    public static final Block NETHERSTONE_BRICKS = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(NETHERSTONE_BRICK_SOUND));
    public static final Block NETHERSTONE_BRICK_STAIRS = new StairsBlock(NETHERSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));
    public static final Block NETHERSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));
    public static final Block NETHERSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));

    public static void register() {
        addBlock("netherstone", NETHERSTONE);
        addBlock("netherstone_gold_ore", NETHERSTONE_GOLD_ORE);
        addBlock("netherstone_quartz_ore", NETHERSTONE_QUARTZ_ORE);
        addBlock("netherstone_bricks", NETHERSTONE_BRICKS);
        addBlock("netherstone_brick_stairs", NETHERSTONE_BRICK_STAIRS);
        addBlock("netherstone_brick_slab", NETHERSTONE_BRICK_SLAB);
        addBlock("netherstone_brick_wall", NETHERSTONE_BRICK_WALL);
    }

    private static void addBlock(String blockName, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(NetherDepths.MOD_ID, blockName), block);
    }
}
