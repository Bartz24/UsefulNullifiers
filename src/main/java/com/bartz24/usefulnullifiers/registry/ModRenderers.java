package com.bartz24.usefulnullifiers.registry;

import com.bartz24.usefulnullifiers.render.ModelNullifier;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModRenderers {
    public static void preInit() {
        registerItemRenderer(ModItems.voidNullifier);
        registerItemRenderer(ModItems.fluidVoidNullifier);
        registerItemRenderer(Item.getItemFromBlock(ModBlocks.voidNullifierBlock));
        registerItemRenderer(Item.getItemFromBlock(ModBlocks.fluidVoidNullifierBlock));
        registerItemRenderer(Item.getItemFromBlock(ModBlocks.energyVoidNullifierBlock));
        registerItemRenderer(Item.getItemFromBlock(ModBlocks.aionBlock));

        ModelLoader.setCustomModelResourceLocation(ModItems.overflowNullifier, 0, ModelNullifier.modelResourceLocation);
        ModelLoader.setCustomModelResourceLocation(ModItems.aionNullifier, 0, ModelNullifier.aionModelResourceLocation);

    }

    public static void init() {
    }

    public static void registerItemRenderer(Item item, int meta,
                                            ResourceLocation name) {
        ModelBakery.registerItemVariants(item, name);
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(name, "inventory"));
    }

    public static void registerItemRenderer(Item item, int meta) {
        registerItemRenderer(item, meta,
                new ResourceLocation(item.getRegistryName().toString() + meta));
    }

    public static void registerItemRenderer(Item item, int meta, boolean global) {
        if (!global)
            registerItemRenderer(item, meta);
        else
            registerItemRenderer(item, meta, item.getRegistryName());
    }

    public static void registerItemRenderer(Item item) {
        registerItemRenderer(item, item.getRegistryName());
    }

    public static void registerItemRenderer(Item item, ResourceLocation name) {
        registerItemRenderer(item, 0, name);
    }

    public static void registerBlockRenderer(Block block, IStateMapper mapper,
                                             int... metadata) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .getModelManager().getBlockModelShapes()
                .registerBlockWithStateMapper(block, mapper);
    }

    private static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted(
            Block b, Class<T> enumclazz, String variantHeader) {
        Item item = Item.getItemFromBlock(b);
        for (T e : enumclazz.getEnumConstants()) {
            String variantName = variantHeader + "="
                    + e.getName().toLowerCase();
            ModelLoader.setCustomModelResourceLocation(item, e.ordinal(),
                    new ModelResourceLocation(b.getRegistryName(),
                            variantName));
        }
    }
}
