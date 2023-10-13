package net.moistti.nether_depths.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.content.DepthsBlockEntities;

public class EnhancedBeaconBlockEntity extends BeaconBlockEntity {
    public EnhancedBeaconBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        this.type = DepthsBlockEntities.ENHANCED_BEACON;
    }
}
