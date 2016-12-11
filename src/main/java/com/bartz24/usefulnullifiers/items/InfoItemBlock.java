package com.bartz24.usefulnullifiers.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class InfoItemBlock extends ItemBlock{

	String noShiftInfo;
	String shiftInfo;

	public InfoItemBlock(Block block, String noShiftingInfo, String shiftingInfo) {
		super(block);
		noShiftInfo = noShiftingInfo;
		shiftInfo = shiftingInfo;
	}
	
	public InfoItemBlock(Block block, String info) {
		super(block);
		noShiftInfo = shiftInfo = info;
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player,
			List list, boolean par4)
	{		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add(shiftInfo);			
		}
		else
			list.add(noShiftInfo);
	}
}
