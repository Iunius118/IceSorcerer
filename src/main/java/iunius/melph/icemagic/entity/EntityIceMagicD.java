package iunius.melph.icemagic.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIceMagicD extends EntityThrowable {

	public boolean isImpacted;

	public EntityIceMagicD(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityIceMagicD(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityIceMagicD(World worldIn) {
		super(worldIn);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

	@Override
	public void onUpdate() {
		isImpacted = false;

		super.onUpdate();

		if (this.ticksExisted > 10) {
			if (!isImpacted) {
				onImpact(new RayTraceResult(this));
			}

			this.setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		isImpacted = true;

		this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);

		if (!this.worldObj.isRemote) {

			double range = 5.0D;
			double x1 = result.hitVec.xCoord - range;
			double x2 = result.hitVec.xCoord + range;
			double y1 = result.hitVec.yCoord - range;
			double y2 = result.hitVec.yCoord + range;
			double z1 = result.hitVec.zCoord - range;
			double z2 = result.hitVec.zCoord + range;
	        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.getThrower(), new AxisAlignedBB(x1, y1, z1, x2, y2, z2));

			for(Entity entity : list) {
				int damage = 2;

				if (entity instanceof EntityBlaze) {
					damage = 4;
				}

				entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.getThrower()), (float)damage);

				if (entity instanceof EntityLivingBase) {
					EntityLivingBase entityLiving = (EntityLivingBase)entity;
					PotionEffect potioneffectSlow = new PotionEffect(MobEffects.moveSlowdown, 160, 1);
					entityLiving.addPotionEffect(potioneffectSlow);

					EntityFreeze freeze = new EntityFreeze(this.worldObj, entityLiving, 110);
					this.worldObj.spawnEntityInWorld(freeze);
				}
			}


			this.setDead();
		}
	}

}
