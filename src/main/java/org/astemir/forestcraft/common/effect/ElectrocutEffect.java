package org.astemir.forestcraft.common.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import org.astemir.forestcraft.registries.FCEffects;


public class ElectrocutEffect extends Effect {



    public ElectrocutEffect() {
        super(EffectType.HARMFUL, 7116322);
    }

    public void addToEntity(LivingEntity entity,int duration,int amplifier){
        entity.addPotionEffect(new EffectInstance(this,duration,amplifier,false,false));
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
        if (entityLivingBaseIn.getActivePotionEffect(FCEffects.ELECTRO_RESISTANCE.get()) == null) {
            if (entityLivingBaseIn.isInWaterRainOrBubbleColumn()) {
                entityLivingBaseIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2 * (1 + amplifier));
            } else {
                entityLivingBaseIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, amplifier + 1);
            }
        }
    }





}
