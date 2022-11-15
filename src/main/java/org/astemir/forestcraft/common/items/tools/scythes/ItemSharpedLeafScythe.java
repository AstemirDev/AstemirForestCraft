package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;


public class ItemSharpedLeafScythe extends FCScytheItem {


    public ItemSharpedLeafScythe() {
        super("forestcraft:sharped_leaf_scythe",FCItemTier.SHARPED_LEAF, 4, -2.8f,3);
        getDefaultLore().add(TextUtils.effectTooltip(FCEffects.BROKEN_ARMOR,8,2,false));
        sound(FCSounds.SCYTHE_WHOOSH);
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),160,2));
        return super.onHit(stack, target, attacker);
    }

}
