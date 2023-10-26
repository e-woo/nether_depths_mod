package net.moistti.nether_depths.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.network.packet.*;

public class Packets {
    public static final Identifier HEAT_ID = new Identifier(NetherDepths.MOD_ID, "heat");
    public static final Identifier HEAT_SYNC_ID = new Identifier(NetherDepths.MOD_ID, "heat_sync");
    public static final Identifier FIREPROOF_ID = new Identifier(NetherDepths.MOD_ID, "fireproof");
    public static final Identifier FIREPROOF_SYNC_ID = new Identifier(NetherDepths.MOD_ID, "fireproof_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(HEAT_ID, HeatC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(HEAT_SYNC_ID, HeatSyncS2CPacket::receive);
    }
}
