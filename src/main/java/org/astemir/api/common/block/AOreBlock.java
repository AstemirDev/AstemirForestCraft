package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.astemir.api.common.block.vanilla.ModHorizontalBlock;
import org.astemir.api.common.block.vanilla.ModOreBlock;
import org.astemir.api.math.RandomUtils;

import static net.minecraft.block.HorizontalBlock.HORIZONTAL_FACING;


public class AOreBlock extends ABlock{

    private int minExpDrop = 0;
    private int maxExpDrop = 0;

    public AOreBlock(String registryName) {
        super(registryName);
    }


    public Block build(Block.Properties properties) {
        ModOreBlock resultItem = (ModOreBlock) new ModOreBlock(properties).blockConstructor(this);
        return resultItem;
    }

    public AOreBlock expDrop(int min,int max){
        this.minExpDrop = min;
        this.maxExpDrop = max;
        return this;
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return RandomUtils.randomInt(minExpDrop,maxExpDrop);
    }
}