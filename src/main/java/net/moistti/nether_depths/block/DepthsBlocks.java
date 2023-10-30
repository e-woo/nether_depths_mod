package net.moistti.nether_depths.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.moistti.nether_depths.NetherDepths;

public final class DepthsBlocks {
    private static final BlockSoundGroup NETHERSTONE_BRICK_SOUND = new BlockSoundGroup(1.25f, 0.5f, SoundEvents.BLOCK_DEEPSLATE_BRICKS_BREAK, SoundEvents.BLOCK_DEEPSLATE_BRICKS_STEP, SoundEvents.BLOCK_DEEPSLATE_BRICKS_PLACE, SoundEvents.BLOCK_DEEPSLATE_BRICKS_HIT, SoundEvents.BLOCK_DEEPSLATE_BRICKS_FALL);
    public static final Block NETHERSTONE = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(new BlockSoundGroup(1.25f, 0.75f, SoundEvents.BLOCK_STONE_BREAK, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL)));
    public static final Block NETHERSTONE_GOLD_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_GOLD_ORE), UniformIntProvider.create(0, 1));
    public static final Block NETHERSTONE_QUARTZ_ORE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(4.0f, 3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(2, 5));
    public static final Block MINERAL_CLUSTER = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(5.0f).requiresTool().mapColor(MapColor.BLACK).sounds(BlockSoundGroup.NETHER_ORE), UniformIntProvider.create(4, 6));
    public static final Block NETHERSTONE_BRICKS = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(NETHERSTONE_BRICK_SOUND));
    public static final Block NETHERSTONE_BRICK_STAIRS = new StairsBlock(NETHERSTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));
    public static final Block NETHERSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));
    public static final Block NETHERSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(NETHERSTONE_BRICKS));
    public static final Block CRACKED_NETHERSTONE_BRICKS = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(NETHERSTONE_BRICK_SOUND));
    public static final Block POLISHED_NETHERSTONE = new Block(FabricBlockSettings.create().strength(3.5f).requiresTool().mapColor(MapColor.BLACK).sounds(NETHERSTONE_BRICK_SOUND));
    public static final Block POLISHED_NETHERSTONE_STAIRS = new StairsBlock(POLISHED_NETHERSTONE.getDefaultState(), FabricBlockSettings.copyOf(POLISHED_NETHERSTONE));
    public static final Block POLISHED_NETHERSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(POLISHED_NETHERSTONE));
    public static final Block POLISHED_NETHERSTONE_WALL = new WallBlock(FabricBlockSettings.copyOf(POLISHED_NETHERSTONE));
    public static final Block CHISELED_POLISHED_NETHERSTONE = new Block(FabricBlockSettings.copyOf(POLISHED_NETHERSTONE));
    public static final Block ANCIENT_FORGE = new AncientForgeBlock(FabricBlockSettings.create().strength(-1.0f, 3600000.0f).luminance(Blocks.createLightLevelFromLitBlockState(15)).mapColor(MapColor.BLACK));
    public static final Block FORGE = new ForgeBlock(FabricBlockSettings.create().strength(70.0f, 1200.0f).luminance(Blocks.createLightLevelFromLitBlockState(15)).requiresTool().mapColor(MapColor.BLACK));
    public static final Block RADIATING_NETHERSTONE = new ExperienceDroppingBlock(FabricBlockSettings.create().strength(10.0f).requiresTool().mapColor(MapColor.BLACK).sounds(new BlockSoundGroup(1.25f, 0.5f, SoundEvents.BLOCK_GILDED_BLACKSTONE_BREAK, SoundEvents.BLOCK_GILDED_BLACKSTONE_STEP, SoundEvents.BLOCK_GILDED_BLACKSTONE_PLACE, SoundEvents.BLOCK_GILDED_BLACKSTONE_HIT, SoundEvents.BLOCK_GILDED_BLACKSTONE_FALL)).luminance(10), UniformIntProvider.create(10, 15));
    public static final Block RUBY_CRYSTAL = new CrystalBlock(FabricBlockSettings.create().strength(10.0f).requiresTool().mapColor(MapColor.RED).sounds(BlockSoundGroup.AMETHYST_CLUSTER).luminance(state -> 5).solid().nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY));
    public static final Block TOPAZ_CRYSTAL = new CrystalBlock(FabricBlockSettings.copyOf(RUBY_CRYSTAL).mapColor(MapColor.YELLOW));
    public static final Block JADE_CRYSTAL = new CrystalBlock(FabricBlockSettings.copyOf(RUBY_CRYSTAL).mapColor(MapColor.GREEN));
    public static final Block SAPPHIRE_CRYSTAL = new CrystalBlock(FabricBlockSettings.copyOf(RUBY_CRYSTAL).mapColor(MapColor.BLUE));
    public static final Block ENHANCED_BEACON = new EnhancedBeaconBlock(FabricBlockSettings.copyOf(Blocks.BEACON));

    public static void register() {
        addBlock("netherstone", NETHERSTONE);
        addBlock("netherstone_gold_ore", NETHERSTONE_GOLD_ORE);
        addBlock("netherstone_quartz_ore", NETHERSTONE_QUARTZ_ORE);
        addBlock("mineral_cluster", MINERAL_CLUSTER);
        addBlock("netherstone_bricks", NETHERSTONE_BRICKS);
        addBlock("netherstone_brick_stairs", NETHERSTONE_BRICK_STAIRS);
        addBlock("netherstone_brick_slab", NETHERSTONE_BRICK_SLAB);
        addBlock("netherstone_brick_wall", NETHERSTONE_BRICK_WALL);
        addBlock("ancient_forge", ANCIENT_FORGE);
        addBlock("forge", FORGE);
        addBlock("cracked_netherstone_bricks", CRACKED_NETHERSTONE_BRICKS);
        addBlock("polished_netherstone", POLISHED_NETHERSTONE);
        addBlock("polished_netherstone_stairs", POLISHED_NETHERSTONE_STAIRS);
        addBlock("polished_netherstone_slab", POLISHED_NETHERSTONE_SLAB);
        addBlock("polished_netherstone_wall", POLISHED_NETHERSTONE_WALL);
        addBlock("chiseled_polished_netherstone", CHISELED_POLISHED_NETHERSTONE);
        addBlock("radiating_netherstone", RADIATING_NETHERSTONE);
        addBlock("ruby_crystal", RUBY_CRYSTAL);
        addBlock("topaz_crystal", TOPAZ_CRYSTAL);
        addBlock("jade_crystal", JADE_CRYSTAL);
        addBlock("sapphire_crystal", SAPPHIRE_CRYSTAL);
        addBlock("enhanced_beacon", ENHANCED_BEACON);
    }

    private static void addBlock(String blockName, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(NetherDepths.MOD_ID, blockName), block);
    }
}
