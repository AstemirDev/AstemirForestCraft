package org.astemir.forestcraft.common.items.tools.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;



public class ItemRainFlute extends AItem {


    public ItemRainFlute() {
        super("forestcraft:rain_flute");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        rarity(Rarity.RARE);
        lore(new TranslationTextComponent(FCStrings.RAIN_FLUTE).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
        int i = stack.getItem().getUseDuration(stack)-count;
        if (count > 5) {
            livingEntityIn.playSound(FCSounds.WHISTLE_LOOP.get(), 1-(i*0.01f), 0.75f+(i*0.005f));
        }
    }



    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),50);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 100;
    }


    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),2000);
        entityLiving.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, 1, 2);
        if (!worldIn.isRemote) {
            if (!worldIn.isRaining()) {
                if (RandomUtils.doWithChance(80)) {
                    ((ServerWorld) worldIn).func_241113_a_(0, RandomUtils.randomInt(5, 10) * 60 * 20, true, false);
                } else {
                    ((ServerWorld) worldIn).func_241113_a_(0, RandomUtils.randomInt(5, 10) * 60 * 20, true, true);
                }
            }else{
                ((ServerWorld) worldIn).func_241113_a_(RandomUtils.randomInt(10, 15) * 60 * 20, 0, false, false);
            }
        }
        stack.shrink(1);
        if (entityLiving.getActiveHand() != null) {
            entityLiving.sendBreakAnimation(entityLiving.getActiveHand());
        }
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn,playerIn.getHeldItem(handIn))) {
            playerIn.setActiveHand(handIn);
            playerIn.playSound(FCSounds.WHISTLE_USE.get(),1,1);
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }else{
            return ActionResult.resultFail(playerIn.getHeldItem(handIn));
        }
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }



}
