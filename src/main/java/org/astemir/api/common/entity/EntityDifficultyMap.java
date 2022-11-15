package org.astemir.api.common.entity;

import net.minecraft.world.Difficulty;
import org.astemir.api.math.MapBuilder;

import java.util.HashMap;
import java.util.Map;

public class EntityDifficultyMap {

    private Map<Difficulty,EntityStats> stats = new HashMap<>();

    public EntityDifficultyMap(MapBuilder<Difficulty,EntityStats> builder) {
        this.stats = builder.build();
    }


    public EntityStats getStats(Difficulty difficulty){
        if (stats.containsKey(difficulty)) {
            return stats.get(difficulty);
        }else{
            return stats.values().stream().findFirst().get();
        }
    }
}
