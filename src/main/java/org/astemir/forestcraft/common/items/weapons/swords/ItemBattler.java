package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemBattler extends AItemSword {



    public ItemBattler() {
        super("forestcraft:battler",FCItemTier.INSANE, 2, -2.5f);
        maxStack(1);
        maxDamage(300);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        loreAdd(new TranslationTextComponent(FCStrings.BATTLER).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.addPotionEffect(new EffectInstance(Effects.REGENERATION,80,0));
        return super.onHit(stack, target, attacker);
    }



}
