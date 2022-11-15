package org.astemir.forestcraft.common.items.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemElegantBalloon extends AItem {


    public ItemElegantBalloon() {
        super("forestcraft:elegant_balloon");
        maxStack(1);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        loreAdd(new TranslationTextComponent(FCStrings.JUMP_BOOST).mergeStyle(TextFormatting.GRAY));
        loreAdd(new TranslationTextComponent(FCStrings.LIGHT_BOOTS).mergeStyle(TextFormatting.GRAY));
        setupISTER(ForestCraft.PROXY::setupISTER);
    }


    @Override
    public void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity) {
        if (!entity.isOnGround()) {
            entity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 20, 2, false, false));
        }else{
            entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20, 2, false, false));
        }
    }

    @Override
    public boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source, float damage) {
        if (source.equals(DamageSource.FALL)){
            return false;
        }
        return super.onHurtWhileEquipped(itemStack, entity, source, damage);
    }


}
