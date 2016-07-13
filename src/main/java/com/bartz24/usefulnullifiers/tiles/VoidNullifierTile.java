package com.bartz24.usefulnullifiers.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class VoidNullifierTile extends TileEntity implements IInventory, IItemHandler
{

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack)
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Void Nullifier";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(getName());
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack itemStack = getStackInSlot(index);
		if (itemStack != null)
		{
			if (itemStack.stackSize <= count)
			{
				setInventorySlotContents(index, null);
			} else
			{
				itemStack = itemStack.splitStack(count);
				if (itemStack.stackSize == 0)
				{
					setInventorySlotContents(index, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
		
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
		
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{

	}

	@Override
	public void markDirty()
	{
		
	}

	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) this;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

	@Override
	public int getSlots()
	{
		return 1;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		return null;
	}
}
