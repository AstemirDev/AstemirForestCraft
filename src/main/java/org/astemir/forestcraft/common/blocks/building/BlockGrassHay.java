package org.astemir.forestcraft.common.blocks.building;


import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import org.astemir.forestcraft.common.blocks.IFuel;


public class BlockGrassHay extends RotatedPillarBlock implements IFuel {


    public BlockGrassHay() {
        super(Properties.create(Material.PLANTS).hardnessAndResistance(1f).sound(SoundType.PLANT).harvestTool(ToolType.HOE));
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.Y));
    }

    @Override
    public int getBurnTime() {
        return 600;
    }
}
