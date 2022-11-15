package org.astemir.forestcraft.configuration;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FCConfigurationValues {

    //Generation
    public static ConfigBoolean SPAWN_BEE_HIVE = newBool(true,"config.forestcraft.beehive","Generate Bee Queen hives in the world?");
    public static ConfigBoolean SPAWN_DEEP_ROCK = newBool(true,"config.forestcraft.deep_rock","Spawn Deep rock in the world?");
    public static ConfigBoolean SPAWN_CRYSTAL_BIOME = newBool(true,"config.forestcraft.crystal_biome","Generate Crystal Biome in the world?");
    public static ConfigBoolean SPAWN_DOGS = newBool(true,"config.forestcraft.dogs","Generate Dogs Lairs in the world?");
    public static ConfigBoolean SPAWN_BLUEBERRY = newBool(true,"config.forestcraft.blueberry","Generate Blueberry in the world?");
    public static ConfigBoolean SPAWN_SHARP_LEAVES = newBool(true,"config.forestcraft.sharp_leaves","Generate Sharp Leaves in the world?");
    public static ConfigBoolean SPAWN_SPORE_MUSH = newBool(true,"config.forestcraft.spore_mush","Generate Spore Mushrooms in world?");
    public static ConfigBoolean SPAWN_ANCIENT_TEMPLES = newBool(true,"config.forestcraft.ancient_temples","Generate Ancient Temples?");
    public static ConfigBoolean SPAWN_SNOWBERRY = newBool(true,"config.forestcraft.snowberry","Generate Snowberries in world?");
    public static ConfigBoolean SPAWN_FOSSIL = newBool(true,"config.forestcraft.fossil","Generate Fossils in the desert?");
    public static ConfigBoolean SPAWN_DESERT_BONES = newBool(true,"config.forestcraft.bones","Generate Bones in the desert?");
    public static ConfigBoolean SPAWN_LIGHT_BULBS = newBool(true,"config.forestcraft.light_bulbs","Generate Light Bulbs in the caves?");
    public static ConfigBoolean SPAWN_DANDELION_HILLS = newBool(true,"config.forestcraft.dandelion_hills","Generate Dandelion Hills in the world?");
    public static ConfigBoolean SPAWN_DANDELIONS = newBool(true,"config.forestcraft.dandelions","Generate Dandelions in the world?");
    public static ConfigBoolean SPAWN_DANDELION_FIELDS = newBool(true,"config.forestcraft.dandelion_fields","Generate Dandelion Fields in the world?");
    public static ConfigBoolean SPAWN_IGUANA_KING_NESTS = newBool(true,"config.forestcraft.iguana_king_nests","Generate Iguana King Nests in the world?");
    public static ConfigBoolean SPAWN_CROCUS_FLOWERS = newBool(true,"config.forestcraft.crocus_flowers","Spawn Crocus Flowers in the world?");
    public static ConfigBoolean SPAWN_SEA_SPONGES = newBool(true,"config.forestcraft.sea_sponges","Generate Sea Sponges in the world?");
    public static ConfigBoolean SPAWN_NITER_ORES = newBool(true,"config.forestcraft.niter_ores","Generate Niter Ores in the world?");
    public static ConfigBoolean SPAWN_SULFUR_ORES = newBool(true,"config.forestcraft.sulfur_ores","Generate Sulfur Ores in the world?");
    public static ConfigBoolean SPAWN_SKY_FRAGMENTS = newBool(true,"config.forestcraft.sky_fragments","Spawn Sky Fragments in the world?");
    public static ConfigBoolean SPAWN_DIRT_PILES = newBool(true,"config.forestcraft.dirt_piles","Generate Dirt Piles in the world?");
    public static ConfigBoolean SPAWN_MESA_PILES = newBool(true,"config.forestcraft.mesa_piles","Generate Mesa Piles in the world?");

    //Entities
    public static ConfigBoolean SPAWN_GEM_GOLEMS = newBool(true,"config.forestcraft.gem_golems","Spawn Gem Golems in the world?");
    public static ConfigBoolean SPAWN_CICADAS = newBool(true,"config.forestcraft.cicadas","Spawn Cicadas in the world?");
    public static ConfigBoolean SPAWN_THUNDER_BIRDS = newBool(true,"config.forestcraft.thunder_birds","Spawn Thunder Birds in the world?");
    public static ConfigBoolean SPAWN_TERROR_BIRDS = newBool(true,"config.forestcraft.terror_birds","Spawn Terror Birds in the world?");
    public static ConfigBoolean SPAWN_SPORE_ZOMBIE = newBool(true,"config.forestcraft.spore_zombie","Spawn Spore Zombie in the world?");
    public static ConfigBoolean SPAWN_KROCKS = newBool(true,"config.forestcraft.krocks","Spawn Krocks in the world?");
    public static ConfigBoolean SPAWN_BAKUDANS = newBool(true,"config.forestcraft.bakudans","Spawn Bakudans in the world?");
    public static ConfigBoolean SPAWN_AIR_SUCKERS = newBool(true,"config.forestcraft.air_suckers","Spawn Air Suckers in the ocean?");
    public static ConfigBoolean SPAWN_DEERS = newBool(true,"config.forestcraft.deers","Spawn Deers in the world?");
    public static ConfigBoolean SPAWN_SOUL_BLAZES = newBool(true,"config.forestcraft.soul_blazes","Spawn Soul Blazes in the nether?");
    public static ConfigBoolean SPAWN_EATERS_OF_THE_DEPTHS = newBool(true,"config.forestcraft.eaters_of_the_depths","Spawn Eaters Of The Depths in the ocean?");
    public static ConfigBoolean SPAWN_WOOD_ABOMINATIONS = newBool(true,"config.forestcraft.wood_abominations","Spawn Wood Abominations in the world?");
    public static ConfigBoolean SPAWN_NIGHT_WATCHERS = newBool(true,"config.forestcraft.night_watchers","Spawn Night watchers while sleep?");
    public static ConfigBoolean CHANGELING_RANDOM_SPAWN = newBool(true,"config.forestcraft.changeling_random","Spawn Changelings near player?");

    //Mechanics
    public static ConfigBoolean SQUID_BUCKET = newBool(true,"config.forestcraft.squid_bucket","Can you catch squids in bucket?");
    public static ConfigBoolean ENABLE_BOSSBARS = newBool(true,"config.forestcraft.bossbars","Makes bossbars visible by default.");
    public static ConfigBoolean ENABLE_FLINT_AND_STEEL = newBool(false,"config.forestcraft.flint_and_steel","Can be turned on to return old flint and steel portal lighting mechanic.");
    public static ConfigBoolean DEFAULT_FISHING = newBool(false,"config.forestcraft.default_fishing","Disable new fishing system and fishes.");
    public static ConfigBoolean NEED_BAIT = newBool(true,"config.forestcraft.need_bait","Bait requirement while fishing.");
    public static ConfigBoolean MOB_GRIEFING = newBool(true,"config.forestcraft.mob_griefing","Mob griefing.");
    public static ConfigBoolean VILLAGER_NEW_TRADES = newBool(true,"config.forestcraft.villager_new_trades","Villager new trades.");
    public static ConfigBoolean COSMIC_FIEND_FOG = newBool(true,"config.forestcraft.cosmic_fiend_fog","Fog from Cosmic Fiend.");
    public static ConfigBoolean SCREEN_SHAKE = newBool(true,"config.forestcraft.screen_shake","Screen shaking effect.");
    public static ConfigBoolean DIFFICULTY_SCALE = newBool(true,"config.forestcraft.difficulty_scale","Scaling boss stats by difficulty.");

    //Integer values
    public static ConfigInteger DESERT_BONES_RATE = newInt(50,1,100,"config.forestcraft.desert_bones_rarity","How often Desert Bones will spawn?");
    public static ConfigInteger FOSSIL_RATE = newInt(50,1,100,"config.forestcraft.fossil_rarity","How often Fossils will spawn?");
    public static ConfigInteger MIN_DOGS_CHUNK_DISTANCE = newInt(4,1,16,"config.forestcraft.dogs_lair_min","Min distance in chunks between dogs lairs.");
    public static ConfigInteger MAX_DOGS_CHUNK_DISTANCE = newInt(8,1,16,"config.forestcraft.dogs_lair_max","Max distance in chunks between dogs lairs.");
    public static ConfigInteger CRYSTAL_BIOME_RATE = newInt(30,1,100,"config.forestcraft.crystal_biome_rarity","How often Crystal Biome will spawn?");
    public static ConfigInteger CICADAS_RATE = newInt(10,1,100,"config.forestcraft.cicadas_rarity","How often Cicadas will spawn?");
    public static ConfigInteger SKY_FRAGMENT_RATE = newInt(5,1,100,"config.forestcraft.sky_fragment_rarity","How often sky fragments will fall?");
    public static ConfigInteger SPORE_MUSH_RATE = newInt(4,1,16,"config.forestcraft.spore_mush_rarity","How ofter spore mushrooms will spawn?");
    public static ConfigInteger NIGHT_WATCHERS_RATE = newInt(5,1,100,"config.forestcraft.night_watchers_rarity","How often night watchers will spawn?");
    public static ConfigInteger ANCIENT_TEMPLE_RARITY = newInt(800,1,1000,"config.forestcraft.ancient_temple_rarity","How often Ancient Temple will spawn?");
    public static ConfigInteger CRYSTAL_BIOME_HEIGHT = newInt(18,1,255,"config.forestcraft.crystal_biome_height","Height for spawning Crystal Biome.");
    public static ConfigInteger EATER_OF_THE_DEPTHS_RARITY = newInt(20,1,100,"config.forestcraft.eater_of_the_depths_rarity","How rare Eater Of The Depths will spawn?");

    //Mobs Spawn Rate
    public static ConfigInteger CROCUS_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.crocus_flower_spawn_weight","Crocus flower spawn weight.");
    public static ConfigInteger CROCUS_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.crocus_flower_spawn_min_count","Crocus flower minimum count when spawning.");
    public static ConfigInteger CROCUS_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.crocus_flower_spawn_max_count","Crocus flower maximum count when spawning.");

    public static ConfigInteger TERROR_BRINGER_SPAWN_WEIGHT = newInt(2,1,100,"config.forestcraft.terror_bringer_spawn_weight","Terror Bringer spawn weight.");
    public static ConfigInteger TERROR_BRINGER_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.terror_bringer_spawn_min_count","Terror Bringer minimum count when spawning.");
    public static ConfigInteger TERROR_BRINGER_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.terror_bringer_spawn_max_count","Terror Bringer maximum count when spawning.");

    public static ConfigInteger KROCK_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.krock_spawn_weight","Krock spawn weight.");
    public static ConfigInteger KROCK_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.krock_spawn_min_count","Krock minimum count when spawning.");
    public static ConfigInteger KROCK_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.krock_spawn_max_count","Krock maximum count when spawning.");

    public static ConfigInteger BAKUDAN_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.bakudan_spawn_weight","Bakudan spawn weight.");
    public static ConfigInteger BAKUDAN_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.bakudan_spawn_min_count","Bakudan minimum count when spawning.");
    public static ConfigInteger BAKUDAN_SPAWN_MAX_COUNT = newInt(3,1,100,"config.forestcraft.bakudan_spawn_max_count","Bakudan maximum count when spawning.");

    public static ConfigInteger AIR_SUCKER_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.air_sucker_spawn_weight","Air Sucker spawn weight.");
    public static ConfigInteger AIR_SUCKER_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.air_sucker_spawn_min_count","Air Sucker minimum count when spawning.");
    public static ConfigInteger AIR_SUCKER_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.air_sucker_spawn_max_count","Air Sucker maximum count when spawning.");

    public static ConfigInteger EATER_OF_THE_DEPTHS_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.eater_of_the_depths_spawn_weight","Eater Of The Depths spawn weight.");
    public static ConfigInteger EATER_OF_THE_DEPTHS_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.eater_of_the_depths_spawn_min_count","Eater Of The Depths minimum count when spawning.");
    public static ConfigInteger EATER_OF_THE_DEPTHS_SPAWN_MAX_COUNT = newInt(1,1,100,"config.forestcraft.eater_of_the_depths_spawn_max_count","Eater Of The Depths maximum count when spawning.");

    public static ConfigInteger INFECTED_ZOMBIE_SPAWN_WEIGHT = newInt(30,1,100,"config.forestcraft.infected_zombie_spawn_weight","Infected Zombie spawn weight.");
    public static ConfigInteger INFECTED_ZOMBIE_SPAWN_MIN_COUNT = newInt(2,1,100,"config.forestcraft.infected_zombie_spawn_min_count","Infected Zombie minimum count when spawning.");
    public static ConfigInteger INFECTED_ZOMBIE_SPAWN_MAX_COUNT = newInt(6,1,100,"config.forestcraft.infected_zombie_spawn_max_count","Infected Zombie maximum count when spawning.");

    public static ConfigInteger THUNDER_SCREAMER_SPAWN_WEIGHT = newInt(5,1,100,"config.forestcraft.thunder_screamer_spawn_weight","Thunder Screamer spawn weight.");
    public static ConfigInteger THUNDER_SCREAMER_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.thunder_screamer_spawn_min_count","Thunder Screamer minimum count when spawning.");
    public static ConfigInteger THUNDER_SCREAMER_SPAWN_MAX_COUNT = newInt(1,1,100,"config.forestcraft.thunder_screamer_spawn_max_count","Thunder Screamer maximum count when spawning.");

    public static ConfigInteger DEER_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.deer_spawn_weight","Deer spawn weight.");
    public static ConfigInteger DEER_SPAWN_MIN_COUNT = newInt(3,1,100,"config.forestcraft.deer_spawn_min_count","Deer minimum count when spawning.");
    public static ConfigInteger DEER_SPAWN_MAX_COUNT = newInt(4,1,100,"config.forestcraft.deer_spawn_max_count","Deer maximum count when spawning.");

    public static ConfigInteger WOOD_ABOMINATION_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.wood_abomination_spawn_weight","Wood Abomination spawn weight.");
    public static ConfigInteger WOOD_ABOMINATION_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.wood_abomination_spawn_min_count","Wood Abomination minimum count when spawning.");
    public static ConfigInteger WOOD_ABOMINATION_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.wood_abomination_spawn_max_count","Wood Abomination maximum count when spawning.");

    public static ConfigInteger GEM_GOLEM_SPAWN_WEIGHT = newInt(40,1,100,"config.forestcraft.gem_golem_spawn_weight","Gem Golem spawn weight.");
    public static ConfigInteger GEM_GOLEM_SPAWN_MIN_COUNT = newInt(2,1,100,"config.forestcraft.gem_golem_spawn_min_count","Gem Golem minimum count when spawning.");
    public static ConfigInteger GEM_GOLEM_SPAWN_MAX_COUNT = newInt(4,1,100,"config.forestcraft.gem_golem_spawn_max_count","Gem Golem maximum count when spawning.");

    public static ConfigInteger DANDELIONEER_SPAWN_WEIGHT = newInt(5,1,100,"config.forestcraft.dandelioneer_spawn_weight","Dandelioneer spawn weight.");
    public static ConfigInteger DANDELIONEER_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.dandelioneer_spawn_min_count","Dandelioneer minimum count when spawning.");
    public static ConfigInteger DANDELIONEER_SPAWN_MAX_COUNT = newInt(1,1,100,"config.forestcraft.dandelioneer_spawn_max_count","Dandelioneer maximum count when spawning.");

    public static ConfigInteger INSANE_DOG_SPAWN_WEIGHT = newInt(60,1,100,"config.forestcraft.insane_dog_spawn_weight","Insane Dog spawn weight.");
    public static ConfigInteger INSANE_DOG_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.insane_dog_spawn_min_count","Insane Dog minimum count when spawning.");
    public static ConfigInteger INSANE_DOG_SPAWN_MAX_COUNT = newInt(1,1,100,"config.forestcraft.insane_dog_spawn_max_count","Insane Dog maximum count when spawning.");

    public static ConfigInteger ALPHA_INSANE_DOG_SPAWN_WEIGHT = newInt(5,1,100,"config.forestcraft.alpha_insane_dog_spawn_weight","Alpha Insane Dog spawn weight.");
    public static ConfigInteger ALPHA_INSANE_DOG_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.alpha_insane_dog_spawn_min_count","Alpha Insane Dog minimum count when spawning.");
    public static ConfigInteger ALPHA_INSANE_DOG_SPAWN_MAX_COUNT = newInt(1,1,100,"config.forestcraft.alpha_insane_dog_spawn_max_count","Alpha Insane Dog maximum count when spawning.");

    public static ConfigInteger NIGHT_BAT_SPAWN_WEIGHT = newInt(2,1,100,"config.forestcraft.night_bat_spawn_weight","Night Bat spawn weight.");
    public static ConfigInteger NIGHT_BAT_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.night_bat_spawn_min_count","Night Bat minimum count when spawning.");
    public static ConfigInteger NIGHT_BAT_SPAWN_MAX_COUNT = newInt(3,1,100,"config.forestcraft.night_bat_spawn_max_count","Night Bat maximum count when spawning.");

    public static ConfigInteger NETHER_BAT_SPAWN_WEIGHT = newInt(2,1,100,"config.forestcraft.nether_bat_spawn_weight","Nether Bat spawn weight.");
    public static ConfigInteger NETHER_BAT_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.nether_bat_spawn_min_count","Nether Bat minimum count when spawning.");
    public static ConfigInteger NETHER_BAT_SPAWN_MAX_COUNT = newInt(3,1,100,"config.forestcraft.nether_bat_spawn_max_count","Nether Bat maximum count when spawning.");

    public static ConfigInteger KELPY_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.kelpy_spawn_weight","Kelpy spawn weight.");
    public static ConfigInteger KELPY_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.kelpy_spawn_min_count","Kelpy minimum count when spawning.");
    public static ConfigInteger KELPY_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.kelpy_spawn_max_count","Kelpy maximum count when spawning.");

    public static ConfigInteger DESECRATED_SOUL_SPAWN_WEIGHT = newInt(10,1,100,"config.forestcraft.desecrated_soul_spawn_weight","Souls spawn weight.");
    public static ConfigInteger DESECRATED_SOUL_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.desecrated_soul_spawn_min_count","Souls minimum count when spawning.");
    public static ConfigInteger DESECRATED_SOUL_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.desecrated_soul_spawn_max_count","Souls maximum count when spawning.");

    public static ConfigInteger JUNGLE_WASP_SPAWN_WEIGHT = newInt(40,1,100,"config.forestcraft.jungle_wasp_spawn_weight","Wasps spawn weight.");
    public static ConfigInteger JUNGLE_WASP_SPAWN_MIN_COUNT = newInt(2,1,100,"config.forestcraft.jungle_wasp_spawn_min_count","Wasps minimum count when spawning.");
    public static ConfigInteger JUNGLE_WASP_SPAWN_MAX_COUNT = newInt(4,1,100,"config.forestcraft.jungle_wasp_spawn_max_count","Wasps maximum count when spawning.");

    public static ConfigInteger IGUANA_SPAWN_WEIGHT = newInt(30,1,100,"config.forestcraft.iguana_spawn_weight","Iguana spawn weight.");
    public static ConfigInteger IGUANA_SPAWN_MIN_COUNT = newInt(2,1,100,"config.forestcraft.iguana_spawn_min_count","Iguana minimum count when spawning.");
    public static ConfigInteger IGUANA_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.iguana_spawn_max_count","Iguana maximum count when spawning.");

    public static ConfigInteger CLOUD_RAY_SPAWN_WEIGHT = newInt(5,1,100,"config.forestcraft.cloud_ray_spawn_weight","Cloud Ray spawn weight.");
    public static ConfigInteger CLOUD_RAY_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.cloud_ray_spawn_min_count","Cloud Ray minimum count when spawning.");
    public static ConfigInteger CLOUD_RAY_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.cloud_ray_spawn_max_count","Cloud Ray maximum count when spawning.");

    public static ConfigInteger WORM_SPAWN_WEIGHT = newInt(2,1,100,"config.forestcraft.worm_spawn_weight","Worm spawn weight.");
    public static ConfigInteger WORM_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.worm_spawn_min_count","Worm minimum count when spawning.");
    public static ConfigInteger WORM_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.worm_spawn_max_count","Worm maximum count when spawning.");


    public static ConfigInteger CHANGELING_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.changeling_spawn_weight","Changeling spawn weight.");
    public static ConfigInteger CHANGELING_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.changeling_spawn_min_count","Changeling minimum count when spawning.");
    public static ConfigInteger CHANGELING_SPAWN_MAX_COUNT = newInt(3,1,100,"config.forestcraft.changeling_spawn_max_count","Changeling maximum count when spawning.");

    public static ConfigInteger SEA_DEVIL_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.sea_devil_spawn_weight","Sea Devil spawn weight.");
    public static ConfigInteger SEA_DEVIL_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.sea_devil_spawn_min_count","Sea Devil minimum count when spawning.");
    public static ConfigInteger SEA_DEVIL_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.sea_devil_spawn_max_count","Sea Devil maximum count when spawning.");

    public static ConfigInteger WATER_BUG_SPAWN_WEIGHT = newInt(1,1,100,"config.forestcraft.water_bug_spawn_weight","Water Bug spawn weight.");
    public static ConfigInteger WATER_BUG_SPAWN_MIN_COUNT = newInt(1,1,100,"config.forestcraft.water_bug_spawn_min_count","Water Bug minimum count when spawning.");
    public static ConfigInteger WATER_BUG_SPAWN_MAX_COUNT = newInt(2,1,100,"config.forestcraft.water_bug_spawn_max_count","Water Bug maximum count when spawning.");


    public static List<ConfigBoolean> getBooleanValues(){
        List<ConfigBoolean> list = new ArrayList<>();
        for (Field field : FCConfigurationValues.class.getFields()) {
            try {
                Object value = field.get(null);
                if (value instanceof ConfigBoolean) {
                    ConfigBoolean configValue = (ConfigBoolean) field.get(null);
                    list.add(configValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<ConfigInteger> getIntegerValues(){
        List<ConfigInteger> list = new ArrayList<>();
        for (Field field : FCConfigurationValues.class.getFields()) {
            try {
                Object value = field.get(null);
                if (value instanceof ConfigInteger) {
                    ConfigInteger configValue = (ConfigInteger) field.get(null);
                    list.add(configValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static ConfigBoolean newBool(boolean b,String path,String comment){
        return (ConfigBoolean) new ConfigBoolean(path,b).comment(comment);
    }

    private static ConfigInteger newInt(int i,int min,int max,String path,String comment){
        return ((ConfigInteger) new ConfigInteger(path,i).comment(comment)).range(min,max);
    }

    private static ConfigDouble newDouble(double d,double min,double max,String path,String comment){
        return ((ConfigDouble) new ConfigDouble(path,d).comment(comment)).range(min,max);
    }



    public static abstract class ConfigValue<T>{

        private String comment = "";
        private String path = "";
        private T value;

        public ConfigValue(String path,T value) {
            this.path = path;
            this.value = value;
        }

        public ConfigValue comment(String text){
            this.comment = comment;
            return this;
        }


        public String getComment() {
            return comment;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public String getPath() {
            return path;
        }

        public T getValue() {
            return value;
        }
    }

    public static class ConfigInteger extends ConfigValue<Integer>{

        private int max = 0;
        private int min = 0;

        public ConfigInteger(String path, Integer value) {
            super(path, value);
        }

        public ConfigInteger range(int min,int max){
            this.min = min;
            this.max = max;
            return this;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }
    }

    public static class ConfigDouble extends ConfigValue<Double>{

        private double max = 0;
        private double min = 0;

        public ConfigDouble(String path, Double value) {
            super(path, value);
        }

        public ConfigDouble range(double min,double max){
            this.min = min;
            this.max = max;
            return this;
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }
    }

    public static class ConfigBoolean extends ConfigValue<Boolean>{

        public ConfigBoolean(String path, Boolean value) {
            super(path, value);
        }

    }
}
