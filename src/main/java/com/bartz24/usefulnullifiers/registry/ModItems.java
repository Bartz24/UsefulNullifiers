package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item overflowNullifier;

	public static void init()
	{
		overflowNullifier = registerItem(new OverflowNullifierItem(
				"overflowNullifier", "OverflowNullifier"));
	}

	private static Item registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name);

		return item;
	}

	private static Item registerItem(Item item)
	{
		if (item.getRegistryName() == null)
		{
			UsefulNullifiers.logger.error(
					"Item {} doesn't have a registry name. Item will not be registered.",
					item.getClass().getCanonicalName());
			return item;
		}
		GameRegistry.register(item);

		return item;
	}
}
