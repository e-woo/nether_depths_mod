package net.moistti.nether_depths.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.moistti.nether_depths.content.DepthsRecipes;
import net.moistti.nether_depths.forging.AbstractForgingRecipe;

public class AncientForgeScreenHandler extends AbstractForgeScreenHandler {

    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(DepthsRecipes.ANCIENT_FORGE_SCREEN_HANDLER, DepthsRecipes.FORGING, null, syncId, playerInventory, inventory, propertyDelegate);
    }

    public AncientForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(syncId, playerInventory);
    }
}
