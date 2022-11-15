package org.astemir.forestcraft.common.items.weapons.guns;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.common.item.IAnimatedItem;
import org.astemir.api.utils.TextUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.client.ScreenShaker;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.common.entities.projectiles.other.DaybreakProjectile;
import org.astemir.forestcraft.common.event.GunShootEvent;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.constructors.FCMiniGun;
import org.astemir.forestcraft.registries.properties.FCGuns;

import java.util.Arrays;
import java.util.List;

public class ItemDaybreak extends FCMiniGun implements IAnimatedItem {

    public ItemDaybreak() {
        super("forestcraft:daybreak", FCGuns.DAYBREAK);
        rarity(Rarity.EPIC);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.GUNS);
        maxDamage(2000);
        loreAdd(TextUtils.translate(FCStrings.DAYBREAK,TextFormatting.GRAY));
        setupISTER(ForestCraft.PROXY::setupISTER);
    }

    @Override
    public void afterShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack) {
        super.afterShot(worldIn, livingEntityIn, stack);
        ScreenShaker.shakeScreen(((PlayerEntity) livingEntityIn),30,5);
    }

    @Override
    public void afterProjectileCreation(World world, GunShootEvent e) {
        Vector3d pos = e.getBullet().getPositionVec();
        e.getBullet().setPosition(pos.getX(),pos.getY()-0.25f,pos.getZ());
        if (WorldUtils.isDay(world)){
            e.setDamage(e.getDamage()+2);
        }else{
            e.setSpeed(e.getSpeed()+0.15f);
        }
    }

    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.MENDING,Enchantments.UNBREAKING);
    }

    @Override
    public FCBulletEntity createProjectile(LivingEntity entity, World world) {
        return new DaybreakProjectile(entity,world).initialize(entity,Items.AIR);
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public int getModelIndex() {
        return FCItemModels.DAYBREAK;
    }
}
