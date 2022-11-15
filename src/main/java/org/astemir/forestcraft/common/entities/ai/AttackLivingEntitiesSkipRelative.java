package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ArmorStandEntity;

import java.util.function.Predicate;

public class AttackLivingEntitiesSkipRelative extends NearestAttackableTargetGoal {

    public AttackLivingEntitiesSkipRelative(MobEntity goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, (Predicate<LivingEntity>) entity -> {
            if (!(entity instanceof ArmorStandEntity) && !(entity.getClass().equals(goalOwnerIn.getClass()))) {
                return true;
            }else{
                return false;
            }
        });
    }
}
