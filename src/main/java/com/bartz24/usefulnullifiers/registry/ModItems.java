package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.items.FluidVoidNullifierItem;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;
import com.bartz24.usefulnullifiers.items.VoidNullifierItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item overflowNullifier;
	public static Item voidNullifier;
	public static Item fluidVoidNullifier;

	public static void init()
	{
		overflowNullifier = registerItem(new OverflowNullifierItem(
				"overflowNullifierItem", "OverflowNullifierItem"));
		voidNullifier = registerItem(new VoidNullifierItem(
				"voidNullifierItem", "VoidNullifierItem"));
		fluidVoidNullifier = registerItem(new FluidVoidNullifierItem(
				"fluidVoidNullifierItem", "FluidVoidNullifierItem"));
	}

	private static Item registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name);

		return item;
	}

	private static Item registerItem(Item item)
	{
		if (item.getRegistryName() == null)
		{
			UsefulNullifiers.logger.error(
					"Item {} doesn't have a registry name. Item will not be registered.",
					item.getClass().getCanonicalName());
			return item;
		}
		GameRegistry.register(item);

		return item;
	}
}
