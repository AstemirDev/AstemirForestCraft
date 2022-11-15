package org.astemir.forestcraft.common.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

public class FCWorldData extends WorldSavedData {

    private static final String IDENTIFIER = "forestcraft_general";
    private World world;
    private boolean spawnAquamarine = false;
    private boolean runestoneLordKilled = false;


    public FCWorldData() {
        super(IDENTIFIER);
    }

    public FCWorldData(World world) {
        super(IDENTIFIER);
        this.world = world;
        this.markDirty();
    }

    public static FCWorldData getData(World world) {
        if (world instanceof ServerWorld) {
            ServerWorld overworld = world.getServer().getWorld(world.getDimensionKey());
            DimensionSavedDataManager storage = overworld.getSavedData();
            FCWorldData data = storage.getOrCreate(FCWorldData::new, IDENTIFIER);
            if (data != null) {
                data.world = world;
                data.markDirty();
            }
            return data;
        }
        return null;
    }

    public boolean isSpawnAquamarine() {
        return spawnAquamarine;
    }

    public boolean isRunestoneLordKilled() {
        return runestoneLordKilled;
    }

    public void setSpawnAquamarine(boolean spawnAquamarine) {
        this.spawnAquamarine = spawnAquamarine;
    }

    public void setRunestoneLordKilled(boolean runestoneLordKilled) {
        this.runestoneLordKilled = runestoneLordKilled;
    }


    public void read(CompoundNBT nbt) {
        spawnAquamarine = nbt.getBoolean("spawnAquamarine");
        runestoneLordKilled = nbt.getBoolean("runestoneLordKilled");
    }

    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("spawnAquamarine",spawnAquamarine);
        compound.putBoolean("runestoneLordKilled",runestoneLordKilled);
        return compound;
    }

}
