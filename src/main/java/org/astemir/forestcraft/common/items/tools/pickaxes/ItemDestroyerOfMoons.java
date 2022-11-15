package org.astemir.forestcraft.common.items.tools.pickaxes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemPickaxe;
import org.astemir.api.common.item.AItemToolType;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;

public class ItemDestroyerOfMoons extends AItemPickaxe {

    public ItemDestroyerOfMoons() {
        super("forestcraft:destroyer_of_moons",FCItemTier.COSMIC, 1, -2.8f);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MULTITOOLS);
        addToolType(AItemToolType.AXE);
        addToolType(AItemToolType.SHOVEL);
        rarity(FCRarity.MYTHICAL);
        lore(TextUtils.translate(FCStrings.RMB_ABILITY, TextFormatting.GRAY));
    }

    @Override
    public int getMaxDamage() {
        return 20000;
    }


    @Override
    public void onTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean selected) {
        if (selected) {
            if (entityIn instanceof PlayerEntity) {
                ((PlayerEntity) entityIn).addPotionEffect(new EffectInstance(Effects.HASTE, 5, 3, false, false));
            }
        }
        super.onTick(stack, worldIn, entityIn, itemSlot, selected);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 60;
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        BlockPos pos = EntityUtils.rayTrace(worldIn,entityLiving,4).getPos();
        worldIn.playSound(null,pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,1,1);
        int distance = 3;
        for (int i = -3;i<3;i++){
            for (int j = -3;j<3;j++){
                for (int k = 0;k<distance;k++){
                    float dir = k;
                    if (entityLiving.rotationPitch > 0){
                        dir *=-1;
                    }
                    BlockPos newPos = pos.add(i,dir,j);
                    if (worldIn.getBlockState(newPos).canHarvestBlock(worldIn,newPos,(PlayerEntity) entityLiving)) {
                        if (!worldIn.getBlockState(newPos).getBlock().equals(Blocks.BEDROCK)) {
                            worldIn.destroyBlock(newPos, true, entityLiving);
                        }
                    }
                }
            }
        }
        PlayerUtils.cooldownItem((PlayerEntity) entityLiving,stack.getItem(),200);
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn,playerIn.getHeldItem(handIn))){
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
        }
        return super.onRightClick(worldIn, playerIn, handIn);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return super.getDestroySpeed(stack, state)*2;
    }
}
