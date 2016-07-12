package com.bartz24.usefulnullifiers.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTabs
{
	public static CreativeTabs tabMain = new CreativeTabs(
			"usefulnullifiers.tabMain")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return ModItems.overflowNullifier;
		}
	};
}
