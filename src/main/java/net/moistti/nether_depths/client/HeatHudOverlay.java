package net.moistti.nether_depths.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.NetherDepths;
import net.moistti.nether_depths.util.DataSaver;

public class HeatHudOverlay implements HudRenderCallback {
    private static final Identifier HEAT = new Identifier(NetherDepths.MOD_ID, "textures/heat/heat.png");
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null) {

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }
        if (client.player.isCreative() || client.player.isSpectator())
            return;
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, HEAT);
        drawContext.getMatrices().translate(0, 0, 50);
        for (int i = 0; i < (((DataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("heat") + 1) / 2; i++) {
            drawContext.drawTexture(HEAT, x + 91 - (i * 8) - 9, y - 39 - 10, 0, 0, 9, 9, 9, 9);
        }
    }
}
