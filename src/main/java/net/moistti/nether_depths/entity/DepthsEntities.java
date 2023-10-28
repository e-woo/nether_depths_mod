package net.moistti.nether_depths.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.entity.vehicle.LavaBoat;

public final class DepthsEntities {
    public static final EntityType<LavaBoat> LAVA_BOAT = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(NetherDepths.MOD_ID, "lava_boat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<LavaBoat>) LavaBoat::new).
            dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).trackRangeChunks(10).fireImmune().build());

    public static final EntityType<FireSpiritEntity> FIRE_SPIRIT = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(NetherDepths.MOD_ID, "fire_spirit"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FireSpiritEntity::new).
            dimensions(EntityDimensions.fixed(1.1f, 0.7f)).trackRangeChunks(8).fireImmune().build());

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(FIRE_SPIRIT, FireSpiritEntity.createFireSpiritAttributes());
    }
}
