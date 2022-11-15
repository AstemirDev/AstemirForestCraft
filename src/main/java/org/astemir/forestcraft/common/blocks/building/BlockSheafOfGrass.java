package org.astemir.forestcraft.common.blocks.building;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.registries.FCBlocks;

import java.util.Random;



public class BlockSheafOfGrass extends RotatedPillarBlock {

    public static final BooleanProperty WATERED = BooleanProperty.create("watered");



    public BlockSheafOfGrass() {
        super(Properties.create(Material.PLANTS).hardnessAndResistance(1f).sound(SoundType.PLANT).harvestTool(ToolType.HOE));
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.Y).with(WATERED,false));
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(WATERED,false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(WATERED));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (state.get(WATERED) == false) {
            if (RandomUtils.doWithChance(5)) {
                worldIn.setBlockState(pos, FCBlocks.GRASS_HAY.getDefaultState().with(AXIS, state.get(AXIS)), 2);
            }
        }
    }


}
