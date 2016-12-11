package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

public class FluidVoidInventory implements IInventory
{

	NonNullList<ItemStack> inventory = NonNullList.<ItemStack>func_191197_a(1, ItemStack.field_190927_a);

	public FluidVoidInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack)
	{
		return true;
	}

	@Override
	public String getName()
	{
		return "Fluid Void Nullifier";
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
		return inventory.get(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (!this.getStackInSlot(index).func_190926_b())
		{
			ItemStack itemstack;

			if (this.getStackInSlot(index).func_190916_E() <= count)
			{
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, ItemStack.field_190927_a);
				this.markDirty();
				return itemstack;
			} else
			{
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).func_190916_E() <= 0)
				{
					this.setInventorySlotContents(index, ItemStack.field_190927_a);
				} else
				{
					this.setInventorySlotContents(index,
							this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else
		{
			return ItemStack.field_190927_a;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (!stack.func_190926_b() && stack.func_190916_E() > this.getInventoryStackLimit())
			stack.func_190920_e(this.getInventoryStackLimit());

		if (!stack.func_190926_b() && stack.func_190916_E() == 0)
			stack = ItemStack.field_190927_a;

		ItemStack emptyContainer = ItemStack.field_190927_a;
		if (!stack.func_190926_b())
		{
			FluidStack fluid = FluidUtil.getFluidContained(stack);
			if (fluid != null)
			{
				emptyContainer = FluidUtil.tryEmptyContainer(stack,
						new FluidTank(Integer.MAX_VALUE), fluid.amount, null,
						true).getResult();
			}
			if (stack.getItem() == Items.WATER_BUCKET)
			{
				emptyContainer = new ItemStack(Items.BUCKET);
			}
			if (stack.getItem() == Items.LAVA_BUCKET)
			{
				emptyContainer = new ItemStack(Items.BUCKET);
			}
			if (stack.getItem() == Items.MILK_BUCKET)
			{
				emptyContainer = new ItemStack(Items.BUCKET);
			}
		}

		if (!emptyContainer.func_190926_b())
			this.inventory.set(index, emptyContainer);
		else
			this.inventory.set(index, stack);
		this.markDirty();

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
		InventoryHelper.dropInventoryItems(player.worldObj, player, this);
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

    public boolean func_191420_l()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.func_190926_b())
            {
                return false;
            }
        }

        return true;
    }
	
}
