package org.astemir.forestcraft.common.items.tools.axes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemAxe;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemSoulEater extends AItemAxe {

    public ItemSoulEater() {
        super("forestcraft:soul_eater",FCItemTier.SOUL, 8, -3);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        addToolType(AItemToolType.AXE);
        rarity(Rarity.RARE);
        lore(TextUtils.translate("tooltip.forestcraft.soul_eater"));
    }

    @Override
    public int getMaxDamage() {
        return 2000;
    }

    @Override
    public void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity) {
        if (entity.world.getDimensionKey() == World.THE_NETHER){
            entity.addPotionEffect(new EffectInstance(Effects.STRENGTH,20,0,false,false));
        }
    }

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE,1,1.5f);
        target.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE,1,1.75f);
        target.playSound(SoundEvents.ENTITY_WITHER_HURT,0.5f,1.5f);
        target.setFire(5);
        double d1 = attacker.getPosX() - target.getPosX();
        double d0;
        for(d0 = attacker.getPosZ() - target.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
            d1 = (Math.random() - Math.random()) * 0.01D;
        }
        target.applyKnockback(1F, d1, d0);
        return super.onHit(stack, target, attacker);
    }
}
