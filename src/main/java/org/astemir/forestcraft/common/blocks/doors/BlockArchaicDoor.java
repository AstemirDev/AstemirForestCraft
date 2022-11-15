package org.astemir.forestcraft.common.blocks.doors;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockArchaicDoor extends DoorBlock {

    public BlockArchaicDoor() {
        super(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(50.0F,1200F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).notSolid());
    }
}
