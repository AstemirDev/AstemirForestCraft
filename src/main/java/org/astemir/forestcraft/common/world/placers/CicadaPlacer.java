package org.astemir.forestcraft.common.world.placers;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.entities.animals.EntityCicada;
import org.astemir.forestcraft.registries.FCEntities;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class CicadaPlacer extends BlockPlacer {

    @Override
    protected BlockPlacerType<?> getBlockPlacerType() {
        return BlockPlacerType.SIMPLE_BLOCK;
    }

    public EntityCicada spawnCicada(BlockPos pos, IWorld world){
        EntityCicada cicada = FCEntities.CICADA.create(((WorldGenRegion)world).getWorld());
        cicada.moveToBlockPosAndAngles(pos,0,0);
        world.addEntity(cicada);
        return cicada;
    }

    @Override
    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        if (RandomUtils.doWithChance(5) && RandomUtils.doWithChance(FCConfigurationValues.CICADAS_RATE.getValue())) {
            if (pos.getY() > world.getSeaLevel()) {
                for (int i = 0; i < RandomUtils.randomInt(4, 7); i++) {
                    int height = RandomUtils.randomInt(0,10);
                    int x = RandomUtils.randomInt(-5,5);
                    int z = RandomUtils.randomInt(-5,5);
                    if (world.getBlockState(pos.add(x,height,z)).isAir()) {
                        spawnCicada(pos.add(x,height,z), world);
                    }
                }
            }
        }
    }
}
