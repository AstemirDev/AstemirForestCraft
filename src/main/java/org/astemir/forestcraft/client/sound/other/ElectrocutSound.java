package org.astemir.forestcraft.client.sound.other;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.sound.Audio;
import org.astemir.forestcraft.registries.FCParticles;
import org.astemir.forestcraft.registries.FCSounds;

@OnlyIn(Dist.CLIENT)
public class ElectrocutSound extends Audio {

    private final LivingEntity target;
    private int duration = 2000;

    public ElectrocutSound(LivingEntity target,int duration) {
        super(FCSounds.ELECTRON_ELECTROCUT.get(), SoundCategory.AMBIENT);
        this.target = target;
        this.volume = 20;
        this.attenuationType = AttenuationType.NONE;
        this.repeat = true;
        this.duration = duration;
        this.repeatDelay = 0;
    }


    @Override
    public void tick() {
        if (!target.removed && duration >= 0){
            this.x = target.getPosX();
            this.y = target.getPosY();
            this.z = target.getPosZ();
            for (int i = 0; i < 12; i++) {
                Minecraft.getInstance().world.addParticle(FCParticles.ELECTRO.get(), true, target.getPosXRandom(1), target.getPosYRandom(), target.getPosZRandom(1), 0, 0, 0);
            }
        }else{
            finish();
        }
        duration--;
    }

    public LivingEntity getTarget() {
        return target;
    }
}
