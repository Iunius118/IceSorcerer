package iunius.melph.icemagic.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryIceMagicA implements IRenderFactory {

	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderSnowball(manager, Items.snowball, Minecraft.getMinecraft().getRenderItem());
	}

}
