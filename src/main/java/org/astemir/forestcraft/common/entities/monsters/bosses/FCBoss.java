package org.astemir.forestcraft.common.entities.monsters.bosses;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.EntityDifficultyMap;
import org.astemir.api.common.entity.IDamageResistable;
import org.astemir.forestcraft.configuration.FCConfigurationValues;

import javax.annotation.Nullable;

public class FCBoss extends MonsterEntity implements IDamageResistable {

    private DamageResistanceMap resistanceMap = new DamageResistanceMap();
    private ABossbar bossbar;
    private EntityDifficultyMap difficultyMap;
    private Difficulty difficulty;

    protected FCBoss(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (difficultyMap != null){
            difficulty = difficultyIn.getDifficulty();
            difficultyMap.getStats(difficultyIn.getDifficulty()).apply(this);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (difficulty != null) {
            compound.putInt("Difficulty", difficulty.getId());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("Difficulty")) {
            this.difficulty = Difficulty.byId(compound.getInt("Difficulty"));
        }
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        if (bossbar != null) {
            if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
                bossbar.show(player);
            }
        }
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        if (bossbar != null) {
            if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
                bossbar.hide(player);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (bossbar != null) {
            if (FCConfigurationValues.ENABLE_BOSSBARS.getValue()) {
                bossbar.update();
            }
        }
    }

    public void setDifficultyMap(EntityDifficultyMap difficultyMap) {
        this.difficultyMap = difficultyMap;
    }

    public void setResistanceToDamage(DamageResistanceMap resistanceMap) {
        this.resistanceMap = resistanceMap;
    }

    public ABossbar getBossbar() {
        return bossbar;
    }

    public void setBossbar(ABossbar bossbar) {
        this.bossbar = bossbar;
        this.bossbar.boss(this);
    }

    @Override
    public DamageResistanceMap getDamageResistanceMap() {
        return resistanceMap;
    }
}
