package org.astemir.forestcraft.common.effect;


import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.block.ABlock;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.client.ScreenShaker;
import org.astemir.forestcraft.registries.FCSounds;


public class SneezingEffect extends Effect {

    private static DamageSource ALLERGIA = new DamageSource("allergia").setDamageBypassesArmor();

    public SneezingEffect() {
        super(EffectType.HARMFUL, 23765491);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        for (int i = 0;i<RandomUtils.randomInt(1,3);i++) {
            AstemirAPI.TASK_MANAGER.createTask(()->{
                Particle3D particle3D = new Particle3D(ParticleTypes.SNEEZE).randomSpeed().deltaSpeed(0.25f).speed(0.5f, 0.5f, 0.5f).count(10).size(0.25f, 0.25f, 0.25f);
                if (entityLivingBaseIn instanceof PlayerEntity) {
                    ScreenShaker.shakeScreen((PlayerEntity) entityLivingBaseIn, 20, RandomUtils.randomInt(1, 2) * amplifier + 10);
                    SoundUtils.playSound(entityLivingBaseIn.world, FCSounds.SNEEZE.get(), SoundCategory.PLAYERS, entityLivingBaseIn.getPosition(), 2, 0.9f, 1.2f);
                } else {
                    SoundUtils.playSound(entityLivingBaseIn.world, FCSounds.SNEEZE.get(), SoundCategory.AMBIENT, entityLivingBaseIn.getPosition(), 3, 0.9f, 1.2f);
                }
                entityLivingBaseIn.attackEntityFrom(ALLERGIA, 1);
                particle3D.play(entityLivingBaseIn.world, entityLivingBaseIn.getPosX(), entityLivingBaseIn.getPosYEye(), entityLivingBaseIn.getPosZ());
                EquipmentSlotType slot = RandomUtils.doWithChance(50) ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND;
                dropItem(entityLivingBaseIn, slot);
            },i*RandomUtils.randomInt(10,20));
        }
    }

    private void dropItem(LivingEntity entity, EquipmentSlotType slot){
        ItemStack stack = entity.getItemStackFromSlot(slot);
        if (!stack.isEmpty()) {
            if (entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).dropItem(stack, false, true);
            } else {
                WorldUtils.dropItem(entity.world, entity.getPosition(), stack);
            }
            entity.setItemStackToSlot(slot, ItemStack.EMPTY);
        }
    }


    @Override
    public boolean isReady(int duration, int amplifier) {
        if (duration % 60 == 0){
            float chance = 20*(amplifier+1);
            if (RandomUtils.doWithChance(chance)){
                return true;
            }
        }
        return false;
    }
}
