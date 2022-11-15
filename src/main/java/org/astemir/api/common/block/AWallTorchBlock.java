package org.astemir.api.common.block;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.astemir.api.common.block.vanilla.ModTorchBlock;
import org.astemir.api.common.block.vanilla.ModWallTorch;
import org.astemir.api.math.MapBuilder;
import org.astemir.forestcraft.registries.FCNewBlocks;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;


public class AWallTorchBlock extends ABlock{

    protected final IParticleData particleData;
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

    public AWallTorchBlock(String registryName, IParticleData particleData) {
        super(registryName);
        this.particleData = particleData;
        shape(Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D));
        states(new MapBuilder().put(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH).build());
    }


    @Override
    public boolean isNeedToRegisterItem() {
        return false;
    }

    public Block build(Block.Properties properties) {
        ModWallTorch resultItem = (ModWallTorch) new ModWallTorch(properties,particleData).blockConstructor(this);
        return resultItem;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(WallTorchBlock.HORIZONTAL_FACING));
    }

    @Override
    public Item getBlockItem() {
        return FCNewBlocks.SKY_TORCH.asItem();
    }

    @Override
    public <T extends Comparable<T>, V extends T> BlockState placement(Block block, BlockItemUseContext context) {
        BlockState blockstate = block.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for(Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.with(WallTorchBlock.HORIZONTAL_FACING, direction1);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(WallTorchBlock.HORIZONTAL_FACING, rot.rotate(state.get(WallTorchBlock.HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(WallTorchBlock.HORIZONTAL_FACING)));
    }

    @Override
    public void onClientTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(this.particleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}