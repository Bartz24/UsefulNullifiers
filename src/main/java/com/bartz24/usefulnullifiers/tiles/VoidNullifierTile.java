package com.bartz24.usefulnullifiers.tiles;

import mcjty.lib.compat.CompatItemHandler;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;

public class VoidNullifierTile extends TileEntity implements CompatItemHandler {

	@Override
	public ItemStack getStackInSlot(int index) {
		return ItemStackTools.getEmptyStack();
	}

	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability,
			net.minecraft.util.EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability,
			net.minecraft.util.EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
	}

	@Override
	public int getSlots() {
		return 1;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return ItemStackTools.getEmptyStack();
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return ItemStackTools.getEmptyStack();
	}

	@Override
	public int getSlotMaxLimit() {
		return 64;
	}
}
