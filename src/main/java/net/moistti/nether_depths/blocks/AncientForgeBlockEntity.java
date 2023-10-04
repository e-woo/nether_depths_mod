package net.moistti.nether_depths.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.moistti.nether_depths.content.DepthsBlockEntities;
import net.moistti.nether_depths.forging.ForgeInventory;
import net.moistti.nether_depths.screen.AncientForgeScreenHandler;

public class AncientForgeBlockEntity extends LockableContainerBlockEntity implements ForgeInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public AncientForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntities.ANCIENT_FORGE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.nether_depths.ancient_forge");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AncientForgeScreenHandler(syncId, playerInventory, this);
    }
}
