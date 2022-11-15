package org.astemir.forestcraft.common.items.tools.pickaxes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemMesargenstaxe extends AItemPickaxe {

    public ItemMesargenstaxe() {
        super("forestcraft:mesargenstaxe",FCItemTier.SUPER_MESA, 1, -2.8f);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES);
        maxStack(1);
        rarity(Rarity.RARE);
        lore(new TranslationTextComponent(FCStrings.MESARGENSTAXE).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public boolean onSwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity attacker = (PlayerEntity)entity;
            if (EntityUtils.hasArmorFullSet(attacker, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS)) {
                entity.addPotionEffect(new EffectInstance(Effects.HASTE, 5, 2, false, false));
            }
        }
        return super.onSwing(stack, entity);
    }


}
