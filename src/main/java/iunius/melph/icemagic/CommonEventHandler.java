package iunius.melph.icemagic;

import iunius.melph.icemagic.entity.EntityFreeze;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHandler {

	@SubscribeEvent
	public void onEntityMountEvent(EntityMountEvent event) {
		if (event.getEntityBeingMounted() instanceof EntityFreeze && event.isDismounting()) {
			event.setCanceled(true);
		}

	}

}
