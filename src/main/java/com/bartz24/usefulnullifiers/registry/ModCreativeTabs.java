package com.bartz24.usefulnullifiers.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
	public static CreativeTabs tabMain = new CreativeTabs("usefulnullifiers.tabMain") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.overflowNullifier);
		}
	};
}
