package com.bartz24.usefulnullifiers.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.registry.ModCreativeTabs;
import com.bartz24.usefulnullifiers.tiles.FluidVoidNullifierTile;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class FluidVoidNullifierBlock extends BlockContainer
{

	public FluidVoidNullifierBlock(Material material, String unlocalizedName,
			String registryName, float hardness, float resistance,
			SoundType stepSound)
	{
		super(material);
		this.setUnlocalizedName(References.ModID + "." + unlocalizedName);
		this.setCreativeTab(ModCreativeTabs.tabMain);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setRegistryName(registryName);
		this.setSoundType(stepSound);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new FluidVoidNullifierTile();
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

	public void addInformation(ItemStack stack, EntityPlayer player,
			List list, boolean par4)
	{
		OverflowInventory inv = new OverflowInventory(stack);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.add(TextFormatting.DARK_GREEN + "Destroys fluids put into it. Cannot be interacted with, has to be given fluids.");			
		}
		else
			list.add(TextFormatting.DARK_GREEN + "Hold LSHIFT for description.");
	}
}
