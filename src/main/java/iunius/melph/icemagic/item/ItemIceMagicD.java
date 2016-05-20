package iunius.melph.icemagic.item;

import iunius.melph.icemagic.entity.EntityIceMagicD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;


public class ItemIceMagicD extends ItemIceMagic {

	public ItemIceMagicD() {
		super();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		float cooldown = playerIn.getCooldownTracker().getCooldown(itemStackIn.getItem(), 0.0F);

		if (cooldown == 0.0F && hand == EnumHand.MAIN_HAND) {
			playerIn.swingArm(hand);
			playerIn.getCooldownTracker().setCooldown(itemStackIn.getItem(), 120);
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!worldIn.isRemote) {
				EntityIceMagicD entity = new EntityIceMagicD(worldIn, playerIn);
				entity.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
				worldIn.spawnEntityInWorld(entity);
			}

			playerIn.addStat(StatList.func_188057_b(this));
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		} else {
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
	}

}
