package org.astemir.forestcraft.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import org.astemir.api.common.capability.ARegistryCapabilities;
import org.astemir.api.common.capability.CapabilityTarget;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.capabilities.CapabilityCosmoFog;
import org.astemir.forestcraft.common.capabilities.CapabilityFlyingOnWings;
import org.astemir.forestcraft.common.capabilities.CapabilitySleep;


public class FCCapabilities {

    @CapabilityInject(CapabilitySleep.class)
    public static Capability<CapabilitySleep> SLEEPING_CAP;

    @CapabilityInject(CapabilityCosmoFog.class)
    public static Capability<CapabilityCosmoFog> COSMO_FOG;

    @CapabilityInject(CapabilityFlyingOnWings.class)
    public static Capability<CapabilityFlyingOnWings> CAPABILITY_FLYING_ON_WINGS;


    public static void registerCapabilitites(ARegistryCapabilities registry){
        registry.registerCapability(new ResourceLocation(ForestCraft.MOD_ID, "sleeping"),CapabilitySleep.class,CapabilityTarget.ENTITY);
        registry.registerCapability(new ResourceLocation(ForestCraft.MOD_ID, "cosmofog"),CapabilityCosmoFog.class,CapabilityTarget.PLAYER);
        registry.registerCapability(new ResourceLocation(ForestCraft.MOD_ID,"flying_on_wings"),CapabilityFlyingOnWings.class,CapabilityTarget.PLAYER,true);
        registry.scanCapabilities(FCCapabilities.class);
    }

}
