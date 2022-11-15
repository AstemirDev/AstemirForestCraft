package org.astemir.api.common.sound;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.math.MathUtils;
import org.astemir.api.math.RandomUtils;

public class SoundUtils {


    public static void playSound(World world,SoundEvent sound,SoundCategory category,double x,double y,double z,float volume,float pitch){
        world.playSound(null, x, y, z, sound, category, volume, pitch);
    }

    public static void playSound(World world,SoundEvent sound,SoundCategory category,BlockPos pos,float volume,float pitch){
        playSound(world,sound,category,pos.getX(),pos.getY(),pos.getZ(),volume,pitch);
    }

    public static void playSound(World world, SoundEvent sound, SoundCategory category, PlayerEntity player, float volume, float pitch){
        playSound(world,sound,category,player.getPosX(),player.getPosY(),player.getPosZ(),volume,pitch);
    }

    public static void playSound(World world,SoundEvent sound,SoundCategory category,BlockPos pos,float volume,float minPitch,float maxPitch){
        playSound(world,sound,category,pos.getX(),pos.getY(),pos.getZ(),volume,RandomUtils.randomFloat(minPitch,maxPitch));
    }

    public static void playSound(World world, SoundEvent sound, SoundCategory category, PlayerEntity player, float volume, float minPitch,float maxPitch){
        playSound(world,sound,category,player.getPosX(),player.getPosY(),player.getPosZ(),volume,RandomUtils.randomFloat(minPitch,maxPitch));
    }

    public static void playSound(World world,SoundEvent sound,SoundCategory category,double x,double y,double z,float volume,float minPitch,float maxPitch){
        world.playSound(null, x, y, z, sound, category, volume, RandomUtils.randomFloat(minPitch, maxPitch));
    }

    public static void playPianoSound(World world, SoundEvent soundEvent, BlockPos pos, int note){
        float f = (float)Math.pow(2.0D, (double)(note - 12) / 12.0D);
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundEvent, SoundCategory.PLAYERS, 3, f);
    }

    public static int turnToNote(float pitch){
        return (int) MathUtils.range(-90,90,24,0,pitch);
    }
}
