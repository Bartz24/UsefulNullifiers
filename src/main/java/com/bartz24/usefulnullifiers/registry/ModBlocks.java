package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.blocks.EnergyVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.FluidVoidNullifierBlock;
import com.bartz24.usefulnullifiers.blocks.VoidNullifierBlock;
import com.bartz24.usefulnullifiers.items.InfoItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block voidNullifierBlock;
	public static Block fluidVoidNullifierBlock;
	public static Block energyVoidNullifierBlock;

	public static void init() {
		voidNullifierBlock = registerInfoBlock(
				new VoidNullifierBlock(Material.ROCK, "voidNullifierBlock", "voidnullifierblock", 6F, 6F,
						SoundType.STONE),
				TextFormatting.DARK_GREEN + "Hold LSHIFT for description.", TextFormatting.DARK_GREEN
						+ "Destroys items put into it. Cannot be interacted with, has to be given items.");
		fluidVoidNullifierBlock = registerInfoBlock(
				new FluidVoidNullifierBlock(Material.ROCK, "fluidVoidNullifierBlock", "fluidvoidnullifierblock", 6F, 6F,
						SoundType.STONE),
				TextFormatting.DARK_GREEN + "Hold LSHIFT for description.", TextFormatting.DARK_GREEN
						+ "Destroys fluids put into it. Cannot be interacted with, has to be given fluids.");
		energyVoidNullifierBlock = registerInfoBlock(
				new EnergyVoidNullifierBlock(Material.ROCK, "energyVoidNullifierBlock", "energyvoidnullifierblock", 6F,
						6F, SoundType.STONE),
				TextFormatting.DARK_GREEN + "Hold LSHIFT for description.",
				TextFormatting.DARK_GREEN + "Destroys Forge Energy put into it. Cannot be interacted with.");
	}

	public static Block registerBlock(Block block, String name) {
		GameRegistry.register(block, new ResourceLocation(References.ModID, name));
		GameRegistry.register(new ItemBlock(block).setRegistryName(new ResourceLocation(References.ModID, name)));

		return block;
	}

	public static Block registerBlock(Block block) {
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));

		return block;
	}

	public static Block registerInfoBlock(Block block, String noShiftInfo, String shiftInfo) {
		GameRegistry.register(block);
		GameRegistry
				.register(new InfoItemBlock(block, noShiftInfo, shiftInfo).setRegistryName(block.getRegistryName()));

		return block;
	}

	public static Block registerInfoBlock(Block block, String info) {
		GameRegistry.register(block);
		GameRegistry.register(new InfoItemBlock(block, info).setRegistryName(block.getRegistryName()));

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
