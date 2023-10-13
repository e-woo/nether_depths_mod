package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.items.GemItem;
import net.moistti.nether_depths.items.LavaBoatItem;

public abstract class DepthsItems {
    public static final Item NETHER_CORE = new Item(new FabricItemSettings());
    public static final Item FIRE_SHARD = new Item(new FabricItemSettings().fireproof());
    public static final Item RUBY = new GemItem(new FabricItemSettings(), GemItem.Type.RUBY);
    public static final Item TOPAZ = new GemItem(new FabricItemSettings(), GemItem.Type.TOPAZ);
    public static final Item JADE = new GemItem(new FabricItemSettings(), GemItem.Type.JADE);
    public static final Item SAPPHIRE = new GemItem(new FabricItemSettings(), GemItem.Type.SAPPHIRE);
    public static final Item LAVA_BOAT = new LavaBoatItem(false, BoatEntity.Type.OAK, new FabricItemSettings().fireproof().maxCount(1));
    public static void register() {
        addBlockItem("netherstone", DepthsBlocks.NETHERSTONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE.asItem(), Items.POLISHED_BLACKSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE.asItem(), Items.SMOOTH_BASALT);

        addBlockItem("polished_netherstone", DepthsBlocks.POLISHED_NETHERSTONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.POLISHED_NETHERSTONE.asItem(), DepthsBlocks.NETHERSTONE.asItem());

        addBlockItem("polished_netherstone_stairs", DepthsBlocks.POLISHED_NETHERSTONE_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.POLISHED_NETHERSTONE_STAIRS.asItem(), DepthsBlocks.POLISHED_NETHERSTONE.asItem());

        addBlockItem("polished_netherstone_slab", DepthsBlocks.POLISHED_NETHERSTONE_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.POLISHED_NETHERSTONE_SLAB.asItem(), DepthsBlocks.POLISHED_NETHERSTONE_STAIRS.asItem());

        addBlockItem("polished_netherstone_wall", DepthsBlocks.POLISHED_NETHERSTONE_WALL);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.POLISHED_NETHERSTONE_WALL.asItem(), DepthsBlocks.POLISHED_NETHERSTONE_SLAB.asItem());

        addBlockItem("chiseled_polished_netherstone", DepthsBlocks.CHISELED_POLISHED_NETHERSTONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.CHISELED_POLISHED_NETHERSTONE.asItem(), DepthsBlocks.POLISHED_NETHERSTONE_WALL.asItem());

        addBlockItem("netherstone_bricks", DepthsBlocks.NETHERSTONE_BRICKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICKS.asItem(), DepthsBlocks.CHISELED_POLISHED_NETHERSTONE.asItem());

        addBlockItem("netherstone_brick_stairs", DepthsBlocks.NETHERSTONE_BRICK_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem(), DepthsBlocks.NETHERSTONE_BRICKS.asItem());

        addBlockItem("netherstone_brick_slab", DepthsBlocks.NETHERSTONE_BRICK_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem(), DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem());

        addBlockItem("netherstone_brick_wall", DepthsBlocks.NETHERSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_WALL.asItem(), DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem());

        addBlockItem("cracked_netherstone_bricks", DepthsBlocks.CRACKED_NETHERSTONE_BRICKS);

        addBlockItem("netherstone_gold_ore", DepthsBlocks.NETHERSTONE_GOLD_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem(), Items.NETHER_QUARTZ_ORE);

        addBlockItem("netherstone_quartz_ore", DepthsBlocks.NETHERSTONE_QUARTZ_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem(), DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem());

        addBlockItem("mineral_cluster", DepthsBlocks.MINERAL_CLUSTER);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.MINERAL_CLUSTER.asItem(), DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem());

        addItem("nether_core", NETHER_CORE);

        addItem("fire_shard", FIRE_SHARD);
        addToItemGroup(ItemGroups.INGREDIENTS, FIRE_SHARD, Items.AMETHYST_SHARD);

        addBlockItem("ancient_forge", DepthsBlocks.ANCIENT_FORGE);
        addToItemGroup(ItemGroups.FUNCTIONAL, DepthsBlocks.ANCIENT_FORGE.asItem(), Items.BLAST_FURNACE);

        addItem("fire_crystal", new BlockItem(DepthsBlocks.FIRE_CRYSTAL, new FabricItemSettings().fireproof()));
        addBlockItem("ruby_crystal", DepthsBlocks.RUBY_CRYSTAL);
        addBlockItem("topaz_crystal", DepthsBlocks.TOPAZ_CRYSTAL);
        addBlockItem("jade_crystal", DepthsBlocks.JADE_CRYSTAL);
        addBlockItem("sapphire_crystal", DepthsBlocks.SAPPHIRE_CRYSTAL);

        addItem("ruby", RUBY);
        addToItemGroup(ItemGroups.INGREDIENTS, RUBY, Items.NETHERITE_INGOT);
        addItem("jade", JADE);
        addToItemGroup(ItemGroups.INGREDIENTS, JADE, RUBY);
        addItem("topaz", TOPAZ);
        addToItemGroup(ItemGroups.INGREDIENTS, TOPAZ, JADE);
        addItem("sapphire", SAPPHIRE);
        addToItemGroup(ItemGroups.INGREDIENTS, SAPPHIRE, TOPAZ);

        addItem("lava_boat", LAVA_BOAT);
        addToItemGroup(ItemGroups.TOOLS, LAVA_BOAT, Items.BAMBOO_CHEST_RAFT);

        addBlockItem("enhanced_beacon", DepthsBlocks.ENHANCED_BEACON);
    }

    private static void addItem(String itemName, Item item) {
        Registry.register(Registries.ITEM, new Identifier(NetherDepths.MOD_ID, itemName), item);
    }

    private static void addBlockItem(String itemName, Block block) {
        addItem(itemName, new BlockItem(block, new FabricItemSettings()));
    }

    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item, Item addAfter) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.addAfter(addAfter, item));
    }
}
