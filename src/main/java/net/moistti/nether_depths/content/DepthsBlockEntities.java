package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.blocks.AncientForgeBlockEntity;
import net.moistti.nether_depths.blocks.EnhancedBeaconBlockEntity;
import net.moistti.nether_depths.blocks.ForgeBlockEntity;

public final class DepthsBlockEntities {
    public static BlockEntityType<AncientForgeBlockEntity> ANCIENT_FORGE;
    public static BlockEntityType<EnhancedBeaconBlockEntity> ENHANCED_BEACON;
    public static BlockEntityType<ForgeBlockEntity> FORGE;
    public static void register() {
        ANCIENT_FORGE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(NetherDepths.MOD_ID, "ancient_forge"),
                FabricBlockEntityTypeBuilder.create(AncientForgeBlockEntity::new, DepthsBlocks.ANCIENT_FORGE).build());
        FORGE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(NetherDepths.MOD_ID, "forge"),
                FabricBlockEntityTypeBuilder.create(ForgeBlockEntity::new, DepthsBlocks.FORGE).build());
        ENHANCED_BEACON = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(NetherDepths.MOD_ID, "enhanced_beacon"),
                FabricBlockEntityTypeBuilder.create(EnhancedBeaconBlockEntity::new, DepthsBlocks.ENHANCED_BEACON).build());
    }
}
