package net.moistti.nether_depths.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.block.DepthsBlocks;
import net.moistti.nether_depths.entity.DepthsEntities;

import java.util.ArrayList;
import java.util.List;

public final class DepthsItems {
    private static final List<Item> netherDepthsItems = new ArrayList<>();
    public static final Item HEART_OF_THE_NETHER = new Item(new FabricItemSettings().fireproof().rarity(Rarity.UNCOMMON));
    public static final Item NETHER_CORE = new Item(new FabricItemSettings().fireproof().rarity(Rarity.RARE));
    public static final Item FIRE_SHARD = new Item(new FabricItemSettings().fireproof());
    public static final Item RUBY = new GemItem(new FabricItemSettings(), GemItem.Type.RUBY);
    public static final Item TOPAZ = new GemItem(new FabricItemSettings(), GemItem.Type.TOPAZ);
    public static final Item JADE = new GemItem(new FabricItemSettings(), GemItem.Type.JADE);
    public static final Item SAPPHIRE = new GemItem(new FabricItemSettings(), GemItem.Type.SAPPHIRE);
    public static final Item LAVA_BOAT = new LavaBoatItem(false, BoatEntity.Type.OAK, new FabricItemSettings().fireproof().maxCount(1));
    public static final Item FIRE_SPIRIT_SPAWN_EGG = new SpawnEggItem(DepthsEntities.FIRE_SPIRIT, 0xeb9413, 0x2e1b00, new FabricItemSettings());
    public static final Item EXPLOSIVE_ARROW = new ExplosiveArrowItem(new FabricItemSettings());
    public static void register() {
        addBlockItem("netherstone", DepthsBlocks.NETHERSTONE);

        addBlockItem("polished_netherstone", DepthsBlocks.POLISHED_NETHERSTONE);

        addBlockItem("polished_netherstone_stairs", DepthsBlocks.POLISHED_NETHERSTONE_STAIRS);

        addBlockItem("polished_netherstone_slab", DepthsBlocks.POLISHED_NETHERSTONE_SLAB);

        addBlockItem("polished_netherstone_wall", DepthsBlocks.POLISHED_NETHERSTONE_WALL);

        addBlockItem("chiseled_polished_netherstone", DepthsBlocks.CHISELED_POLISHED_NETHERSTONE);

        addBlockItem("netherstone_bricks", DepthsBlocks.NETHERSTONE_BRICKS);

        addBlockItem("cracked_netherstone_bricks", DepthsBlocks.CRACKED_NETHERSTONE_BRICKS);

        addBlockItem("netherstone_brick_stairs", DepthsBlocks.NETHERSTONE_BRICK_STAIRS);

        addBlockItem("netherstone_brick_slab", DepthsBlocks.NETHERSTONE_BRICK_SLAB);

        addBlockItem("netherstone_brick_wall", DepthsBlocks.NETHERSTONE_BRICK_WALL);

        addBlockItem("netherstone_gold_ore", DepthsBlocks.NETHERSTONE_GOLD_ORE);

        addBlockItem("netherstone_quartz_ore", DepthsBlocks.NETHERSTONE_QUARTZ_ORE);

        addBlockItem("mineral_cluster", DepthsBlocks.MINERAL_CLUSTER);

        addItem("heart_of_the_nether", HEART_OF_THE_NETHER);
        addItem("nether_core", NETHER_CORE);
        FuelRegistry.INSTANCE.add(NETHER_CORE, 1);

        addItem("fire_shard", FIRE_SHARD);
        FuelRegistry.INSTANCE.add(FIRE_SHARD, 20000);

        addItem("ancient_forge", new BlockItem(DepthsBlocks.ANCIENT_FORGE, new FabricItemSettings().rarity(Rarity.RARE).fireproof()));

        addItem("forge", new BlockItem(DepthsBlocks.FORGE, new FabricItemSettings().rarity(Rarity.RARE).fireproof()));

        addItem("radiating_netherstone", new BlockItem(DepthsBlocks.RADIATING_NETHERSTONE, new FabricItemSettings()));
        addBlockItem("ruby_crystal", DepthsBlocks.RUBY_CRYSTAL);
        addBlockItem("topaz_crystal", DepthsBlocks.TOPAZ_CRYSTAL);
        addBlockItem("jade_crystal", DepthsBlocks.JADE_CRYSTAL);
        addBlockItem("sapphire_crystal", DepthsBlocks.SAPPHIRE_CRYSTAL);

        addItem("ruby", RUBY);
        addItem("jade", JADE);
        addItem("topaz", TOPAZ);
        addItem("sapphire", SAPPHIRE);

        addItem("lava_boat", LAVA_BOAT);

        addItem("enhanced_beacon", new BlockItem(DepthsBlocks.ENHANCED_BEACON, new FabricItemSettings().rarity(Rarity.EPIC)));

        addItem("fire_spirit_spawn_egg", FIRE_SPIRIT_SPAWN_EGG);

        addItem("explosive_arrow", EXPLOSIVE_ARROW);

        ItemGroup itemGroup = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DepthsBlocks.NETHERSTONE.asItem()))
            .displayName(Text.translatable("itemGroup.nether_depths.group"))
            .entries((context, entries) -> {
                for (Item i : netherDepthsItems)
                    entries.add(i);
            })
            .build();
        Registry.register(Registries.ITEM_GROUP, new Identifier(NetherDepths.MOD_ID, "item_group"), itemGroup);
    }

    private static void addItem(String itemName, Item item) {
        Registry.register(Registries.ITEM, new Identifier(NetherDepths.MOD_ID, itemName), item);
        netherDepthsItems.add(item);
    }

    private static void addBlockItem(String itemName, Block block) {
        addItem(itemName, new BlockItem(block, new FabricItemSettings()));
    }
}
