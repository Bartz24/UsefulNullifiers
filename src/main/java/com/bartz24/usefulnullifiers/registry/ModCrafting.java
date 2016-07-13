package com.bartz24.usefulnullifiers.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModItems.overflowNullifier, 1, 0), new Object[]
				{ " XX", " XX", "YZ ", 'X', "cobblestone", 'Y', "stickWood", 'Z', new ItemStack(Items.LAVA_BUCKET) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModItems.voidNullifier, 1, 0), new Object[]
				{ "  X", " Y ", "Y  ", 'X', new ItemStack(Items.LAVA_BUCKET), 'Y', "stickWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.voidNullifierBlock), new Object[]
				{ "XXX", "XYX", "XXX", 'X', "stone", 'Y', new ItemStack(Items.LAVA_BUCKET) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(ModBlocks.fluidVoidNullifierBlock), new Object[]
				{ "XXX", "XYX", "XXX", 'X', "stone", 'Y', new ItemStack(Items.ENDER_PEARL) }));
	}
}
