package iunius.melph.icemagic.client;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.model.TRSRTransformation;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

public class MultiBakedItemModel implements IPerspectiveAwareModel {

	public IBakedModel modelGUI;
	public IBakedModel modelWeapon;

	public MultiBakedItemModel(IBakedModel modelGUIIn, IBakedModel modelWeaponIn) {
		modelGUI = modelGUIIn;
		modelWeapon = modelWeaponIn;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		if (side != null) {
			return Collections.EMPTY_LIST;
		}

		GlStateManager.popMatrix();

		List<BakedQuad> quads = modelWeapon.getQuads(state, side, rand);
		int size = quads.size();
		VertexBuffer renderer = Tessellator.getInstance().getBuffer();

		GlStateManager.translate(0.0F, -0.5F, 0.0F);

		for (int i = 0; i < size; ++i) {
			LightUtil.renderQuadColor(renderer, quads.get(i), -1);
		}

		Tessellator.getInstance().draw();

		GlStateManager.pushMatrix();

		renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		return Collections.EMPTY_LIST;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return modelGUI.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return null;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return modelGUI.getItemCameraTransforms();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(
			TransformType cameraTransformType) {
		Matrix4f matrix;

		if (modelGUI != null && modelGUI instanceof IPerspectiveAwareModel) {
			matrix = ((IPerspectiveAwareModel) modelGUI).handlePerspective(cameraTransformType).getValue();
		} else {
			matrix = TRSRTransformation.identity().getMatrix();
		}

		switch (cameraTransformType) {
		case THIRD_PERSON_LEFT_HAND:
		case THIRD_PERSON_RIGHT_HAND:
		case FIRST_PERSON_LEFT_HAND:
		case FIRST_PERSON_RIGHT_HAND:
			return Pair.of(this, matrix);

		default:
			return Pair.of(modelGUI, matrix);
		}
	}

}
