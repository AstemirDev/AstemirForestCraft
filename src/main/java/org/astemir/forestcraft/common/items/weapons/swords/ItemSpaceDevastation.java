package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCosmicFire;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;

public class ItemSpaceDevastation extends AItemSword {

    public ItemSpaceDevastation() {
        super("forestcraft:space_devastation",FCItemTier.COSMIC, 28, -2.5f);
        rarity(FCRarity.LEGENDARY);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
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
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.getCooldownTracker().hasCooldown(playerIn.getHeldItem(handIn).getItem())) {
            Vector3d dir = EntityUtils.direction(playerIn).normalize();
            float speed = 3;
            playerIn.world.playSound(null,playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(), FCSounds.COSMIC_FIEND_ATTACK.get(),SoundCategory.NEUTRAL,0.5f,2);
            playerIn.addVelocity(dir.x*speed,dir.y*speed,dir.z*speed);
            playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(),40);
            playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,60,0,false,false));
        }
        return super.onRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            if (!player.world.isRemote) {
                int sharpness = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
                float damage = getAttackDamage();
                if (sharpness > 0){
                    damage = damage+((damage*sharpness)*0.25f);
                }
                for (int i = -2;i<2;i++) {
                    EntityCosmicFire projectile = new EntityCosmicFire(player.getEntityWorld(), player,0,0,0);
                    projectile.damage = (int)(damage*0.5f);
                    float rot = (float)Math.toRadians(player.getRotationYawHead());
                    projectile.setPosition(projectile.getPosX()+Math.cos(rot)*i,projectile.getPosY()+0.5f,projectile.getPosZ()+Math.sin(rot)*i);
                    EntityUtils.shootProjectileIgnoreMotion(projectile,player,player.rotationPitch,player.rotationYaw,0.0f,1f,0);
                    player.world.addEntity(projectile);
                    PlayerUtils.damageItem((PlayerEntity)player,stack,player.getActiveHand(),1);
                }
                player.getCooldownTracker().setCooldown(stack.getItem(),10);
            }
        }
        return super.onLeftClick(stack, player);
    }
}
