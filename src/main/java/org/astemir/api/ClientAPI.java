package org.astemir.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.EntityType;
import org.astemir.api.client.item.ItemModelRegistry;
import org.astemir.api.client.item.ItemModelsHandler;
import org.astemir.api.client.item.renderer.CurioLayer;
import org.astemir.api.client.sound.AudioManager;
import org.astemir.api.client.ui.BossbarHandler;

public class ClientAPI {

    public AudioManager AUDIO_MANAGER;
    public BossbarHandler BOSSBAR_HANDLER;
    public ItemModelRegistry ITEM_MODELS_REGISTRY;
    public ItemModelsHandler ITEM_MODELS_HANDLER;
    public static long TICKS;


    public void init(){
        AUDIO_MANAGER = new AudioManager();
        BOSSBAR_HANDLER = new BossbarHandler();
        ITEM_MODELS_REGISTRY = new ItemModelRegistry();
        ITEM_MODELS_HANDLER = new ItemModelsHandler();
        EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();
        if (APIMods.CURIOS.isLoaded()) {
            renderManager.getSkinMap().forEach((name,renderer)->{
                renderer.addLayer(new CurioLayer(renderer));
            });
        }
    }
}
