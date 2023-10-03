package net.moistti.nether_depths.forging;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.moistti.nether_depths.content.DepthsRecipes;

public class ForgingRecipe extends AbstractForgingRecipe {
    public ForgingRecipe(RecipeType<?> type, Identifier id, String group, Ingredient input, ItemStack output, float experience, int time) {
        super(type, id, group, input, output, experience, time);
    }

    public ForgingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int time) {
        this(DepthsRecipes.FORGING, id, group, input, output, experience, time);
    }

    @Override
    public ItemStack createIcon() {
        return null;
    }
}
