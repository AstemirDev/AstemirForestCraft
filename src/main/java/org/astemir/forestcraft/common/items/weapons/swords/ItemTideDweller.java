package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;


public class ItemTideDweller extends AItemSword {


    public ItemTideDweller() {
        super("forestcraft:tide_dweller",FCItemTier.KELP, 3, -2.5f);
        rarity(Rarity.UNCOMMON);
        lore(TextUtils.translate(FCStrings.WATER_SPEED, TextFormatting.GRAY), TextUtils.translate(FCStrings.RMB_ABILITY, TextFormatting.GRAY));
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public void onTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean selected) {
        if (selected && entityIn.isInWater()){
            entityIn.setMotion(entityIn.getMotion().mul(1.05f,1.1f,1.05f));
        }
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn,playerIn.getHeldItem(handIn))) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }else{
            return ActionResult.resultFail(playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.playSound(FCSounds.SCYTHE_WHOOSH.get(),1, RandomUtils.randomFloat(0.5f,0.6f));
        for (double d = 1;d<4;d+=0.5) {
            Vector3d pos = new Vector3d(entityLiving.getPosX(),entityLiving.getPosYEye(),entityLiving.getPosZ()).add(EntityUtils.direction(entityLiving).mul(d,d,d));
            if (worldIn.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ())).isAir() || worldIn.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ())).getBlock().equals(Blocks.WATER)) {
                worldIn.addParticle(ParticleTypes.SWEEP_ATTACK, pos.getX() + RandomUtils.randomFloat(-0.1f, 0.1f), pos.getY(), pos.getZ() + RandomUtils.randomFloat(-0.1f, 0.1f), 0, 0, 0);
                for (LivingEntity entity : worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.x - 0.5, pos.y - 0.5, pos.z - 0.5, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5), entity -> {
                    if (entity.getUniqueID().equals(entityLiving.getUniqueID())) {
                        return false;
                    } else {
                        return true;
                    }
                })) {
                    entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), (float) (getAttackDamage() - d));
                }
            }
        }
        PlayerUtils.damageItem((PlayerEntity)entityLiving,stack,entityLiving.getActiveHand(),3);
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack,20);
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 5;
    }
}
