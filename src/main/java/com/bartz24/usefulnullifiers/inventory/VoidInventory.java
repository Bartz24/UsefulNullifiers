package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class VoidInventory implements IInventory {

	public VoidInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}

	@Override
	public String getName() {
		return "Void Nullifier";
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
		return ItemStack.field_190927_a;
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
		return ItemStack.field_190927_a;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
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

	}

	@Override
	public void closeInventory(EntityPlayer player) {

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

	@Override
	public void markDirty() {

	}

	public boolean func_191420_l() {
		return true;
	}
}
