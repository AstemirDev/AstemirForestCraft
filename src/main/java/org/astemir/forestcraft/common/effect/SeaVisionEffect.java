package org.astemir.forestcraft.common.effect;


import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;


public class SeaVisionEffect extends Effect {



    public SeaVisionEffect() {
        super(EffectType.BENEFICIAL, 5756671);
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


}
