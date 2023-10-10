package net.moistti.nether_depths.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.moistti.nether_depths.NetherDepths;

@Environment(value= EnvType.CLIENT)
public class ForgingScreen extends HandledScreen<AbstractForgeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(NetherDepths.MOD_ID, "textures/gui/container/forge.png");
    private boolean narrow;
    private ButtonWidget forgeButton;
    public ForgingScreen(AbstractForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.narrow = this.width < 379;
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        this.addDrawableChild(forgeButton = ButtonWidget.builder(Text.literal("Forge!"), button -> {
            if (this.client != null && this.client.interactionManager != null)
                this.client.interactionManager.clickButton(handler.syncId, 0);
            init();

        }).size(40, 18).position(this.width / 2 - 18, this.height / 2 - 22).build()).active = false;
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        forgeButton.active = handler.validIngredients() && !handler.isForging() && handler.getSlot(3).getStack().isEmpty();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        if (this.narrow)
            this.drawBackground(context, delta, mouseX, mouseY);
        else
            super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, this.x, (this.height - this.backgroundHeight) / 2, 0, 0, this.backgroundWidth, this.backgroundHeight);
        context.drawTexture(TEXTURE, this.x + 79, (this.height - this.backgroundHeight) / 2 + 34, 176, 14, this.handler.getCookProgress() + 1, 16);
    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        super.onMouseClick(slot, slotId, button, actionType);
    }

}
