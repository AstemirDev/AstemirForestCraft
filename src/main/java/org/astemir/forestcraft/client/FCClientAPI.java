package org.astemir.forestcraft.client;

import org.astemir.api.AstemirAPI;
import org.astemir.forestcraft.client.managers.FCItemRenderManager;
import org.astemir.forestcraft.client.sound.boss.BeeQueenMusic;
import org.astemir.forestcraft.client.sound.boss.IguanaKingMusic;

public class FCClientAPI {

    public static ScreenShaker SCREEN_SHAKER;
    public static FCItemModels ITEM_MODELS;

    public void initialize(){
        SCREEN_SHAKER = new ScreenShaker();
        ITEM_MODELS = new FCItemModels();
        ITEM_MODELS.registerModels();
        FCItemRenderManager.registerRenderers();
        FCRenderingRegistry.registerRenderers();
        registerAudio();
    }

    public void registerAudio(){
        AstemirAPI.CLIENT.AUDIO_MANAGER.registerAudio(0, BeeQueenMusic.class);
        AstemirAPI.CLIENT.AUDIO_MANAGER.registerAudio(1, IguanaKingMusic.class);
    }
}
