package com.bartz24.usefulnullifiers.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting
{
	public static void init()
	{
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.overflowNullifier, 1, 0), new Object[]
				{ " XX", " XX", "YZ ", 'X', "cobblestone", 'Y', "stickWood", 'Z', new ItemStack(Items.LAVA_BUCKET) }));
	}
}
