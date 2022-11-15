package org.astemir.forestcraft.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.ChestTileEntity;
import org.astemir.forestcraft.common.blocks.tileentities.BlockAncientChest;
import org.astemir.forestcraft.registries.FCTileEntities;


public class TileEntityAncientChest extends ChestTileEntity {


    public TileEntityAncientChest() {
        super(FCTileEntities.ANCIENT_CHEST);
    }

    @Override
    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof BlockAncientChest) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }

    }
}
