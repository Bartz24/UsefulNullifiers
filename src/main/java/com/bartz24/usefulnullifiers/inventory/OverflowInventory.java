package com.bartz24.usefulnullifiers.inventory;

import mcjty.lib.compat.CompatInventory;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class OverflowInventory implements CompatInventory {
	public ItemStack parentItemStack;
	private ItemStack stackInv = ItemStackTools.getEmptyStack();
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
		if (!ItemStackTools.isEmpty(itemStack)) {
			if (ItemStackTools.getStackSize(itemStack) <= count) {
				setInventorySlotContents(index, ItemStackTools.getEmptyStack());
			} else {
				itemStack = itemStack.splitStack(count);
				if (ItemStackTools.getStackSize(itemStack) == 0) {
					setInventorySlotContents(index, ItemStackTools.getEmptyStack());
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
		if (!ItemStackTools.isEmpty(stack) && ItemStackTools.getStackSize(stack) > getInventoryStackLimit()) {
			ItemStackTools.setStackSize(stack, getInventoryStackLimit());
		}
		stackInv = stack;
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsable(EntityPlayer player) {
		return true;
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
			this.setInventorySlotContents(slot, ItemStackTools.loadFromNBT(stackTag));
		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (!ItemStackTools.isEmpty(this.getStackInSlot(i))) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		compound.setTag("Items", list);
	}
}
