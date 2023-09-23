package net.moistti.volcanic_caves;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DepthsHeat implements ClientTickEvents.EndWorldTick {

    @Override
    public void onEndTick(ClientWorld world) {
        for (PlayerEntity p : world.getPlayers()) {
            if (world.getBiome(p.getBlockPos()).matchesId(new Identifier(VolcanicCaves.MOD_ID, "nether_depths"))) {
                p.sendMessage(Text.of("In depths!"));
            }
        }
    }
}
