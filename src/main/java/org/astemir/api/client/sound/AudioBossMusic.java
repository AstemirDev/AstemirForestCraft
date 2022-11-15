package org.astemir.api.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.math.MathUtils;

@OnlyIn(Dist.CLIENT)
public class AudioBossMusic extends Audio implements IMusic{

    private final LivingEntity boss;

    public AudioBossMusic(SoundEvent music, LivingEntity boss, int volume) {
        super(music, SoundCategory.MUSIC);
        this.boss = boss;
        this.volume = volume;
        this.attenuationType = AttenuationType.LINEAR;
        this.repeat = true;
        this.repeatDelay = 0;
        this.x = boss.getPosX();
        this.y = boss.getPosY();
        this.z = boss.getPosZ();
    }

    @Override
    public void tick() {
        Minecraft.getInstance().getMusicTicker().stop();
        if (!boss.removed){
            this.x = boss.getPosX();
            this.y = boss.getPosY();
            this.z = boss.getPosZ();
        }else{
            if (volume > 1){
                volume = 1;
            }
            volume = MathUtils.interpolate(volume,0,0.05f);
            if (volume <= 0.1f){
                finish();
            }
        }
    }
}
