package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntitySkyBullet;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.constructors.FCPistol;
import org.astemir.forestcraft.registries.properties.FCGuns;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;

import java.util.Arrays;
import java.util.List;

public class ItemSkyShooter extends FCPistol {

    public ItemSkyShooter() {
        super("forestcraft:sky_shooter", FCGuns.SKY_SHOOTER);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
        maxDamage(300);
    }

    @Override
    public void readyToShoot(ItemStack stack, LivingEntity player) {
        SoundUtils.playSound(player.world,FCSounds.SKY_SHOOTER_READY.get(),SoundCategory.PLAYERS,player.getPosition(),0.5f,2);
    }

    @Override
    public FCBulletEntity createProjectile(LivingEntity entity, World world) {
        return new EntitySkyBullet(entity,world).initialize(entity, FCItems.SKY_BULLET);
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
