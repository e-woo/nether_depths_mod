package net.moistti.nether_depths.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moistti.nether_depths.block.AncientForgeBlock;
import net.moistti.nether_depths.forging.ForgeInventory;
import net.moistti.nether_depths.screen.AbstractForgeScreenHandler;
import net.moistti.nether_depths.util.GemUtil;

import java.util.Map;

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
        cookingPower = type.equals(DepthsBlockEntityTypes.FORGE) ? 2 : 1;
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
        String gemType = inventory.get(2).getItem().toString();

        // get gem type, write into the item's nbt
        NbtCompound nbt = outputItem.getNbt();
        if (nbt != null)
            nbt.putString("gem", gemType);

        // give a free level upgrade of enchantments that are boosted by the gem
        Map<Enchantment, Integer> itemEnchants = EnchantmentHelper.get(outputItem);
        for (Enchantment enchantment : GemUtil.GEM_ENCHANTS.get(gemType))
            if (itemEnchants.containsKey(enchantment))
                itemEnchants.put(enchantment, Math.min(itemEnchants.get(enchantment) + 1, enchantment.getMaxLevel() + 1));
        EnchantmentHelper.set(itemEnchants, outputItem);

        // decrement forge contents
        for (int i = 0; i <= 2; i++)
            inventory.get(i).decrement(1);

        // add the item to the output slot
        inventory.set(3, outputItem);
    }
}
