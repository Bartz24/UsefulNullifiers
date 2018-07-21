package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.inventory.*;
import com.bartz24.usefulnullifiers.items.AIONItem;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler
{
	public static final int OverflowGUI = 0;
	public static final int VoidGUI = 1;
	public static final int FluidVoidGUI = 2;
	public static final int AIONGUI = 3;

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		if (id == OverflowGUI)
			return new OverflowContainer(player, new OverflowInventory(getOverflowStack(player)), x);
		else if (id == VoidGUI)
			return new VoidContainer(player, new VoidInventory(), x);
		else if (id == FluidVoidGUI)
			return new FluidVoidContainer(player, new FluidVoidInventory(), x);
		else if (id == AIONGUI)
			return new AIONContainer(player, new AIONInventory(getAIONStack(player)), x);
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z)
	{
		if (id == OverflowGUI)
			return new OverflowGui(player, new OverflowInventory(getOverflowStack(player)));
		else if (id == VoidGUI)
			return new VoidGui(player, new VoidInventory());
		else if (id == FluidVoidGUI)
			return new FluidVoidGui(player, new FluidVoidInventory());
		else if (id == AIONGUI)
			return new AIONGui(player, new AIONInventory(getAIONStack(player)));

		return null;
	}

	static ItemStack getOverflowStack(EntityPlayer p) {
        if (!p.getHeldItemMainhand().isEmpty()
                && p.getHeldItemMainhand().getItem() instanceof OverflowNullifierItem) {
            return p.getHeldItemMainhand();
        } else {
            return p.getHeldItemOffhand();
        }
    }

	static ItemStack getAIONStack(EntityPlayer p) {
		if (!p.getHeldItemMainhand().isEmpty()
				&& p.getHeldItemMainhand().getItem() instanceof AIONItem) {
			return p.getHeldItemMainhand();
		} else {
			return p.getHeldItemOffhand();
		}
	}
}
