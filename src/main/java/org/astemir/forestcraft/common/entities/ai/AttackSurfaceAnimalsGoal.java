package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;


public class AttackSurfaceAnimalsGoal extends NearestAttackableTargetGoal {

    public AttackSurfaceAnimalsGoal(MobEntity goalOwnerIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn) {
        super(goalOwnerIn, AnimalEntity.class, targetChanceIn, checkSight, nearbyOnlyIn, (entity)-> !(entity instanceof AbstractFishEntity) && !(entity instanceof SquidEntity));
    }
}
