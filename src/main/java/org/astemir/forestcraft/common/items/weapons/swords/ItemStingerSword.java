package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemStingerSword extends AItemSword {

    public ItemStingerSword() {
        super("forestcraft:stinger_sword",FCItemTier.STINGER, 4, -2.6f);
        rarity(Rarity.EPIC);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
        loreAdd(TextUtils.effectTooltip(()->Effects.POISON,6,2,false));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(Effects.POISON,120,1));
        return super.onHit(stack, target, attacker);
    }

}
