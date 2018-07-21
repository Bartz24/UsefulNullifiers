package com.bartz24.usefulnullifiers.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class AIONTile extends TileEntity implements IItemHandler, IFluidHandler {

	private EnergyStorage energy = new EnergyStorage(Integer.MAX_VALUE, Integer.MAX_VALUE, 0) {
		@Override
		public int receiveEnergy(int maxReceive, boolean simulate)
		{
			return maxReceive;
		}
	};

	@Override
	public ItemStack getStackInSlot(int index) {
		return ItemStack.EMPTY;
	}

	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability,
			net.minecraft.util.EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this;
		else if(capability == CapabilityEnergy.ENERGY)
			return CapabilityEnergy.ENERGY.cast(energy);
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability,
			net.minecraft.util.EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY;
	}

	@Override
	public int getSlots() {
		return 1;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public IFluidTankProperties[] getTankProperties()
	{
		return new FluidTank(Integer.MAX_VALUE).getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill)
	{
		return resource.amount;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain)
	{
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain)
	{
		return null;
	}
}
