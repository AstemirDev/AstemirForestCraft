package org.astemir.forestcraft;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import org.astemir.api.AstemirAPI;
import org.astemir.api.network.PacketHandler;
import org.astemir.api.common.block.ARegistryBlocks;
import org.astemir.api.common.item.ARegistryItems;
import org.astemir.forestcraft.client.FCClientAPI;
import org.astemir.forestcraft.client.FCItemModels;
import org.astemir.forestcraft.client.managers.FCItemRenderManager;
import org.astemir.forestcraft.client.sound.boss.IguanaKingMusic;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityCosmicFiend;
import org.astemir.forestcraft.data.FCDataGenerators;
import org.astemir.forestcraft.registries.FCNewBlocks;
import org.astemir.forestcraft.client.FCRenderingRegistry;
import org.astemir.forestcraft.configuration.FCConfiguration;
import org.astemir.forestcraft.client.container.FCContainers;
import org.astemir.forestcraft.registries.*;
import org.astemir.forestcraft.common.event.PotionEvents;
import org.astemir.forestcraft.common.event.*;
import org.astemir.forestcraft.network.*;
import org.astemir.forestcraft.client.sound.boss.BeeQueenMusic;
import org.astemir.forestcraft.common.world.FCWorldGenerator;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.Optional;


@Mod("forestcraft")
public class ForestCraft extends AstemirAPI
{
    public final static String MOD_ID = "forestcraft";
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static FCClientAPI CLIENT;

    public ForestCraft() {
        super("forestcraft");
        ARegistryBlocks.BLOCKS_CONTAINER = FCNewBlocks.class;
        ARegistryItems.ITEMS_CONTAINER = FCItems.class;
        registerGenericEvent(Structure.class,FCStructures::registerStructures);
        registerGenericEvent(Feature.class,FCFeatures::registerFeatures);
        registerEvent(ForestCraft::onCommunicateWithOtherMods);
        setupConfig();
        setupEvents();
        setupRegistries();
        PROXY.init();
    }

    private void setupRegistries(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FCSounds.SOUNDS.register(modEventBus);
        FCEffects.EFFECTS.register(modEventBus);
        FCPotions.POTIONS.register(modEventBus);
        FCContainers.CONTAINERS.register(modEventBus);
        FCParticles.PARTICLES.register(modEventBus);
    }

    private void setupConfig(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FCConfiguration.COMMON_CONFIG,"forestcraft.toml");
    }

    private void setupEvents(){
        registerEvent(FCWorldGenerator.class);
        registerEvent(ItemEvents.class);
        registerEvent(EntityEvents.class);
        registerEvent(VillagerEvents.class);
        registerEvent(PotionEvents.class);
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        CLIENT = new FCClientAPI();
        CLIENT.initialize();
    }

    @Override
    public void onCommonSetup(FMLCommonSetupEvent event) {
        FCCapabilities.registerCapabilitites(getCapabilityRegistry());
        FCPotions.registerPotionRecipes();
    }


    @SubscribeEvent
    public static void onCommunicateWithOtherMods(InterModEnqueueEvent event){
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,()-> SlotTypePreset.BODY.getMessageBuilder().build());
    }

    @Override
    public void registerPackets(PacketHandler handler) {
        handler.registerMessage(1, GlueMessage.class, GlueMessage::encode, GlueMessage::decode, GlueMessage.Handler::onMessageReceived, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        handler.registerMessage(2, ElectroSoundMessage.class, ElectroSoundMessage::encode, ElectroSoundMessage::decode, ElectroSoundMessage.Handler::checkConditions, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        handler.registerMessage(3, MinigunSoundMessage.class, MinigunSoundMessage::encode, MinigunSoundMessage::decode, MinigunSoundMessage.Handler::checkConditions, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        handler.registerMessage(4, AncientMonumentMessage.class, AncientMonumentMessage::write, AncientMonumentMessage::read, AncientMonumentMessage.Handler::handle);
        handler.registerMessage(5, ScreenShakeMessage.class, ScreenShakeMessage::encode, ScreenShakeMessage::decode, ScreenShakeMessage.Handler::checkConditions,Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
