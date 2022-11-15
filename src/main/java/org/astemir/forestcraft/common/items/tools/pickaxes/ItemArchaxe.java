package org.astemir.forestcraft.common.items.tools.pickaxes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemArchaxe extends AItemPickaxe {

    public ItemArchaxe() {
        super("forestcraft:archaxe",FCItemTier.ANCIENT_RUNESTONE, 9, -3);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MULTITOOLS);
        rarity(Rarity.EPIC);
        addToolType(AItemToolType.AXE);
    }

    @Override
    public int getMaxDamage() {
        return 2000;
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE,1,2);
        double d1 = attacker.getPosX() - target.getPosX();
        double d0;
        for(d0 = attacker.getPosZ() - target.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }
        target.applyKnockback(0.4F, d1, d0);
        return super.onHit(stack, target, attacker);
    }
}
