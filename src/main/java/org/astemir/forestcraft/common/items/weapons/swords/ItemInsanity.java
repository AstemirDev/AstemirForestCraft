package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.SoundCategory;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.weapons.bows.ItemInsaneBow;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;

public class ItemInsanity extends AItemSword {

    public ItemInsanity() {
        super("forestcraft:insanity",FCItemTier.INSANE, 3, -3f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
    }

    @Override
    public boolean onSwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
                player.getCooldownTracker().setCooldown(stack.getItem(),10);
                entity.world.playSound(entity.getPosX(),entity.getPosY(),entity.getPosZ(), FCSounds.LAZERBEAM.get(), SoundCategory.PLAYERS,0.25f, RandomUtils.randomFloat(1.5f,1.6f),false);
                ItemInsaneBow.insaneDogLaserLaunch(player,7,5);
                PlayerUtils.damageItem((PlayerEntity)entity,stack,entity.getActiveHand(),1);
            }
        }
        return false;
    }
}
