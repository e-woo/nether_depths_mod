package net.moistti.nether_depths.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moistti.nether_depths.content.DepthsBlockEntities;
import net.moistti.nether_depths.forging.ForgeInventory;
import net.moistti.nether_depths.screen.AbstractForgeScreenHandler;
import net.moistti.nether_depths.screen.AncientForgeScreenHandler;

public class AncientForgeBlockEntity extends LockableContainerBlockEntity implements ForgeInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private AbstractForgeScreenHandler handler;
    int forgeTime;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){
        @Override
        public int get(int index) {
            switch (index) {
                case 0 -> {
                    return AncientForgeBlockEntity.this.forgeTime;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> AncientForgeBlockEntity.this.forgeTime = value;
            }
        }
        @Override
        public int size() {
            return 1;
        }
    };
    public AncientForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntities.ANCIENT_FORGE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, AncientForgeBlockEntity blockEntity) {
        if(world.isClient)
            return;
        if (blockEntity.isForging())
            blockEntity.forgeTime++;
        else if (blockEntity.forgeTime >= 400)
            blockEntity.finishForging();
        blockState = blockState.with(AncientForge.LIT, blockEntity.isForging());
        world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        AncientForgeBlockEntity.markDirty(world, blockPos, blockState);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.forgeTime = nbt.getInt("forgeTime");
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("forgeTime", this.forgeTime);
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.nether_depths.ancient_forge");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return handler = new AncientForgeScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void markDirty() {
        if (handler != null)
            handler.onContentChanged(this);
        super.markDirty();
    }

    public boolean isForging() {
        return this.forgeTime > 0;
    }

    public void finishForging() {
        forgeTime = 0; // set the forging time back to 0
        if (!AbstractForgeScreenHandler.validIngredients(ForgeInventory.of(inventory)))
            return;
        ItemStack outputItem = inventory.get(1).copy();
        ItemStack gem = inventory.get(2);

        // get gem type, write into the item's nbt
        NbtCompound nbt = outputItem.getNbt();
        if (nbt != null)
            nbt.putString("gem", gem.getItem().toString());

        // decrement forge contents
        for (int i = 0; i <= 2; i++)
            inventory.get(i).decrement(1);

        // add the item to the output slot
        inventory.set(3, outputItem);
    }
}
