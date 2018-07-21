package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

public class FluidVoidInventory implements IInventory {

	NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

	public FluidVoidInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}

	@Override
	public String getName() {
		return "Fluid Void Nullifier";
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
		return inventory.get(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack returnStack = getStackInSlot(index).copy();
		this.inventory.set(index, ItemStack.EMPTY);
		return returnStack;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
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

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
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
			this.inventory.set(index, emptyContainer);
		else
			this.inventory.set(index, stack);
		this.markDirty();

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		InventoryHelper.dropInventoryItems(player.getEntityWorld(), player, this);
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

	@Override
	public boolean isEmpty() {
		return this.inventory.get(0).isEmpty();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}
}
