package org.astemir.forestcraft.common.items.tools.pickaxes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemArchaicHammer extends AItemPickaxe {


    public ItemArchaicHammer() {
        super("forestcraft:archaic_hammer", FCItemTier.ANCIENT_RUNESTONE, 5,-3.5f);
        maxDamage(1000);
        rarity(Rarity.RARE);
        addToolType(AItemToolType.PICKAXE);
        addToolType(AItemToolType.AXE);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MULTITOOLS);
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        double d1 = attacker.getPosX() - target.getPosX();
        double d0;
        for(d0 = attacker.getPosZ() - target.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }
        target.applyKnockback(0.4F, d1, d0);
        return super.onHit(stack, target, attacker);
    }
}
