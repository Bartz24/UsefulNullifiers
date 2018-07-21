package com.bartz24.usefulnullifiers.config;

import com.bartz24.usefulnullifiers.References;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = References.ModID)
@Mod.EventBusSubscriber
public class ConfigOptions
{
	@Config.Comment("Add the recipe for the Portable Overflow Nullifier")
	public static boolean enableOverflow = true;
	@Config.Comment("Add the recipe for the Portable Item Nullifier")
	public static boolean enablePortVoid = true;
	@Config.Comment("Add the recipe for the Portable Fluid Nullifier")
	public static boolean enablePortFluidVoid = true;
	@Config.Comment("Add the recipe for the Portable AION")
	public static boolean enablePortAION = true;
	@Config.Comment("Add the recipe for the Item Nullifier")
	public static boolean enableVoid = true;
	@Config.Comment("Add the recipe for the Fluid Nullifier")
	public static boolean enableFluidVoid = true;
	@Config.Comment("Add the recipe for the Energy Nullifier")
	public static boolean enableEnergyVoid = true;
	@Config.Comment("Add the recipe for the AION")
	public static boolean enableAION = true;

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(References.ModID)) {
			ConfigManager.sync(References.ModID, Config.Type.INSTANCE);
		}
	}
}
