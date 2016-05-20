package iunius.melph.icemagic;

import iunius.melph.icemagic.client.ClientEventHandler;
import iunius.melph.icemagic.item.ItemIceMagicA;
import iunius.melph.icemagic.item.ItemIceMagicB;
import iunius.melph.icemagic.item.ItemIceMagicC;
import iunius.melph.icemagic.item.ItemIceMagicD;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = IceSorcererCore.MOD_ID,
	name = IceSorcererCore.MOD_NAME,
	version = IceSorcererCore.MOD_VERSION,
	dependencies = IceSorcererCore.MOD_DEPENDENCIES,
	acceptedMinecraftVersions = IceSorcererCore.MOD_ACCEPTED_MC_VERSIONS,
	useMetadata = true)
public class IceSorcererCore {
	public static final String MOD_ID = "icesorcerer";
	public static final String MOD_NAME = "IceSorcerer";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_DEPENDENCIES = "required-after:Forge@[1.9-12.16.1.1887,)";
	public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.9]";

	@Mod.Instance(MOD_ID)
	public static IceSorcererCore INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
		IceSorcererRegistry.registerItems();
		IceSorcererRegistry.resisterEntities();

		if (event.getSide().isClient()) {
			OBJLoader.INSTANCE.addDomain(MOD_ID);
			MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
			IceSorcererRegistry.registerItemModels();
			IceSorcererRegistry.registerRenderers();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {


		if (event.getSide().isClient()) {

		}
	}

	public static class Items {
		public static final String NAME_ITEM_ICE_MAGIC_A = "ice_magic_a";
		public static final String NAME_ITEM_ICE_MAGIC_B = "ice_magic_b";
		public static final String NAME_ITEM_ICE_MAGIC_C = "ice_magic_c";
		public static final String NAME_ITEM_ICE_MAGIC_D = "ice_magic_d";

		public static Item itemIceMagicA = new ItemIceMagicA().setRegistryName(NAME_ITEM_ICE_MAGIC_A).setUnlocalizedName(NAME_ITEM_ICE_MAGIC_A);
		public static Item itemIceMagicB = new ItemIceMagicB().setRegistryName(NAME_ITEM_ICE_MAGIC_B).setUnlocalizedName(NAME_ITEM_ICE_MAGIC_B);
		public static Item itemIceMagicC = new ItemIceMagicC().setRegistryName(NAME_ITEM_ICE_MAGIC_C).setUnlocalizedName(NAME_ITEM_ICE_MAGIC_C);
		public static Item itemIceMagicD = new ItemIceMagicD().setRegistryName(NAME_ITEM_ICE_MAGIC_D).setUnlocalizedName(NAME_ITEM_ICE_MAGIC_D);
	}

	@SideOnly(Side.CLIENT)
	public static class ModelLocations {
		public static ModelResourceLocation modelItemIceMagicA = new ModelResourceLocation(Items.itemIceMagicA.getRegistryName(), "inventory");
		public static ModelResourceLocation modelItemIceMagicB = new ModelResourceLocation(Items.itemIceMagicB.getRegistryName(), "inventory");
		public static ModelResourceLocation modelItemIceMagicC = new ModelResourceLocation(Items.itemIceMagicC.getRegistryName(), "inventory");
		public static ModelResourceLocation modelItemIceMagicD = new ModelResourceLocation(Items.itemIceMagicD.getRegistryName(), "inventory");
	}

}
