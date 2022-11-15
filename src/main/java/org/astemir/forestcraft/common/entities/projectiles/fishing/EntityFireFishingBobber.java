package org.astemir.forestcraft.common.entities.projectiles.fishing;



import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCItems;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;


public class EntityFireFishingBobber extends EntityFCFishingBobber {


    public EntityFireFishingBobber(World worldIn, PlayerEntity player, double x, double y, double z) {
        super(worldIn, player, x, y, z);
    }

    public EntityFireFishingBobber(PlayerEntity p_i50220_1_, World world, int luckBonus, int speedBonus) {
        super(p_i50220_1_, world, luckBonus, speedBonus);
    }

    @OnlyIn(Dist.CLIENT)
    public EntityFireFishingBobber(World worldIn) {
        super(worldIn, Minecraft.getInstance().player, 0, 0, 0);
    }



    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        if (this.isInLava()) {
            this.fallDistance = 0.0F;
        } else {
            super.updateFallState(y, onGroundIn, state, pos);
        }
    }

    @Override
    public boolean handleFluidAcceleration(ITag<Fluid> fluidTag, double p_210500_2_) {
        if (!fluidTag.equals(FluidTags.LAVA)) {
            doBlockCollisions();
            return super.handleFluidAcceleration(fluidTag, p_210500_2_);
        }else{
            return false;
        }
    }

    @Override
    public Item getItem() {
        return FCItems.MOLTEN_FISHING_ROD;
    }

    @Override
    public List<FishingLiquid> liquids() {
        return Arrays.asList(FishingLiquid.WATER,FishingLiquid.LAVA);
    }

    @Override
    public void tick() {
        super.tick();
        if (isInLava()){
            fallDistance = 0;
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (isInLava()){
            fallDistance = 0;
        }
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Nonnull
    @Override
    public EntityType<?> getType() {
        return FCEntities.FIRE_FISHING_BOBBER;
    }

}