package net.moistti.nether_depths.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.content.DepthsBlockEntities;
import net.moistti.nether_depths.screen.AncientForgeScreenHandler;

public class AncientForgeBlockEntity extends AbstractForgeBlockEntity {
    public AncientForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntities.ANCIENT_FORGE, pos, state);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.ancient_forge");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return handler = new AncientForgeScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
}
