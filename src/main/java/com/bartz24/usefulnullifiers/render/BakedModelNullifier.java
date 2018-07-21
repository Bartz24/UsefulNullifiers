package com.bartz24.usefulnullifiers.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.List;

public class BakedModelNullifier implements IBakedModel {

    private final ModelNullifier parentModel;
    private final List<BakedQuad> textQuads;

    public BakedModelNullifier(ModelNullifier parent, List<BakedQuad> textQuads) {
        this.parentModel = parent;
        this.textQuads = textQuads;
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        List<BakedQuad> combinedQuadsList = new ArrayList(parentModel.getQuads(state, side, rand));
        combinedQuadsList.addAll(textQuads);
        return combinedQuadsList;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return parentModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return parentModel.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return parentModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return parentModel.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return parentModel.getItemCameraTransforms();
    }

    @Override
    public ItemOverrideList getOverrides() {
        throw new UnsupportedOperationException("The finalized model does not have an override list.");
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        if (parentModel instanceof IBakedModel) {
            Pair<? extends IBakedModel, Matrix4f> matrix4f = ((IBakedModel) parentModel).handlePerspective(cameraTransformType);
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, matrix4f.getRight());
            else
                return matrix4f;
        }
        ItemCameraTransforms itemCameraTransforms = parentModel.getItemCameraTransforms();
        ItemTransformVec3f itemTransformVec3f = itemCameraTransforms.getTransform(cameraTransformType);
        TRSRTransformation tr = new TRSRTransformation(itemTransformVec3f);
        Matrix4f mat = null;
        if (tr != null) {
            mat = tr.getMatrix();
        }
        return Pair.of(this, mat);
    }

}