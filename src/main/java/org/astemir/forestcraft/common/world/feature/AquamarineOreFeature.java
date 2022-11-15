package org.astemir.forestcraft.common.world.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import org.astemir.forestcraft.common.world.FCWorldData;

import java.util.Random;

public class AquamarineOreFeature extends OreFeature {

    public AquamarineOreFeature() {
        super(OreFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        if (FCWorldData.getData(reader.getWorld()) != null){
            boolean generateAquamarine = FCWorldData.getData(reader.getWorld()).isSpawnAquamarine();
            if (generateAquamarine){
                return super.generate(reader,generator,rand,pos,config);
            }
        }
        return false;
    }
}
