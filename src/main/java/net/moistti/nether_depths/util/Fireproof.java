package net.moistti.nether_depths.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.moistti.nether_depths.network.Packets;
import net.minecraft.item.ItemStack;

public class Fireproof {
    public static boolean setFireproof(ItemStack is, boolean fireproof, DataSaver player) {
        DataSaver item = (DataSaver) (Object) is;
        NbtCompound nbt = item.getPersistentData();
        nbt.putBoolean("fireproof", fireproof);
        System.out.println("written nbt : " + nbt.getBoolean("fireproof"));
        if (player instanceof PlayerEntity)
            syncFireproof((ServerPlayerEntity) player, fireproof);
        return fireproof;
    }

    public static void syncFireproof(ServerPlayerEntity player, boolean fireproof) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(fireproof);
        ServerPlayNetworking.send(player, Packets.FIREPROOF_SYNC_ID, buffer);
    }
}
