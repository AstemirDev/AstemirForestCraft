package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.astemir.forestcraft.common.entities.monsters.EntityInsaneDog;
import org.astemir.forestcraft.registries.FCItems;


public class AttackPlayerWithoutInsaneHelmetGoal extends NearestAttackableTargetGoal {

    public AttackPlayerWithoutInsaneHelmetGoal(MobEntity goalOwnerIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn) {
        super(goalOwnerIn, PlayerEntity.class, targetChanceIn, checkSight, nearbyOnlyIn, (player)->{
            PlayerEntity playerEntity = (PlayerEntity)player;
            ItemStack helmet = playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD);
            if (!helmet.isEmpty()){
                boolean hasHelmet = helmet.isItemEqualIgnoreDurability(FCItems.INSANE_HELMET.getDefaultInstance());
                if (hasHelmet){
                    if (goalOwnerIn instanceof EntityInsaneDog) {
                        EntityInsaneDog insaneDog = (EntityInsaneDog) goalOwnerIn;
                        if (insaneDog.getAlpha() == null) {
                            insaneDog.setAlpha(playerEntity);
                        }
                    }
                    return false;
                }else{
                    return true;
                }
            }
            return true;
        });
    }
}
