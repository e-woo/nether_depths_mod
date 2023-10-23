package net.moistti.nether_depths.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.screen.ForgeScreenHandler;

public class ForgeBlockEntity extends AbstractForgeBlockEntity {
    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntityTypes.FORGE, pos, state);
    }

    @Override
    public Text getContainerName() {
        return Text.translatable("container.forge");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return handler = new ForgeScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }
}
