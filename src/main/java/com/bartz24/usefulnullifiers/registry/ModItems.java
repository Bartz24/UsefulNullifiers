package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.items.FluidVoidNullifierItem;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;
import com.bartz24.usefulnullifiers.items.VoidNullifierItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.GameData;

public class ModItems {
	
	
	public static Item overflowNullifier;
	public static Item voidNullifier;
	public static Item fluidVoidNullifier;

	public static void init() {
		overflowNullifier = registerItem(new OverflowNullifierItem("overflowNullifierItem", "overflownullifieritem"));
		voidNullifier = registerItem(new VoidNullifierItem("voidNullifierItem", "voidnullifieritem"));
		fluidVoidNullifier = registerItem(
				new FluidVoidNullifierItem("fluidVoidNullifierItem", "fluidvoidnullifieritem"));
	}
	
	public static Item registerItem(Item item) {
		GameData.register_impl(item);
		return item;
	}
}
