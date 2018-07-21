package com.bartz24.usefulnullifiers.render;

import com.bartz24.usefulnullifiers.inventory.AIONInventory;
import com.bartz24.usefulnullifiers.inventory.OverflowInventory;
import com.bartz24.usefulnullifiers.items.AIONItem;
import com.bartz24.usefulnullifiers.items.OverflowNullifierItem;
import com.bartz24.usefulnullifiers.registry.ModItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.SimpleModelFontRenderer;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelNullifier implements IBakedModel {

    private static final ResourceLocation font = new ResourceLocation("minecraft", "textures/font/ascii.png");
    private static final ResourceLocation font2 = new ResourceLocation("minecraft", "font/ascii");
    private IBakedModel baseNullifierModel;
    public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(
            ModItems.overflowNullifier.getRegistryName().toString());
    public static final ModelResourceLocation aionModelResourceLocation = new ModelResourceLocation(
            ModItems.aionNullifier.getRegistryName().toString());

    public ModelNullifier(IBakedModel parentModel) {
        this.baseNullifierModel = parentModel;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        return baseNullifierModel.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return baseNullifierModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return baseNullifierModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return baseNullifierModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return baseNullifierModel.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return baseNullifierModel.getItemCameraTransforms();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return NullifierOverrideList.INSTANCE;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        if (baseNullifierModel instanceof IBakedModel) {
            Matrix4f matrix4f = ((IBakedModel) baseNullifierModel).handlePerspective(cameraTransformType)
                    .getRight();
            return Pair.of(this, matrix4f);
        }
        ItemCameraTransforms itemCameraTransforms = baseNullifierModel.getItemCameraTransforms();
        ItemTransformVec3f itemTransformVec3f = itemCameraTransforms.getTransform(cameraTransformType);
        TRSRTransformation tr = new TRSRTransformation(itemTransformVec3f);
        Matrix4f mat = null;
        if (tr != null) {
            mat = tr.getMatrix();
        }
        return Pair.of(this, mat);
    }

    private static BakedModelNullifier rebake(ModelNullifier model, ItemStack stack) {
        TextureAtlasSprite fontSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(font2.toString());
        List<BakedQuad> textQuads = new ArrayList<BakedQuad>();
        if (stack.isEmpty())
            return new BakedModelNullifier(model, textQuads);

        Matrix4f m = new Matrix4f();

        //No clue what these next lines do, besides the first, which is font size.
        m.m20 = 1f / 16f;
        m.m01 = m.m12 = -m.m20;
        m.m33 = 1;

        Matrix3f rotation = new Matrix3f();
        m.getRotationScale(rotation);

        //Z rot      //I have no fucking clue how these work lmao
        Matrix3f angleZ = new Matrix3f();
        angleZ.rotZ(-1.5708f); //in radians.
        rotation.mul(rotation, angleZ);

        //X rot
        Matrix3f angleX = new Matrix3f();
        angleX.rotX(1.5708f); //in radians.
        rotation.mul(rotation, angleX);

        m.setRotationScale(rotation); //Apply rots
        m.setScale(0.05f);

        m.setTranslation(new Vector3f(1f, 0.40f, 0.6f)); //Translate text


        SimpleModelFontRenderer fontRenderer = new SimpleModelFontRenderer(Minecraft.getMinecraft().gameSettings, font, Minecraft.getMinecraft().getTextureManager(), false, m, DefaultVertexFormats.ITEM) {
            @Override
            protected float renderUnicodeChar(char c, boolean italic) {
                return super.renderDefaultChar(0, italic);
            }
        };

        fontRenderer.setSprite(fontSprite);
        fontRenderer.setFillBlanks(false);

        fontRenderer.drawStringWithShadow(Integer.toString(stack.getCount()), -fontRenderer.getStringWidth(Integer.toString(stack.getCount())), 0, 0xEEEEEE);

        textQuads.addAll(fontRenderer.build());


       /* m.setTranslation(new Vector3f(0.8f, 0.90f, 1f));
        m.setScale(.01f);
        IBakedModel itemModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
        itemModel = itemModel.handlePerspective(ItemCameraTransforms.TransformType.GUI).getKey();

        textQuads.addAll(itemModel.getQuads(null, EnumFacing.SOUTH, fontRenderer.fontRandom.nextLong()));
*/

        return new BakedModelNullifier(model, textQuads);
    }

    public static final class NullifierOverrideList extends ItemOverrideList {
        public static final NullifierOverrideList INSTANCE = new NullifierOverrideList();

        private final Map<String, IBakedModel> cache;

        public NullifierOverrideList() {
            super(ImmutableList.<ItemOverride>of());
            this.cache = new HashMap<String, IBakedModel>();
        }

        public void clearCache() {
            this.cache.clear();
        }

        @Override
        @Nonnull
        public IBakedModel handleItemState(@Nonnull IBakedModel originalModel, @Nonnull ItemStack stack,
                                           @Nullable World world, @Nullable EntityLivingBase entity) {

            String name = stack.getDisplayName();

            if (!cache.containsKey(name)) {
                ModelNullifier model = (ModelNullifier) originalModel;
                TextureAtlasSprite fontSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(font2.toString());
                ItemStack inside = ItemStack.EMPTY;
                if (stack.getItem() instanceof OverflowNullifierItem)
                    inside = new OverflowInventory(stack).getStackInSlot(0);
                else if (stack.getItem() instanceof AIONItem)
                    inside = new AIONInventory(stack).getStackInSlot(1);

                BakedModelNullifier bakedBakedModel = rebake(model, inside);
                cache.put(name, bakedBakedModel);

                return bakedBakedModel;
            }
            return cache.get(name);
        }

    }

}