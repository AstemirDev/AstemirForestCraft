package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityDemonBulletProjectile;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.constructors.FCMiniGun;
import org.astemir.forestcraft.registries.properties.FCGuns;
import org.astemir.forestcraft.registries.FCItems;

import java.util.Arrays;
import java.util.List;

public class ItemDemonBuster extends FCMiniGun {

    public ItemDemonBuster() {
        super("forestcraft:demon_buster", FCGuns.DEMON_BUSTER);
        rarity(FCRarity.MYTHICAL);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
        maxDamage(3000);
    }

    @Override
    public FCBulletEntity createProjectile(LivingEntity entity, World world) {
        return new EntityDemonBulletProjectile(world,entity).initialize(entity, FCItems.DEMON_BULLET);
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
