package com.bartz24.usefulnullifiers.events;

import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.registry.ModItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	@SubscribeEvent
	public void onItemPickup(EntityItemPickupEvent event) {
		ItemStack stack = event.getItem().getEntityItem();
		for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
			if (stack.func_190926_b() || stack.func_190916_E() == 0) {
				break;
			}
			ItemStack stackInv = event.getEntityPlayer().inventory.getStackInSlot(i);

			if (!stackInv.func_190926_b() && stackInv.getItem() == ModItems.overflowNullifier) {
				OverflowInventory inv = new OverflowInventory(stackInv);
				stack = fillOverflowInventory(inv, stack);
				inv.markDirty();
			}
		}
	}

	public static boolean canStacksMerge(ItemStack stack1, ItemStack stack2) {
		if (stack1.func_190926_b() || stack2.func_190926_b()) {
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
		int mergeCount = Math.min(mergeTarget.getMaxStackSize() - mergeTarget.func_190916_E(),
				mergeSource.func_190916_E());
		if (mergeCount < 1) {
			return 0;
		}
		if (doMerge) {
			mergeTarget.func_190917_f(mergeCount);
		}
		return mergeCount;
	}

	public static ItemStack fillOverflowInventory(IInventory inv, ItemStack stack) {
		if (inv != null) {
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack inside = inv.getStackInSlot(i);
				if (stack.func_190926_b() || stack.func_190916_E() <= 0)
					return ItemStack.field_190927_a;
				if (canStacksMerge(inside, stack)) {
					mergeStacks(stack, inside, true);
					stack.func_190920_e(0);
				}
			}
		}
		return stack;

	}
}
