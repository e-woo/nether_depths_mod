package net.moistti.nether_depths.block;

import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moistti.nether_depths.block.entity.EnhancedBeaconBlockEntity;
import net.moistti.nether_depths.block.entity.DepthsBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class EnhancedBeaconBlock extends BeaconBlock {
    public EnhancedBeaconBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnhancedBeaconBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BeaconBlock.checkType(type, DepthsBlockEntityTypes.ENHANCED_BEACON, EnhancedBeaconBlockEntity::tick);
    }
}
