package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.tiles.FluidVoidNullifierTile;
import com.bartz24.usefulnullifiers.tiles.VoidNullifierTile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModEntities
{

	public static void init()
	{
		GameRegistry.registerTileEntity(VoidNullifierTile.class, "voidNullifierTile");
		GameRegistry.registerTileEntity(FluidVoidNullifierTile.class, "fluidVoidNullifierTile");

	}
}
