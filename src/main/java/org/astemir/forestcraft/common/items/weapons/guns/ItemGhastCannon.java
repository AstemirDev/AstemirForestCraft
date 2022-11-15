package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.constructors.FCPistol;
import org.astemir.forestcraft.registries.properties.FCGuns;

import java.util.Arrays;
import java.util.List;


public class ItemGhastCannon extends FCPistol {

    public ItemGhastCannon() {
        super("forestcraft:ghast_cannon", FCGuns.GHAST_CANNON);
        maxDamage(100);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
    }


    @Override
    public void immediateShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack) {
        FireballEntity fireball = new FireballEntity(worldIn, livingEntityIn,livingEntityIn.getLookVec().x, livingEntityIn.getLookVec().y, livingEntityIn.getLookVec().z);
        fireball.setPosition(fireball.getPosX(),fireball.getPosY()+1,fireball.getPosZ());
        EntityUtils.shootProjectileIgnoreMotion(fireball,livingEntityIn,livingEntityIn.rotationPitch,livingEntityIn.rotationYaw,0.0f,2f,0f);
        worldIn.addEntity(fireball);
    }

    @Override
    public void readyToShoot(ItemStack stack, LivingEntity player) {
        SoundUtils.playSound(player.world,SoundEvents.ENTITY_GHAST_WARN, SoundCategory.NEUTRAL,player.getPosition(), 0.5F, RandomUtils.randomFloat(1f, 1.1f));
    }

    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.MENDING,Enchantments.UNBREAKING);
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }
}
