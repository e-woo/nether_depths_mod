package net.moistti.nether_depths.content;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.entities.LavaBoat;

public final class DepthsEntities {
    public static final EntityType<LavaBoat> LAVA_BOAT = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(NetherDepths.MOD_ID, "lava_boat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<LavaBoat>) LavaBoat::new).
            dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).trackRangeChunks(10).fireImmune().build());
    public static void register() {

    }
}
