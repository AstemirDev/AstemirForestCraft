package org.astemir.forestcraft.common.effect;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class BrokenArmorEffect extends Effect {

    public BrokenArmorEffect() {
        super(EffectType.HARMFUL, 7116322);
        addAttributesModifier(Attributes.ARMOR,"03C3C29D-7067-4B52-86AF-B146BCB64D1E",-2, AttributeModifier.Operation.ADDITION);
    }

}
