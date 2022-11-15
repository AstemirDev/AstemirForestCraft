package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;



public class ItemDolphinium extends AItemSword {

    public ItemDolphinium() {
        super("forestcraft:dolphinium",FCItemTier.TIERLESS, 4, -2.5f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        rarity(Rarity.RARE);
        maxDamage(800);
        maxStack(1);
        lore(new TranslationTextComponent(FCStrings.DOLPHINIUM).mergeStyle(TextFormatting.GRAY),new TranslationTextComponent(FCStrings.WATER_SPEED).mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public void onTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean selected) {
        if (selected && entityIn.isInWater()){
            if (worldIn.getBlockState(entityIn.getPosition().add(0,2,0)).isAir()){
                double yMult = 1.1f;
                if (entityIn.getMotion().y > 0){
                    yMult = 1.5f;
                }
                entityIn.setMotion(entityIn.getMotion().mul(1.15f, yMult, 1.15f));
            }else {
                entityIn.setMotion(entityIn.getMotion().mul(1.05f, 1.1f, 1.05f));
            }
        }
    }

}
