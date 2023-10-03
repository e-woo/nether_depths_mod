package net.moistti.nether_depths.network.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.moistti.nether_depths.util.DataSaver;
import net.moistti.nether_depths.util.Fireproof;

public class FireproofC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        ServerWorld world = player.getServerWorld();
        for (LivingEntity e : world.getEntitiesByType(TypeFilter.instanceOf(PlayerEntity.class), p -> !p.isFireImmune())) {
            if (e.age % 20 != 0)
                continue;
//            System.out.println("p: " + ((DataSaver) (Object) (e.getEquippedStack(EquipmentSlot.HEAD))).getPersistentData().getBoolean("fireproof"));
            if(e.getEquippedStack(EquipmentSlot.HEAD).isOf(Items.NETHERITE_HELMET) && !((DataSaver) (Object) (e.getEquippedStack(EquipmentSlot.HEAD))).getPersistentData().getBoolean("fireproof")) {


//            for (ItemStack armor : e.getArmorItems()) {
//                if (armor.isOf(Items.NETHERITE_HELMET)) {
                    Fireproof.setFireproof(e.getEquippedStack(EquipmentSlot.HEAD), true, (DataSaver) e);
                    Fireproof.syncFireproof(player, true);
//                }
            }
        }
    }
}
