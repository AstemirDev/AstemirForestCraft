package org.astemir.forestcraft.common.event;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.entities.ai.SafeMeleeAttackGoal;
import org.astemir.forestcraft.common.entities.animals.EntityKelpy;
import org.astemir.forestcraft.common.entities.projectiles.fishing.EntityFCFishingBobber;
import org.astemir.forestcraft.common.entities.projectiles.fishing.EntityFireFishingBobber;
import org.astemir.forestcraft.common.entities.monsters.EntitySoulBlaze;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.api.math.RandomUtils;

import java.util.function.Predicate;

public class EntityEvents {



    @SubscribeEvent
    public static void onTeleport(EnderTeleportEvent e){
        if (e.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity owner = (PlayerEntity) e.getEntityLiving();
            if (EntityUtils.hasArmorFullSet(owner, FCItems.ENDERITE_ARMOR)) {
                e.setAttackDamage(0);
            }
        }
    }


    @SubscribeEvent
    public static void onSpawnNonLiving(EntityJoinWorldEvent e){
        if (!e.getWorld().isRemote) {
            if (e.getEntity() instanceof MonsterEntity){
                MonsterEntity monster = ((MonsterEntity)e.getEntity());
                if (monster.isEntityUndead()){
                    if (monster.getMaxHealth() <= 40){
                        monster.goalSelector.addGoal(1,new AvoidEntityGoal(monster, LivingEntity.class, 30, 1, 1.25f, (Predicate<LivingEntity>) livingEntity -> {
                            ItemStack chestplate = EntityUtils.chestplate(livingEntity);
                            if (!chestplate.isEmpty()){
                                if (chestplate.isItemEqualIgnoreDurability(FCItems.ETERNAL_HUNGER_CHESTPLATE.getDefaultInstance())){
                                    return true;
                                }
                            }
                            return false;
                        }));
                    }
                }
            }
            if (e.getEntity() instanceof TurtleEntity){
                TurtleEntity turtle = ((TurtleEntity)e.getEntity());
                turtle.targetSelector.addGoal(0,new SafeMeleeAttackGoal(turtle,0.8f,true));
                turtle.targetSelector.addGoal(1,new NearestAttackableTargetGoal(turtle, EntityKelpy.class,true));
            }
            if (e.getEntity() instanceof CowEntity || e.getEntity() instanceof SheepEntity) {
                ((AnimalEntity) e.getEntity()).goalSelector.addGoal(3, new TemptGoal((AnimalEntity) e.getEntity(), 1.25D, Ingredient.fromItems(FCBlocks.GRASS_HAY.asItem()), false));
            }
            if (FCConfigurationValues.SPAWN_SOUL_BLAZES.getValue()) {
                if (e.getEntity() instanceof BlazeEntity && !(e.getEntity() instanceof EntitySoulBlaze)) {
                    if (RandomUtils.doWithChance(10)) {
                        EntitySoulBlaze soulBlaze = FCEntities.SOUL_BLAZE.create(e.getWorld());
                        soulBlaze.setPosition(e.getEntity().getPosX(), e.getEntity().getPosY(), e.getEntity().getPosZ());
                        e.getWorld().addEntity(soulBlaze);
                        e.setCanceled(true);
                    }
                }
            }
            if (!(e.getEntity() instanceof EntityFCFishingBobber || e.getEntity() instanceof EntityFireFishingBobber)) {
                if (e.getEntity().getClass() == FishingBobberEntity.class && !FCConfigurationValues.DEFAULT_FISHING.getValue()) {
                    FishingBobberEntity fishingBobber = (FishingBobberEntity) e.getEntity();
                    PlayerEntity owner = fishingBobber.func_234606_i_();
                    e.getWorld().addEntity(new EntityFCFishingBobber(owner, e.getWorld(), fishingBobber.luck, fishingBobber.lureSpeed));
                    e.setCanceled(true);
                }
            }
        }
    }


}
