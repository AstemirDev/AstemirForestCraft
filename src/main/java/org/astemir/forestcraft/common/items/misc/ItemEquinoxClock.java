package org.astemir.forestcraft.common.items.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;


public class ItemEquinoxClock extends AItem {


    public ItemEquinoxClock() {
        super("forestcraft:equinox_clock");
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        rarity(Rarity.EPIC);
        maxStack(1);
        lore(new TranslationTextComponent(FCStrings.EQUINOX_CLOCK).mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),50);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 20;
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),8000);
        SoundUtils.playSound(worldIn,FCSounds.EQUINOX_USE.get(), SoundCategory.PLAYERS,entityLiving.getPosition(),1,1);
        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld)worldIn;
            if (!WorldUtils.isNight(worldIn)){
                WorldUtils.setNight(serverWorld);
            }else{
                WorldUtils.setDay(serverWorld);
            }
        }
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
        return UseAction.BOW;
    }

}
