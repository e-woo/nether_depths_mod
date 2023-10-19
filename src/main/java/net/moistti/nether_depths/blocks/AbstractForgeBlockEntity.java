package net.moistti.nether_depths.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
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

public abstract class AbstractForgeBlockEntity extends LockableContainerBlockEntity implements ForgeInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    protected AbstractForgeScreenHandler handler;
    protected int forgeTime;
    private final int cookingPower;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){
        @Override
        public int get(int index) {
            switch (index) {
                case 0 -> {
                    return AbstractForgeBlockEntity.this.forgeTime;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> AbstractForgeBlockEntity.this.forgeTime = value;
            }
        }
        @Override
        public int size() {
            return 1;
        }
    };
    public AbstractForgeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        cookingPower = type.equals(DepthsBlockEntities.FORGE) ? 2 : 1;
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, AbstractForgeBlockEntity blockEntity) {
        if(world.isClient)
            return;
        if (blockEntity.isForging())
            blockEntity.forgeTime += blockEntity.cookingPower;
        if (blockEntity.forgeTime >= 400)
            blockEntity.finishForging();
        blockState = blockState.with(AncientForgeBlock.LIT, blockEntity.isForging());
        world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        AbstractForgeBlockEntity.markDirty(world, blockPos, blockState);
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
