package org.astemir.forestcraft.common.items.tools.pickaxes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemOceanMonumentBreaker extends AItemPickaxe {

    public ItemOceanMonumentBreaker() {
        super("forestcraft:ocean_monument_breaker",FCItemTier.PRISMARINE, 1, -2.8f);
        maxDamage(800);
        maxStack(1);
        rarity(Rarity.RARE);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        lore(TextUtils.translate(FCStrings.OCEAN_MONUMENT_BREAKER).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public boolean onSwing(ItemStack stack, LivingEntity entity) {
        if (entity.isInWater()) {
            entity.addPotionEffect(new EffectInstance(Effects.HASTE,5,2,false,false));
        }
        return super.onSwing(stack, entity);
    }

}
