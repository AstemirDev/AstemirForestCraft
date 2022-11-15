package org.astemir.api.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.AstemirAPI;

@OnlyIn(Dist.CLIENT)
public abstract class Audio extends TickableSound {

    protected Audio(SoundEvent soundIn, SoundCategory categoryIn) {
        super(soundIn, categoryIn);
    }

    public void play(){
        AstemirAPI.CLIENT.AUDIO_MANAGER.playSound(this);
    }

    public void playIfNotPlaying(){
        if (!AstemirAPI.CLIENT.AUDIO_MANAGER.isPlaying(getClass())) {
            AstemirAPI.CLIENT.AUDIO_MANAGER.playSound(this);
        }
    }

    public void stop(){
        AstemirAPI.CLIENT.AUDIO_MANAGER.stopSound(this);
    }

    public void finish(){
        AstemirAPI.CLIENT.AUDIO_MANAGER.stopSound(this);
        finishPlaying();
    }

    @Override
    public void tick() {
        if (this instanceof IMusic) {
            Minecraft.getInstance().getMusicTicker().timeUntilNextMusic = 100;
        }
    }
}

