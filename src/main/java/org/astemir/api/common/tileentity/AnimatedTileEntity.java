package org.astemir.api.common.tileentity;


import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import org.astemir.api.client.animator.IAnimated;


public abstract class AnimatedTileEntity extends TileEntity implements IAnimated, ITickableTileEntity {

    public AnimatedTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
        getFactory().reset(world);
    }
}


