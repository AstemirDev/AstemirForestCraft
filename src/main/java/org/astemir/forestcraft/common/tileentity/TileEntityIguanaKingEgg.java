package org.astemir.forestcraft.common.tileentity;


import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.server.ServerWorld;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityIguanaKing;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.forestcraft.registries.FCTileEntities;


public class TileEntityIguanaKingEgg extends TileEntity implements ITickableTileEntity {

    private long ticks = 0;


    public TileEntityIguanaKingEgg(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TileEntityIguanaKingEgg() {
        this(FCTileEntities.IGUANA_KING_EGG);
    }

    public void spawnIguanaKing(PlayerEntity player){
        EntityIguanaKing iguanaKing = new EntityIguanaKing(FCEntities.IGUANA_KING,world);
        iguanaKing.moveToBlockPosAndAngles(pos, (float) ((Math.PI)-player.rotationYaw),0);
        if (world instanceof ServerWorld) {
            iguanaKing.onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(pos), SpawnReason.NATURAL, null, null);
        }
        world.addEntity(iguanaKing);
    }



    @Override
    public void tick() {
        ticks++;
    }


    public long getTicks() {
        return ticks;
    }
}
