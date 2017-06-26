package com.bartz24.usefulnullifiers.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.registry.ModCreativeTabs;
import com.bartz24.usefulnullifiers.registry.ModGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class FluidVoidNullifierItem extends Item
{

	public FluidVoidNullifierItem(String unlocalizedName, String registryName)
	{
		this.setUnlocalizedName(References.ModID + "." + unlocalizedName);
		setRegistryName(registryName);
		this.setCreativeTab(ModCreativeTabs.tabMain);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if (!world.isRemote)
		{
			player.openGui(UsefulNullifiers.instance, ModGuiHandler.FluidVoidGUI,
					world, player.inventory.currentItem, 0, 0);
		}
		return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	public void addInformation(ItemStack stack, EntityPlayer player,
			List list, boolean par4)
	{
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add(TextFormatting.DARK_GREEN + "Empties fluid containers put into it.");			
		}
		else
			list.add(TextFormatting.DARK_GREEN + "Hold LSHIFT for description.");
	}
}
