package org.astemir.api.common.event;

import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.client.animator.IAnimated;
import org.astemir.api.common.entity.DamageResistanceMap;
import org.astemir.api.common.entity.IDamageResistable;

public class EntityEventHandler {

    @SubscribeEvent
    public static void onEntityRemoved(EntityLeaveWorldEvent e){
       if (e.getEntity() instanceof IAnimated){
           ((IAnimated)e.getEntity()).getFactory().reset(e.getWorld());
       }
    }

    @SubscribeEvent
    public static void onHitFromAttack(LivingAttackEvent e){
        if (e.getEntityLiving() instanceof IDamageResistable){
            DamageResistanceMap map = ((IDamageResistable)e.getEntityLiving()).getDamageResistanceMap();
            float r1 = map.getResistance(e.getSource());
            float r2 = 0;
            if (e.getSource().getImmediateSource() != null){
                r2 = map.getResistance(e.getSource().getImmediateSource());
            }
            float res  = 1-(r1+r2);
            if (res <= 0) {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onHit(LivingHurtEvent e) {
        if (e.getEntityLiving() instanceof IDamageResistable){
            DamageResistanceMap map = ((IDamageResistable)e.getEntityLiving()).getDamageResistanceMap();
            float r1 = map.getResistance(e.getSource());
            float r2 = 0;
            if (e.getSource().getImmediateSource() != null){
                r2 = map.getResistance(e.getSource().getImmediateSource());
            }
            float res  = 1-(r1+r2);
            if (res <= 0) {
                e.setCanceled(true);
            }else {
                e.setAmount(e.getAmount() * res);
            }
        }
    }


}
