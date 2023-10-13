package net.moistti.nether_depths.mixin;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.moistti.nether_depths.screen.EnhancedBeaconScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeaconScreen.class)
public abstract class BeaconScreenMixin {
    @Unique
    BeaconScreen self = (BeaconScreen) (Object) this;

    @Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/BeaconScreen;addButton(Lnet/minecraft/client/gui/widget/ClickableWidget;)V"))
    private <T extends ClickableWidget> void injectAddButton(BeaconScreen instance, T button) {
        if (self instanceof EnhancedBeaconScreen) {
            if (button instanceof BeaconScreen.LevelTwoEffectButtonWidget eButton) {
                instance.addButton(instance.new LevelTwoEffectButtonWidget(eButton.getX(), eButton.getY(), eButton.effect) {
                    @Override
                    public MutableText getEffectName(StatusEffect statusEffect) {
                        return Text.translatable(statusEffect.getTranslationKey()).append(" III");
                    }
                });
            } else if (button instanceof BeaconScreen.EffectButtonWidget eButton) {
                instance.addButton(instance.new EffectButtonWidget(eButton.getX(), eButton.getY(), eButton.effect, eButton.primary, eButton.level) {
                    @Override
                    public MutableText getEffectName(StatusEffect statusEffect) {
                        return Text.translatable(statusEffect.getTranslationKey()).append(" II");
                    }
                });
            }
            else
                instance.addButton((ClickableWidget & BeaconScreen.BeaconButtonWidget) button);
        }
        else
            instance.addButton((ClickableWidget & BeaconScreen.BeaconButtonWidget) button);
    }

}
