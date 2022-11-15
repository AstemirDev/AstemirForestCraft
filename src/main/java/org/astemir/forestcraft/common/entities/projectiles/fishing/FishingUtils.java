package org.astemir.forestcraft.common.entities.projectiles.fishing;


import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import java.util.Random;

public class FishingUtils {

    public static boolean isFish(Item item){
        if (item == Items.PUFFERFISH || item == Items.TROPICAL_FISH || item == Items.COD || item == Items.SALMON){
            return true;
        }
        return false;
    }

    public static boolean isValidLightLevel(World worldIn, BlockPos pos, Random randomIn) {
        if (worldIn.getLightFor(LightType.SKY, pos) > randomIn.nextInt(32)) {
            return false;
        } else {
            int i = worldIn.isThundering() ? worldIn.getNeighborAwareLightSubtracted(pos, 10) : worldIn.getLight(pos);
            return i <= randomIn.nextInt(8);
        }
    }
}
