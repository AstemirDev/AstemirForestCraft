package org.astemir.forestcraft.common.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.effect.ElectrocutEffect;
import org.astemir.forestcraft.common.effect.SleepingEffect;
import org.astemir.forestcraft.network.ElectroSoundMessage;
import org.astemir.forestcraft.common.capabilities.CapabilitySleep;
import org.astemir.forestcraft.registries.FCCapabilities;
public class PotionEvents {


    @SubscribeEvent
    public static void onEntityEffect(PotionEvent.PotionAddedEvent e){
        if (e.getPotionEffect() != null) {
            if (e.getPotionEffect().getPotion() instanceof ElectrocutEffect) {
                if (!e.getEntityLiving().world.isRemote) {
                    e.getEntityLiving().world.getEntitiesWithinAABB(PlayerEntity.class, e.getEntityLiving().getBoundingBox().grow(20, 20, 20)).forEach((player) -> {
                        ForestCraft.PACKET_HANDLER.getNetwork().send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new ElectroSoundMessage(e.getEntityLiving().getEntityId(), e.getPotionEffect().getDuration()));
                    });
                }
            } else if (e.getPotionEffect().getPotion() instanceof SleepingEffect) {
                if (!e.getEntityLiving().world.isRemote) {
                    CapabilitySleep sleepCap = CapabilityUtils.getCapability(FCCapabilities.SLEEPING_CAP,e.getEntityLiving());
                    sleepCap.setSleepingTicks(e.getPotionEffect().getDuration());
                    e.getEntityLiving().addPotionEffect(new EffectInstance(Effects.BLINDNESS, e.getPotionEffect().getDuration(), e.getPotionEffect().getAmplifier(), false, false));
                }
            }
        }
    }
}
