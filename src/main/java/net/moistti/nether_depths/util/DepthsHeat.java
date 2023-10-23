package net.moistti.nether_depths.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.moistti.nether_depths.enchantment.DepthsEnchantments;
import net.moistti.nether_depths.network.Packets;

import java.util.Set;

public class DepthsHeat {
    private static final Set<EntityType<? extends LivingEntity>> netherMobTypes = Set.of(EntityType.BLAZE, EntityType.ENDERMAN, EntityType.GHAST, EntityType.HOGLIN, EntityType.MAGMA_CUBE, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.SKELETON, EntityType.STRIDER, EntityType.WITHER_SKELETON, EntityType.ZOMBIFIED_PIGLIN, EntityType.ZOGLIN);
    public static int addHeat(DataSaver entity, int amount) {
        NbtCompound nbt = entity.getPersistentData();
        int heat = nbt.getInt("heat");
        heat = Math.min(20, heat + amount);

        nbt.putInt("heat", heat);
        if (entity instanceof PlayerEntity)
            syncHeat((ServerPlayerEntity) entity, heat);

        return heat;
    }

    public static int removeHeat(DataSaver entity, int amount) {
        NbtCompound nbt = entity.getPersistentData();
        int heat = nbt.getInt("heat");
        heat = Math.max(0, heat - amount);

        nbt.putInt("heat", heat);
        if (entity instanceof PlayerEntity)
            syncHeat((ServerPlayerEntity) entity, heat);
        return heat;
    }

    public static void syncHeat(ServerPlayerEntity player, int heat) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(heat);
        ServerPlayNetworking.send(player, Packets.HEAT_SYNC_ID, buffer);
    }

    public static boolean isImmune(LivingEntity entity) {
        return entity.isFireImmune() ||
                EnchantmentHelper.getEquipmentLevel(DepthsEnchantments.FLAME_GUARD, entity) >= 1 ||
                netherMobTypes.contains(entity.getType()) ||
                (entity instanceof ServerPlayerEntity && (((ServerPlayerEntity) entity).isCreative() || entity.isSpectator()));
    }
}
