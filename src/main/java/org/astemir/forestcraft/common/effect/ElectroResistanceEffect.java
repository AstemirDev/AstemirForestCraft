package org.astemir.forestcraft.common.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import org.astemir.forestcraft.registries.FCEffects;


public class ElectroResistanceEffect extends Effect {



    public ElectroResistanceEffect() {
        super(EffectType.BENEFICIAL, 16765491);
    }



    @Override
    public boolean isReady(int duration, int amplifier) {
        int k = 6 >> amplifier;
        if (k > 0) {
            return duration % k == 0;
        } else {
            return true;
        }
    }


    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getActivePotionEffect(FCEffects.ELECTROCUT.get()) != null){
            entityLivingBaseIn.removePotionEffect(FCEffects.ELECTROCUT.get());
        }
    }




}
