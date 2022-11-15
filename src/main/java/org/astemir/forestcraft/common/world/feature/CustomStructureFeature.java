package org.astemir.forestcraft.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.stream.Stream;

public class CustomStructureFeature extends Feature<NoFeatureConfig> {

    public CustomStructureFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        return false;
    }


    public <T extends TileEntity> T setTileEntity(IWorld world, BlockState state, BlockPos pos, Class<T> tileEntityClass){
        world.setBlockState(pos,state,2);
        return (T)world.getTileEntity(pos);
    }

    public Stream<BlockPos> generateSquare(BlockPos position, int height, int width){
        return BlockPos.getAllInBox(position.add(-width/2,0,-width/2),position.add(width/2,height,width/2)).map(BlockPos::toImmutable);
    }

    public Stream<BlockPos> createSphere(BlockPos position, int radius) {
        int j = radius;
        int k = radius / 2;
        int l = radius;
        float f = (float) (j + k + l) * 0.333F + 0.5F;
        return BlockPos.getAllInBox(position.add(-j, -k, -l), position.add(j, k, l)).map(BlockPos::toImmutable).filter((blockPos)->{
            if (blockPos.distanceSq(position) <= (double) (f * f)) {
                return true;
            }else{
                return false;
            }
        });
    }
}
