package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;


public class ItemSporeDagger extends AItemSword {

    public ItemSporeDagger() {
        super("forestcraft:spore_dagger",FCItemTier.IRON, 1, -1.5f);
        maxDamage(300);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
        lore(TextUtils.effectTooltip(()->Effects.POISON,4,1,false),TextUtils.effectTooltip(()->Effects.NAUSEA,2,1,false));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(Effects.POISON,80,0));
        target.addPotionEffect(new EffectInstance(Effects.NAUSEA,40,0));
        return super.onHit(stack, target, attacker);
    }
}
