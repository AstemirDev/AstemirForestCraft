package org.astemir.forestcraft.client.sound.boss;


import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.sound.AudioBossMusic;
import org.astemir.forestcraft.registries.FCSounds;

@OnlyIn(Dist.CLIENT)
public class IguanaKingMusic extends AudioBossMusic {

    public IguanaKingMusic(LivingEntity entity) {
        super(FCSounds.IGUANA_KING_MUSIC.get(),entity,100);
    }
}
