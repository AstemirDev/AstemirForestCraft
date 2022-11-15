package org.astemir.forestcraft.common.items.tools.other;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

public class ItemSickle extends AItemSword {


    public ItemSickle() {
        super("forestcraft:sickle",FCItemTier.IRON, 2, -2.9f);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
        lore(new TranslationTextComponent(FCStrings.SICKLE).mergeStyle(TextFormatting.GRAY));
    }


    @Override
    public boolean onSwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity)entity;
            if (!playerEntity.getCooldownTracker().hasCooldown(stack.getItem())){
                entity.world.playSound(entity.getPosX(),entity.getPosY(),entity.getPosZ(), FCSounds.SCYTHE_WHOOSH.get(), SoundCategory.PLAYERS,1f, RandomUtils.randomFloat(1.8f,2f),false);
                playerEntity.getCooldownTracker().setCooldown(stack.getItem(),10);
            }
        }else{
            entity.world.playSound(entity.getPosX(),entity.getPosY(),entity.getPosZ(), FCSounds.SCYTHE_WHOOSH.get(), SoundCategory.PLAYERS,1f, RandomUtils.randomFloat(1.8f,2f),false);
        }
        return false;
    }

    public boolean isPlant(BlockState state){
        return state.getBlock().equals(Blocks.GRASS) || state.getBlock().equals(Blocks.TALL_GRASS);
    }


    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (isPlant(state)){
            for (int i = -1;i<1;i++){
                for (int j = -1;j<1;j++){
                    if (RandomUtils.doWithChance(20)) {
                        PlayerUtils.damageItem((PlayerEntity) entityLiving,stack,Hand.MAIN_HAND,1);
                    }
                    if (isPlant(worldIn.getBlockState(pos.add(i,0,j)))) {
                        worldIn.destroyBlock(pos.add(i, 0, j), true, entityLiving);
                        WorldUtils.dropItem(worldIn,pos.add(i,0,j), ItemUtils.count(FCItems.BUNCH_OF_GRASS,RandomUtils.randomInt(1,4)));
                    }
                    if (isPlant(worldIn.getBlockState(pos.add(i,1,j)))) {
                        worldIn.destroyBlock(pos.add(i, 1, j), true, entityLiving);
                        WorldUtils.dropItem(worldIn,pos.add(i,1,j), ItemUtils.count(FCItems.BUNCH_OF_GRASS,RandomUtils.randomInt(1,4)));
                    }

                }
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

}
