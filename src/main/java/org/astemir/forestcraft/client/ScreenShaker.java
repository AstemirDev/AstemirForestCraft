package org.astemir.forestcraft.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.network.ScreenShakeMessage;

public class ScreenShaker {

    private int ticks = 0;
    private int power = 1;

    /**
     Used only in packets
     */
    @Deprecated
    public void shake(int ticks,int power){
        this.ticks = ticks;
        this.power = power;
    }

    public void tick(){
        if (ticks > 0){
            ticks--;
        }
    }

    public static void shakeScreen(PlayerEntity player,int ticks, int power){
        if (player instanceof ServerPlayerEntity) {
            ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ScreenShakeMessage(ticks, power));
        }
    }

    public int getTicks() {
        return ticks;
    }

    public int getPower() {
        return power;
    }
}
