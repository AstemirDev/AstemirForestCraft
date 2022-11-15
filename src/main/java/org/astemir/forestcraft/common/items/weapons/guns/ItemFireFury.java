package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityMoltenBullet;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntitySkyBullet;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.common.items.constructors.FCPistol;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.forestcraft.registries.properties.FCGuns;

import java.util.Arrays;
import java.util.List;

public class ItemFireFury extends FCPistol {

    public ItemFireFury() {
        super("forestcraft:fire_fury", FCGuns.FIRE_FURY);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
        maxDamage(600);
    }

    @Override
    public void readyToShoot(ItemStack stack, LivingEntity player) {
        SoundUtils.playSound(player.world,FCSounds.SKY_SHOOTER_READY.get(),SoundCategory.PLAYERS,player.getPosition(),0.5f,1);
    }

    @Override
    public FCBulletEntity createProjectile(LivingEntity entity, World world) {
        return new EntityMoltenBullet(entity,world).initialize(entity, FCItems.MOLTEN_BULLET);
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
