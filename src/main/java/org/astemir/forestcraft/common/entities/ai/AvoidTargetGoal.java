package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;

import java.util.function.Predicate;

public class AvoidTargetGoal extends AvoidEntityGoal {

    public AvoidTargetGoal(CreatureEntity entityIn, Class avoidClass, float distance, double nearSpeedIn, double farSpeedIn) {
        super(entityIn, avoidClass, distance, nearSpeedIn, farSpeedIn, (Predicate<LivingEntity>) livingEntity -> {
            LivingEntity attackTarget = entityIn.getAttackTarget();
            if (attackTarget != null && livingEntity != null) {
                return livingEntity.getUniqueID().equals(attackTarget.getUniqueID());
            }
            return false;
        });
    }


}
