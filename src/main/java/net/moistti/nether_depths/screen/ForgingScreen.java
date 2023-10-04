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
import net.moistti.nether_depths.NetherDepths;

@Environment(value= EnvType.CLIENT)
public class ForgingScreen extends HandledScreen<AbstractForgeScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(NetherDepths.MOD_ID, "textures/gui/container/forge.png");
    private boolean narrow;
    public ForgingScreen(AbstractForgeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.narrow = this.width < 379;
        this.titleX = 29;
    }

//    @Override
//    public void handledScreenTick() {
//        super.handledScreenTick();
//    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        if (this.narrow) {
            this.drawBackground(context, delta, mouseX, mouseY);
        } else {
            super.render(context, mouseX, mouseY, delta);
        }
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        super.onMouseClick(slot, slotId, button, actionType);
    }

}
