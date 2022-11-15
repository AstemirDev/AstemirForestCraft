package org.astemir.forestcraft.common.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.common.entities.monsters.EntityAlphaInsaneDog;

public class AlphaInsaneDogLaserGoal extends Goal {

    private int timer = 0;
    private final EntityAlphaInsaneDog dog;

    public AlphaInsaneDogLaserGoal(EntityAlphaInsaneDog dog){
        this.dog = dog;
    }


    @Override
    public boolean shouldExecute() {
        return dog.getAttackTarget() != null;
    }

    @Override
    public void tick() {
        if (timer <= 0){
            if (dog.getDistanceSq(dog.getAttackTarget()) < 10) {
                timer = 150+ RandomUtils.randomInt(100);
                dog.setLazerTicks(60);
                dog.getFactory().playAnimation(EntityAlphaInsaneDog.LASER);
            }
        }else{
            timer--;
        }
    }

    @Override
    public void startExecuting() {
        timer = 250+RandomUtils.randomInt(100);
    }


    @Override
    public boolean shouldContinueExecuting() {
        return dog.getAttackTarget() != null;
    }
}
