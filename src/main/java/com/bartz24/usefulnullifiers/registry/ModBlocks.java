package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.blocks.EnergyVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.FluidVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.VoidNullifierBlock;

import mcjty.lib.compat.CompatItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block voidNullifierBlock;
	public static Block fluidVoidNullifierBlock;
	public static Block energyVoidNullifierBlock;

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
	}

	public static Block registerBlock(Block block, String name) {
		GameRegistry.register(block, new ResourceLocation(References.ModID, name));
		GameRegistry.register(new CompatItemBlock(block).setRegistryName(new ResourceLocation(References.ModID, name)));

		return block;
	}

	public static Block registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new CompatItemBlock(block).setRegistryName(block.getRegistryName()));

		return block;
	}

	/*
	 * public static void registerItemBlock(Block block) {
	 * GameRegistry.register(block); GameRegistry.register(new
	 * ItemBlockMeta(block) .setRegistryName(block.getRegistryName()));
	 * 
	 * }
	 */
}
