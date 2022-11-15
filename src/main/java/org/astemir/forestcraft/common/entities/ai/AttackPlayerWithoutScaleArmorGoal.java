package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCItems;

public class AttackPlayerWithoutScaleArmorGoal extends NearestAttackableTargetGoal {

    public AttackPlayerWithoutScaleArmorGoal(MobEntity goalOwnerIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn) {
        super(goalOwnerIn, PlayerEntity.class, targetChanceIn, checkSight, nearbyOnlyIn, (player)->{
            PlayerEntity playerEntity = (PlayerEntity)player;
            if (EntityUtils.hasArmorFullSet(playerEntity, FCItems.SEA_SCALE_ARMOR)){
                return false;
            }
            return true;
        });
    }
}
