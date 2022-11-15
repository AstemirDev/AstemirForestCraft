package org.astemir.forestcraft.registries;

import net.minecraft.world.Difficulty;
import org.astemir.api.common.entity.EntityDifficultyMap;
import org.astemir.api.common.entity.EntityStats;
import org.astemir.api.math.MapBuilder;

public class FCEntityStats {

    public static final EntityDifficultyMap COSMIC_FIEND = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
              put(Difficulty.EASY,new EntityStats().health(800).damage(12).armor(10)).
              put(Difficulty.NORMAL,new EntityStats().health(1000).damage(15).armor(15)).
              put(Difficulty.HARD,new EntityStats().health(1500).damage(20).armor(20))
    );

    public static final EntityDifficultyMap AKNAYAH = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(3000).damage(30).armor(20)).
            put(Difficulty.NORMAL,new EntityStats().health(6000).damage(40).armor(30)).
            put(Difficulty.HARD,new EntityStats().health(15000).damage(50).armor(40))
    );

    public static final EntityDifficultyMap BEE_QUEEN = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(200).damage(6).armor(4)).
            put(Difficulty.NORMAL,new EntityStats().health(300).damage(7).armor(8)).
            put(Difficulty.HARD,new EntityStats().health(375).damage(9).armor(10))
    );

    public static final EntityDifficultyMap IGUANA_KING = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(275).damage(8).armor(8)).
            put(Difficulty.NORMAL,new EntityStats().health(350).damage(10).armor(10)).
            put(Difficulty.HARD,new EntityStats().health(450).damage(11).armor(12))
    );

    public static final EntityDifficultyMap ARCHAIC_SENTINEL = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(300).speed(0.45f).damage(7).armor(8)).
            put(Difficulty.NORMAL,new EntityStats().health(400).speed(0.5f).damage(9).armor(10)).
            put(Difficulty.HARD,new EntityStats().health(500).speed(0.55f).damage(12).armor(12))
    );

    public static final EntityDifficultyMap NETHER_SCOURGE = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(450).damage(8).armor(8)).
            put(Difficulty.NORMAL,new EntityStats().health(550).damage(10).armor(10)).
            put(Difficulty.HARD,new EntityStats().health(650).damage(12).armor(12))
    );

    public static final EntityDifficultyMap NETHER_SCOURGE_ENRAGED = new EntityDifficultyMap(new MapBuilder<Difficulty,EntityStats>().
            put(Difficulty.EASY,new EntityStats().health(666).damage(10).speed(0.8f).armor(10)).
            put(Difficulty.NORMAL,new EntityStats().health(766).damage(12).speed(0.8f).armor(12)).
            put(Difficulty.HARD,new EntityStats().health(966).damage(15).speed(0.8f).armor(14))
    );
}
