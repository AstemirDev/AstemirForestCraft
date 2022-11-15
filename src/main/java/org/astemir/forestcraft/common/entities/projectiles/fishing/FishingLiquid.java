package org.astemir.forestcraft.common.entities.projectiles.fishing;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import org.astemir.api.loot.ItemDrop;

import java.util.List;


public class FishingLiquid {

    private Material fluidMaterial;
    private ITag fluidTag;
    private IParticleData bubbleParticle;
    private IParticleData splashParticle;
    private IParticleData fishingParticle;
    private BiomeCatch defaultFish;
    private BiomeCatch[] catchableFishes;
    private boolean hot = false;
    private boolean canFishTreasures = false;

    public static final FishingLiquid WATER = new FishingLiquid(Fishes.WATER,Material.WATER,FluidTags.WATER,ParticleTypes.BUBBLE,ParticleTypes.SPLASH,ParticleTypes.FISHING,
            Fishes.END,
            Fishes.RARE,
            Fishes.GEM,
            Fishes.MINES,
            Fishes.NIGHT,
            Fishes.EXTREME_HILLS,
            Fishes.SWAMP,
            Fishes.DESERT,
            Fishes.JUNGLE,
            Fishes.MESA,
            Fishes.MUSHROOM,
            Fishes.SNOW,
            Fishes.OCEAN).canFishTreasures();

    public static final FishingLiquid LAVA = new FishingLiquid(Fishes.LAVA,Material.LAVA,FluidTags.LAVA,ParticleTypes.SMOKE,ParticleTypes.LAVA,ParticleTypes.LAVA,
            Fishes.NETHER).hot();


    public FishingLiquid(BiomeCatch defaultFish,Material fluidMaterial, ITag fluidTag, IParticleData bubbleParticle, IParticleData splashParticle, IParticleData fishingParticle,BiomeCatch... catchable) {
        this.fluidMaterial = fluidMaterial;
        this.fluidTag = fluidTag;
        this.bubbleParticle = bubbleParticle;
        this.splashParticle = splashParticle;
        this.fishingParticle = fishingParticle;
        this.defaultFish = defaultFish;
        this.catchableFishes = catchable;
    }

    public ItemStack fish(List<ItemStack> vanillaDrop, PlayerEntity player, FishingBobberEntity bobber){
        for (ItemStack drop : vanillaDrop) {
            if (FishingUtils.isFish(drop.getItem()) || !canFishTreasures){
                for (BiomeCatch catchableFish : getCatchableFishes()) {
                    if (catchableFish.getPredicate().test(new FishPredicate(bobber,this,player.world.getBiome(player.getPosition())))) {
                        ItemStack fishDrop = catchableFish.drop();
                        if (fishDrop != null) {
                            return fishDrop;
                        }
                    }
                }
                ItemStack defaultDrop = getDefaultFish().drop();
                return defaultDrop;
            }else{
                return drop;
            }
        }
        return null;
    }

    public FishingLiquid canFishTreasures(){
        canFishTreasures = true;
        return this;
    }


    public boolean isHot(){
        return hot;
    }

    public FishingLiquid hot(){
        this.hot = true;
        return this;
    }

    public Material getFluidMaterial() {
        return fluidMaterial;
    }

    public ITag getFluidTag() {
        return fluidTag;
    }

    public IParticleData getBubbleParticle() {
        return bubbleParticle;
    }

    public IParticleData getSplashParticle() {
        return splashParticle;
    }

    public IParticleData getFishingParticle() {
        return fishingParticle;
    }

    public BiomeCatch getDefaultFish(){
        return defaultFish;
    }

    public BiomeCatch[] getCatchableFishes(){
        return catchableFishes;
    }
}
