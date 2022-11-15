package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

import java.util.List;

public class ItemIguanaKingClaw extends AItemSword {



    public ItemIguanaKingClaw() {
        super("forestcraft:iguana_king_claw",FCItemTier.TIERLESS, 5, -2.6f);
        maxDamage(2048);
        rarity(Rarity.EPIC);
        maxStack(1);
        loreAdd(TextUtils.effectTooltip(()->Effects.WEAKNESS,2,1,false));
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(Effects.WEAKNESS,40,1));
        double d1 = attacker.getPosX() - target.getPosX();
        double d0;
        for(d0 = attacker.getPosZ() - target.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }
        target.applyKnockback(0.3F, d1, d0);
        return super.onHit(stack, target, attacker);
    }


}
