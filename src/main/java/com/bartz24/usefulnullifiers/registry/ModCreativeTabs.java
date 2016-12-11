package com.bartz24.usefulnullifiers.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTabs
{
	public static CreativeTabs tabMain = new CreativeTabs(
			"usefulnullifiers.tabMain")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ModItems.overflowNullifier);
		}
	};
}
