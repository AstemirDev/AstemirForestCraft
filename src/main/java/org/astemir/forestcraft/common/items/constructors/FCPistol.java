package org.astemir.forestcraft.common.items.constructors;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.items.constructors.FCGun;
import org.astemir.forestcraft.common.items.weapons.guns.FCGunProperties;

import java.util.Arrays;
import java.util.List;

public abstract class FCPistol extends FCGun {

    public FCPistol(String registryName, FCGunProperties properties) {
        super(registryName, properties);
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (canShoot(stack,timeLeft)) {
            if (hasEnoughAmmo(entityLiving,stack)) {
                ItemStack ammoStack = getAmmoStack(entityLiving,stack);
                shoot((PlayerEntity) entityLiving, worldIn, stack, ammoStack);
            }
        }
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if (getUseTime(stack,count) == getProperties().ticksToShot()){
            readyToShoot(stack,player);
        }
    }

    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.MENDING,Enchantments.UNBREAKING);
    }

    @Override
    public boolean canShoot(ItemStack stack, int useCount) {
        return getUseTime(stack,useCount) > getProperties().ticksToShot();
    }

    public void readyToShoot(ItemStack stack, LivingEntity player){};
}
