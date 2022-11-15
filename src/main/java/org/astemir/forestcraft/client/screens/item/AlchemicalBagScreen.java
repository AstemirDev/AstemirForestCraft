package org.astemir.forestcraft.client.screens.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.client.container.ContainerAlchemicalBag;
import org.astemir.forestcraft.registries.FCItems;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class AlchemicalBagScreen extends ContainerScreen<ContainerAlchemicalBag> {

    public AlchemicalBagScreen(ContainerAlchemicalBag container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
        final float PLAYER_LABEL_XPOS = 8;
        final float PLAYER_LABEL_DISTANCE_FROM_BOTTOM = (96 - 2);

        final float BAG_LABEL_YPOS = 6;
        TranslationTextComponent bagLabel = new TranslationTextComponent(FCItems.ALCHEMICAL_BAG.getTranslationKey());
        float BAG_LABEL_XPOS = (xSize / 2.0F) - this.font.getStringWidth(bagLabel.getString()) / 2.0F;                  // centre the label
        this.font.func_243248_b(matrixStack, bagLabel, BAG_LABEL_XPOS, BAG_LABEL_YPOS, Color.darkGray.getRGB());            //this.font.drawString;

        float PLAYER_LABEL_YPOS = ySize - PLAYER_LABEL_DISTANCE_FROM_BOTTOM;
        this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(),                              //this.font.drawString;
                PLAYER_LABEL_XPOS, PLAYER_LABEL_YPOS, Color.darkGray.getRGB());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);                //this.minecraft
        int edgeSpacingX = (this.width - this.xSize) / 2;
        int edgeSpacingY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation("forestcraft","textures/gui/alchemical_bag.png");

}
