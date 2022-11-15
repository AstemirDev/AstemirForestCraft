package org.astemir.forestcraft.common.entities.monsters;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityTamableMonster extends MonsterEntity {

    protected static final DataParameter<Byte> TAMED = EntityDataManager.createKey(EntityTamableMonster.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(EntityTamableMonster.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private boolean field_233683_bw_;

    protected EntityTamableMonster(EntityType<? extends EntityTamableMonster> type, World worldIn) {
        super(type, worldIn);
        this.setupTamedAI();
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TAMED, (byte)0);
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.empty());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.getOwnerId() != null) {
            compound.putUniqueId("Owner", this.getOwnerId());
        }

        compound.putBoolean("Sitting", this.field_233683_bw_);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        UUID uuid;
        if (compound.hasUniqueId("Owner")) {
            uuid = compound.getUniqueId("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
                this.setTamed(true);
            } catch (Throwable throwable) {
                this.setTamed(false);
            }
        }

        this.field_233683_bw_ = compound.getBoolean("Sitting");
        this.setSleeping(this.field_233683_bw_);
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return !this.getLeashed();
    }

    /**
     * Play the taming effect, will either be hearts or smoke depending on status
     */
    @OnlyIn(Dist.CLIENT)
    protected void playTameEffect(boolean play) {
        IParticleData iparticledata = ParticleTypes.HEART;
        if (!play) {
            iparticledata = ParticleTypes.SMOKE;
        }

        for(int i = 0; i < 7; ++i) {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.addParticle(iparticledata, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
        }

    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 7) {
            this.playTameEffect(true);
        } else if (id == 6) {
            this.playTameEffect(false);
        } else {
            super.handleStatusUpdate(id);
        }

    }

    public boolean isTamed() {
        return (this.dataManager.get(TAMED) & 4) != 0;
    }

    public void setTamed(boolean tamed) {
        byte b0 = this.dataManager.get(TAMED);
        if (tamed) {
            this.dataManager.set(TAMED, (byte)(b0 | 4));
        } else {
            this.dataManager.set(TAMED, (byte)(b0 & -5));
        }

        this.setupTamedAI();
    }

    protected void setupTamedAI() {
    }

    public boolean isEntitySleeping() {
        return (this.dataManager.get(TAMED) & 1) != 0;
    }

    public void setSleeping(boolean p_233686_1_) {
        byte b0 = this.dataManager.get(TAMED);
        if (p_233686_1_) {
            this.dataManager.set(TAMED, (byte)(b0 | 1));
        } else {
            this.dataManager.set(TAMED, (byte)(b0 & -2));
        }

    }

    @Nullable
    public UUID getOwnerId() {
        return this.dataManager.get(OWNER_UNIQUE_ID).orElse((UUID)null);
    }

    public void setOwnerId(@Nullable UUID p_184754_1_) {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.ofNullable(p_184754_1_));
    }


    @Override
    protected boolean isDespawnPeaceful() {
        return !isTamed();
    }

    public void setTamedBy(PlayerEntity player) {
        this.setTamed(true);
        this.enablePersistence();
        this.setOwnerId(player.getUniqueID());
        if (getAttackTarget() != null) {
            if (getAttackTarget().getUniqueID().equals(player.getUniqueID())) {
                setAttackTarget(null);
            }
        }
        if (getRevengeTarget() != null) {
            if (getRevengeTarget().getUniqueID().equals(player.getUniqueID())) {
                setRevengeTarget(null);
            }
        }
        setAggroed(false);
    }

    @Nullable
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : this.world.getPlayerByUuid(uuid);
        } catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return this.isOwner(target) ? false : super.canAttack(target);
    }

    public boolean isOwner(LivingEntity entityIn) {
        return entityIn == this.getOwner();
    }

    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        return true;
    }

    @Override
    public Team getTeam() {
        if (this.isTamed()) {
            LivingEntity livingentity = this.getOwner();
            if (livingentity != null) {
                return livingentity.getTeam();
            }
        }

        return super.getTeam();
    }


    @Override
    public boolean isOnSameTeam(Entity entityIn) {
        if (this.isTamed()) {
            LivingEntity livingentity = this.getOwner();
            if (entityIn == livingentity) {
                return true;
            }

            if (livingentity != null) {
                return livingentity.isOnSameTeam(entityIn);
            }
        }

        return super.isOnSameTeam(entityIn);
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (!this.world.isRemote && this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES) && this.getOwner() instanceof ServerPlayerEntity) {
            this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.DUMMY_UUID);
        }

        super.onDeath(cause);
    }

    public boolean isSitting() {
        return this.field_233683_bw_;
    }

    public void func_233687_w_(boolean p_233687_1_) {
        this.field_233683_bw_ = p_233687_1_;
    }

}
