package org.astemir.forestcraft.common.blocks.plants;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.api.math.RandomUtils;

import java.util.Random;

public class BlockGrownDandelion extends FlowerBlock {

    public BlockGrownDandelion() {
        super(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(50) == 0) {
            for (int i = 0;i<5;i++) {
                Vector3d offset = stateIn.getOffset(worldIn,pos);
                worldIn.addParticle(FCParticles.DANDELION_SEED.get(), pos.getX()-offset.x , pos.getY() , pos.getZ()-offset.z, RandomUtils.randomFloat(-0.1f,0.1f), RandomUtils.randomFloat(0.5f, 0.65f), RandomUtils.randomFloat(-0.1f,0.1f));
            }
        }
    }
}
