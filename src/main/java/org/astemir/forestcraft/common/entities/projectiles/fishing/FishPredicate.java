package org.astemir.forestcraft.common.entities.projectiles.fishing;


import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.biome.Biome;

public class FishPredicate{

    public Biome biome;
    public final ProjectileEntity bobber;
    public FishingLiquid liquid;

    public FishPredicate(ProjectileEntity bobber, FishingLiquid liquid,Biome biome){
        this.bobber = bobber;
        this.liquid = liquid;
        this.biome = biome;
    }
}
