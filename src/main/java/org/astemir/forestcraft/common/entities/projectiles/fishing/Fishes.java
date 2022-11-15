package org.astemir.forestcraft.common.entities.projectiles.fishing;

import net.minecraft.item.Items;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.utils.WorldUtils;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItems;

import java.util.Random;

public class Fishes {

    public static final BiomeCatch WATER = new BiomeCatch((predicate)-> true).
            add(75.0, ItemDrop.fromItem(()->Items.COD)).
            add(60.0, ItemDrop.fromItem(()->Items.SALMON)).build();

    public static final BiomeCatch LAVA = new BiomeCatch((predicate)-> true).
            add(75.0, ItemDrop.fromItem(()-> FCItems.OBSIDIAN_GROUPER)).
            add(20.0, ItemDrop.fromItem(()-> FCItems.LAVA_DRIFTER)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.MOLTEN_ALLOY)).
            add(5.0,ItemDrop.fromItem(()->Items.MAGMA_CREAM)).build();

    public static final BiomeCatch END = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.THEEND)).
            add(100.0, ItemDrop.fromItem(()-> FCItems.ENDER_TROUT)).build();

    public static final BiomeCatch NETHER = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.NETHER)).
            add(35.0, ItemDrop.fromItem(()-> FCItems.WARPED_FISH)).
            add(35.0, ItemDrop.fromItem(()-> FCItems.CRIMSON_FISH)).
            add(3.0,ItemDrop.fromItem(()-> FCItems.NETHEROACH)).canHasNoResult().build();


    public static final BiomeCatch RARE = new BiomeCatch((predicate)->true).
            add(8.0, ItemDrop.fromItem(()-> FCItems.ENDER_TROUT)).
            add(8.0, ItemDrop.fromItem(()-> FCItems.GOLDENFISH)).
            add(3.0, ItemDrop.fromItem(()-> FCItems.TREASURE_CASE)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.JAWSAW)).
            add(5.0,ItemDrop.fromItem(()-> FCItems.VEGETABLE_CASE)).canHasNoResult().build();

    public static final BiomeCatch GEM = new BiomeCatch((predicate)-> BlockPos.getAllInBox(new AxisAlignedBB(predicate.bobber.func_234616_v_().getPosition()).grow(10,10,10)).filter((pos)->predicate.bobber.world.getBlockState(pos).getBlock().equals(FCBlocks.PEARLSTONE) || predicate.bobber.world.getBlockState(pos).getBlock().equals(FCBlocks.MOSSY_PEARLSTONE) || predicate.bobber.world.getBlockState(pos).getBlock().equals(FCBlocks.GEM_ORE)).count() > 5).
            add(35.0,ItemDrop.fromItem(()-> FCItems.GEMINNOW)).canHasNoResult().build();

    public static final BiomeCatch JUNGLE = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.JUNGLE)).
            add(15.0,ItemDrop.fromItem(()-> FCItems.FLESHER)).canHasNoResult().build();

    public static final BiomeCatch NIGHT = new BiomeCatch((predicate)-> (FishingUtils.isValidLightLevel(predicate.bobber.world,predicate.bobber.getPosition(),new Random()))).
            add(20.0, ItemDrop.fromItem(()-> FCItems.BOOM_FISH)).
            add(10.0, ItemDrop.fromItem(()-> FCItems.FRESHWATER_PHANTOM)).
            add(5.0,ItemDrop.fromItem(()-> FCItems.ENDER_TROUT)).canHasNoResult().build();

    public static final BiomeCatch MESA = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.MESA)).
            add(15.0,ItemDrop.fromItem(()-> FCItems.GOLDENFISH)).canHasNoResult().build();

    public static final BiomeCatch SNOW = new BiomeCatch((predicate)-> WorldUtils.isBiomes(predicate.biome, Biomes.ICE_SPIKES,Biomes.SNOWY_BEACH,Biomes.SNOWY_TUNDRA,Biomes.SNOWY_TAIGA,Biomes.SNOWY_TAIGA_HILLS,Biomes.SNOWY_TAIGA_MOUNTAINS)).
            add(50.0,ItemDrop.fromItem(()-> FCItems.ICICLE)).canHasNoResult().build();

    public static final BiomeCatch DESERT = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.DESERT)).
            add(50.0,ItemDrop.fromItem(()-> FCItems.CACTUSH)).canHasNoResult().build();

    public static final BiomeCatch MUSHROOM = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.MUSHROOM)).
            add(50.0,ItemDrop.fromItem(()-> FCItems.FISHROOM)).canHasNoResult().build();

    public static final BiomeCatch OCEAN = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.OCEAN) || predicate.biome.getCategory().equals(Biome.Category.BEACH)).
            add(50.0, ItemDrop.fromItem(()->Items.TROPICAL_FISH)).
            add(15.0, ItemDrop.fromItem(()->Items.PUFFERFISH)).
            add(20.0, ItemDrop.fromItem(()-> FCItems.SQUID)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.PIKARINA)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.TREASURE_CASE)).
            add(5.0,ItemDrop.fromItem(()-> FCItems.VEGETABLE_CASE)).canHasNoResult().build();

    public static final BiomeCatch MINES = new BiomeCatch((predicate)->(predicate.bobber.getPosition().getY() < 50)).
            add(50.0, ItemDrop.fromItem(()-> FCItems.STONE_BASS)).
            add(30.0, ItemDrop.fromItem(()-> FCItems.COAL_BASS)).
            add(15.0, ItemDrop.fromItem(()-> FCItems.IRON_BASS)).
            add(10.0, ItemDrop.fromItem(()-> FCItems.GOLDEN_BASS)).
            add(10.0, ItemDrop.fromItem(()-> FCItems.REDSTONE_BASS)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.LAPIS_BASS)).
            add(5.0, ItemDrop.fromItem(()-> FCItems.ORE_EATER)).
            add(5.0,ItemDrop.fromItem(()-> FCItems.DIAMOND_BASS)).canHasNoResult().build();

    public static final BiomeCatch EXTREME_HILLS = new BiomeCatch((predicate)->(predicate.bobber.getPosition().getY() < 40 && predicate.biome.getCategory().equals(Biome.Category.EXTREME_HILLS))).
            add(5.0,ItemDrop.fromItem(()-> FCItems.EMERALD_BASS)).canHasNoResult().build();


    public static final BiomeCatch SWAMP = new BiomeCatch((predicate)->predicate.biome.getCategory().equals(Biome.Category.SWAMP)).
            add(40,ItemDrop.fromItem(()-> FCItems.SLIMY_CARP)).canHasNoResult().build();

}
