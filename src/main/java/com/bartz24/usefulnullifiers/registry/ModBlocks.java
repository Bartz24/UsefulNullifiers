package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.blocks.FluidVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.VoidNullifierBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static Block voidNullifierBlock;
	public static Block fluidVoidNullifierBlock;
	
	public static void init()
	{
		voidNullifierBlock = registerBlock(new VoidNullifierBlock(Material.ROCK,
				"voidNullifierBlock", "VoidNullifierBlock", 6F, 6F, SoundType.STONE));
		fluidVoidNullifierBlock = registerBlock(new FluidVoidNullifierBlock(Material.ROCK,
				"fluidVoidNullifierBlock", "FluidVoidNullifierBlock", 6F, 6F, SoundType.STONE));
	}
	
	public static Block registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block, name);

		return block;
	}

	public static Block registerBlock(Block block)
	{
		if (block.getRegistryName() == null)
		{
			UsefulNullifiers.logger.error(
					"Block {} doesn't have a registry name. Block will not be registered.",
					block.getClass().getCanonicalName());
			return block;
		}
		GameRegistry.registerBlock(block);

		return block;
	}

	/*public static void registerItemBlock(Block block)
	{
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlockMeta(block)
				.setRegistryName(block.getRegistryName()));

	}*/
}
