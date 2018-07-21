package com.bartz24.usefulnullifiers.events;

import com.bartz24.usefulnullifiers.render.ModelNullifier;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(Side.CLIENT)
public class EventHandlerClient {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onOverflowBakeEvent(ModelBakeEvent event) {
        processBakeEvent(event, ModelNullifier.modelResourceLocation);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onAIONBakeEvent(ModelBakeEvent event) {
        processBakeEvent(event, ModelNullifier.aionModelResourceLocation);
    }

    private static void processBakeEvent(ModelBakeEvent event, ModelResourceLocation location) {
        Object object = event.getModelRegistry().getObject(location);
        if (object != null) {
            IBakedModel existingModel = (IBakedModel) object;
            ModelNullifier elementItemModel = new ModelNullifier(existingModel);
            event.getModelRegistry().putObject(location, elementItemModel);
            ModelNullifier.NullifierOverrideList.INSTANCE.clearCache();
        }
    }
}