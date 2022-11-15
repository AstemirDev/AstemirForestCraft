package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.common.item.AShootableItem;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.projectiles.bullets.FCBulletEntity;
import org.astemir.forestcraft.common.event.GunShootEvent;
import org.astemir.forestcraft.common.items.weapons.guns.FCGunProperties;

public abstract class FCGun extends AShootableItem {

    private FCGunProperties properties;

    public FCGun(String registryName,FCGunProperties properties) {
        super(registryName);
        this.properties = properties;
        if (properties.usingAmmo()){
            ammo((itemStack)->itemStack.getItem() == properties.ammo().get());
        }
        lore(properties.maxDamage() == properties.minDamage() ? FCStrings.rangedDamageTooltip(properties.maxDamage()) : FCStrings.rangedDamageTooltip(properties.minDamage(),properties.maxDamage()),FCStrings.speedToolTip(properties.ticksToShot()).mergeStyle(TextFormatting.DARK_GREEN));
        if (properties.chanceToNotConsumeAmmo() != 0){
            loreAdd(FCStrings.chanceToNotConsume(properties.chanceToNotConsumeAmmo()));
        }
        if (properties.usingAmmo()){
            loreAdd(FCStrings.consumes(properties.ammo()));
        }
        maxStack(1);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack gunHeld = playerIn.getHeldItem(handIn);
        if (!hasEnoughAmmo(playerIn,gunHeld)) {
            return ActionResult.resultFail(gunHeld);
        } else {
            playerIn.setActiveHand(handIn);
            startUsing(worldIn,playerIn);
            return ActionResult.resultConsume(gunHeld);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public void shoot(PlayerEntity playerEntity, World world,ItemStack gunStack,ItemStack ammoStack){
        FCBulletEntity projectile = createProjectile(playerEntity, world);
        GunShootEvent event = new GunShootEvent(projectile, getProperties().minDamage() == getProperties().maxDamage() ? getProperties().maxDamage() : RandomUtils.randomFloat(getProperties().minDamage(), getProperties().maxDamage()), getProperties().projectileSpeed(), getProperties().knockback(), getProperties().projectileSpread());
        if (!world.isRemote) {
            if (projectile != null) {
                afterProjectileCreation(world, event);
                if (!event.isCanceled()) {
                    projectile.shoot(playerEntity, gunStack, playerEntity.rotationPitch, playerEntity.rotationYaw, 0, event.getSpeed(), event.getSpread());
                    projectile.setKnockback(event.getKnockback());
                    projectile.setDefaultDamage(event.getDamage());
                    world.addEntity(projectile);
                    if (ammoStack != null) {
                        if (getProperties().chanceToNotConsumeAmmo() != 0) {
                            if (RandomUtils.doWithChance(100 - getProperties().chanceToNotConsumeAmmo())) {
                                ammoStack.shrink(1);
                            }
                        } else {
                            ammoStack.shrink(1);
                        }
                    }
                    afterShot(world, playerEntity, gunStack);
                }
            }
            immediateShot(world,playerEntity,gunStack);
            if (getProperties().shotSound() != null && !event.isCanceled()) {
                getProperties().shotSound().play(world, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ());
            }
        }
    }

    public FCBulletEntity createProjectile(LivingEntity entity, World world){
        return null;
    }

    public void afterProjectileCreation(World world, GunShootEvent e){}

    public boolean hasEnoughAmmo(LivingEntity entity,ItemStack gunStack){
        PlayerEntity playerIn = (PlayerEntity) entity;
        boolean flag = playerIn.abilities.isCreativeMode || !getProperties().usingAmmo();
        ItemStack ammoStack = playerIn.findAmmo(gunStack);
        if (!ammoStack.isEmpty() || flag) {
            return true;
        }
        return false;
    }

    public ItemStack getAmmoStack(LivingEntity entity,ItemStack gunStack){
        PlayerEntity playerIn = (PlayerEntity) entity;
        if (properties.usingAmmo()) {
            if (playerIn.abilities.isCreativeMode) {
                return properties.ammo().get().getDefaultInstance();
            } else {
                ItemStack ammoStack = playerIn.findAmmo(gunStack);
                return ammoStack;
            }
        }else{
            return null;
        }
    }

    public float getAverageDamage(){
        return (properties.maxDamage()+properties.minDamage())/2;
    }

    public float getSpeed(){
        return properties.ticksToShot();
    }

    public boolean canShoot(ItemStack stack,int useCount){
        return true;
    }

    public void startUsing(World worldIn, PlayerEntity playerIn){};

    public void whileUsing(World worldIn, LivingEntity livingEntityIn, ItemStack stack,int count){};

    public void afterShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack){}

    public void immediateShot(World worldIn, LivingEntity livingEntityIn, ItemStack stack){}

    public FCGunProperties getProperties() {
        return properties;
    }
}
