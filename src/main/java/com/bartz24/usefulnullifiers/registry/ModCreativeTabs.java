package com.bartz24.usefulnullifiers.registry;

import mcjty.lib.compat.CompatCreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTabs
{
	public static CompatCreativeTabs tabMain = new CompatCreativeTabs(
			"usefulnullifiers.tabMain")
	{
		@Override
		public Item getItem()
		{
			return ModItems.overflowNullifier;
		}
	};
}
