package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;


public class ItemMysteryScythe extends FCScytheItem {


    public ItemMysteryScythe() {
        super("forestcraft:mystery_scythe",FCItemTier.SOUL, 12, -2.7f,8);
        sound(FCSounds.SCYTHE_WHOOSH);
        getDefaultLore().add(TextUtils.effectTooltip(()->Effects.SLOWNESS,8,3,false));
        getDefaultLore().add(TextUtils.effectTooltip(()->Effects.WITHER,8,3,false));
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.getEntityWorld().playSound(target.getPosX(),target.getPosY(),target.getPosZ(), SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.PLAYERS,1,2,false);
        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS,160,3));
        target.addPotionEffect(new EffectInstance(Effects.WITHER,160,3));
        return super.onHit(stack, target, attacker);
    }



}
