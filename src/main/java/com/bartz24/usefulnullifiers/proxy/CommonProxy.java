package com.bartz24.usefulnullifiers.proxy;

import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.events.EventHandler;
import com.bartz24.usefulnullifiers.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		ModItems.init();
		ModBlocks.init();
	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(UsefulNullifiers.instance, new ModGuiHandler());

		MinecraftForge.EVENT_BUS.register(new EventHandler());

		ModEntities.init();
		ModCrafting.init();
	}

	public void postInit(FMLPostInitializationEvent e) {
	}
}
