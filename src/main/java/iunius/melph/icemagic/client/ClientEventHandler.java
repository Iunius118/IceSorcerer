package iunius.melph.icemagic.client;

import iunius.melph.icemagic.IceSorcererRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEventHandler {

	@SubscribeEvent
	public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
		IceSorcererRegistry.registerTextures(event.getMap());
	}

	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent event) {
		IceSorcererRegistry.registerBakedModels(event);
	}

}
