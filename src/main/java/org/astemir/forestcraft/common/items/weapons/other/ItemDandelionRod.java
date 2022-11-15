package org.astemir.forestcraft.common.items.weapons.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemDandelionRod extends AItemSword {



    public ItemDandelionRod() {
        super("forestcraft:dandelion_rod",FCItemTier.TIERLESS, -2, -2.5f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        double d1 = attacker.getPosX() - target.getPosX();
        double d0;
        for(d0 = attacker.getPosZ() - target.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }
        target.applyKnockback(1.5F, d1, d0);
        return super.onHit(stack, target, attacker);
    }
}
