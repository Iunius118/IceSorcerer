package iunius.melph.icemagic;

import iunius.melph.icemagic.IceSorcererCore.ModelLocations;
import iunius.melph.icemagic.client.MultiBakedItemModel;
import iunius.melph.icemagic.client.renderer.RenderFactoryFreeze;
import iunius.melph.icemagic.client.renderer.RenderFactoryIceMagicA;
import iunius.melph.icemagic.entity.EntityFreeze;
import iunius.melph.icemagic.entity.EntityIceMagicA;
import iunius.melph.icemagic.entity.EntityIceMagicB;
import iunius.melph.icemagic.entity.EntityIceMagicD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Function;

public class IceSorcererRegistry {
	public static void resisterEntities() {
		EntityRegistry.registerModEntity(EntityFreeze.class, "entity_freeze", EntityID.FREEZE.ordinal(), IceSorcererCore.INSTANCE, 250, 5, true);
		EntityRegistry.registerModEntity(EntityIceMagicA.class, "entity_ice_magic_a", EntityID.ICE_MAGIC_A.ordinal(), IceSorcererCore.INSTANCE, 250, 5, true);
		EntityRegistry.registerModEntity(EntityIceMagicB.class, "entity_ice_magic_b", EntityID.ICE_MAGIC_B.ordinal(), IceSorcererCore.INSTANCE, 250, 5, true);
		EntityRegistry.registerModEntity(EntityIceMagicD.class, "entity_ice_magic_d", EntityID.ICE_MAGIC_D.ordinal(), IceSorcererCore.INSTANCE, 250, 5, true);
	}

	public static void registerItems() {
		GameRegistry.register(IceSorcererCore.Items.itemIceMagicA);
		GameRegistry.register(IceSorcererCore.Items.itemIceMagicB);
		GameRegistry.register(IceSorcererCore.Items.itemIceMagicC);
		GameRegistry.register(IceSorcererCore.Items.itemIceMagicD);

		GameRegistry.addRecipe(new ItemStack(IceSorcererCore.Items.itemIceMagicA),
				"@D@",
				" @ ",
				" # ",
				'#', Items.stick,
				'D', Items.diamond,
				'@', Items.snowball);
		GameRegistry.addRecipe(new ItemStack(IceSorcererCore.Items.itemIceMagicB),
				"@",
				"A",
				'@', Items.snowball,
				'A', IceSorcererCore.Items.itemIceMagicA);
		GameRegistry.addRecipe(new ItemStack(IceSorcererCore.Items.itemIceMagicC),
				"S",
				"A",
				'S', Blocks.snow,
				'A', IceSorcererCore.Items.itemIceMagicA);
		GameRegistry.addRecipe(new ItemStack(IceSorcererCore.Items.itemIceMagicD),
				"C",
				"B",
				'C', IceSorcererCore.Items.itemIceMagicC,
				'B', IceSorcererCore.Items.itemIceMagicB);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityFreeze.class, new RenderFactoryFreeze());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceMagicA.class, new RenderFactoryIceMagicA());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceMagicB.class, new RenderFactoryIceMagicA());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceMagicD.class, new RenderFactoryIceMagicA());
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModels() {
		ModelLoader.setCustomModelResourceLocation(IceSorcererCore.Items.itemIceMagicA, 0, ModelLocations.modelItemIceMagicA);
		ModelLoader.setCustomModelResourceLocation(IceSorcererCore.Items.itemIceMagicB, 0, ModelLocations.modelItemIceMagicB);
		ModelLoader.setCustomModelResourceLocation(IceSorcererCore.Items.itemIceMagicC, 0, ModelLocations.modelItemIceMagicC);
		ModelLoader.setCustomModelResourceLocation(IceSorcererCore.Items.itemIceMagicD, 0, ModelLocations.modelItemIceMagicD);
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures(TextureMap textureMap) {
		textureMap.registerSprite(new ResourceLocation("icesorcerer:items/ice_magic_3d"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerBakedModels(ModelBakeEvent event) {
		IBakedModel modelWeaponRod = bakeModel(new ResourceLocation("icesorcerer:item/ice_magic_3d.obj"));

		MultiBakedItemModel modelItemIceMagicA = new MultiBakedItemModel(
				event.getModelRegistry().getObject(ModelLocations.modelItemIceMagicA),
				modelWeaponRod);
		MultiBakedItemModel modelItemIceMagicB = new MultiBakedItemModel(
				event.getModelRegistry().getObject(ModelLocations.modelItemIceMagicB),
				modelWeaponRod);
		MultiBakedItemModel modelItemIceMagicC = new MultiBakedItemModel(
				event.getModelRegistry().getObject(ModelLocations.modelItemIceMagicC),
				modelWeaponRod);
		MultiBakedItemModel modelItemIceMagicD = new MultiBakedItemModel(
				event.getModelRegistry().getObject(ModelLocations.modelItemIceMagicD),
				modelWeaponRod);

		event.getModelRegistry().putObject(ModelLocations.modelItemIceMagicA, modelItemIceMagicA);
		event.getModelRegistry().putObject(ModelLocations.modelItemIceMagicB, modelItemIceMagicB);
		event.getModelRegistry().putObject(ModelLocations.modelItemIceMagicC, modelItemIceMagicC);
		event.getModelRegistry().putObject(ModelLocations.modelItemIceMagicD, modelItemIceMagicD);
	}

	@SideOnly(Side.CLIENT)
	public static IBakedModel bakeModel(ResourceLocation location) {
		try {
			IModel model = ModelLoaderRegistry.getModel(location);
			IBakedModel bakedModel = model.bake(model.getDefaultState(),
					DefaultVertexFormats.ITEM,
					new Function<ResourceLocation, TextureAtlasSprite>() {

						@Override
						public TextureAtlasSprite apply(ResourceLocation location) {
							Minecraft mc = Minecraft.getMinecraft();
							return mc.getTextureMapBlocks().getAtlasSprite(location.toString());
						}

					});

			return bakedModel;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
