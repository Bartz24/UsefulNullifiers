package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

public class AIONInventory implements IInventory {
    public ItemStack parentItemStack;
    private NonNullList<ItemStack> stackInv = NonNullList.withSize(3, ItemStack.EMPTY);
    public NBTTagCompound tag;

    public AIONInventory(ItemStack parent) {
        parentItemStack = parent;
        if (!parent.hasTagCompound()) {
            parent.setTagCompound(new NBTTagCompound());
        }
        tag = parentItemStack.getTagCompound();
        readFromNBT(tag);
    }

    @Override
    public void markDirty() {
        writeToNBT(tag);
        // stackInv.writeToNBT(tag);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack) {
        return true;
    }

    @Override
    public String getName() {
        return "AION Portable Nullifier";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        switch (index) {
            case 0:
                return ItemStack.EMPTY;
            case 1:
            case 2:
                return stackInv.get(index);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        switch (index) {
            case 0:
            case 1:
                ItemStack itemStack = getStackInSlot(index);
                if (!itemStack.isEmpty()) {
                    if (itemStack.getCount() <= count) {
                        setInventorySlotContents(index, ItemStack.EMPTY);
                    } else {
                        itemStack = itemStack.splitStack(count);
                        if (itemStack.getCount() == 0) {
                            setInventorySlotContents(index, ItemStack.EMPTY);
                        }
                    }
                }
                return itemStack;
            case 2:
                if (!this.getStackInSlot(index).isEmpty()) {
                    ItemStack itemstack;

                    if (this.getStackInSlot(index).getCount() <= count) {
                        itemstack = this.getStackInSlot(index);
                        this.setInventorySlotContents(index, ItemStack.EMPTY);
                        this.markDirty();
                        return itemstack;
                    } else {
                        itemstack = this.getStackInSlot(index).splitStack(count);

                        if (this.getStackInSlot(index).getCount() <= 0) {
                            this.setInventorySlotContents(index, ItemStack.EMPTY);
                        } else {
                            this.setInventorySlotContents(index, this.getStackInSlot(index));
                        }

                        this.markDirty();
                        return itemstack;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        switch (index) {
            case 0:
                return ItemStack.EMPTY;
            case 1:
            case 2:
                ItemStack stack = stackInv.get(index).copy();
                stackInv.set(index, ItemStack.EMPTY);
                return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        switch (index) {
            case 0:
                break;
            case 1:
                if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
                    stack.setCount(getInventoryStackLimit());
                }
                stackInv.set(index, stack);
                markDirty();
                break;
            case 2:
                if (index < 0 || index >= this.getSizeInventory())
                    return;

                if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
                    stack.setCount(this.getInventoryStackLimit());

                if (!stack.isEmpty() && stack.getCount() == 0)
                    stack = ItemStack.EMPTY;

                ItemStack emptyContainer = ItemStack.EMPTY;
                if (!stack.isEmpty()) {
                    FluidStack fluid = FluidUtil.getFluidContained(stack);
                    if (fluid != null) {
                        FluidActionResult result = FluidUtil.tryEmptyContainer(stack, new FluidTank(Integer.MAX_VALUE), Integer.MAX_VALUE,
                                null, true);
                        if (result.success)
                            emptyContainer = result.getResult();
                    }
                    if (stack.getItem() == Items.WATER_BUCKET) {
                        emptyContainer = new ItemStack(Items.BUCKET);
                    }
                    if (stack.getItem() == Items.LAVA_BUCKET) {
                        emptyContainer = new ItemStack(Items.BUCKET);
                    }
                    if (stack.getItem() == Items.MILK_BUCKET) {
                        emptyContainer = new ItemStack(Items.BUCKET);
                    }
                }

                if (!emptyContainer.isEmpty())
                    this.stackInv.set(index, emptyContainer);
                else
                    this.stackInv.set(index, stack);
                this.markDirty();
                break;
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {
        readFromNBT(tag);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        writeToNBT(tag);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList list = compound.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            this.setInventorySlotContents(slot, new ItemStack(stackTag));
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (!this.getStackInSlot(i).isEmpty()) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }
        compound.setTag("Items", list);
    }

    @Override
    public boolean isEmpty() {
        return stackInv.isEmpty();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        // TODO Auto-generated method stub
        return false;
    }
}
