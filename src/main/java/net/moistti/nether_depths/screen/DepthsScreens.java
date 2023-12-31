package net.moistti.nether_depths.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public final class DepthsScreens {
    public static final ScreenHandlerType<AncientForgeScreenHandler> ANCIENT_FORGE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, "ancient_forge", new ScreenHandlerType<>(AncientForgeScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final ScreenHandlerType<ForgeScreenHandler> FORGE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, "forge", new ScreenHandlerType<>(ForgeScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final ScreenHandlerType<EnhancedBeaconScreenHandler> ENHANCED_BEACON_SCREEN_HANDLER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, "enhanced_beacon", new ScreenHandlerType<>(EnhancedBeaconScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static void register() {
        HandledScreens.register(ANCIENT_FORGE_SCREEN_HANDLER, ForgingScreen::new);
        HandledScreens.register(FORGE_SCREEN_HANDLER, ForgingScreen::new);
        HandledScreens.register(ENHANCED_BEACON_SCREEN_HANDLER_SCREEN_HANDLER, EnhancedBeaconScreen::new);
    }
}
