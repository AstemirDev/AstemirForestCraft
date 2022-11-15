package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.item.IAnimatedItem;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.client.ScreenShaker;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemVileTentacle extends AItemSword implements IAnimatedItem {

    public ItemVileTentacle() {
        super("forestcraft:vile_tentacle",FCItemTier.INSANE, 8, -3f);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
        rarity(Rarity.RARE);
        setupISTER(ForestCraft.PROXY::setupISTER);
        maxDamage(1048);
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerUtils.cooldownItem((PlayerEntity) entityLiving,stack,80);
        super.onUsingStop(stack, worldIn, entityLiving, timeLeft);
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
        if (count % 10 == 0 && getUseDuration(stack)-count > 0){
            Vector3d dir = EntityUtils.direction(livingEntityIn);
            EntityUtils.addMotion(livingEntityIn, Vector3.from(dir.normalize()).mul(0.25f));
            ScreenShaker.shakeScreen(((PlayerEntity) livingEntityIn),20,5);
            int range = 5;
            for (int i = 0;i<range;i++) {
                Vector3d pos = livingEntityIn.getEyePosition(0).add(dir.mul(i,i,i));
                worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(new BlockPos(pos)),(entity)->{
                    if (!entity.getUniqueID().equals(livingEntityIn.getUniqueID())){
                        Particle3D blood = new Particle3D(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.getDefaultState()).size(0.5f,0.5f,0.5f).speed(0.25f,0.25f,0.25f).randomSpeed().count(8).renderTimes(4);
                        Particle3D flesh = new Particle3D(ParticleTypes.BLOCK, FCBlocks.ROTTEN_FLESH_BLOCK.getDefaultState()).size(0.5f,0.5f,0.5f).speed(0.1f,0.1f,0.1f).randomSpeed().count(4).renderTimes(2);
                        blood.play(worldIn,entity.getPosX(),entity.getPosY(),entity.getPosZ());
                        flesh.play(worldIn,entity.getPosX(),entity.getPosY(),entity.getPosZ());
                        entity.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) livingEntityIn),10);
                        return true;
                    }
                    return false;
                });
            }
            PlayerUtils.damageItem((PlayerEntity) livingEntityIn,stack,Hand.MAIN_HAND,1);
            SoundUtils.playSound(worldIn,FCSounds.CHANGELING_PARASITE_ATTACK.get(), SoundCategory.PLAYERS,livingEntityIn.getPosition(),1,1);
        }
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        SoundUtils.playSound(player.world,FCSounds.CHANGELING_PARASITE_ATTACK.get(), SoundCategory.PLAYERS,player.getPosition(),1,1);
        return super.onLeftClick(stack, player);
    }



    @Override
    public int getUseDuration(ItemStack stack) {
        return 36000;
    }


    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn, playerIn.getHeldItem(handIn))) {
            PlayerUtils.cooldownItem(playerIn,playerIn.getHeldItem(handIn),80);
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
        }
        return super.onRightClick(worldIn,playerIn,handIn);
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getModelIndex() {
        return FCItemModels.VILE_TENTACLE;
    }
}
