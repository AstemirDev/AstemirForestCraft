package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityMiniTornadoProjectile;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemKeeperOfClouds extends AItemSword {

    public ItemKeeperOfClouds() {
        super("forestcraft:keeper_of_clouds",FCItemTier.DIAMOND, 5, -2.2f);
        rarity(Rarity.RARE);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public int getMaxDamage() {
        return 2048;
    }

    @Override
    public void onTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean selected) {
        if (entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityIn;
            if (selected) {
                player.fallDistance = 0;
            }
        }
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
        livingEntityIn.setMotion(EntityUtils.direction((PlayerEntity)livingEntityIn).mul(0.25f,0.25f,0.25f));
        if (count % 5 == 0) {
            EntityMiniTornadoProjectile miniTornadoProjectile = new EntityMiniTornadoProjectile(livingEntityIn.getEntityWorld(), livingEntityIn);
            miniTornadoProjectile.damage = 2;
            EntityUtils.shootProjectileIgnoreMotion(miniTornadoProjectile,livingEntityIn,livingEntityIn.rotationPitch,livingEntityIn.rotationYaw,0.0f,0.25f,0);
            livingEntityIn.world.addEntity(miniTornadoProjectile);
            PlayerUtils.damageItem((PlayerEntity)livingEntityIn,stack,livingEntityIn.getActiveHand(),1);
            livingEntityIn.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1, RandomUtils.randomFloat(1.5f, 1.8f));
        }
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            SoundUtils.playSound(player.world, FCSounds.SCYTHE_WHOOSH_2.get(),SoundCategory.PLAYERS,player,1,0.75f,0.8f);
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            if (!player.world.isRemote) {
                EntityMiniTornadoProjectile miniTornadoProjectile = new EntityMiniTornadoProjectile(player.getEntityWorld(), player);
                miniTornadoProjectile.damage = 5;
                EntityUtils.shootProjectileIgnoreMotion(miniTornadoProjectile,player,player.rotationPitch,player.rotationYaw,0.0f,0.4f,0);
                player.world.addEntity(miniTornadoProjectile);

                player.getCooldownTracker().setCooldown(stack.getItem(),10);
            }
        }
        return super.onLeftClick(stack, player);
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),200);
        super.onUsingStop(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 200;
    }


    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),200);
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn,playerIn.getHeldItem(handIn))) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }else{
            return ActionResult.resultFail(playerIn.getHeldItem(handIn));
        }
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }


}
