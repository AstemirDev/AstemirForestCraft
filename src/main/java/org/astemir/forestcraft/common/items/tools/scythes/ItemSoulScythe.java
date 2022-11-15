package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemSoulScythe extends FCScytheItem {


    public ItemSoulScythe() {
        super("forestcraft:soul_scythe",FCItemTier.SOUL, 4, -2.7f,4);
        sound(FCSounds.SCYTHE_WHOOSH);
        rarity(Rarity.RARE);
        getDefaultLore().add(TextUtils.effectTooltip(()->Effects.WITHER,8,2,false));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.getEntityWorld().playSound(target.getPosX(),target.getPosY(),target.getPosZ(), SoundEvents.ENTITY_WITHER_AMBIENT, SoundCategory.PLAYERS,1,2,false);
        target.addPotionEffect(new EffectInstance(Effects.WITHER,160,2));
        return super.onHit(stack, target, attacker);
    }


}
