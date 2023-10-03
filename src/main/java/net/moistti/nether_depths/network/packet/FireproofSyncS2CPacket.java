package net.moistti.nether_depths.network.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.network.PacketByteBuf;
import net.moistti.nether_depths.util.DataSaver;

public class FireproofSyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        if (client.player != null)
            ((DataSaver) (Object) (client.player.getEquippedStack(EquipmentSlot.HEAD))).getPersistentData().putBoolean("fireproof", buf.readBoolean());
    }
}
