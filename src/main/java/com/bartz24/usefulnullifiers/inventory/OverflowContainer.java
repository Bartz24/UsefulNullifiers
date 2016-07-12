package com.bartz24.usefulnullifiers.inventory;

import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class OverflowContainer extends Container
{
	private final EntityPlayer player;
	public final OverflowInventory inv;
	
	public OverflowContainer(EntityPlayer entityPlayer,
			OverflowInventory inventory)
	{

		this.player = entityPlayer;
		this.inv = inventory;

		OverflowNullifierItem item = (OverflowNullifierItem) inv.parentItemStack
				.getItem();

		int i = 0;

		this.addSlotToContainer(new Slot(inv, 0, 80, 53));

		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 9; ++x)
			{
				this.addSlotToContainer(new Slot(player.inventory,
						x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x)
		{
			this.addSlotToContainer(
					new Slot(player.inventory, x, 8 + x * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	{
		ItemStack previous = null;
		Slot slot = this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 1)
			{
				if (!this.mergeItemStack(current, 1, 37, true))
					return null;
			} else
			{
				if (!this.mergeItemStack(current, 0, 1, false))
					return null;
			}

			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}
		return previous;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
}