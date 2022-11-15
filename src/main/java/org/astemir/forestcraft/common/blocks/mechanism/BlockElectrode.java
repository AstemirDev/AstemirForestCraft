package org.astemir.forestcraft.common.blocks.mechanism;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;
import org.astemir.api.math.RandomUtils;

import java.util.Random;


public class BlockElectrode extends Block {

    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final DirectionProperty FACING = DirectionalBlock.FACING;


    public BlockElectrode() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1f).sound(SoundType.STONE).setRequiresTool().harvestTool(ToolType.PICKAXE));
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(false)));
    }



    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }


    public Vector3i getDirection(BlockState state) {
        Direction direction = state.get(FACING);
        return new Vector3i(direction.getXOffset(), direction.getYOffset(), direction.getZOffset());
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        if (id == 0){
            worldIn.playSound(pos.getX(),pos.getY(),pos.getZ(), FCSounds.ELECTRON_HIT.get(), SoundCategory.BLOCKS,1,RandomUtils.randomFloat(1.8f,2f),false);
        }
        return super.eventReceived(state, worldIn, pos, id, param);
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
                    BlockPos cloudPos = pos;
                    Vector3i direction = getDirection(state);
                    createElectroCloud(worldIn,new Vector3d(cloudPos.getX()+0.5+direction.getX(),cloudPos.getY()+direction.getY(),cloudPos.getZ()+0.5+direction.getZ()));
                }
            }
        }
    }

    public void createElectroCloud(World worldIn,Vector3d position){
        if (!worldIn.isRemote) {
            AreaEffectCloudEntity cloud = EntityType.AREA_EFFECT_CLOUD.create(worldIn);
            cloud.setRadius(1f);
            cloud.setParticleData(FCParticles.ELECTRO.get());
            cloud.setDuration(10);
            cloud.setWaitTime(0);
            cloud.setRadiusOnUse(-0.001f);
            cloud.setRadiusPerTick(-0.001f);
            cloud.addEffect(new EffectInstance(FCEffects.ELECTROCUT.get(), 20, 0, false, false));
            cloud.setLocationAndAngles(position.x,position.y,position.z,0,0);
            worldIn.addEntity(cloud);
        }
    }


    public boolean canBreak(World world,BlockPos blockPos){
        BlockState block = world.getBlockState(blockPos).getBlock().getDefaultState();
        if (block.getFluidState() != Fluids.EMPTY.getDefaultState() || block.equals(FCBlocks.BLOCK_EXCAVATOR.getDefaultState()) || block.equals(Blocks.REDSTONE_WIRE.getDefaultState()) || block.equals(Blocks.DAYLIGHT_DETECTOR.getDefaultState()) || block.equals(Blocks.REPEATER.getDefaultState()) || block.equals(Blocks.REDSTONE_WALL_TORCH.getDefaultState()) || block.equals(Blocks.REDSTONE_TORCH.getDefaultState()) || block.equals(Blocks.REDSTONE_BLOCK.getDefaultState()) || block.equals(Blocks.LEVER.getDefaultState()) || block.equals(Blocks.BARRIER.getDefaultState()) || block.equals(Blocks.BEDROCK.getDefaultState()) || block.equals(Blocks.OBSIDIAN.getDefaultState())){
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
        builder.add(LIT).add(FACING);
    }

}
