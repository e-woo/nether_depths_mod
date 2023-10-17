package net.moistti.nether_depths.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.content.DepthsEnchantments;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(Block.class)
public abstract class HeatedTouchMixin {
    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
    at = @At("RETURN"), cancellable = true)
    private static void injectDrops(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (EnchantmentHelper.getLevel(DepthsEnchantments.HEATED_TOUCH, stack) <= 0)
            return;
        List<ItemStack> drops = cir.getReturnValue();
        for (int i = 0; i < drops.size(); i++) {
            ItemStack drop = drops.get(i);
            Optional<SmeltingRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(drop), world);
            if (recipe.isPresent()) {
                ItemStack smeltedItem = recipe.get().craft(new SimpleInventory(drops.get(i)), DynamicRegistryManager.EMPTY);
                smeltedItem.setCount(drop.getCount());
                drops.set(i, smeltedItem);
            }
        }
        cir.setReturnValue(drops);
    }
}
