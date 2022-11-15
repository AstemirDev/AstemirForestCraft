package org.astemir.api.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@OnlyIn(Dist.CLIENT)
public class AudioManager {

    private CopyOnWriteArrayList<Audio> sounds = new CopyOnWriteArrayList();
    private Map<Integer,Class<? extends Audio>> audioIndexed = new HashMap<>();


    public <T extends Audio> void playSound(T sound){
        if (sound instanceof IMusic){
            if (!isMusicPlaying()){
                Minecraft.getInstance().getMusicTicker().stop();
                sounds.add(sound);
                Minecraft.getInstance().getSoundHandler().play(sound);
            }
        }else {
            sounds.add(sound);
            Minecraft.getInstance().getSoundHandler().play(sound);
        }
    }


    public void stopSound(Audio sound){
        sounds.remove(sound);
    }

    public boolean isPlaying(Class className){
        for (Audio sound: sounds){
            if (className.isInstance(sound)){
                return true;
            }
        }
        return false;
    }

    public boolean isMusicPlaying(){
        for (Audio sound: sounds){
            if (sound instanceof IMusic){
                return true;
            }
        }
        return false;
    }

    public CopyOnWriteArrayList<Audio> getSounds() {
        return sounds;
    }

    public void registerAudio(int index,Class<? extends Audio> audio){
        this.audioIndexed.put(index,audio);
    }

    public boolean hasIndexedAudio(int index){
        return audioIndexed.get(index) != null;
    }

    public Class<? extends Audio> getIndexedAudio(int index){
        return audioIndexed.get(index);
    }
}
