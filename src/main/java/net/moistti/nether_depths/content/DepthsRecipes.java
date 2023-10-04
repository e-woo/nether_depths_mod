package net.moistti.nether_depths.content;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.forging.ForgingRecipe;
import net.moistti.nether_depths.forging.ForgingRecipeSerializer;
import net.moistti.nether_depths.screen.AncientForgeScreenHandler;
import net.moistti.nether_depths.screen.ForgingScreen;

public class DepthsRecipes {
    public static final RecipeType<ForgingRecipe> FORGING = Registry.register(Registries.RECIPE_TYPE, new Identifier("forging"), new RecipeType<>(){

        public String toString() {
            Registries.RECIPE_TYPE.freeze();
            return "forging";
        }
    });

    public static final RecipeSerializer<ForgingRecipe> FORGING_SERIALIZER = RecipeSerializer.register("forging", new ForgingRecipeSerializer<ForgingRecipe>(ForgingRecipe::new, 100));
    public static final ScreenHandlerType<AncientForgeScreenHandler> ANCIENT_FORGE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, "ancient_forge", new ScreenHandlerType<>(AncientForgeScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    static {
        HandledScreens.register(ANCIENT_FORGE_SCREEN_HANDLER, ForgingScreen::new);
    }
}
