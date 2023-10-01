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

public abstract class RegisterItems {
    public static void register() {
        addBlockItem("netherstone", RegisterBlocks.NETHERSTONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, RegisterBlocks.NETHERSTONE.asItem(), Items.POLISHED_BLACKSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.NATURAL, RegisterBlocks.NETHERSTONE.asItem(), Items.SMOOTH_BASALT);

        addBlockItem("netherstone_gold_ore", RegisterBlocks.NETHERSTONE_GOLD_ORE);
        addToItemGroup(ItemGroups.NATURAL, RegisterBlocks.NETHERSTONE_GOLD_ORE.asItem(), Items.NETHER_QUARTZ_ORE);

        addBlockItem("netherstone_quartz_ore", RegisterBlocks.NETHERSTONE_QUARTZ_ORE);
        addToItemGroup(ItemGroups.NATURAL, RegisterBlocks.NETHERSTONE_QUARTZ_ORE.asItem(), RegisterBlocks.NETHERSTONE_GOLD_ORE.asItem());

        addBlockItem("netherstone_bricks", RegisterBlocks.NETHERSTONE_BRICKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, RegisterBlocks.NETHERSTONE_BRICKS.asItem(), RegisterBlocks.NETHERSTONE.asItem());

        addBlockItem("netherstone_brick_stairs", RegisterBlocks.NETHERSTONE_BRICK_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, RegisterBlocks.NETHERSTONE_BRICK_STAIRS.asItem(), RegisterBlocks.NETHERSTONE_BRICKS.asItem());

        addBlockItem("netherstone_brick_slab", RegisterBlocks.NETHERSTONE_BRICK_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, RegisterBlocks.NETHERSTONE_BRICK_SLAB.asItem(), RegisterBlocks.NETHERSTONE_BRICK_STAIRS.asItem());

        addBlockItem("netherstone_brick_wall", RegisterBlocks.NETHERSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, RegisterBlocks.NETHERSTONE_BRICK_WALL.asItem(), RegisterBlocks.NETHERSTONE_BRICK_SLAB.asItem());
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
