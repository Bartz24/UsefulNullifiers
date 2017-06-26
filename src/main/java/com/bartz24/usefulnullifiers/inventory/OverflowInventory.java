package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class OverflowInventory implements IInventory {
	public ItemStack parentItemStack;
	private ItemStack stackInv = ItemStack.EMPTY;
	public NBTTagCompound tag;

	public OverflowInventory(ItemStack parent) {
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
		return "Overflow Nullifier";
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
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stackInv;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
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
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return stackInv;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		stackInv = stack;
		markDirty();
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
