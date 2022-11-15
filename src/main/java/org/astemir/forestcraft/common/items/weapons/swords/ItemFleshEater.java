package org.astemir.forestcraft.common.items.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import org.astemir.api.common.item.AItemSword;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.registries.FCItemGroups;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.registries.FCSounds;

public class ItemFleshEater extends AItemSword {

    public ItemFleshEater() {
        super("forestcraft:flesh_eater",FCItemTier.TIERLESS, 24, -2.4f);
        rarity(FCRarity.MYTHICAL);
        itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS);
    }

    @Override
    public int getMaxDamage() {
        return 8000;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        if (!PlayerUtils.hasCooldown(player,stack.getItem())){
            launch(player);
            SoundUtils.playSound(player.world, FCSounds.SCYTHE_WHOOSH_2.get(), SoundCategory.PLAYERS,player,1,1,1.1f);
            PlayerUtils.cooldownItem(player,stack.getItem(),40);
        }
        return super.onLeftClick(stack, player);
    }

    private void launch(LivingEntity entity) {
        Vector3d dir = EntityUtils.direction(entity);
        entity.world.playEvent((PlayerEntity)null, 1024, entity.getPosition(), 0);
        WitherSkullEntity witherskullentity = new WitherSkullEntity(entity.world,entity,dir.getX(),dir.getY(),dir.getZ()){
            @Override
            protected void onImpact(RayTraceResult result) {
                RayTraceResult.Type raytraceresult$type = result.getType();
                if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
                    this.onEntityHit((EntityRayTraceResult)result);
                } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
                    this.func_230299_a_((BlockRayTraceResult)result);
                }
                if (!this.world.isRemote) {
                    this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, false, Explosion.Mode.NONE);
                    this.remove();
                }
            }

            @Override
            public void tick() {
                super.tick();
                if (ticksExisted > 100){
                    remove();
                }
            }

        };
        Vector3d pos = new Vector3d(entity.getPosX() + dir.getX(), entity.getPosYEye()-0.2 + dir.getY(), entity.getPosZ() + dir.getZ());
        witherskullentity.setRawPosition(pos.getX(),pos.getY(),pos.getZ());
        witherskullentity.setShooter(entity);
        entity.world.addEntity(witherskullentity);
    }
}
