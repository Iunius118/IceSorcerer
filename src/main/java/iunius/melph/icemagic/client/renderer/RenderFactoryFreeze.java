package iunius.melph.icemagic.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFactoryFreeze implements IRenderFactory {

	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderFreeze(manager);
	}

	public class RenderFreeze extends Render {

		protected RenderFreeze(RenderManager renderManager) {
			super(renderManager);
		}

		public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);
			GlStateManager.enableRescaleNormal();
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			this.bindTexture(TextureMap.locationBlocksTexture);

			if (this.renderOutlines) {
				GlStateManager.enableColorMaterial();
				GlStateManager.enableOutlineMode(this.getTeamColor(entity));
			}

			Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(Item.getItemFromBlock(Blocks.ice)), ItemCameraTransforms.TransformType.NONE);

			if (this.renderOutlines) {
				GlStateManager.disableOutlineMode();
				GlStateManager.disableColorMaterial();
			}

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			super.doRender(entity, x, y, z, entityYaw, partialTicks);
		}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			return TextureMap.locationBlocksTexture;
		}

	}

}
