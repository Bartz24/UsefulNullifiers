package com.bartz24.usefulnullifiers.events;

import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.registry.ModItems;

import mcjty.lib.tools.ItemStackTools;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

	

	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		ItemStack stack = event.getItem().getEntityItem();
		for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
			if (ItemStackTools.isEmpty(stack) || ItemStackTools.getStackSize(stack) == 0) {
				break;
			}
			ItemStack stackInv = event.getEntityPlayer().inventory.getStackInSlot(i);

			if (!ItemStackTools.isEmpty(stackInv) && stackInv.getItem() == ModItems.overflowNullifier) {
				OverflowInventory inv = new OverflowInventory(stackInv);
				stack = fillOverflowInventory(inv, stack);
				inv.markDirty();
			}
		}
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {
		if (ItemStackTools.isEmpty(stack1) || ItemStackTools.isEmpty(stack2)) {
			return false;
		}
		if (!stack1.isItemEqual(stack2)) {
			return false;
		}
		if (!ItemStack.areItemStackTagsEqual(stack1, stack2)) {
			return false;
		}
		return true;

	}

	public static int mergeStacks(ItemStack mergeSource, ItemStack mergeTarget, boolean doMerge) {
		if (!canStacksMerge(mergeSource, mergeTarget)) {
			return 0;
		}
		int mergeCount = Math.min(mergeTarget.getMaxStackSize() - ItemStackTools.getStackSize(mergeTarget),
				ItemStackTools.getStackSize(mergeSource));
		if (mergeCount < 1) {
			return 0;
		}
		if (doMerge) {
			ItemStackTools.setStackSize(mergeTarget, ItemStackTools.getStackSize(mergeTarget) + mergeCount);
		}
		return mergeCount;
	}

	public static ItemStack fillOverflowInventory(IInventory inv, ItemStack stack) {
		if (inv != null) {
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack inside = inv.getStackInSlot(i);
				if (ItemStackTools.isEmpty(stack) || ItemStackTools.getStackSize(stack) <= 0)
					return ItemStackTools.getEmptyStack();
				if (canStacksMerge(inside, stack)) {
					mergeStacks(stack, inside, true);
					ItemStackTools.setStackSize(stack, 0);
				}
			}
		}
		return stack;

	}
}
