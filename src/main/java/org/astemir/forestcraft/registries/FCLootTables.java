package org.astemir.forestcraft.registries;

import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;

import static net.minecraft.item.Items.*;
import static org.astemir.forestcraft.registries.FCItems.*;


public class FCLootTables {



    public static final ItemDropPool ANCIENT_MONUMENT = new ItemDropPool().
            add(30.0, ItemDrop.fromItem(()-> GOLD_NUGGET)).
            add(30.0, ItemDrop.fromItem(()-> ROTTEN_FLESH)).
            add(30.0, ItemDrop.fromItem(()-> ARCHAIC_FRAGMENT)).
            add(25.0, ItemDrop.fromItem(()-> GOLD_INGOT)).
            add(25.0, ItemDrop.fromItem(()-> ROTTEN_FLESH)).
            add(25.0, ItemDrop.fromItem(()-> ROTTEN_FLESH)).
            add(10.0, ItemDrop.fromItem(()-> GOLD_BLOCK)).
            add(10.0, ItemDrop.fromItem(()-> IRON_BLOCK)).
            add(10.0, ItemDrop.fromItem(()-> ARCHAIC_SHIELD)).
            add(5.0, ItemDrop.fromItem(()-> ARCHAIC_HAMMER)).
            add(5.0, ItemDrop.fromItem(()-> DIAMOND_BLOCK)).
            add(1.0, ItemDrop.fromItem(()-> ELECTRITE)).
            add(1.0, ItemDrop.fromItem(()-> NETHERITE_INGOT)).
            add(1.0, ItemDrop.fromItem(()-> PRISMATIC_DIAMOND)).
            build();

    public static final ItemDropPool VEGETABLES = new ItemDropPool().
            add(40.0, ItemDrop.fromItem(()-> POTATO,4,8)).
            add(40.0, ItemDrop.fromItem(()->CARROT,4,8)).
            add(35.0, ItemDrop.fromItem(()->BEETROOT,4,8)).
            add(30.0, ItemDrop.fromItem(()->PUMPKIN,1,3)).
            add(30.0, ItemDrop.fromItem(()->MELON,1,2)).
            add(30.0, ItemDrop.fromItem(()-> POMIDOR,2,4)).
            add(30.0, ItemDrop.fromItem(()->WEIRD_CUCUMBER,2,4)).
            add(10.0, ItemDrop.fromItem(()->GOLDEN_CARROT,2,4)).
            build();

    public static final ItemDropPool TREASURES = new ItemDropPool().
            add(50.0, ItemDrop.fromItem(()->IRON_NUGGET,4,32)).
            add(40.0, ItemDrop.fromItem(()->GOLD_NUGGET,4,24)).
            add(40.0, ItemDrop.fromItem(()->IRON_INGOT,1,4)).
            add(40.0, ItemDrop.fromItem(()->DIAMOND_PIECE,1,9)).
            add(30.0, ItemDrop.fromItem(()->GOLD_INGOT,1,4)).
            add(30.0, ItemDrop.fromItem(()->EMERALD,2,8)).
            add(20.0, ItemDrop.fromItem(()->DIAMOND,1,2)).
            add(20.0, ItemDrop.fromItem(()->BROKEN_DIAMOND,1,2)).
            add(20.0, ItemDrop.fromItem(()->IRON_BLOCK,1,2)).
            add(15.0, ItemDrop.fromItem(()->GOLD_BLOCK,1,2)).
            add(5.0, ItemDrop.fromItem(()->EMERALD_BLOCK)).
            add(5.0, ItemDrop.fromItem(()->DIAMOND_BLOCK)).
            add(5.0, ItemDrop.fromItem(()->MUSIC_DISC_LATINA)).
            add(5.0, ItemDrop.fromItem(()->MUSIC_DISC_I_SAY_YES)).
            add(5.0, ItemDrop.fromItem(()->NETHERITE_SCRAP_PIECE,2,4)).
            add(5.0, ItemDrop.fromItem(()->NETHERITE_SCRAP_PLATE,1,2)).
            add(1.0, ItemDrop.fromItem(()->NETHERITE_SCRAP,1,2)).
            add(0.5, ItemDrop.fromItem(()->NETHERITE_INGOT)).
            add(0.1, ItemDrop.fromItem(()->NETHERITE_BRICKS)).
            build();

    public static ItemDropPool BAKUDANS_TREASURE = new ItemDropPool().
            add(50.0, ItemDrop.fromItem(()->NETHER_GOLD_ORE,3,8)).
            add(40.0, ItemDrop.fromItem(()->NETHER_WART,4,8)).
            add(40.0, ItemDrop.fromItem(()->GOLD_INGOT,3)).
            add(40.0, ItemDrop.fromItem(()->BLAZE_POWDER,2,4)).
            add(40.0, ItemDrop.fromItem(()->BLAZE_ROD,1,2)).
            add(30.0, ItemDrop.fromItem(()->DIAMOND,1,3)).
            add(20.0, ItemDrop.fromItem(()->NETHERITE_SCRAP_PIECE,2,4)).
            add(15.0, ItemDrop.fromItem(()->NETHERITE_SCRAP_PLATE,1,3)).
            add(10.0, ItemDrop.fromItem(()->NETHERITE_SCRAP,1,2)).
            add(1.0, ItemDrop.fromItem(()->NETHERITE_INGOT)).
            add(0.1, ItemDrop.fromItem(()->PRISMATIC_DIAMOND)).
            build();

