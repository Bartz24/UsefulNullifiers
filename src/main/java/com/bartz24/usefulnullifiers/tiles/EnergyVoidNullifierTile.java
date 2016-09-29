package com.bartz24.usefulnullifiers.tiles;

import cofh.api.energy.IEnergyReceiver;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.info.Info;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Optional;

@Optional.InterfaceList(value = {@Optional.Interface(iface = "ic2.api.energy.tile.IEnergyTile", modid = "IC2"),
        @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "IC2"),
        @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "IC2"),
        @Optional.Interface(iface = "net.minecraft.util.ITickable", modid = "IC2")})
public class EnergyVoidNullifierTile extends TileEntity
		implements IEnergyReceiver, IEnergySink, IEnergyTile, ITickable
{
	boolean addedToEnet = false;
    @Optional.Method(modid = "IC2")
	public void update()
	{
    	onLoaded();
	}

    @Optional.Method(modid = "IC2")
	public void onLoaded()
	{
		if (!addedToEnet
				&& !FMLCommonHandler.instance().getEffectiveSide().isClient() && Info.isIc2Available())
		{
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));

			addedToEnet = true;
		}
	}

	@Override
	public int getEnergyStored(EnumFacing from)
	{
		return 0;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from)
	{
		return 0;
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate)
	{
		return simulate ? 0 : maxReceive;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter arg0, EnumFacing arg1)
	{
		return true;
	}

	@Override
	public double getDemandedEnergy()
	{
		return Double.MAX_VALUE;
	}

	@Override
	public int getSinkTier()
	{
		return 8;
	}

	@Override
	public double injectEnergy(EnumFacing from, double amount, double voltage)
	{
		return 0;
	}

}
