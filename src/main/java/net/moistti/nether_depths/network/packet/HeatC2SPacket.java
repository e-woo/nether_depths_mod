package net.moistti.nether_depths.network.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.util.DepthsHeat;
import net.moistti.nether_depths.util.DataSaver;

public class HeatC2SPacket {
    public static final int HEAT_MIN = 0;
    public static final int HEAT_MAX = 20;
    public static final int HEAT_TICK_AMOUNT = 2;
    private static final Identifier NETHER_DEPTHS_BIOME_ID = new Identifier(NetherDepths.MOD_ID, "nether_depths");
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        ServerWorld world = player.getServerWorld();
        for (LivingEntity p : world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), p -> !p.isFireImmune())) {
            if (p.age % 20 != 0)
                continue;
            DataSaver q = (DataSaver) p;
            if (world.getBiome(p.getBlockPos()).matchesId(NETHER_DEPTHS_BIOME_ID)) {
                if (q.getPersistentData().getInt("heat") <= HEAT_MAX) {
                    DepthsHeat.addHeat((DataSaver) p, HEAT_TICK_AMOUNT);
                    if (p instanceof ServerPlayerEntity)
                        DepthsHeat.syncHeat((ServerPlayerEntity) p, q.getPersistentData().getInt("heat"));
                }
            }
            else if (q.getPersistentData().getInt("heat") >= HEAT_MIN) {
                DepthsHeat.removeHeat((DataSaver) p, HEAT_TICK_AMOUNT);
                if (p instanceof ServerPlayerEntity)
                    DepthsHeat.syncHeat((ServerPlayerEntity) p, q.getPersistentData().getInt("heat"));
            }
        }
    }
}
