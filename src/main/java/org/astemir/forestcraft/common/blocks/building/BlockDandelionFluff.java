package org.astemir.forestcraft.common.blocks.building;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IForgeShearable;


public class BlockDandelionFluff extends Block implements IForgeShearable {

    private static final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(4D, 4D, 4D, 12.0D, 12D, 12D);

    public BlockDandelionFluff() {
        super(Properties.create(Material.WOOL).sound(SoundType.CLOTH).notSolid());
    }


    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.onLivingFall(fallDistance, 0F);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return COLLISION_SHAPE;
    }
}
