package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import org.astemir.api.client.AColor;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityDirtProjectile;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemEarth extends AItemSword {

    public ItemEarth() {
        super("forestcraft:earth",FCItemTier.DIRT, 20, -2.8f);
        rarity(FCRarity.MYTHICAL);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public int getMaxDamage() {
        return 8048;
    }


    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        onLeftClick(stack,((PlayerEntity) attacker));
        return super.onHit(stack, target, attacker);
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            SoundUtils.playSound(player.world, FCSounds.SCYTHE_WHOOSH_2.get(),SoundCategory.PLAYERS,player,1,0.5f,0.55f);
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            Particle3D particle3D = new Particle3D(AColor.GREEN).speed(0.5f,0.5,0.5f).count(8).renderTimes(2).randomSpeed().deltaSpeed(0.1f).size(0.5f,1,0.5f);
            particle3D.play(player.world,player.getPosX(),player.getPosY(),player.getPosZ());
            if (!player.world.isRemote) {
                int sharpness = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
                float damage = getAttackDamage();
                if (sharpness > 0){
                    damage = damage+((damage*sharpness)*0.25f);
                }
                Particle3D dust = new Particle3D(AColor.GREEN).speed(0.5f,0.5,0.5f).count(4).renderTimes(2).randomSpeed().deltaSpeed(0.1f).size(0.1f,0.1f,0.1f);
                for (int i = 0;i<8;i++) {
                    EntityDirtProjectile dirtProj = new EntityDirtProjectile(player.getEntityWorld(), player){
                        @Override
                        public void tick() {
                            super.tick();
                            dust.play(world,getPosX(),getPosY(),getPosZ());
                        }
                    };
                    dirtProj.damage = (int)(damage*0.5f);
                    dirtProj.setItem(Blocks.DIRT.asItem().getDefaultInstance());
                    EntityUtils.shootProjectileIgnoreMotion(dirtProj,player,player.rotationPitch,player.rotationYaw,0.0f,1.5f,5.0f);
                    player.world.addEntity(dirtProj);
                }
                player.getCooldownTracker().setCooldown(stack.getItem(),15);
            }
        }
        return super.onLeftClick(stack, player);
    }
}
