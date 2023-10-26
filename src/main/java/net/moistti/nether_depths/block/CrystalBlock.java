package net.moistti.nether_depths.block;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class CrystalBlock extends AmethystClusterBlock {
    public CrystalBlock(Settings settings) {
        super(7, 3, settings);
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience)
            this.dropExperienceWhenMined(world, pos, tool, UniformIntProvider.create(10, 15));
    }
}
