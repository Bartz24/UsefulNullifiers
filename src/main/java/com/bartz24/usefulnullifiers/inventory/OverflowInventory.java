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
	private ItemStack stackInv = ItemStack.field_190927_a;
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
		if (!itemStack.func_190926_b()) {
			if (itemStack.func_190916_E() <= count) {
				setInventorySlotContents(index, ItemStack.field_190927_a);
			} else {
				itemStack = itemStack.splitStack(count);
				if (itemStack.func_190916_E() == 0) {
					setInventorySlotContents(index, ItemStack.field_190927_a);
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
		if (!stack.func_190926_b() && stack.func_190916_E() > getInventoryStackLimit()) {
			stack.func_190920_e(getInventoryStackLimit());
		}
		stackInv = stack;
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
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
			this.setInventorySlotContents(slot, new ItemStack(stackTag));
		}
	}

	public void writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (!this.getStackInSlot(i).func_190926_b()) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		compound.setTag("Items", list);
	}

	public boolean func_191420_l() {
		if (!stackInv.func_190926_b()) {
			return false;
		}

		return true;
	}
}
