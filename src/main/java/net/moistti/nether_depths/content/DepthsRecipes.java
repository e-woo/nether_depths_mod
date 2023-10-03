package net.moistti.nether_depths.content;

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

public class DepthsRecipes {
    public static final RecipeType<ForgingRecipe> FORGING = Registry.register(Registries.RECIPE_TYPE, new Identifier("forging"), new RecipeType<ForgingRecipe>(){

        public String toString() {
            Registries.RECIPE_TYPE.freeze();
            return "forging";
        }
    });

    public static final RecipeSerializer<ForgingRecipe> FORGING_SERIALIZER = RecipeSerializer.register("forging", new ForgingRecipeSerializer<ForgingRecipe>(ForgingRecipe::new, 100));
    public static final ScreenHandlerType<AncientForgeScreenHandler> ANCIENT_FORGE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, "ancient_forge", new ScreenHandlerType<AncientForgeScreenHandler>(AncientForgeScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
}
