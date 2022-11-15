package org.astemir.forestcraft.common.items.weapons.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.utils.PlayerUtils;

public class ItemStaffOfSleeping extends AItemSword {


    public ItemStaffOfSleeping() {
        super("forestcraft:staff_of_sleeping",FCItemTier.TIERLESS, -1, -2.5f);
        maxDamage(64);
        maxStack(1);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        loreAdd(TextUtils.effectTooltip(FCEffects.SLEEPING,4,1,false));
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            if (!PlayerUtils.hasCooldown((PlayerEntity) attacker, stack)) {
                PlayerUtils.cooldownItem((PlayerEntity) attacker, stack, 240);
                target.addPotionEffect(new EffectInstance(FCEffects.SLEEPING.get(),64,0,false,false));
            }
        }
        return super.onHit(stack, target, attacker);
    }

}