    public static ItemDropPool SAND = new ItemDropPool().
            add(80.0,new ItemDrop(()->null)).
            add(20.0, ItemDrop.fromItem(()-> SAND_PILE,1,2)).
            add(20.0, ItemDrop.fromItem(()-> BONE,1,2)).
            add(20.0, ItemDrop.fromItem(()-> CLAY_BALL,1,2)).
            add(20.0, ItemDrop.fromItem(()-> STICK,1,2)).
            add(5.0, ItemDrop.fromItem(()-> SAND_PILE_WITH_GOLD,1,2)).
            add(5.0, ItemDrop.fromItem(()-> GOLD_NUGGET,1,2)).
            add(3.0, ItemDrop.fromItem(()-> GLASS_BOTTLE)).
            add(3.0, ItemDrop.fromItem(()-> BOWL)).
            add(3.0, ItemDrop.fromItem(()-> BRICK)).
            build();

    public static final ItemDropPool GRAVEL = new ItemDropPool().
            add(80.0,new ItemDrop(()->null)).
            add(20.0, ItemDrop.fromItem(()-> FLINT,1,2)).
            build();

    public static final ItemDropPool DIRT = new ItemDropPool().
            add(80.0,new ItemDrop(()->null)).
            add(50.0, ItemDrop.fromItem(()-> WHEAT_SEEDS,2,4)).
            add(20.0, ItemDrop.fromItem(()-> BONE,1,2)).
            add(20.0, ItemDrop.fromItem(()-> SAND_PILE,1,2)).
            add(20.0, ItemDrop.fromItem(()-> CLAY_BALL,1,2)).
            add(20.0, ItemDrop.fromItem(()-> STICK,1,2)).
            add(10.0, ItemDrop.fromItem(()-> IRON_NUGGET,1,2)).
            add(5.0, ItemDrop.fromItem(()-> GOLD_NUGGET,1,2)).
            add(5.0, ItemDrop.fromItem(()-> BEETROOT_SEEDS)).
            add(5.0, ItemDrop.fromItem(()-> DIRT_WITH_BONES)).
            add(3.0, ItemDrop.fromItem(()-> GLASS_BOTTLE)).
            add(3.0, ItemDrop.fromItem(()-> BOWL)).
            add(3.0, ItemDrop.fromItem(()-> BRICK)).
            add(1.0, ItemDrop.fromItem(()-> DIRT_WITH_IRON)).
            add(1.0, ItemDrop.fromItem(()-> DIRT_WITH_SEEDS)).
            add(5.0, ItemDrop.fromItem(()-> DIRT_WITH_WORMS)).
            add(1.0, ItemDrop.fromItem(()->PUMPKIN_SEEDS)).
            add(1.0, ItemDrop.fromItem(()->MELON_SEEDS)).
            add(1.0, ItemDrop.fromItem(()->DIRT_WITH_DIRTSCALUBUR)).
            build();

    public static final ItemDropPool FOSSIL = new ItemDropPool().
            add(60.0,new ItemDrop(()->null)).
            add(60.0, ItemDrop.fromItem(()->SAND_PILE,4,16)).
            add(50.0, ItemDrop.fromItem(()->BONE,2,4)).
            add(40.0, ItemDrop.fromItem(()->SAND_PILE_WITH_GOLD,2,4)).
            add(30.0, ItemDrop.fromItem(()->IRON_NUGGET,2,8)).
            add(30.0, ItemDrop.fromItem(()->GOLD_NUGGET,2,4)).
            add(10.0, ItemDrop.fromItem(()->GOLD_INGOT,1,2)).
            add(10.0, ItemDrop.fromItem(()->IRON_INGOT,1,2)).
            add(5.0, ItemDrop.fromItem(()->INSANE_FUR,1,2)).
            add(5.0, ItemDrop.fromItem(()->DIAMOND_PIECE,1,2)).
            add(1.0, ItemDrop.fromItem(()->BROKEN_DIAMOND)).
            add(1.0, ItemDrop.fromItem(()->SKELETON_SKULL)).
            add(1.0, ItemDrop.fromItem(()->MUSIC_DISC_LATINA,1,2)).
            add(1.0, ItemDrop.fromItem(()->MUSIC_DISC_I_SAY_YES,1,2)).
            add(1.0, ItemDrop.fromItem(()->PAINTING,1,2)).
            add(1.0, ItemDrop.fromItem(()->FLOWER_POT,1,2)).
            add(1.0, ItemDrop.fromItem(()->SHARP_BONE_JAW)).
            build();

    public static final ItemDropPool SOUL_SAND = new ItemDropPool().
            add(60.0,new ItemDrop(()->null)).
            add(40.0, ItemDrop.fromItem(()->BONE,4,8)).
            add(30.0, ItemDrop.fromItem(()->GOLD_NUGGET,4,8)).
            add(10.0, ItemDrop.fromItem(()->GOLD_INGOT,1,2)).
            add(5.0, ItemDrop.fromItem(()->SKELETON_SKULL)).
            add(5.0, ItemDrop.fromItem(()->NETHER_WART,2,4)).
            add(1.0, ItemDrop.fromItem(()->WITHER_SKELETON_SKULL)).
            build();

    public static final ItemDropPool SEEDS = new ItemDropPool().
            add(80.0, ItemDrop.fromItem(()->WHEAT_SEEDS,4,8)).
            add(50.0, ItemDrop.fromItem(()->BEETROOT_SEEDS,4,8)).
            add(50.0, ItemDrop.fromItem(()->BLUEBERRY_SEEDS,3,5)).
            add(40.0, ItemDrop.fromItem(()->POMIDOR_SEEDS,2,4)).
            add(40.0, ItemDrop.fromItem(()->WEIRD_CUCUMBER_SEEDS,2,4)).
            add(30.0, ItemDrop.fromItem(()->MELON_SEEDS,2,4)).
            add(30.0, ItemDrop.fromItem(()->PUMPKIN_SEEDS,2,4)).
            build();


}
