package org.astemir.forestcraft.registries;

import net.minecraft.item.ItemGroup;
import org.astemir.api.common.item.ItemGroupUtils;
import org.astemir.forestcraft.ForestCraft;

public class FCItemGroups {


    public static class Priorities {

        public static final int SWORDS = 10;
        public static final int GUNS = 9;
        public static final int BOWS = 8;
        public static final int SHIELDS = 7;
        public static final int ARMOR = 6;
        public static final int WINGS = 5;
        public static final int BULLETS = 4;


        public static final int MULTITOOLS = 10;
        public static final int PICKAXES = 10;
        public static final int AXES = 9;
        public static final int SHOVELS = 8;
        public static final int HOES = 7;
        public static final int SCYTHES = 6;


        public static final int MATERIALS = 5;
        public static final int FISHES = 4;
        public static final int POTIONS = 3;
        public static final int MUSIC_DISCS = 2;
        public static final int MISC = 1;
        public static final int SPAWN_EGGS = 0;
    }

    public static final ItemGroup BLOCKS = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"blocks", ()->FCBlocks.PEARLSTONE.asItem().getDefaultInstance(),FCGroupSorting.BLOCKS);
    public static final ItemGroup MISC = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"misc", ()-> FCItems.GEM.getDefaultInstance(),FCGroupSorting.MISC);
    public static final ItemGroup WEAPONS = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"weapons", ()-> FCItems.ELECTRON.getDefaultInstance(),FCGroupSorting.WEAPONS);
    public static final ItemGroup EQUIPMENT = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"equipment", ()-> FCItems.IRON_SCYTHE.getDefaultInstance(),FCGroupSorting.TOOLS);
    public static final ItemGroup FOOD = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"food", ()-> FCItems.SHISHTAUK.getDefaultInstance(),FCGroupSorting.FOOD);
    public static final ItemGroup MUSIC_INSTRUMENTS = ItemGroupUtils.newGroup(ForestCraft.MOD_ID,"music_instruments", ()-> FCItems.BASS_GUITAR.getDefaultInstance());


}
