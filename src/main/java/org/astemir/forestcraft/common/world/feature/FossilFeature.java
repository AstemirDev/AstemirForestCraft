package org.astemir.forestcraft.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.WorldUtils;

import java.util.Random;

public class FossilFeature extends OreFeature {


    public FossilFeature() {
        super(OreFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        if (WorldUtils.isBiome(reader,pos,Biomes.DESERT) && RandomUtils.doWithChance(20)) {
            return super.generate(reader, generator, rand, pos, config);
        }
        return false;
    }

    public static class FossilRuleTest extends RuleTest {

        @Override
        public boolean test(BlockState p_215181_1_, Random p_215181_2_) {
            if (RandomUtils.doWithChance(FCConfigurationValues.FOSSIL_RATE.getValue())) {
                return p_215181_1_.equals(Blocks.SAND.getDefaultState());
            }
            return false;
        }

        @Override
        protected IRuleTestType<?> getType() {
            return IRuleTestType.ALWAYS_TRUE;
        }
    }
}
