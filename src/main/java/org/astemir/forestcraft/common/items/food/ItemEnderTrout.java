package org.astemir.forestcraft.common.items.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemFoodProperties;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemEnderTrout extends AItem {

    public ItemEnderTrout() {
        super("forestcraft:endertrout");
        food(new AItemFoodProperties().hungerRestore(2).saturation(0.4f).alwaysEdible());
        itemGroup(FCItemGroups.FOOD);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, LivingEntity entity) {
        super.onFoodEaten(itemStack, entity);
        if (entity instanceof PlayerEntity){
            PlayerEntity playerIn = (PlayerEntity)entity;
            if (!playerIn.getCooldownTracker().hasCooldown(itemStack.getItem())) {
                BlockRayTraceResult ray = EntityUtils.rayTrace(entity.world,playerIn,18);
                if (entity.world.getBlockState(ray.getPos().add(0, 1, 0)).isAir()) {
                    playerIn.getCooldownTracker().setCooldown(itemStack.getItem(), 60);
                    for (int i = 0; i < 32; ++i) {
                        entity.world.addParticle(ParticleTypes.PORTAL, ray.getPos().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                    }
                    playerIn.setPosition(ray.getPos().getX(), ray.getPos().getY() + 1f, ray.getPos().getZ());
                    for (int i = 0; i < 32; ++i) {
                        entity.world.addParticle(ParticleTypes.PORTAL, ray.getPos().getX() + RandomUtils.randomFloat(-0.5f, 0.5f), ray.getPos().getY() + 0.5f + RandomUtils.randomFloat(0, 1.5f), ray.getPos().getZ() + RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(-0.2f, 0.2f), 0.0D, RandomUtils.randomFloat(-0.2f, 0.2f));
                    }
                    entity.world.playSound(ray.getPos().getX(), ray.getPos().getY(), ray.getPos().getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1, 1, false);
                }
                entity.attackEntityFrom(DamageSource.GENERIC,2);
            }
        }
    }
}
