package net.moistti.nether_depths.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.moistti.nether_depths.network.Packets;

public class DepthsHeat {
    public static int addHeat(EntityDataSaver entity, int amount) {
        NbtCompound nbt = entity.getPersistentData();
        int heat = nbt.getInt("heat");
        heat = Math.min(20, heat + amount);

        nbt.putInt("heat", heat);
        if (entity instanceof PlayerEntity)
            syncHeat((ServerPlayerEntity) entity, heat);

        return heat;
    }

    public static int removeHeat(EntityDataSaver entity, int amount) {
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
}
