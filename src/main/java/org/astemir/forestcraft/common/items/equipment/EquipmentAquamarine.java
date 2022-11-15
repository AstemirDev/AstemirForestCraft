package org.astemir.forestcraft.common.items.equipment;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AWeaponToolSet;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityBubbleProjectile;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class EquipmentAquamarine extends AWeaponToolSet {

    public EquipmentAquamarine() {
        super(FCItemTier.AQUAMARINE);
        swordItemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        pickaxeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        axeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES);
        shovelItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SHOVELS);
        hoeItemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.HOES);
        sword("forestcraft:aquamarine_sword",2, -2.2f);
        pickaxe("forestcraft:aquamarine_pickaxe",1, -2.8f);
        axe("forestcraft:aquamarine_axe",5, -3f);
        shovel("forestcraft:aquamarine_shovel",1.5f, -3f);
        hoe("forestcraft:aquamarine_hoe",-3, -2.4f);
    }


    @Override
    public void onSwing(AItem item, ItemStack stack, LivingEntity entity) {
        if (item == getSword()){
            if (!entity.world.isRemote) {
                if (entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity)entity;
                    if (!playerEntity.getCooldownTracker().hasCooldown(stack.getItem())) {
                        EntityBubbleProjectile proj = new EntityBubbleProjectile(entity.getEntityWorld(), entity);
                        EntityUtils.shootProjectileIgnoreMotion(proj,playerEntity,entity.rotationPitch,entity.rotationYaw,0.0f,0.5f,1);
                        entity.world.addEntity(proj);
                        PlayerUtils.damageItem((PlayerEntity)entity,stack,entity.getActiveHand(),1);
                        playerEntity.getCooldownTracker().setCooldown(stack.getItem(),20);
                        entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_DOLPHIN_SPLASH, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
                    }
                }
            }
        }
    }
}
