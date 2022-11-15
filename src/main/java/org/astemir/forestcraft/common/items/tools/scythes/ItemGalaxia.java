package org.astemir.forestcraft.common.items.tools.scythes;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.constructors.FCScytheItem;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;

import java.util.function.Predicate;


public class ItemGalaxia extends FCScytheItem {

    public ItemGalaxia() {
        super("forestcraft:galaxia",FCItemTier.COSMIC, 40, -3.5f,10);
        getDefaultLore().add(new TranslationTextComponent(FCStrings.RMB_ABILITY).mergeStyle(TextFormatting.GRAY));
        rarity(FCRarity.LEGENDARY);
        sound(FCSounds.SCYTHE_WHOOSH);
    }

    @Override
    public int getMaxDamage() {
        return 20000;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        super.onUse(worldIn, livingEntityIn, stack, count);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 5;
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
        entityLiving.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,1, RandomUtils.randomFloat(0.5f,0.6f));
        entityLiving.playSound(FCSounds.COSMIC_FIEND_HURT.get(),0.5f, RandomUtils.randomFloat(1.4f,1.5f));
        for (double d = 1;d<6;d+=0.5) {
            Vector3d pos = new Vector3d(entityLiving.getPosX(),entityLiving.getPosYEye(),entityLiving.getPosZ()).add(EntityUtils.direction(entityLiving).mul(d,d,d));
            if (worldIn.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ())).isAir() || worldIn.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ())).getBlock().equals(Blocks.WATER)) {
                worldIn.addParticle(ParticleTypes.SWEEP_ATTACK, pos.getX() + RandomUtils.randomFloat(-0.1f, 0.1f), pos.getY(), pos.getZ() + RandomUtils.randomFloat(-0.1f, 0.1f), 0, 0, 0);
                for (LivingEntity entity : worldIn.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.x - 0.5, pos.y - 0.5, pos.z - 0.5, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5), new Predicate<LivingEntity>() {
                    @Override
                    public boolean test(LivingEntity entity) {
                        if (entity.getUniqueID().equals(entityLiving.getUniqueID())) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })) {
                    int sharpness = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
                    float damage = getAttackDamage()*2f;
                    if (sharpness > 0){
                        damage = damage+((damage*sharpness)*0.25f);
                    }
                    entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), damage);
                }
            }
        }
        PlayerUtils.damageItem((PlayerEntity)entityLiving,stack,entityLiving.getActiveHand(),3);
        PlayerUtils.forceItemCooldown((PlayerEntity)entityLiving,stack.getItem(),200);
        return super.onUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

}
