package iunius.melph.icemagic.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFreeze extends Entity {

	public float prevHealth;
	public int ticksExpiry;

	public EntityFreeze(World worldIn) {
		this(worldIn, null, 0);
	}

	public EntityFreeze(World worldIn, Entity entityFrozenIn, int ticksExpiryIn) {
		super(worldIn);

		setSize(1.0F, 1.0F);

		if (!worldIn.isRemote) {
			ticksExpiry = ticksExpiryIn;

			if (entityFrozenIn != null) {
				this.setPosition(entityFrozenIn.posX, entityFrozenIn.posY, entityFrozenIn.posZ);
				entityFrozenIn.startRiding(this);
			}
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public boolean canAttackWithItem() {
		return false;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	public double getMountedYOffset() {
		List<Entity> passengers = this.getPassengers();

		if (!passengers.isEmpty()) {
			return -passengers.get(0).getYOffset();
		}

		return 0.0D;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		List<Entity> passengers = this.getPassengers();

		if (!this.worldObj.isRemote) {
			if (passengers.isEmpty()) {
				this.setDead();
			} else {
				if (passengers.get(0) instanceof EntityLivingBase) {
					EntityLivingBase entityLiving = (EntityLivingBase)passengers.get(0);

					if (prevHealth - entityLiving.getHealth() > 1.0F) {
						this.setDead();
						return;
					}

					prevHealth = entityLiving.getHealth();
				}

				if (this.ticksExisted > ticksExpiry) {
					this.setDead();
				}
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {

	}

}
