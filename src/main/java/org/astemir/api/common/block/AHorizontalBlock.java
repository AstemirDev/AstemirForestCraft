package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import org.astemir.api.common.block.vanilla.ModHorizontalBlock;


import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;


public class AHorizontalBlock extends ABlock{

    public AHorizontalBlock(String registryName) {
        super(registryName);
    }


    public Block build(Block.Properties properties) {
        ModHorizontalBlock resultItem = (ModHorizontalBlock) new ModHorizontalBlock(properties).blockConstructor(this);
        return resultItem;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }
}