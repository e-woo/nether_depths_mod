package net.moistti.nether_depths.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.screen.AncientForgeScreenHandler;

public class AncientForgeBlockEntity extends AbstractForgeBlockEntity {
    public AncientForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntityTypes.ANCIENT_FORGE, pos, state);
    }

    @Override
    public Text getContainerName() {
        return Text.translatable("container.ancient_forge");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return handler = new AncientForgeScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
}
