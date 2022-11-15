package org.astemir.forestcraft.common.blocks.building;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.IForgeShearable;


public class BlockInsaneWool extends Block implements IForgeShearable {


    public BlockInsaneWool() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(1f).sound(SoundType.CLOTH));
    }


}
