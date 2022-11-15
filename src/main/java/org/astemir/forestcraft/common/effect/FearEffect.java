package org.astemir.forestcraft.common.effect;


import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;


public class FearEffect extends Effect {

    public FearEffect() {
        super(EffectType.HARMFUL, 16765491);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

}
