package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.config.ConfigOptions;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting
{
	public static void init()
	{
		if (ConfigOptions.enableOverflow)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModItems.overflowNullifier, 1, 0),
					new Object[]
					{ " XX", " XX", "YZ ", 'X', "cobblestone", 'Y', "stickWood", 'Z', new ItemStack(Items.LAVA_BUCKET) }));
		if (ConfigOptions.enablePortVoid)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModItems.fluidVoidNullifier, 1, 0),
					new Object[]
					{ "  X", " YZ", "Y  ", 'X', new ItemStack(Items.ENDER_PEARL), 'Y', "stickWood", 'Z', new ItemStack(Items.BUCKET) }));
		if (ConfigOptions.enablePortVoid)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModItems.voidNullifier, 1, 0), new Object[]
					{ "  X", " Y ", "Y  ", 'X', new ItemStack(Items.LAVA_BUCKET), 'Y', "stickWood" }));
		if (ConfigOptions.enableVoid)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.voidNullifierBlock), new Object[]
					{ "XZX", "ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Items.LAVA_BUCKET), 'Z', "cobblestone" }));
		if (ConfigOptions.enableFluidVoid)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.fluidVoidNullifierBlock),
					new Object[]
					{ "XZX", "ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Items.ENDER_PEARL), 'Z', "cobblestone" }));
		if (ConfigOptions.enableEnergyVoid)
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(ModBlocks.energyVoidNullifierBlock),
					new Object[]
					{ "XZX", "ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Blocks.REDSTONE_BLOCK), 'Z', "cobblestone" }));
	}
}
