package com.bartz24.usefulnullifiers.events;

import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.registry.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event)
	{
		ItemStack stack = event.getItem().getEntityItem();
		boolean success = false;
		for (int i = 0; i < event.getEntityPlayer().inventory
				.getSizeInventory(); i++)
		{
			if (stack == null || stack.stackSize==0)
				break;
			ItemStack stackInv = event.getEntityPlayer().inventory
					.getStackInSlot(i);

			if (stackInv != null
					&& stackInv.getItem() == ModItems.overflowNullifier)
			{
				OverflowInventory inv = new OverflowInventory(stackInv);
				fillOverflowInventory(inv, stack);
				success=true;
				inv.markDirty();
			}
		}
		if(success)
			stack.stackSize=0;
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2)
	{
		if (stack1 == null || stack2 == null)
		{
			return false;
		}
		if (!stack1.isItemEqual(stack2))
		{
			return false;
		}
		if (!ItemStack.areItemStackTagsEqual(stack1, stack2))
		{
			return false;
		}
		return true;

	}

	public static int mergeStacks(ItemStack mergeSource, ItemStack mergeTarget,
			boolean doMerge)
	{
		if (!canStacksMerge(mergeSource, mergeTarget))
		{
			return 0;
		}
		int mergeCount = Math.min(
				mergeTarget.getMaxStackSize() - mergeTarget.stackSize,
				mergeSource.stackSize);
		if (mergeCount < 1)
		{
			return 0;
		}
		if (doMerge)
		{
			mergeTarget.stackSize += mergeCount;
		}
		return mergeCount;
	}

	public static ItemStack fillOverflowInventory(IInventory inv, ItemStack stack)
	{
		if (inv != null)
		{
			for (int i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack inside = inv.getStackInSlot(i);
				if (stack == null || stack.stackSize <= 0 || inside == null
						|| inside.stackSize <= 0)
					return null;
				if (canStacksMerge(inside, stack))
				{
					stack.stackSize -= mergeStacks(stack, inside, true);
				}
			}
		}
		return stack;

	}
}
