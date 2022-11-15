package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.ModList;
import org.astemir.api.APIMods;
import org.astemir.api.common.item.vanilla.ModArmorItem;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.math.Vector3;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.common.capabilities.CapabilityFlyingOnWings;
import org.astemir.forestcraft.common.items.armor.wings.curios.ModWings;
import org.astemir.forestcraft.registries.FCCapabilities;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCSounds;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class FCWings extends AItemArmor {


    public FCWings(String registryName, IArmorMaterial tier, float damageReduction, float toughness, float knockbackResistance) {
        super(registryName, tier, EquipmentSlotType.CHEST, damageReduction, toughness, knockbackResistance);
        setArmorModel(FCItemModels.WINGS_MODEL);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.WINGS);
        lore(
            TextUtils.translate(FCStrings.WINGS_POWER, TextFormatting.GRAY).append(TextUtils.text(getMaxFlyHeight()+"", TextFormatting.GRAY)),
            TextUtils.translate(FCStrings.WINGS_SPEED, TextFormatting.GRAY),
            TextUtils.text(" ").append(TextUtils.translate(FCStrings.WINGS_FORWARD).append(TextUtils.text(getSpeedZ()+""))),
            TextUtils.text(" ").append(TextUtils.translate(FCStrings.WINGS_HORIZONTAL).append(TextUtils.text(getSpeedX()+""))),
            TextUtils.text(" ").append(TextUtils.translate(FCStrings.WINGS_VERTICAL).append(TextUtils.text(getFlyingSpeed()+""))),
            TextUtils.translate(FCStrings.NO_FALL_DAMAGE,TextFormatting.GRAY)
            );
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        if (entity.isInWater()){
            if (!ignoreWater()){
                return;
            }
        }
        if (entity.isCreative()){
            return;
        }
        CapabilityFlyingOnWings flyingOnWings = CapabilityUtils.getCapability(FCCapabilities.CAPABILITY_FLYING_ON_WINGS,entity);
        if (flyingOnWings.getFlyingTicks() > 0) {
            flyingOnWings.update();
            if (entity instanceof ServerPlayerEntity) {
                CapabilityUtils.sendCapabilityChangedPacket(FCCapabilities.CAPABILITY_FLYING_ON_WINGS, (ServerPlayerEntity) entity);
            }
        }
        if (entity.ticksExisted % 20 == 0 && !entity.isOnGround()) {
            if (RandomUtils.doWithChance(30)) {
                itemStack.damageItem(1, entity, playerEntity -> {
                    playerEntity.sendBreakAnimation(EquipmentSlotType.CHEST);
                });
            }
        }
        if (entity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) entity).connection.floating = false;
        }
        if (entity.isOnGround()) {
            flyingOnWings.setTotalFlyingTicks(0);
            if (entity instanceof ServerPlayerEntity) {
                CapabilityUtils.sendCapabilityChangedPacket(FCCapabilities.CAPABILITY_FLYING_ON_WINGS, (ServerPlayerEntity) entity);
            }
        }
        entity.fallDistance = 0;
    }

    @Override
    public void onTickWhileArmorEquippedPost(ItemStack itemStack, PlayerEntity entity) {
        entity.fallDistance = 0;
    }


    @Override
    public void onJumpWhileEquipped(ItemStack stack, PlayerEntity player) {
        if (player.isInWater()){
            if (!ignoreWater()){
                return;
            }
        }
        if (player.isCreative()){
            return;
        }
        CapabilityFlyingOnWings flyingOnWings = CapabilityUtils.getCapability(FCCapabilities.CAPABILITY_FLYING_ON_WINGS, player);
        Vector3 input = absoluteMotion(new Vector3(player.moveStrafing,player.moveVertical,player.moveForward),player.rotationYaw);
        if (player instanceof ServerPlayerEntity) {
            CapabilityUtils.sendCapabilityChangedPacket(FCCapabilities.CAPABILITY_FLYING_ON_WINGS, (ServerPlayerEntity) player);
        }
        if (flyingOnWings.setFlyingTicks(10, getFlyingPower())) {
            if (player.ticksExisted % 5 == 0) {
                player.playSound(FCSounds.WINGS_FLAP.get(), 1, RandomUtils.randomFloat(1.9f,2.0f));
            }
            EntityUtils.addMotion(player,new Vector3((input.getX()*getSpeedX())/5f, 0, (input.getZ()*getSpeedZ())/5));
            EntityUtils.setMotionY(player,getFlyingSpeed()/2);
            player.velocityChanged = true;
        }
        if (flyingOnWings.getFlyingTicks() <= 0) {
            EntityUtils.addMotion(player,new Vector3((input.getX()*getSpeedX())/5f,0, (input.getZ()*getSpeedZ())/5));
            EntityUtils.setMotionY(player,getSlowFallingSpeed());
            player.velocityChanged = true;
        }
        player.setJumping(false);
    }

    private Vector3 absoluteMotion(Vector3 relative, float yaw) {
        double d0 = relative.lengthSquared();
        if (d0 < 1.0E-7D) {
            return Vector3.ZERO;
        } else {
            Vector3 vector3 = (d0 > 1.0D ? relative.normalize() : relative);
            return vector3.rotateYaw(yaw);
        }
    }



    public boolean ignoreWater(){
        return false;
    }


    @Override
    public Item build(Item.Properties properties) {
        if (APIMods.CURIOS.isLoaded()) {
            try {
                Class className = Class.forName("org.astemir.forestcraft.common.items.armor.wings.curios.ModWings");
                Constructor constructor = className.getConstructor(IArmorMaterial.class,EquipmentSlotType.class, Item.Properties.class,Float.TYPE,Float.TYPE,Float.TYPE);
                Item item = (Item) constructor.newInstance(getTier(), getSlotType(), properties, getDamageReduction(), getToughness(), getKnockbackResistance());
                ((ModArmorItem)item).itemConstructor(this);
                if (armorModel != -1) {
                    ((ModArmorItem)item).setArmorModel(armorModel);
                }
                if (armorTexture != null) {
                    ((ModArmorItem)item).setArmorTexture(armorTexture);
                }
                return item;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return super.build(properties);
    }

    @Override
    public boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source, float damage) {
        if (source.equals(DamageSource.FALL)){
            return false;
        }
        return super.onHurtWhileEquipped(itemStack, entity, source, damage);
    }

    public int getMaxFlyHeight(){return (int) ((getFlyingPower()*getFlyingSpeed())/10);}

    public float getSlowFallingSpeed() {
        return -0.1f;
    }

    public float getSpeedX(){
        return 1.25f;
    }

    public float getSpeedZ(){
        return 1.25f;
    }

    public float getFlyingSpeed(){
        return 0.5f;
    }

    public int getFlyingPower(){
        return 250;
    }
}
