package org.astemir.forestcraft.common.items.weapons.bows;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.client.AColor;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.monsters.EntityAlphaInsaneDog;
import org.astemir.forestcraft.common.entities.monsters.EntityInsaneDog;
import org.astemir.forestcraft.common.items.constructors.FCBow;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.registries.properties.FCBows;


public class ItemInsaneBow extends FCBow {


    public ItemInsaneBow() {
        super("forestcraft:insane_bow", FCBows.INSANE_BOW);
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.BOWS);
        maxDamage(300);
    }

    public static void insaneDogLaserLaunch(PlayerEntity entity, int distance, float damage){
        Vector3d dir = EntityUtils.direction(entity);
        for (double i = 0; i < distance + 0.75D * 0.5D; i += 0.5D) {
            Vector3d pos = new Vector3d(entity.getPosX() + dir.getX() * i, entity.getPosYEye()-0.2 + dir.getY() * i, entity.getPosZ() + dir.getZ() * i);
            if (entity.world.getBlockState(new BlockPos(pos)).isAir()) {
                entity.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() - 0.1, pos.getY() - 0.1, pos.getZ() - 0.1, pos.getX() + 0.1, pos.getY() + 0.1, pos.getZ() + 0.1), livingEntity -> !(livingEntity instanceof EntityInsaneDog) && !(livingEntity instanceof EntityAlphaInsaneDog) && !livingEntity.getUniqueID().equals(entity.getUniqueID())).forEach((e) -> {
                    e.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 0));
                    e.attackEntityFrom(DamageSource.causeMobDamage(entity), damage);
                });
                Particle3D particle3D = new Particle3D(new AColor(1,0,0,1));
                particle3D.play(entity.world,pos.getX(), pos.getY(), pos.getZ());
            }else{
                break;
            }
        }
    }


    @Override
    public void afterShot(World world, PlayerEntity player,ItemStack bowStack,float velocity) {
        insaneDogLaserLaunch(player, (int) (velocity*20),calculateDamage(bowStack));
    }
}

