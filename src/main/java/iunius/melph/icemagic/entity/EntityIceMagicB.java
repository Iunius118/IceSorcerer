package iunius.melph.icemagic.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityIceMagicB extends EntityThrowable {

	public EntityIceMagicB(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityIceMagicB(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityIceMagicB(World worldIn) {
		super(worldIn);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.ticksExisted > 12) {
			this.setDead();
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		for (int i = 0; i < 8; ++i) {
			this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}

		if (!this.worldObj.isRemote) {
			if (result.entityHit != null && result.entityHit != this.getThrower()) {
				int damage = 3;

				if (result.entityHit instanceof EntityBlaze) {
					damage = 5;
				}

				result.entityHit.attackEntityFrom(DamageSource.causeIndirectDamage(this, this.getThrower()), (float)damage);

				if (result.entityHit instanceof EntityLivingBase) {
					EntityLivingBase entityLiving = (EntityLivingBase)result.entityHit;
					EntityFreeze freeze = new EntityFreeze(this.worldObj, entityLiving, 80);
					this.worldObj.spawnEntityInWorld(freeze);
				}
			}


			this.setDead();
		}
	}

}
