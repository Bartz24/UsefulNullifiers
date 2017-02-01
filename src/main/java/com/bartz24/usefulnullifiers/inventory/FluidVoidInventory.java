package com.bartz24.usefulnullifiers.inventory;

import mcjty.lib.compat.CompatInventory;
import mcjty.lib.tools.FluidTools;
import mcjty.lib.tools.ItemStackList;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class FluidVoidInventory implements CompatInventory {

	ItemStackList inventory = ItemStackList.create(1);

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
		this.inventory.set(index, ItemStackTools.getEmptyStack());
		return returnStack;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (!ItemStackTools.isEmpty(this.getStackInSlot(index))) {
			ItemStack itemstack;

			if (ItemStackTools.getStackSize(this.getStackInSlot(index)) <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, ItemStackTools.getEmptyStack());
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (ItemStackTools.getStackSize(this.getStackInSlot(index)) <= 0) {
					this.setInventorySlotContents(index, ItemStackTools.getEmptyStack());
				} else {
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return ItemStackTools.getEmptyStack();
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (!ItemStackTools.isEmpty(stack) && ItemStackTools.getStackSize(stack) > this.getInventoryStackLimit())
			ItemStackTools.setStackSize(stack, this.getInventoryStackLimit());

		if (!ItemStackTools.isEmpty(stack) && ItemStackTools.getStackSize(stack) == 0)
			stack = ItemStackTools.getEmptyStack();

		ItemStack emptyContainer = ItemStackTools.getEmptyStack();
		if (!ItemStackTools.isEmpty(stack)) {
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			if (fluid != null) {
				emptyContainer = FluidTools.drainContainer(stack);
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

		if (!ItemStackTools.isEmpty(emptyContainer))
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
	public boolean isUsable(EntityPlayer player) {
		return true;
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
}
