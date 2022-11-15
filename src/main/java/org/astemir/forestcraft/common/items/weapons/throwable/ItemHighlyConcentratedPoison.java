package org.astemir.forestcraft.common.items.weapons.throwable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityHighlyConcentratedPoison;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemHighlyConcentratedPoison extends AItem {

    public ItemHighlyConcentratedPoison() {
        super("forestcraft:highly_concentrated_poison");
        itemGroup(FCItemGroups.MISC, FCItemGroups.Priorities.POTIONS);
        maxStack(16);
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (player instanceof PlayerEntity) {
            if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
                if (!player.world.isRemote) {
                    EntityHighlyConcentratedPoison snowballentity = new EntityHighlyConcentratedPoison(player.world, player);
                    snowballentity.setItem(stack);
                    EntityUtils.shootProjectileIgnoreMotion(snowballentity,player,player.rotationPitch,player.rotationYaw,0.0f,1f,1.0f);
                    player.world.addEntity(snowballentity);
                    player.getCooldownTracker().setCooldown(stack.getItem(),20);
                    stack.shrink(1);
                }
            }
        }
        return false;
    }

}
