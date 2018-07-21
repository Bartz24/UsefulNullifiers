package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.config.ConfigOptions;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModCrafting {
	public static void init() {
		if (ConfigOptions.enableOverflow)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModItems.overflowNullifier, 1, 0), new Object[] { " XX",
					" XX", "YZ ", 'X', "cobblestone", 'Y', "stickWood", 'Z', new ItemStack(Items.LAVA_BUCKET) });
		if (ConfigOptions.enablePortFluidVoid)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModItems.fluidVoidNullifier, 1, 0),
					new Object[] { "  X", " YZ", "Y  ", 'X', new ItemStack(Items.ENDER_PEARL), 'Y', "stickWood", 'Z',
							new ItemStack(Items.BUCKET) });
		if (ConfigOptions.enablePortVoid)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModItems.voidNullifier, 1, 0),
					new Object[] { "  X", " Y ", "Y  ", 'X', new ItemStack(Items.LAVA_BUCKET), 'Y', "stickWood" });
		if (ConfigOptions.enablePortAION)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModItems.aionNullifier, 1, 0), new Object[] { " XA",
					" XX", "YZ ", 'X', Blocks.OBSIDIAN, 'Y', "stickWood", 'Z', new ItemStack(Items.LAVA_BUCKET), 'A', Items.ENDER_PEARL });
		if (ConfigOptions.enableVoid)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModBlocks.voidNullifierBlock), new Object[] { "XZX",
					"ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Items.LAVA_BUCKET), 'Z', "cobblestone" });
		if (ConfigOptions.enableFluidVoid)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModBlocks.fluidVoidNullifierBlock), new Object[] { "XZX",
					"ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Items.ENDER_PEARL), 'Z', "cobblestone" });
		if (ConfigOptions.enableEnergyVoid)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModBlocks.energyVoidNullifierBlock), new Object[] { "XZX",
					"ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Blocks.REDSTONE_BLOCK), 'Z', "cobblestone" });
		if (ConfigOptions.enableAION)
			CraftingRegistry.addShapedOreRecipe(new ItemStack(ModBlocks.aionBlock), new Object[] { "XZX",
					"ZYZ", "XZX", 'X', "stone", 'Y', new ItemStack(Blocks.OBSIDIAN), 'Z', "cobblestone" });
	}
}
