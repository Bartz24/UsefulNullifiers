package com.bartz24.usefulnullifiers;

import org.apache.logging.log4j.Logger;

import com.bartz24.usefulnullifiers.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = References.ModID, name = References.ModName, useMetadata = true)
public class UsefulNullifiers
{
	@SidedProxy(clientSide = "com.bartz24.usefulnullifiers.proxy.ClientProxy", serverSide = "com.bartz24.usefulnullifiers.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static UsefulNullifiers instance;

	public static Logger logger;

	@Mod.EventHandler
	public void serverLoading(FMLServerStartingEvent event)
	{
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}

	public UsefulNullifiers()
	{
	}
}
