package com.bartz24.usefulnullifiers.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class OverflowContainer extends Container
{
	private final EntityPlayer player;
	public final OverflowInventory inv;
	private final int protectSlot;

	public OverflowContainer(EntityPlayer entityPlayer,
			OverflowInventory inventory, int protectedSlot)
	{

		this.player = entityPlayer;
		this.inv = inventory;
		protectSlot = protectedSlot;

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
			if (x != this.protectSlot)
				addSlotToContainer(
						new Slot(player.inventory, x, 8 + x * 18, 142));
			else
				this.addSlotToContainer(
						new Slot(player.inventory, x, 8 + x * 18, 142)
						{
							public boolean canTakeStack(EntityPlayer playerIn)
							{
								return false;
							}
						});
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	{
		ItemStack previous = ItemStack.field_190927_a;
		Slot slot = this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 1)
			{
				if (!this.mergeItemStack(current, 1, 37, true))
					return ItemStack.field_190927_a;
			} else
			{
				if (!this.mergeItemStack(current, 0, 1, false))
					return ItemStack.field_190927_a;
			}

			if (current.func_190916_E() == 0)
				slot.putStack(ItemStack.field_190927_a);
			else
				slot.onSlotChanged();

			if (current.func_190916_E() == previous.func_190916_E())
				return ItemStack.field_190927_a;
			slot.onSlotChanged();
		}
		return previous;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
}
