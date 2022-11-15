package org.astemir.api.common.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import java.util.function.Supplier;

public class Sound3D {

    private Supplier<SoundEvent> sound;
    private SoundCategory category;
    private float pitch = 1;
    public float volume = 1;
    private float minPitch = -1;
    private float maxPitch = -1;

    public Sound3D(Supplier<SoundEvent> sound, SoundCategory category) {
        this.sound = sound;
        this.category = category;
    }

    public Sound3D(Supplier<SoundEvent> sound, SoundCategory category, float volume, float pitch) {
        this.sound = sound;
        this.category = category;
        this.pitch = pitch;
        this.volume = volume;
    }


    public Sound3D(Supplier<SoundEvent> sound, SoundCategory category, float volume, float minPitch,float maxPitch) {
        this.sound = sound;
        this.category = category;
        this.volume = volume;
        this.minPitch = minPitch;
        this.maxPitch = maxPitch;
    }

    public Sound3D pitchRange(float minPitch,float maxPitch){
        this.minPitch = minPitch;
        this.maxPitch = maxPitch;
        return this;
    }

    public void play(World world,double x,double y,double z){
        if (world != null) {
            if (minPitch == -1 && maxPitch == -1) {
                SoundUtils.playSound(world, sound.get(), category, x, y, z, volume, pitch);
            }else
            if (minPitch == -1 && maxPitch !=-1){
                SoundUtils.playSound(world, sound.get(), category, x, y, z, volume,0.5f,maxPitch);
            }else
            if (minPitch != -1 && maxPitch ==-1){
                SoundUtils.playSound(world, sound.get(), category, x, y, z, volume, minPitch,2);
            }else
            if (minPitch != -1 && maxPitch != -1){
                SoundUtils.playSound(world, sound.get(), category, x, y, z, volume, minPitch,maxPitch);
            }
        }
    }

    public Supplier<SoundEvent> getSound() {
        return sound;
    }
}
