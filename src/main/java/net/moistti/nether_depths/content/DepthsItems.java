package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public abstract class DepthsItems {
    public static void register() {
        addBlockItem("netherstone", DepthsBlocks.NETHERSTONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE.asItem(), Items.POLISHED_BLACKSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE.asItem(), Items.SMOOTH_BASALT);

        addBlockItem("netherstone_gold_ore", DepthsBlocks.NETHERSTONE_GOLD_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem(), Items.NETHER_QUARTZ_ORE);

        addBlockItem("netherstone_quartz_ore", DepthsBlocks.NETHERSTONE_QUARTZ_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem(), DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem());

        addBlockItem("mineral_cluster", DepthsBlocks.MINERAL_CLUSTER);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.MINERAL_CLUSTER.asItem(), DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem());

        addBlockItem("netherstone_bricks", DepthsBlocks.NETHERSTONE_BRICKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICKS.asItem(), DepthsBlocks.NETHERSTONE.asItem());

        addBlockItem("netherstone_brick_stairs", DepthsBlocks.NETHERSTONE_BRICK_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem(), DepthsBlocks.NETHERSTONE_BRICKS.asItem());

        addBlockItem("netherstone_brick_slab", DepthsBlocks.NETHERSTONE_BRICK_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem(), DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem());

        addBlockItem("netherstone_brick_wall", DepthsBlocks.NETHERSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_WALL.asItem(), DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem());
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