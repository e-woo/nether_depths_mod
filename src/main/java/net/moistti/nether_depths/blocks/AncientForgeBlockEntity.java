package net.moistti.nether_depths.blocks;

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
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private AbstractForgeScreenHandler handler;
    int burnTime;
    int cookTime;
    int cookTimeTotal;
    int cookable;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            switch (index) {
                case 0 -> {
                    return AncientForgeBlockEntity.this.burnTime;
                }
                case 1 -> {
                    return AncientForgeBlockEntity.this.cookTime;
                }
                case 2 -> {
                    return AncientForgeBlockEntity.this.cookTimeTotal;
                }
                case 3 -> {
                    return AncientForgeBlockEntity.this.cookable;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> AncientForgeBlockEntity.this.burnTime = value;
                case 1 -> AncientForgeBlockEntity.this.cookTime = value;
                case 2 -> AncientForgeBlockEntity.this.cookTimeTotal = value;
                case 3 -> AncientForgeBlockEntity.this.cookable = value;
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    public AncientForgeBlockEntity(BlockPos pos, BlockState state) {
        super(DepthsBlockEntities.ANCIENT_FORGE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, AncientForgeBlockEntity blockEntity) {
//        if (blockEntity.items.get(1).getItem() instanceof ToolItem)
//            blockEntity.propertyDelegate.set(3, 1);
        if(world.isClient)
            return;
        if (world.getTimeOfDay() % 10 != 0)
            return;
//        System.out.println(blockEntity.handler);
        System.out.println(blockEntity.burnTime);
//        System.out.println(blockEntity.items);
        if (blockEntity.isForging()) {
            blockEntity.burnTime++;
            System.out.println("burntime: " + blockEntity.burnTime);
        }
//        if (blockEntity.burnTime == 60)
//            blockEntity.setForging(false);

    }

    public void setBurnTime(int time) {
        this.burnTime = time;
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
        System.out.println("create");
        System.out.println(this.getPos());
        return handler = new AncientForgeScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public void markDirty() {
        handler.onContentChanged(this);
    }

    public boolean isForging() {
        return this.burnTime > 0;
    }

//    public void setForging(boolean forging) {
//        handler.setForging(forging);
//        burnTime = 0;
//    }
}
