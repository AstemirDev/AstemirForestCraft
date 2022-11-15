package org.astemir.forestcraft.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.client.managers.FCBlockRenderManager;
import org.astemir.forestcraft.client.managers.FCEntityRenderManager;
import org.astemir.forestcraft.client.managers.FCItemPropertyManager;
import org.astemir.forestcraft.client.managers.FCScreenRenderManager;


@OnlyIn(Dist.CLIENT)
public class FCRenderingRegistry {

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(){
        FCEntityRenderManager.register();
        FCBlockRenderManager.register();
        FCScreenRenderManager.register();
        FCItemPropertyManager.register();
    }
}
