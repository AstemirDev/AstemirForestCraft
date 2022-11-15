package org.astemir.forestcraft.common.items.shields;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import org.astemir.api.common.item.AItemTier;
import org.astemir.api.common.item.AShieldItem;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.registries.FCItemGroups;


public class ItemHoneyKeeper extends AShieldItem {


    public ItemHoneyKeeper() {
        super("forestcraft:honey_keeper");
        repairMaterial(AItemTier.repairMaterialFrom(()->Ingredient.fromItems(Blocks.HONEY_BLOCK.asItem())));
        maxDamage(1236);
        rarity(Rarity.EPIC);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SHIELDS);
        setupISTER(ForestCraft.PROXY::setupISTER);
    }

    @Override
    public boolean onHurtByEntityWhileEquipped(ItemStack itemStack, LivingEntity entity, Entity attacker, float damage) {
        if (attacker != null) {
            if (attacker instanceof LivingEntity) {
                ((LivingEntity)attacker).addPotionEffect(new EffectInstance(Effects.SLOWNESS,20,1));
            }
        }
        return super.onHurtByEntityWhileEquipped(itemStack, entity, attacker, damage);
    }
}
