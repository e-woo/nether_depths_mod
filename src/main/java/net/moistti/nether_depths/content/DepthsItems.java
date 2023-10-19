package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.items.GemItem;
import net.moistti.nether_depths.items.LavaBoatItem;

public final class DepthsItems {
    public static final Item HEART_OF_THE_NETHER = new Item(new FabricItemSettings().fireproof().rarity(Rarity.UNCOMMON));
    public static final Item NETHER_CORE = new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE));
    public static final Item FIRE_SHARD = new Item(new FabricItemSettings().fireproof());
    public static final Item RUBY = new GemItem(new FabricItemSettings(), GemItem.Type.RUBY);
    public static final Item TOPAZ = new GemItem(new FabricItemSettings(), GemItem.Type.TOPAZ);
    public static final Item JADE = new GemItem(new FabricItemSettings(), GemItem.Type.JADE);
    public static final Item SAPPHIRE = new GemItem(new FabricItemSettings(), GemItem.Type.SAPPHIRE);
    public static final Item LAVA_BOAT = new LavaBoatItem(false, BoatEntity.Type.OAK, new FabricItemSettings().fireproof().maxCount(1));
    public static final Item PIGLIN_ELITE_SPAWN_EGG = new SpawnEggItem(DepthsEntities.PIGLIN_ELITE, 0x5c463a, 0xd69347, new FabricItemSettings());
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

        addBlockItem("cracked_netherstone_bricks", DepthsBlocks.CRACKED_NETHERSTONE_BRICKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.CRACKED_NETHERSTONE_BRICKS.asItem(),DepthsBlocks.NETHERSTONE_BRICKS.asItem());

        addBlockItem("netherstone_brick_stairs", DepthsBlocks.NETHERSTONE_BRICK_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem(), DepthsBlocks.CRACKED_NETHERSTONE_BRICKS.asItem());

        addBlockItem("netherstone_brick_slab", DepthsBlocks.NETHERSTONE_BRICK_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem(), DepthsBlocks.NETHERSTONE_BRICK_STAIRS.asItem());

        addBlockItem("netherstone_brick_wall", DepthsBlocks.NETHERSTONE_BRICK_WALL);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, DepthsBlocks.NETHERSTONE_BRICK_WALL.asItem(), DepthsBlocks.NETHERSTONE_BRICK_SLAB.asItem());

        addBlockItem("netherstone_gold_ore", DepthsBlocks.NETHERSTONE_GOLD_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem(), Items.NETHER_QUARTZ_ORE);

        addBlockItem("netherstone_quartz_ore", DepthsBlocks.NETHERSTONE_QUARTZ_ORE);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem(), DepthsBlocks.NETHERSTONE_GOLD_ORE.asItem());

        addBlockItem("mineral_cluster", DepthsBlocks.MINERAL_CLUSTER);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.MINERAL_CLUSTER.asItem(), DepthsBlocks.NETHERSTONE_QUARTZ_ORE.asItem());

        addItem("heart_of_the_nether", HEART_OF_THE_NETHER);
        addToItemGroup(ItemGroups.INGREDIENTS, HEART_OF_THE_NETHER, Items.HEART_OF_THE_SEA);
        addItem("nether_core", NETHER_CORE);
        addToItemGroup(ItemGroups.INGREDIENTS, NETHER_CORE, HEART_OF_THE_NETHER);
        FuelRegistry.INSTANCE.add(NETHER_CORE, 200);

        addItem("fire_shard", FIRE_SHARD);
        addToItemGroup(ItemGroups.INGREDIENTS, FIRE_SHARD, Items.AMETHYST_SHARD);

        addItem("ancient_forge", new BlockItem(DepthsBlocks.ANCIENT_FORGE, new FabricItemSettings().rarity(Rarity.RARE).fireproof()));
        addToItemGroup(ItemGroups.FUNCTIONAL, DepthsBlocks.ANCIENT_FORGE.asItem(), Items.BLAST_FURNACE);

        addItem("forge", new BlockItem(DepthsBlocks.FORGE, new FabricItemSettings().rarity(Rarity.RARE).fireproof()));
        addToItemGroup(ItemGroups.FUNCTIONAL, DepthsBlocks.FORGE.asItem(), DepthsBlocks.ANCIENT_FORGE.asItem());

        addItem("fire_crystal", new BlockItem(DepthsBlocks.FIRE_CRYSTAL, new FabricItemSettings().fireproof()));
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.FIRE_CRYSTAL.asItem(), Items.AMETHYST_CLUSTER);
        addBlockItem("ruby_crystal", DepthsBlocks.RUBY_CRYSTAL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.RUBY_CRYSTAL.asItem(), DepthsBlocks.FIRE_CRYSTAL.asItem());
        addBlockItem("topaz_crystal", DepthsBlocks.TOPAZ_CRYSTAL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.TOPAZ_CRYSTAL.asItem(), DepthsBlocks.RUBY_CRYSTAL.asItem());
        addBlockItem("jade_crystal", DepthsBlocks.JADE_CRYSTAL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.JADE_CRYSTAL.asItem(), DepthsBlocks.TOPAZ_CRYSTAL.asItem());
        addBlockItem("sapphire_crystal", DepthsBlocks.SAPPHIRE_CRYSTAL);
        addToItemGroup(ItemGroups.NATURAL, DepthsBlocks.SAPPHIRE_CRYSTAL.asItem(), DepthsBlocks.JADE_CRYSTAL.asItem());

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

        addItem("enhanced_beacon", new BlockItem(DepthsBlocks.ENHANCED_BEACON, new FabricItemSettings().rarity(Rarity.EPIC)));
        addToItemGroup(ItemGroups.FUNCTIONAL, DepthsBlocks.ENHANCED_BEACON.asItem(), Items.BEACON);

        addItem("piglin_elite_spawn_egg", PIGLIN_ELITE_SPAWN_EGG);
        addToItemGroup(ItemGroups.SPAWN_EGGS, PIGLIN_ELITE_SPAWN_EGG, Items.PIGLIN_BRUTE_SPAWN_EGG);
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
