package org.astemir.forestcraft.common.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;


public class BlockMoltenBlock extends Block {


    public BlockMoltenBlock() {
        super(Properties.create(Material.IRON).hardnessAndResistance(2f).sound(SoundType.METAL).setRequiresTool().harvestTool(ToolType.PICKAXE).setLightLevel((v)->{return 4;}));
    }


    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity){
            ((LivingEntity)entityIn).setFire(200);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }


}
