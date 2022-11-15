package org.astemir.forestcraft.common.items.weapons.other;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.astemir.api.common.item.AShootableItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityNoteProjectile;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ItemSoulHarp extends AShootableItem {


    public ItemSoulHarp() {
        super("forestcraft:soul_harp");
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.MISC);
        maxStack(1);
        maxDamage(900);
        lore(FCStrings.rangedDamageTooltip(3,7));
    }

    @Override
    public List<Enchantment> getAppliableEnchantments() {
        return Arrays.asList(Enchantments.UNBREAKING,Enchantments.MENDING);
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (count % 10 == 0) {
            worldIn.playSound(null, livingEntityIn.getPosX(), livingEntityIn.getPosY(), livingEntityIn.getPosZ(), SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundCategory.PLAYERS, 1f, RandomUtils.randomFloat(0.5f,1.5f));
        }
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, true);
            float f = getArrowVelocity(i);
            if (f == 1) {
                for (int k = 0;k<4;k++) {
                    EntityNoteProjectile noteProjectile = new EntityNoteProjectile(playerentity.getEntityWorld(), playerentity);
                    noteProjectile.setItem(Items.AIR.getDefaultInstance());
                    EntityUtils.shootProjectileIgnoreMotion(noteProjectile,playerentity,playerentity.rotationPitch,playerentity.rotationYaw,0.0f,1f,5.0f);
                    playerentity.world.addEntity(noteProjectile);
                    worldIn.playSound(null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.BLOCK_NOTE_BLOCK_HARP, SoundCategory.PLAYERS, 1f, RandomUtils.randomFloat(0.5f,1.5f));
                    stack.damageItem(1,playerentity,(player) -> {
                        playerentity.sendBreakAnimation(Hand.MAIN_HAND);
                    });
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    public static float getArrowVelocity(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }


    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return (item)-> true;
    }


    @Override
    public int getItemEnchantability() {
        return 20;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}
