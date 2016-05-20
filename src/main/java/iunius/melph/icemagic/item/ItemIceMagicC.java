package iunius.melph.icemagic.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class ItemIceMagicC extends ItemIceMagic {

	public ItemIceMagicC() {
		super();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		float cooldown = playerIn.getCooldownTracker().getCooldown(itemStackIn.getItem(), 0.0F);

		if (cooldown == 0.0F && hand == EnumHand.MAIN_HAND) {
			playerIn.swingArm(hand);
			playerIn.getCooldownTracker().setCooldown(itemStackIn.getItem(), 80);
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, playerIn.posX, playerIn.posY, playerIn.posZ, 0.0D, 0.0D, 0.0D, new int[0]);

			if (!worldIn.isRemote) {
				double range = 5.0D;
				double x1 = playerIn.posX - range;
				double x2 = playerIn.posX + range;
				double y1 = playerIn.posY - range;
				double y2 = playerIn.posY + range;
				double z1 = playerIn.posZ - range;
				double z2 = playerIn.posZ + range;
		        List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

				for(Entity entity : list) {
					int damage = 2;

					if (entity instanceof EntityBlaze) {
						damage = 4;
					}

					entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), (float)damage);

					if (entity instanceof EntityLivingBase) {
						EntityLivingBase entityLiving = (EntityLivingBase)entity;
						PotionEffect potioneffectSlow = new PotionEffect(MobEffects.moveSlowdown, 200, 2);
						entityLiving.addPotionEffect(potioneffectSlow);

						double dx = entityLiving.posX + entityLiving.width / 2.0D - playerIn.posX;
						double dy = entityLiving.posY + entityLiving.height / 2.0D - playerIn.posY;
						double dz = entityLiving.posZ + entityLiving.width / 2.0D - playerIn.posZ;
						Vec3d v = new Vec3d(dx, dy, dz);
						Vec3d v1 = v.normalize().scale(1.6D);
						entity.setVelocity(v1.xCoord, v1.yCoord, v1.zCoord);
					}
				}
			}

			playerIn.addStat(StatList.func_188057_b(this));
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		} else {
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
	}

}
