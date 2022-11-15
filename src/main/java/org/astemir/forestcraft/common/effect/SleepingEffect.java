package org.astemir.forestcraft.common.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;


public class SleepingEffect extends Effect {



    public SleepingEffect() {
        super(EffectType.BENEFICIAL, 1756671);
        addAttributesModifier(Attributes.MOVEMENT_SPEED, "1107DE5E-7AE8-2030-840A-21B21F160890", (double)-1F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }


    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        entityLivingBaseIn.jumpMovementFactor = 0;
        entityLivingBaseIn.rotationYaw = entityLivingBaseIn.prevRotationYaw;
        entityLivingBaseIn.rotationPitch = entityLivingBaseIn.prevRotationPitch;
        entityLivingBaseIn.rotationYawHead = entityLivingBaseIn.prevRotationYawHead;
    }


    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }


}
