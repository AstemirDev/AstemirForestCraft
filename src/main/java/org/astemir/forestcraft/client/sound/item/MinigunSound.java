package org.astemir.forestcraft.client.sound.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.sound.Audio;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.registries.FCSounds;

@OnlyIn(Dist.CLIENT)
public class MinigunSound extends Audio {

    private final LivingEntity player;

    public MinigunSound(LivingEntity player) {
        super(FCSounds.MINIGUN_LOOP.get(), SoundCategory.AMBIENT);
        this.player = player;
        this.volume = 0.5f;
        this.attenuationType = AttenuationType.LINEAR;
        this.repeat = true;
        this.repeatDelay = 0;
    }


    @Override
    public void tick() {
        if (player.getActiveItemStack().getItem().equals(FCItems.DARK_MATTER)){
            this.x = player.getPosX();
            this.y = player.getPosY();
            this.z = player.getPosZ();
        }else{
            finish();
        }
    }

}
