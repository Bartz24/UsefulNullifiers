package com.bartz24.usefulnullifiers.proxy;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.registry.ModCrafting;
import com.bartz24.usefulnullifiers.registry.ModRenderers;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);

		OBJLoader.INSTANCE.addDomain(References.ModID);

		ModRenderers.preInit();
	}

	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		ModRenderers.init();
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);
	}

}
