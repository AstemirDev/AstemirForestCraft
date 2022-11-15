package org.astemir.forestcraft.common.blocks.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.common.entities.monsters.EntityInfectedZombie;
import org.astemir.forestcraft.registries.FCParticles;

import java.util.Random;


public class BlockSporum extends MushroomBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);


    public BlockSporum() {
        super(Properties.create(Material.PLANTS).hardnessAndResistance(0.05f).sound(SoundType.VINE).notSolid().doesNotBlockMovement());
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!(entityIn instanceof EntityInfectedZombie)) {
            if (!worldIn.isRemote) {
                worldIn.destroyBlock(pos, false, entityIn);
                createSporeCloud(worldIn, pos);
            } else {
                worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_SPIDER_DEATH, SoundCategory.BLOCKS, 2, 0.5f, false);
            }
        }
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        if (!((World)worldIn).isRemote){
            createSporeCloud((World)worldIn,pos);
        }else{
            ((World)worldIn).playSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.ENTITY_SPIDER_DEATH, SoundCategory.BLOCKS,2,0.5f,false);
        }
    }

    public void createSporeCloud(World worldIn,BlockPos pos){
        AreaEffectCloudEntity cloud = EntityType.AREA_EFFECT_CLOUD.create(worldIn);
        cloud.setRadius(1.5f);
        cloud.setParticleData(FCParticles.SPORE.get());
        cloud.setDuration(400);
        cloud.setWaitTime(0);
        cloud.setRadiusOnUse(-0.005f);
        cloud.setRadiusPerTick(-0.005f);
        cloud.addEffect(new EffectInstance(Effects.NAUSEA, 200, 0, false, false));
        cloud.addEffect(new EffectInstance(Effects.POISON, 200, 3, false, false));
        cloud.moveToBlockPosAndAngles(pos.add(0, 0.1, 0), 0, 0);
        worldIn.addEntity(cloud);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vector3d vector3d = state.getOffset(worldIn, pos);
        return SHAPE.withOffset(vector3d.x, vector3d.y, vector3d.z);
    }


    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock().equals(Blocks.GRASS_BLOCK) || blockstate.getBlock().equals(Blocks.STONE)) {
            return true;
        } else {
            return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        }
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }
}
