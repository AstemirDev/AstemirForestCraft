package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;

import java.util.List;
import java.util.function.Predicate;


public class ItemKeeperOfHeaven extends AItemSword {


    public ItemKeeperOfHeaven() {
        super("forestcraft:keeper_of_heaven",FCItemTier.DIAMOND, 2, -2.5f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        maxStack(1);
        lore(new TranslationTextComponent(FCStrings.NO_FALL_DAMAGE).mergeStyle(TextFormatting.GRAY),new TranslationTextComponent(FCStrings.RMB_ABILITY).mergeStyle(TextFormatting.GRAY));
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
            PlayerUtils.damageItem((PlayerEntity)livingEntityIn,stack,livingEntityIn.getActiveHand(),1);
            livingEntityIn.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1, RandomUtils.randomFloat(1.5f, 1.8f));
            for (double d = 1; d < 2; d += 0.5) {
                Vector3d pos = new Vector3d(livingEntityIn.getPosX(), livingEntityIn.getPosYEye(), livingEntityIn.getPosZ()).add(EntityUtils.direction(livingEntityIn).mul(d, d, d));
                if (worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).isAir() || worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock().equals(Blocks.WATER)) {
                    worldIn.addParticle(ParticleTypes.SWEEP_ATTACK, pos.getX() + RandomUtils.randomFloat(-0.1f, 0.1f), pos.getY()-0.25f, pos.getZ() + RandomUtils.randomFloat(-0.1f, 0.1f), 0, 0, 0);
                    for (LivingEntity entity : worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.x - 0.5, pos.y - 0.5, pos.z - 0.5, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5), new Predicate<LivingEntity>() {
                        @Override
                        public boolean test(LivingEntity entity) {
                            if (entity.getUniqueID().equals(livingEntityIn.getUniqueID())) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    })) {
                        entity.attackEntityFrom(DamageSource.causeMobDamage(livingEntityIn), 8);
                    }
                }
            }
        }
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
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack,200);
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
