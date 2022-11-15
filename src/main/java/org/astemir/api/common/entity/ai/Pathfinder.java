package org.astemir.api.common.entity.ai;

import net.minecraft.block.BlockState;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.math.Vector3;
import java.util.LinkedList;

public class Pathfinder {

    public static LinkedList<BlockPos> points(World world,BlockPos startPos, BlockPos destinationPos) {
        LinkedList<BlockPos> linkedList = new LinkedList<>();
        while (!(startPos.getX() == destinationPos.getX() && startPos.getY() == destinationPos.getY() && startPos.getZ() == destinationPos.getZ())){
            startPos = minDistBlockPos(world,startPos,destinationPos);
            linkedList.add(startPos);
        }
        return linkedList;
    }


    public static BlockPos minDistBlockPos(World world,BlockPos from, BlockPos to){
        BlockPos res = null;
        float minDist = Float.MAX_VALUE;
        for (int i = -1;i<=1;i++){
            for (int j = -1;j<=1;j++){
                for (int k = -1;k<=1;k++){
                    if (!(i == 0 && j == 0 && k == 0)){
                        Vector3 vector = Vector3.from(from.add(i,j,k));
                        BlockPos pos = new BlockPos(vector.getX(),vector.getY(),vector.getZ());
                        BlockState state = world.getBlockState(pos);
                        if (!state.isSolid()){
                            float dist = vector.distanceTo(Vector3.from(to));
                            if (dist < minDist){
                                minDist = dist;
                                res = pos;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
