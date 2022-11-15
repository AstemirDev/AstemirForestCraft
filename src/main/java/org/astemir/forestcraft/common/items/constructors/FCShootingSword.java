package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItemSword;

import java.lang.reflect.InvocationTargetException;

public class FCShootingSword extends AItemSword {

    private int shootCooldown = 20;
    private Class<? extends ProjectileEntity> projectileClass;

    public FCShootingSword(String registryName, IItemTier tier, float damage, float speed, Class<? extends ProjectileEntity> projectile,int shootCooldown) {
        super(registryName, tier, damage, speed);
        this.shootCooldown = shootCooldown;
        this.projectileClass = projectile;
    }


    public void shootSword(ItemStack stack, PlayerEntity player) {
        try {
            ProjectileEntity projectile = projectileClass.getConstructor(World.class, LivingEntity.class).newInstance(player.world,player);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean canShoot(ItemStack stack,PlayerEntity player) {
        return player.getCooldownTracker().hasCooldown(stack.getItem());
    }



    public int shootCooldown(){
        return 20;
    }
}
