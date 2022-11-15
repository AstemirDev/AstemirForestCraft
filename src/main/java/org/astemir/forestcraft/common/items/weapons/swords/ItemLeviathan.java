package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.utils.PlayerUtils;


public class ItemLeviathan extends AItemSword {



    public ItemLeviathan() {
        super("forestcraft:leviathan",FCItemTier.TIERLESS, 4, -2.4f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        rarity(Rarity.RARE);
        maxStack(1);
        maxDamage(1024);
        loreAdd(TextUtils.effectTooltip(()->Effects.WATER_BREATHING,13,0,false));
        loreAdd(new TranslationTextComponent(FCStrings.RMB_ABILITY).mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }


    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        worldIn.playSound(null,entityLiving.getPosition(),SoundEvents.ENTITY_WITCH_DRINK,SoundCategory.PLAYERS,1,0.5f);
        entityLiving.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING,260,0,true,true));
        PlayerUtils.damageItem((PlayerEntity)entityLiving,stack,entityLiving.getActiveHand(),1);
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

}
