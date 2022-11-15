package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.common.items.constructors.FCGun;
import org.astemir.forestcraft.common.items.weapons.guns.FCGunProperties;

public abstract class FCMiniGun extends FCGun {


    public FCMiniGun(String registryName, FCGunProperties properties) {
        super(registryName, properties);
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (hasEnoughAmmo(livingEntityIn,stack)) {
            ItemStack ammoStack = getAmmoStack(livingEntityIn,stack);
            whileUsing(worldIn,livingEntityIn,stack,count);
            if (canShoot(stack,count)){
                shoot((PlayerEntity) livingEntityIn,worldIn,stack,ammoStack);
            }
        }
    }

    @Override
    public void afterShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack) {
        if (RandomUtils.doWithChance(10)) {
            stack.damageItem(1, livingEntityIn, (livingEntity) -> {
                livingEntityIn.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1, 1);
                livingEntityIn.sendBreakAnimation(Hand.MAIN_HAND);
            });
        }
    }

    @Override
    public boolean canShoot(ItemStack stack, int useCount) {
        return useCount % getProperties().ticksToShot() == 0 && getUseTime(stack,useCount) > getProperties().firstShotDelay();
    }
}
