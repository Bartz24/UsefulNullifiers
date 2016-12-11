package com.bartz24.usefulnullifiers.tiles;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

/*@Optional.InterfaceList(value = {@Optional.Interface(iface = "ic2.api.energy.tile.IEnergyTile", modid = "IC2"),
        @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "IC2"),
        @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "IC2"),
        @Optional.Interface(iface = "net.minecraft.util.ITickable", modid = "IC2")})*/

public class EnergyVoidNullifierTile extends TileEntity
/*implements  IEnergyReceiver, IEnergySink, IEnergyTile, */ {
	/*
	 * boolean addedToEnet = false;
	 * 
	 * @Optional.Method(modid = "IC2") public void update() { onLoaded(); }
	 * 
	 * @Optional.Method(modid = "IC2") public void onLoaded() { if (!addedToEnet
	 * && !FMLCommonHandler.instance().getEffectiveSide().isClient() &&
	 * Info.isIc2Available()) { MinecraftForge.EVENT_BUS.post(new
	 * EnergyTileLoadEvent(this));
	 * 
	 * addedToEnet = true; } }
	 */
    private EnergyStorage energy = new EnergyStorage(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);

	/*
	 * @Override public boolean acceptsEnergyFrom(IEnergyEmitter arg0,
	 * EnumFacing arg1) { return true; }
	 * 
	 * @Override public double getDemandedEnergy() { return Double.MAX_VALUE; }
	 * 
	 * @Override public int getSinkTier() { return 8; }
	 * 
	 * @Override public double injectEnergy(EnumFacing from, double amount,
	 * double voltage) { return 0; }
	 */

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return CapabilityEnergy.ENERGY.cast(energy);
		}

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
	}
}
