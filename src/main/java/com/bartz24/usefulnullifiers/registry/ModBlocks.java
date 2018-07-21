package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.blocks.AIONBlock;
import com.bartz24.usefulnullifiers.blocks.EnergyVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.FluidVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.VoidNullifierBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.GameData;

public class ModBlocks {
	public static Block voidNullifierBlock;
	public static Block fluidVoidNullifierBlock;
	public static Block energyVoidNullifierBlock;
	public static Block aionBlock;

	public static void init() {
		voidNullifierBlock = registerBlock(
				new VoidNullifierBlock(Material.ROCK, "voidNullifierBlock", "voidnullifierblock", 6F, 6F,
						SoundType.STONE));
		fluidVoidNullifierBlock = registerBlock(
				new FluidVoidNullifierBlock(Material.ROCK, "fluidVoidNullifierBlock", "fluidvoidnullifierblock", 6F, 6F,
						SoundType.STONE));
		energyVoidNullifierBlock = registerBlock(
				new EnergyVoidNullifierBlock(Material.ROCK, "energyVoidNullifierBlock", "energyvoidnullifierblock", 6F,
						6F, SoundType.STONE));
		aionBlock = registerBlock(
				new AIONBlock(Material.ROCK, "aion", "aion", 6F,
						6F, SoundType.STONE));
	}

	public static Block registerBlock(Block block) {
		GameData.register_impl(block);
		GameData.register_impl(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		return block;
	}
}
