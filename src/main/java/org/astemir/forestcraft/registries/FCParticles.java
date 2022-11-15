package org.astemir.forestcraft.registries;


import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.particle.*;

@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ForestCraft.MOD_ID);
    public static final RegistryObject<BasicParticleType> ELECTRO = PARTICLES.register("electro", ()->new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SPORE = PARTICLES.register("spore", ()->new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> DANDELION_SEED = PARTICLES.register("dandelion_seed", ()->new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SLEEPING = PARTICLES.register("sleeping", ()->new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> STARDUST = PARTICLES.register("stardust", ()->new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> COSMIC_EXPLOSION = PARTICLES.register("cosmic_explosion", ()->new BasicParticleType(false));



    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManager = Minecraft.getInstance().particles;
        particleManager.registerFactory(ELECTRO.get(), ElectroParticle.Factory::new);
        particleManager.registerFactory(SPORE.get(), SporeParticle.Factory::new);
        particleManager.registerFactory(DANDELION_SEED.get(), DandelionSeedParticle.Factory::new);
        particleManager.registerFactory(SLEEPING.get(), SleepingParticle.Factory::new);
        particleManager.registerFactory(STARDUST.get(), StardustParticle.Factory::new);
        particleManager.registerFactory(COSMIC_EXPLOSION.get(), CosmicExplosionParticle.Factory::new);
    }

}
