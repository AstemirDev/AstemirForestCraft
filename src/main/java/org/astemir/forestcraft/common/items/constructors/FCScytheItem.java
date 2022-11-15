package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.math.RandomUtils;

import java.util.function.Supplier;

public class FCScytheItem extends AItemSword {


    private int scytheEfficiency = 2;
    private Supplier<SoundEvent> sound = ()->SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP;

    public FCScytheItem(String registryName,IItemTier tier, int attackDamageIn, float attackSpeedIn, int efficiency) {
        super(registryName,tier, attackDamageIn, attackSpeedIn);
        scytheEfficiency = efficiency;
        lore(new TranslationTextComponent(FCStrings.SCYTHE).mergeStyle(TextFormatting.GRAY));
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.SCYTHES);
    }

    public FCScytheItem sound(Supplier<SoundEvent> sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return true;
    }

    @Override
    public int getFuelTicks(ItemStack itemStack) {
        return getTier().equals(FCItemTier.WOOD) ? 200 : -1;
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())){
            player.world.playSound(player.getPosX(),player.getPosY(),player.getPosZ(), sound.get(), SoundCategory.PLAYERS,1f, RandomUtils.randomFloat(0.9f,1.1f),false);
            player.getCooldownTracker().setCooldown(stack.getItem(),10);
        }
        return super.onLeftClick(stack, player);
    }

    public boolean isPlant(BlockState state){
        return state.getBlock() instanceof FlowerBlock || state.getBlock() instanceof DoublePlantBlock || state.getBlock() instanceof TallGrassBlock;
    }



    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (isPlant(state)){
            for (int i = -scytheEfficiency;i<scytheEfficiency;i++){
                for (int j = -scytheEfficiency;j<scytheEfficiency;j++){
                    if (RandomUtils.doWithChance(20)) {
                        stack.damageItem(1, entityLiving, (livingEntity)->{
                            entityLiving.playSound(SoundEvents.ITEM_SHIELD_BREAK,1,1);
                            entityLiving.sendBreakAnimation(Hand.MAIN_HAND);
                        });
                    }
                    if (isPlant(worldIn.getBlockState(pos.add(i,0,j)))) {
                        worldIn.destroyBlock(pos.add(i, 0, j), true, entityLiving);
                    }
                    if (isPlant(worldIn.getBlockState(pos.add(i,1,j)))) {
                        worldIn.destroyBlock(pos.add(i, 1, j), true, entityLiving);
                    }
                }
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    public int getScytheEfficiency() {
        return scytheEfficiency;
    }
}
