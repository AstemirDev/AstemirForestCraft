package org.astemir.api.common.item;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import org.astemir.api.common.item.vanilla.ModWallOrFloorItem;

import java.util.function.Supplier;


public class AItemWallOrFloor extends AItemBlockItem{

    protected final Supplier<Block> wallBlock;

    public AItemWallOrFloor(String name,Block floorBlock, Supplier<Block> wallBlockIn) {
        super(name,floorBlock);
        this.wallBlock = wallBlockIn;
    }

    @Override
    public Item build(Item.Properties properties) {
        ModWallOrFloorItem blockItem = new ModWallOrFloorItem(getBlock(),wallBlock.get(),properties).itemConstructor(this);
        return blockItem;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = this.wallBlock.get().getStateForPlacement(context);
        BlockState blockstate1 = null;
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction != Direction.UP) {
                BlockState blockstate2 = direction == Direction.DOWN ? this.getBlock().getStateForPlacement(context) : blockstate;
                if (blockstate2 != null && blockstate2.isValidPosition(iworldreader, blockpos)) {
                    blockstate1 = blockstate2;
                    break;
                }
            }
        }
        return blockstate1 != null && iworldreader.placedBlockCollides(blockstate1, blockpos, ISelectionContext.dummy()) ? blockstate1 : null;
    }
}
