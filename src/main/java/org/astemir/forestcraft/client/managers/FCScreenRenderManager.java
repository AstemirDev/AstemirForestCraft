package org.astemir.forestcraft.client.managers;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import org.astemir.forestcraft.client.container.FCContainers;
import org.astemir.forestcraft.client.screens.config.ConfigBooleansScreen;
import org.astemir.forestcraft.client.screens.item.AlchemicalBagScreen;

public class FCScreenRenderManager {

    @OnlyIn(Dist.CLIENT)
    public static void register(){
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY,
                () -> (mc, screen) -> new ConfigBooleansScreen(screen));
        ScreenManager.registerFactory(FCContainers.ALCHEMICAL_BAG_CONTAINER.get(), AlchemicalBagScreen::new);
    }

}
