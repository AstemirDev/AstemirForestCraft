package org.astemir.forestcraft.common.items.weapons.throwable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityBakudanBall;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCItemGroups;

public class ItemBakudanBall extends AItem {

    public ItemBakudanBall() {
        super("forestcraft:bakudan_ball");
        itemGroup(FCItemGroups.WEAPONS, FCItemGroups.Priorities.MISC);
        maxStack(4);
    }


    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, RandomUtils.randomFloat(0.6f,0.7f));
            if (!player.world.isRemote) {
                EntityBakudanBall bakudanBall = new EntityBakudanBall(player.getEntityWorld(), player);
                Vector3d pos = EntityUtils.direction(player);
                pos = pos.mul(1.25f,1.5f,1.25f);
                bakudanBall.setShooter(player);
                bakudanBall.setLocationAndAngles(player.getPosX()+pos.getX(),player.getPosYEye()+pos.getY(),player.getPosZ()+pos.getZ(),0,0);
                EntityUtils.shootProjectileIgnoreMotion(bakudanBall,player,player.rotationPitch,player.rotationYaw,0.0f,0.75f,0);
                player.world.addEntity(bakudanBall);
                player.getCooldownTracker().setCooldown(stack.getItem(),10);
                stack.shrink(1);
            }
        }
        return super.onLeftClick(stack, player);
    }
}
