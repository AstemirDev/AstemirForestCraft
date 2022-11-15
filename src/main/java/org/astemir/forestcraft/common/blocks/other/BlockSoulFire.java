package org.astemir.forestcraft.common.blocks.other;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class BlockSoulFire extends SoulFireBlock {

    public BlockSoulFire() {

        super(AbstractBlock.Properties.create(Material.FIRE, MaterialColor.LIGHT_BLUE).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) -> {
            return 10;
        }).sound(SoundType.CLOTH).tickRandomly());
    }


    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || areNeighborsFlammable(worldIn, pos);
    }


    public boolean isSoulSandOrNetherrack(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).getBlock().equals(Blocks.SOUL_SOIL) || worldIn.getBlockState(blockpos).getBlock().equals(Blocks.SOUL_SAND) || worldIn.getBlockState(blockpos).getBlock().equals(Blocks.NETHERRACK);
    }

    public boolean canCatchFire(IBlockReader world, BlockPos pos, Direction face) {
        return world.getBlockState(pos).isFlammable(world, pos, face);
    }

    private boolean areNeighborsFlammable(IBlockReader worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (canCatchFire(worldIn, pos.offset(direction), direction.getOpposite())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, getTickCooldown(worldIn.rand));
        if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (!isSoulSandOrNetherrack(state,worldIn,pos)) {
                worldIn.removeBlock(pos, false);
            }
        }
    }

    private static int getTickCooldown(Random rand) {
        return 30 + rand.nextInt(10);
    }

}
