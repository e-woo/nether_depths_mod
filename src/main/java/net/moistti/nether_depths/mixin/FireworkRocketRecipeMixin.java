package net.moistti.nether_depths.mixin;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.FireworkRocketRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.moistti.nether_depths.item.DepthsItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketRecipe.class)
public abstract class FireworkRocketRecipeMixin {
    @Unique
    private static final Ingredient STRONG_DURATION_MODIFIER = Ingredient.ofItems(DepthsItems.UNSTABLE_GUNPOWDER);

    @Redirect(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "INVOKE",
    target = "Lnet/minecraft/recipe/Ingredient;test(Lnet/minecraft/item/ItemStack;)Z", ordinal = 1))
    private boolean injectMatches(Ingredient instance, ItemStack itemStack) {
        return instance.test(itemStack) || STRONG_DURATION_MODIFIER.test(itemStack);
    }

    @Inject(method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;",
    at = @At("RETURN"))
    private void injectCraft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack output = cir.getReturnValue();
        NbtCompound nbt = output.getOrCreateSubNbt("Fireworks");
        byte flightDuration = 0;
        if (nbt.contains("Flight"))
            flightDuration = nbt.getByte("Flight");
        for (int i = 0; i < recipeInputInventory.size(); i++) {
            ItemStack inputStack = recipeInputInventory.getStack(i);
            if (inputStack.isEmpty())
                continue;
            if (STRONG_DURATION_MODIFIER.test(inputStack))
                flightDuration += 4;
        }
        System.out.println(flightDuration);
        nbt.putByte("Flight", flightDuration);
    }
}
