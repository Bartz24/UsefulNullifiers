package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.items.FluidVoidNullifierItem;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;
import com.bartz24.usefulnullifiers.items.VoidNullifierItem;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item overflowNullifier;
	public static Item voidNullifier;
	public static Item fluidVoidNullifier;

	public static void init()
	{
		overflowNullifier = registerItem(new OverflowNullifierItem(
				"overflowNullifierItem", "overflownullifieritem"));
		voidNullifier = registerItem(new VoidNullifierItem(
				"voidNullifierItem", "voidnullifieritem"));
		fluidVoidNullifier = registerItem(new FluidVoidNullifierItem(
				"fluidVoidNullifierItem", "fluidvoidnullifieritem"));
	}

	private static Item registerItem(Item item, String name)
	{
		GameRegistry.register(item, new ResourceLocation(References.ModID, name));

		return item;
	}

	private static Item registerItem(Item item)
	{
		GameRegistry.register(item);

		return item;
	}
}
