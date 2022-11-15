package org.astemir.api.common.item;


import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import org.astemir.api.common.item.vanilla.ModPickaxeItem;


public class AItemPickaxe extends AItemTool{

    public AItemPickaxe(String registryName,IItemTier tier, float damage, float speed) {
        super(registryName,tier,damage,speed);
        addToolType(AItemToolType.PICKAXE);
    }

    public AItemPickaxe(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,-2.8f);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModPickaxeItem pickaxeItem = (ModPickaxeItem) new ModPickaxeItem(getTier(), properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return pickaxeItem;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
            return i >= blockIn.getHarvestLevel();
        }
        Material material = blockIn.getMaterial();
        return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }

}
