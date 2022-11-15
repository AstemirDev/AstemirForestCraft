package org.astemir.forestcraft.common.blocks.building;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.astemir.forestcraft.registries.FCEffects;


public class BlockSharpedLeaves extends Block {

    private static final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(1D, 1D, 1D, 15.0D, 15D, 15D);
    private static final VoxelShape OUTLINE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);


    public BlockSharpedLeaves() {
        super(Properties.create(Material.LEAVES).setRequiresTool().harvestTool(ToolType.HOE).hardnessAndResistance(5).sound(SoundType.VINE).notSolid().setOpaque(BlockSharpedLeaves::isntSolid).setBlocksVision(BlockSharpedLeaves::isntSolid).setSuffocates(BlockSharpedLeaves::isntSolid).harvestLevel(2));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.5F);
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(FCEffects.BROKEN_ARMOR.get(), 10, 1));
            }
        }
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return OUTLINE_SHAPE;
    }


    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return OUTLINE_SHAPE;
    }



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPE;
    }
}
