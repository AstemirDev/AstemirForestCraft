package org.astemir.forestcraft.common.blocks.mechanism;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;


public class BlockBlockExcavator extends Block {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;


    public BlockBlockExcavator() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1f).sound(SoundType.STONE).setRequiresTool().harvestTool(ToolType.PICKAXE));
        this.setDefaultState(this.getDefaultState().with(LIT, Boolean.valueOf(false)));
    }

    private void destroy(BlockPos pos,World worldIn){
        AxisAlignedBB bb = new AxisAlignedBB(pos);
        BlockPos.getAllInBox(bb.grow(1,1,1).offset(new Vector3d(0,-1,0))).forEach((block)->{
            if (!worldIn.isAirBlock(block)) {
                if (RandomUtils.doWithChance(75)) {
                    if (canBreak(worldIn,block) && worldIn.getGameRules().getBoolean(GameRules.MOB_GRIEFING) == true) {
                        FallingBlockEntity blockEntity = new FallingBlockEntity(worldIn, block.getX(), block.getY() + 1f, block.getZ(),worldIn.getBlockState(block));
                        blockEntity.fallTime = 1;
                        blockEntity.setMotion(RandomUtils.randomFloat(-0.5f, 0.5f), RandomUtils.randomFloat(0.5f, 0.75f), RandomUtils.randomFloat(-0.5f, 0.5f));
                        worldIn.addEntity(blockEntity);
                        worldIn.destroyBlock(block, false);
                    }
                }
            }
        });
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            boolean flag = state.get(LIT);
            if (flag != worldIn.isBlockPowered(pos)) {
                if (flag) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                }else {
                    worldIn.setBlockState(pos, state.func_235896_a_(LIT), 2);
                    destroy(pos,worldIn);
                }
            }
        }
    }


    public boolean canBreak(World world,BlockPos blockPos){
        BlockState block = world.getBlockState(blockPos).getBlock().getDefaultState();
        if (block.equals(Blocks.OBSERVER.getDefaultState()) || block.equals(Blocks.SLIME_BLOCK.getDefaultState()) || block.equals(Blocks.STICKY_PISTON.getDefaultState()) || block.equals(Blocks.MOVING_PISTON.getDefaultState()) || block.equals(Blocks.PISTON_HEAD.getDefaultState()) || block.equals(Blocks.PISTON.getDefaultState()) || block.getFluidState() != Fluids.EMPTY.getDefaultState() || block.equals(FCBlocks.BLOCK_EXCAVATOR.getDefaultState()) || block.equals(Blocks.REDSTONE_WIRE.getDefaultState()) || block.equals(Blocks.DAYLIGHT_DETECTOR.getDefaultState()) || block.equals(Blocks.REPEATER.getDefaultState()) || block.equals(Blocks.REDSTONE_WALL_TORCH.getDefaultState()) || block.equals(Blocks.REDSTONE_TORCH.getDefaultState()) || block.equals(Blocks.REDSTONE_BLOCK.getDefaultState()) || block.equals(Blocks.LEVER.getDefaultState()) || block.equals(Blocks.BARRIER.getDefaultState()) || block.equals(Blocks.BEDROCK.getDefaultState()) || block.equals(Blocks.OBSIDIAN.getDefaultState())){
            return false;
        }
        return true;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (state.get(LIT) && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, state.func_235896_a_(LIT), 2);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

}
