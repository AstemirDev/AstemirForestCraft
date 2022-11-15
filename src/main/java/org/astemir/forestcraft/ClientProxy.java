package org.astemir.forestcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.client.screens.guidebook.BookScreen;
import org.astemir.forestcraft.client.managers.FCItemRenderManager;

import java.util.concurrent.Callable;

public class ClientProxy extends CommonProxy{



    @OnlyIn(Dist.CLIENT)
    @Override
    public void openGuidebook() {
        Minecraft.getInstance().displayGuiScreen(new BookScreen());
    }

    @OnlyIn(Dist.CLIENT)
    private static Callable<ItemStackTileEntityRenderer> getTEISR() {
        return FCItemRenderManager::new;
    }

    @Override
    public Item.Properties setupISTER(Item.Properties group) {
        return group.setISTER(ClientProxy::getTEISR);
    }
}
