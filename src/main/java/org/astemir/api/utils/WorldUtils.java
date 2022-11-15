package org.astemir.api.utils;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.api.math.RandomUtils;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class WorldUtils {

    public static void setNight(ServerWorld world){
        long days = world.getDayTime() / 24000;
        if (days < 1){
            days = 1;
        }
        world.setDayTime(days*18000);
    }

    public static void setDay(ServerWorld world){
        long days = world.getDayTime() / 24000;
        if (days < 1){
            days = 1;
        }
        world.setDayTime(days*24000);
    }

    public static boolean isNight(World world){
        long roundedTime = world.getDayTime() % 24000;
        return roundedTime >= 13000 && roundedTime <= 22000;
    }

    public static boolean isDay(World world){
        return !isNight(world);
    }

    public static boolean isNightOrDark(World world,BlockPos pos){
        long roundedTime = world.getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        int i = world.getLightFor(LightType.SKY, pos);
        int j = world.getLightFor(LightType.BLOCK, pos);
        int brightness;
        if (night) {
            brightness = j;
        } else {
            brightness = Math.max(i, j);
        }
        if (brightness < 7) {
            return true;
        }
        return false;
    }

    public static boolean isBiomes(Biome biome,RegistryKey<Biome>... biomes){
        for (RegistryKey<Biome> biomeKey : biomes){
            if (isBiome(biome,biomeKey)){
                return true;
            }
        }
        return false;
    }

    public static boolean isBiome(Biome biome,RegistryKey<Biome> biomeKey){
        if (biomeKey != null && biome != null) {
            ResourceLocation a = biome.getRegistryName();
            if (a != null) {
                ResourceLocation registryBiomeLocation = biomeKey.getLocation();
                if (registryBiomeLocation != null) {
                    if (ForgeRegistries.BIOMES.containsKey(registryBiomeLocation)) {
                        Biome registryBiome = ForgeRegistries.BIOMES.getValue(registryBiomeLocation);
                        if (registryBiome != null){
                            ResourceLocation b = registryBiome.getRegistryName();
                            if (b != null){
                                return a.equals(b);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isBiome(ISeedReader world, BlockPos pos, RegistryKey<Biome> biomeKey){
        return isBiome(world.getBiome(pos),biomeKey);
    }


    public static void dropItem(World world,BlockPos pos, Item item){
        dropItem(world,pos,item,new Vector3d(0,0.25,0));
    }

    public static void spawnItem(World world,BlockPos pos, Item item){
        dropItem(world,pos,item,new Vector3d(0,0,0));
    }

    public static void spawnItem(World world,BlockPos pos, ItemStack stack){
        dropItem(world,pos,stack,new Vector3d(0,0,0));
    }

    public static void dropItem(World world,BlockPos pos, Item item, Vector3d motion){
        ItemEntity drop = EntityType.ITEM.create(world);
        drop.setMotion(motion);
        drop.setItem(item.getDefaultInstance());
        drop.moveToBlockPosAndAngles(pos.add(0.5f, 1, 0.5f), 0, 0);
        world.addEntity(drop);
    }

    public static void dropItem(World world,BlockPos pos, ItemStack item){
        dropItem(world,pos,item,new Vector3d(0,0.25,0));
    }

    public static void dropItem(World world,BlockPos pos, ItemStack item, Vector3d motion){
        ItemEntity drop = EntityType.ITEM.create(world);
        drop.setMotion(motion);
        drop.setItem(item);
        drop.moveToBlockPosAndAngles(pos.add(0.5f, 1, 0.5f), 0, 0);
        world.addEntity(drop);
    }

    public static boolean growPlants(ItemStack stack, World worldIn, BlockPos pos, PlayerEntity player) {
        BlockState blockstate = worldIn.getBlockState(pos);
        int hook = ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();
            if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
                if (worldIn instanceof ServerWorld) {
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
                        igrowable.grow((ServerWorld)worldIn, worldIn.rand, pos, blockstate);
                    }
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean growSeagrass(ItemStack stack, World worldIn, BlockPos pos, Direction side, Random random) {
        if (worldIn.getBlockState(pos).isIn(Blocks.WATER) && worldIn.getFluidState(pos).getLevel() == 8) {
            if (!(worldIn instanceof ServerWorld)) {
                return true;
            } else {
                label80:
                for(int i = 0; i < 128; ++i) {
                    BlockPos blockpos = pos;
                    BlockState blockstate = Blocks.SEAGRASS.getDefaultState();

                    for(int j = 0; j < i / 16; ++j) {
                        blockpos = blockpos.add(random.nextInt(6) - 1, (random.nextInt(6) - 1) * random.nextInt(6) / 2, random.nextInt(6) - 1);
                        if (worldIn.getBlockState(blockpos).hasOpaqueCollisionShape(worldIn, blockpos)) {
                            continue label80;
                        }
                    }

                    Optional<RegistryKey<Biome>> optional = worldIn.func_242406_i(blockpos);
                    if (Objects.equals(optional, Optional.of(Biomes.WARM_OCEAN)) || Objects.equals(optional, Optional.of(Biomes.DEEP_WARM_OCEAN))) {
                        if (i == 0 && side != null && side.getAxis().isHorizontal()) {
                            blockstate = BlockTags.WALL_CORALS.getRandomElement(worldIn.rand).getDefaultState().with(DeadCoralWallFanBlock.FACING, side);
                        } else if (random.nextInt(4) == 0) {
                            blockstate = BlockTags.UNDERWATER_BONEMEALS.getRandomElement(random).getDefaultState();
                        }
                    }

                    if (blockstate.getBlock().isIn(BlockTags.WALL_CORALS)) {
                        for(int k = 0; !blockstate.isValidPosition(worldIn, blockpos) && k < 4; ++k) {
                            blockstate = blockstate.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
                        }
                    }

                    if (blockstate.isValidPosition(worldIn, blockpos)) {
                        BlockState blockstate1 = worldIn.getBlockState(blockpos);
                        if (blockstate1.isIn(Blocks.WATER) && worldIn.getFluidState(blockpos).getLevel() == 8) {
                            worldIn.setBlockState(blockpos, blockstate, 3);
                        } else if (blockstate1.isIn(Blocks.SEAGRASS) && random.nextInt(10) == 0) {
                            ((IGrowable)Blocks.SEAGRASS).grow((ServerWorld)worldIn, random, blockpos, blockstate1);
                        }
                    }
                }
                stack.shrink(1);
                return true;
            }
        } else {
            return false;
        }
    }


    public static BlockPos randomEmptyBlock(World world,BlockPos pos,int radius){
        BlockPos random = pos.add(RandomUtils.randomInt(-radius,radius),0,RandomUtils.randomInt(-radius,radius));
        if (!world.getBlockState(random).isSolid()){
            return random;
        }else{
            return randomEmptyBlock(world,pos,radius);
        }
    }


}

