package net.moistti.nether_depths.forging;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class ForgingRecipeSerializer<T extends AbstractForgingRecipe> implements RecipeSerializer<T> {
    private final int cookingTime;
    private final ForgingFactory<T> recipeFactory;

    public ForgingRecipeSerializer(ForgingFactory<T> recipeFactory, int cookingTime) {
        this.cookingTime = cookingTime;
        this.recipeFactory = recipeFactory;
    }
    @Override
    public T read(Identifier id, JsonObject json) {
        String string = JsonHelper.getString(json, "group", "");
        JsonElement jsonElement = JsonHelper.hasArray(json, "ingredient") ? JsonHelper.getArray(json, "ingredient") : JsonHelper.getObject(json, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonElement, false);
        Identifier outputId = new Identifier(JsonHelper.getString(json, "result"));
        ItemStack output = new ItemStack((ItemConvertible) Registries.ITEM.getOrEmpty(outputId).orElseThrow(() -> new IllegalStateException("Item: " + outputId + " does not exist")));
        float experience = JsonHelper.getFloat(json, "experience", 0.0f);
        int time = JsonHelper.getInt(json, "cookingtime", this.cookingTime);
        return this.recipeFactory.create(id, string, ingredient, output, experience, time);
    }

    @Override
    public T read(Identifier id, PacketByteBuf buf) {
        String group = buf.readString(); // group
        Ingredient ingredient = Ingredient.fromPacket(buf); // ingredient
        ItemStack output = buf.readItemStack(); // output
        float experience = buf.readFloat();
        int time = buf.readVarInt();
        return this.recipeFactory.create(id, group, ingredient, output, experience, time);
    }

    @Override
    public void write(PacketByteBuf buf, T recipe) {
        buf.writeString(recipe.group);
        recipe.input.write(buf);
        buf.writeItemStack(recipe.output);
        buf.writeFloat(recipe.experience);
        buf.writeVarInt(recipe.time);
    }

    public static interface ForgingFactory<T extends AbstractForgingRecipe> {
        public T create(Identifier id, String group, Ingredient input, ItemStack output, float experience, int time);
    }
}
