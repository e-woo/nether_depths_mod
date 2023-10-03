package net.moistti.nether_depths.screen;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsRecipes;
import net.moistti.nether_depths.forging.AbstractForgingRecipe;

public class AbstractForgeScreenHandler extends AbstractRecipeScreenHandler<Inventory> {
    private final RecipeType<? extends AbstractForgingRecipe> recipeType;
    private final RecipeBookCategory bookCategory;
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;

    protected AbstractForgeScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends AbstractForgingRecipe> recipeType, RecipeBookCategory category, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        this.recipeType = recipeType;
        this.bookCategory = category;
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        AbstractForgeScreenHandler.checkSize(inventory, 6);
        this.world = playerInventory.player.getWorld();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.addSlot(new Slot(this.inventory, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    public AbstractForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(DepthsRecipes.ANCIENT_FORGE_SCREEN_HANDLER, DepthsRecipes.FORGING, null, syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(6));
    }

    @Override
    public void populateRecipeFinder(RecipeMatcher finder) {

    }

    @Override
    public void clearCraftingSlots() {

    }

    @Override
    public boolean matches(Recipe<? super Inventory> recipe) {
        return recipe.matches(this.inventory, this.world);
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return 5;
    }

    @Override
    public int getCraftingWidth() {
        return 2;
    }

    @Override
    public int getCraftingHeight() {
        return 2;
    }

    @Override
    public int getCraftingSlotCount() {
        return 6;
    }

    @Override
    public RecipeBookCategory getCategory() {
        return null;
    }

    @Override
    public boolean canInsertIntoSlot(int index) {
        return index != -1;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
