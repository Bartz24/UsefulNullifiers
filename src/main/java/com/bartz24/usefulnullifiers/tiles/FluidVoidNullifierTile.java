package com.bartz24.usefulnullifiers.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidVoidNullifierTile extends TileEntity implements IFluidHandler
{

	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return (T) this;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
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
