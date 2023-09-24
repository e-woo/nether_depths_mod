package net.moistti.nether_depths.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;

public class DepthsHeat implements ClientTickEvents.EndWorldTick {

    @Override
    public void onEndTick(ClientWorld world) {
        for (PlayerEntity p : world.getPlayers()) {
            if (world.getBiome(p.getBlockPos()).matchesId(new Identifier(NetherDepths.MOD_ID, "nether_depths"))) {
                p.sendMessage(Text.of("In depths!"));
            }
        }
    }
}
