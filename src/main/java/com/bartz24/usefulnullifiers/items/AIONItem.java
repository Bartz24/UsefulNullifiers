package com.bartz24.usefulnullifiers.items;

import com.bartz24.usefulnullifiers.References;
import com.bartz24.usefulnullifiers.UsefulNullifiers;
import com.bartz24.usefulnullifiers.inventory.AIONInventory;
import com.bartz24.usefulnullifiers.registry.ModCreativeTabs;
import com.bartz24.usefulnullifiers.registry.ModGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;

@Mod.EventBusSubscriber
public class AIONItem extends Item {

    private static boolean cancelEquipSound = false;

    public AIONItem(String unlocalizedName, String registryName) {
        this.setUnlocalizedName(References.ModID + "." + unlocalizedName);
        setRegistryName(registryName);
        this.setCreativeTab(ModCreativeTabs.tabMain);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        AIONInventory inv = new AIONInventory(itemStack);
        ItemStack invStack = inv.getStackInSlot(1);

        RayTraceResult rayTrace = this.rayTrace(world, player, true);
        if (rayTrace == null || invStack.isEmpty() || world.isAirBlock(rayTrace.getBlockPos())) {
            if (!world.isRemote) {
                player.openGui(UsefulNullifiers.instance, ModGuiHandler.AIONGUI, world,
                        player.inventory.currentItem, 0, 0);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, itemStack);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getHeldItem(hand);
        AIONInventory inv = new AIONInventory(itemStack);
        ItemStack invStack = inv.getStackInSlot(1);

        if (!invStack.isEmpty()) {

            cancelEquipSound = true;
            player.setHeldItem(hand, invStack);
            EnumActionResult result = invStack.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
            invStack = player.getHeldItem(hand);
            inv.setInventorySlotContents(1, invStack);
            inv.markDirty();
            player.setHeldItem(hand, itemStack);
            cancelEquipSound = false;
            return result;
        } else
            return EnumActionResult.PASS;
    }

    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {

        list.add(TextFormatting.BLUE + "Has function similar to a dev/null/");
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            list.add(TextFormatting.DARK_GREEN
                    + "Destroys items put into it.");
            list.add(TextFormatting.DARK_GREEN
                    + "Places blocks stored and destroys any extra items picked up of the same type.");
            list.add(TextFormatting.DARK_GREEN
                    + "Empties fluid containers put into it.");
        } else
            list.add(TextFormatting.DARK_GREEN + "Hold LSHIFT for description.");
    }

    public String getItemStackDisplayName(ItemStack stack) {
        AIONInventory inv = new AIONInventory(stack);
        String name = TextFormatting.GREEN + (inv.getStackInSlot(1).isEmpty() ? "None"
                : inv.getStackInSlot(1).getDisplayName() + " x " + inv.getStackInSlot(1).getCount());
        return super.getItemStackDisplayName(stack) + " (" + name + TextFormatting.WHITE + ")";
    }

    @SubscribeEvent
    public static void cancelSound(PlaySoundAtEntityEvent event) {
        if (cancelEquipSound && event.getSound() == SoundEvents.ITEM_ARMOR_EQUIP_GENERIC)
            event.setCanceled(true);
    }
}
