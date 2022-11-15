package org.astemir.forestcraft.common.world.feature;

import net.minecraft.block.*;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class CrystalCaveFeature extends CustomStructureFeature {

    private static final BlockState PEARLSTONE = FCBlocks.PEARLSTONE.getDefaultState();
    private static final BlockState SHINY_PEARLSTONE = FCBlocks.SHINY_PEARLSTONE.getDefaultState();
    private static final BlockState MOSSY_PEARLSTONE = FCBlocks.MOSSY_PEARLSTONE.getDefaultState();
    private static final BlockState GEM_ORE = FCBlocks.GEM_ORE.getDefaultState();
    private static final BlockState GEM_CRYSTAL = FCBlocks.GEM_CRYSTAL.getDefaultState();


    public CrystalCaveFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }




    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (reader.getWorld().getDimensionKey() == World.OVERWORLD && pos.getY() < FCConfigurationValues.CRYSTAL_BIOME_HEIGHT.getValue()) {
            if (rand.nextInt(200- FCConfigurationValues.CRYSTAL_BIOME_RATE.getValue()) == 0) {
                BlockPos centerPos = new BlockPos(pos.getX(), RandomUtils.randomInt(FCConfigurationValues.CRYSTAL_BIOME_HEIGHT.getValue()-5, FCConfigurationValues.CRYSTAL_BIOME_HEIGHT.getValue()), pos.getZ());
                createSphere(centerPos, 16).forEach((blockPos) -> {
                    reader.setBlockState(blockPos, randomCaveBlock(rand), 2);
                });
                createSphere(centerPos, 15).forEach((blockPos) -> {
                    reader.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 2);
                });


                createSphere(centerPos, 16).forEach((blockPos) -> {
                    if (blockPos.getY() == centerPos.getY()-7) {
                        if (rand.nextInt(25) == 0) {
                            generateStalagmite(reader,blockPos,rand);
                        }
                        if (rand.nextInt(10) == 0) {
                            if (!reader.getBlockState(blockPos).isAir()) {
                                generateCrystalUp(reader, blockPos);
                            }
                        }
                    }
                    if (blockPos.getY() == centerPos.getY()+7) {
                        if (rand.nextInt(25) == 0) {
                            generateStalactite(reader,blockPos,rand);
                        }
                        if (rand.nextInt(10) == 0) {
                            if (!reader.getBlockState(blockPos).isAir()) {
                                generateCrystalDown(reader, blockPos);
                            }
                        }
                    }
                });

                createSphere(centerPos, 16).forEach((blockPos) -> {
                    if (blockPos.getY() == centerPos.getY()-7) {
                        if (rand.nextInt(25) == 0) {
                            generateStalagmite(reader, blockPos,rand);
                        }
                    }
                });
            }
        }
        return false;
    }

    private BlockState randomCaveBlock(Random rand){
        return rand.nextInt(25) == 0 ? GEM_ORE : rand.nextInt(20) == 0 ? SHINY_PEARLSTONE : rand.nextInt(5) == 0 ? MOSSY_PEARLSTONE : PEARLSTONE;
    }

    private void generateStalagmite(IWorld world,BlockPos pos,Random random){
        world.setBlockState(pos.add(1,0,0),randomCaveBlock(random),2);
        world.setBlockState(pos.add(-1,0,0),randomCaveBlock(random),2);
        world.setBlockState(pos.add(0,0,1),randomCaveBlock(random),2);
        world.setBlockState(pos.add(0,0,-1),randomCaveBlock(random),2);
        for (int i = 0;i<random.nextInt(8);i++) {
            BlockState state = randomCaveBlock(random);
            world.setBlockState(pos.add(0,i,0), state, 1);
        }
    }

    private void generateStalactite(IWorld world,BlockPos pos,Random random){
        world.setBlockState(pos.add(1,0,0),randomCaveBlock(random),2);
        world.setBlockState(pos.add(-1,0,0),randomCaveBlock(random),2);
        world.setBlockState(pos.add(0,0,1),randomCaveBlock(random),2);
        world.setBlockState(pos.add(0,0,-1),randomCaveBlock(random),2);
        for (int i = 0;i<random.nextInt(8);i++) {
            BlockState state = randomCaveBlock(random);
            world.setBlockState(pos.add(0,-i,0), state, 1);
        }
    }


    public void generateCrystalUp(ISeedReader world,BlockPos pos){
        world.setBlockState(pos.up(), GEM_CRYSTAL.with(HorizontalBlock.HORIZONTAL_FACING,Direction.NORTH).with(HorizontalFaceBlock.FACE,AttachFace.FLOOR), 1);
    }

    public void generateCrystalDown(ISeedReader world,BlockPos pos){
        world.setBlockState(pos.down(), GEM_CRYSTAL.with(HorizontalBlock.HORIZONTAL_FACING,Direction.NORTH).with(HorizontalFaceBlock.FACE,AttachFace.CEILING), 1);
    }

}
