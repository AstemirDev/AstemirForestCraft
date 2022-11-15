package org.astemir.forestcraft.common.items.weapons.other;



import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AShootableItem;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityBeeProjectile;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ItemRoyalStaff extends AShootableItem {

    public ItemRoyalStaff() {
        super("forestcraft:royal_staff");
        rarity(Rarity.EPIC);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(100);
        lore(FCStrings.rangedDamageTooltip(2,3),new TranslationTextComponent(FCStrings.RMB_ABILITY).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.MENDING,Enchantments.UNBREAKING);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity && getUseDuration(stack)-timeLeft > 5) {
            ((PlayerEntity)entityLiving).getCooldownTracker().setCooldown(stack.getItem(),20);
        }
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (livingEntityIn instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity)livingEntityIn;
            if (count % 20 == 0) {
                if (!playerIn.world.isRemote) {
                    Entity target = EntityUtils.rayTraceEntity(playerIn,(e)->!(e instanceof EntityBeeProjectile),16);
                    EntityBeeProjectile bee = new EntityBeeProjectile(FCEntities.PROJECTILE_BEE,playerIn.getEntityWorld());
                    bee.setPlayer(playerIn);
                    bee.moveToBlockPosAndAngles(new BlockPos(livingEntityIn.getPosX(), livingEntityIn.getPosYEye() - (double)0.1F, livingEntityIn.getPosZ()),playerIn.rotationYaw,playerIn.rotationPitch);
                    bee.setMotion(EntityUtils.direction(playerIn).normalize().mul(0.5f,0.5f,0.5f));
                    if (target != null){
                        if (target instanceof LivingEntity) {
                            bee.setAttackTarget((LivingEntity)target);
                        }
                    }
                    playerIn.world.addEntity(bee);
                }
                SoundUtils.playSound(playerIn.world, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL,SoundCategory.PLAYERS,playerIn,1,1.9f,2f);
                stack.damageItem(1, playerIn, (livingEntity) -> {
                    playerIn.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1, 1);
                    playerIn.sendBreakAnimation(Hand.MAIN_HAND);
                });
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!playerIn.getCooldownTracker().hasCooldown(itemstack.getItem())) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }else{
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return null;
    }

    @Override
    public int getItemEnchantability() {
        return 20;
    }


    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (repair.getItem().equals(Items.HONEY_BLOCK)) {
            return true;
        }
        return false;
    }


}
