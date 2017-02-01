package com.bartz24.usefulnullifiers.inventory;

import mcjty.lib.compat.CompatInventory;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class VoidInventory implements CompatInventory {

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
		return ItemStackTools.getEmptyStack();
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
		return ItemStackTools.getEmptyStack();
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
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
}
