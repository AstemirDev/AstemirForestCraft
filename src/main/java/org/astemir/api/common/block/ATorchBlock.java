package org.astemir.api.common.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.block.vanilla.ModOreBlock;
import org.astemir.api.common.block.vanilla.ModTorchBlock;
import org.astemir.api.common.item.AItemWallOrFloor;

import java.util.Random;
import java.util.function.Supplier;


public class ATorchBlock extends ABlock{

    protected final IParticleData particleData;
    protected final Supplier<Block> wallBlock;

    public ATorchBlock(String registryName, IParticleData particleData, Supplier<Block> wallBlock) {
        super(registryName);
        this.particleData = particleData;
        this.wallBlock = wallBlock;
        shape(Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D));
    }

    @Override
    public Item blockItem(Block block) {
        AItemWallOrFloor item = new AItemWallOrFloor(getRegistryName(),block,wallBlock);
        return item.register();
    }

    public Block build(Block.Properties properties) {
        ModTorchBlock resultItem = (ModTorchBlock) new ModTorchBlock(properties,particleData).blockConstructor(this);
        return resultItem;
    }



    @Override
    public void onClientTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(this.particleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}