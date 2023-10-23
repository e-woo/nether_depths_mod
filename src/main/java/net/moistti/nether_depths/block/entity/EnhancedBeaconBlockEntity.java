package net.moistti.nether_depths.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;

public class EnhancedBeaconBlockEntity extends BeaconBlockEntity {
    public EnhancedBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        this.type = DepthsBlockEntityTypes.ENHANCED_BEACON;
    }
}
