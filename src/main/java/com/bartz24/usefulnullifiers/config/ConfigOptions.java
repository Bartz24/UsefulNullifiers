package com.bartz24.usefulnullifiers.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigOptions
{
	public static Configuration config;
	public static boolean enableOverflow;
	public static boolean enablePortVoid;
	public static boolean enableVoid;
	public static boolean enableFluidVoid;
	
	public static List<IConfigElement> getConfigElements()
	{
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		list.addAll(new ConfigElement(
				config.getCategory(Configuration.CATEGORY_GENERAL))
						.getChildElements());

		return list;
	}

	public static void loadConfigThenSave(FMLPreInitializationEvent e)
	{
		config = new Configuration(e.getSuggestedConfigurationFile());

		config.load();
		enableOverflow = config.get(Configuration.CATEGORY_GENERAL, "Enable Portable Overflow Nullifier (recipe)", true)
				.getBoolean(true);
		enablePortVoid = config.get(Configuration.CATEGORY_GENERAL, "Enable Portable Item Nullifier (recipe)", true)
				.getBoolean(true);
		enableVoid = config.get(Configuration.CATEGORY_GENERAL, "Enable Item Nullifier (recipe)", true)
				.getBoolean(true);
		enableFluidVoid = config.get(Configuration.CATEGORY_GENERAL, "Enable Fluid Nullifier (recipe)", true)
				.getBoolean(true);
		config.save();
	}
}
