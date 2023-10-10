package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.blocks.AncientForgeBlockEntity;

public class DepthsBlockEntities {
    public static BlockEntityType<AncientForgeBlockEntity> ANCIENT_FORGE;
    public static void register() {
        ANCIENT_FORGE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(NetherDepths.MOD_ID, "ancient_forge"),
                FabricBlockEntityTypeBuilder.create(AncientForgeBlockEntity::new, DepthsBlocks.ANCIENT_FORGE).build());
    }
}
