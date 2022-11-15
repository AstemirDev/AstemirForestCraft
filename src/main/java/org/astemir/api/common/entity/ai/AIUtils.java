package org.astemir.api.common.entity.ai;


import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;

import java.util.Optional;
import java.util.function.Predicate;


public class AIUtils {

    public static BlockPos nearestBlockPos(BlockPos pos, int width, int height, Predicate<BlockPos> predicate){
        Optional<BlockPos> optional = BlockPos.getClosestMatchingPosition(pos,width,height,(b)->{
            if (predicate.test(b)){
                return true;
            }
            return false;
        });
        if (optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }

    public static boolean moveToPoint(Entity entity, Vector3 destPos,float speed,boolean syncRotation,boolean canFly,Runnable taskEnd){
        Vector3 pos = EntityUtils.position(entity);
        Vector3 direction = pos.direction(destPos);
        Vector3 velocity = direction.mul(speed);
        if (syncRotation){
            Vector2 rot = direction.yawPitchDegrees();
            float rotX = -rot.x;
            float rotY = rot.y;
            EntityUtils.setEntityRotation(entity,rotX,rotY);
        }
        if (!canFly && entity.isOnGround()){
            velocity.setY(0);
            if (destPos.getY()-pos.getY() > 0 && (pos.distanceXToSquared(destPos) < (double)2.5000003E-7F && pos.distanceZToSquared(destPos) < (double)2.5000003E-7F)){
                Vector3 oldMotion = EntityUtils.motion(entity);
                EntityUtils.setMotion(entity, oldMotion.getX(), speed*4f, oldMotion.getZ());
            }
        }
        if (pos.squareDistanceTo(destPos) < (double)2.5000003E-7F){
            if (taskEnd != null) {
                taskEnd.run();
            }
            return false;
        }else {
            EntityUtils.addMotion(entity,velocity);
        }
        return true;
    }

    public static boolean moveToPoint(Entity entity,Vector3 position,float speed,boolean syncRotation,boolean canFly){
        return moveToPoint(entity,position,speed,syncRotation,canFly,null);
    }

}
