package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import org.astemir.api.common.block.vanilla.ModRotatedPillarBlock;
import org.astemir.api.math.MapBuilder;
import java.util.Map;

import static net.minecraft.block.RotatedPillarBlock.AXIS;


public class ARotatedPillarBlock extends ABlock{

    public ARotatedPillarBlock(String registryName) {
        super(registryName);
        states(new MapBuilder<Property,Object>().put(AXIS, Direction.Axis.Y).build());
    }

    @Override
    public Map<Property, Object> getPlacementState(BlockItemUseContext context) {
        return new MapBuilder<Property,Object>().put(AXIS,context.getFace().getAxis()).build();
    }


    public Block build(Block.Properties properties) {
        ModRotatedPillarBlock resultItem = (ModRotatedPillarBlock) new ModRotatedPillarBlock(properties).blockConstructor(this);
        return resultItem;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch(state.get(AXIS)) {
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }
}