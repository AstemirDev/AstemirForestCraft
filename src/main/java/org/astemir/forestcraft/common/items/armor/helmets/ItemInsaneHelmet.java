package org.astemir.forestcraft.common.items.armor.helmets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.armor.FCArmorTier;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

public class ItemInsaneHelmet extends AItemArmor {


    public ItemInsaneHelmet() {
        super("forestcraft:insane_helmet", FCArmorTier.INSANE, EquipmentSlotType.HEAD,0,0);
        setArmorTexture("forestcraft:textures/armor/insane_helmet.png");
        setArmorModel(FCItemModels.INSANE_HELMET_MODEL);
        rarity(Rarity.UNCOMMON);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.ARMOR);
        lore(TextUtils.effectTooltip(()->Effects.STRENGTH,10,0,false),TextUtils.effectTooltip(()->Effects.HUNGER,10,0,true),TextUtils.translate(FCStrings.INSANE_HELMET,TextFormatting.GRAY));
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        if (entity.ticksExisted % 60 == 0) {
            entity.addPotionEffect(new EffectInstance(Effects.HUNGER, 210, 0, false, false));
            entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 210, 0, false, false));
            itemStack.damageItem(1, entity, playerEntity -> {
                playerEntity.sendBreakAnimation(playerEntity.getActiveHand());
            });
            entity.playSound(FCSounds.INSANE_DOG_AMBIENT.get(), 0.5f, RandomUtils.randomFloat(0.9f, 1.1f));
        }
    }
}
