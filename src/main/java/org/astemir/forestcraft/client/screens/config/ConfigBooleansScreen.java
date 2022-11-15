package org.astemir.forestcraft.client.screens.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.OptionButton;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.configuration.FCConfiguration;
import org.astemir.forestcraft.configuration.FCConfigurationValues;

import java.awt.*;


@OnlyIn(Dist.CLIENT)
public class ConfigBooleansScreen extends Screen {


    private static final int OPTIONS_LIST_TOP_HEIGHT = 24;
    private static final int OPTIONS_LIST_BOTTOM_OFFSET = 32;
    private static final int OPTIONS_LIST_ITEM_HEIGHT = 25;
    private static final int BUTTON_WIDTH = 200;
    private static final int NEXT_BUTTON_WIDTH = 100;

    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_TOP_OFFSET = 26;
    private static final int TITLE_HEIGHT = 8;

    private final Screen parentScreen;
    private OptionsRowList optionsRowList;


    public ConfigBooleansScreen(Screen parent) {
        super(new TranslationTextComponent("config.forestcraft.title"));
        this.parentScreen = parent;
    }

    @Override
    protected void init() {
        this.optionsRowList = new OptionsRowList(
                this.minecraft, this.width, this.height,
                OPTIONS_LIST_TOP_HEIGHT,
                this.height - OPTIONS_LIST_BOTTOM_OFFSET,
                OPTIONS_LIST_ITEM_HEIGHT
        );

        this.addButton(new Button(
                (this.width - BUTTON_WIDTH) / 2,
                this.height - DONE_BUTTON_TOP_OFFSET,
                BUTTON_WIDTH, BUTTON_HEIGHT,
                DialogTexts.GUI_DONE,
                button -> closeGui()
        ));

         this.addButton(new Button(
                 (this.width - NEXT_BUTTON_WIDTH),
                 this.height - DONE_BUTTON_TOP_OFFSET
                 , NEXT_BUTTON_WIDTH, BUTTON_HEIGHT,
                new StringTextComponent(">"),
                button -> {
                    minecraft.displayGuiScreen(new ConfigSlidersScreen(parentScreen));
                })
        );

        for (FCConfigurationValues.ConfigBoolean value : FCConfigurationValues.getBooleanValues()) {
            optionsRowList.addOption(new CustomBooleanOption(value));
        }
        this.children.add(this.optionsRowList);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack,this.font, this.title, this.width / 2, TITLE_HEIGHT, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void closeGui() {
        minecraft.displayGuiScreen(parentScreen);
        FCConfiguration.save();
    }

    public class CustomBooleanOption extends BooleanOption {

        private FCConfigurationValues.ConfigBoolean value;

        public CustomBooleanOption(FCConfigurationValues.ConfigBoolean value) {
            super(value.getPath(), (a)->value.getValue(), (a,b) -> {
                FCConfiguration.set(value,!value.getValue());
            });
            this.value = value;
        }

        @Override
        public Widget createWidget(GameSettings options, int xIn, int yIn, int widthIn) {
            return new OptionButton(xIn, yIn, widthIn, 20, this, this.func_238152_c_(options), (p_216745_2_) -> {
                this.nextValue(options);
                p_216745_2_.setMessage(this.func_238152_c_(options));
            }){
                @Override
                public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                    renderCustomButton(matrixStack, mouseX, mouseY, partialTicks);
                }

                public void renderCustomButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                    Minecraft minecraft = Minecraft.getInstance();
                    Color color = value.getValue() ? Color.GREEN : Color.RED;
                    FontRenderer fontrenderer = minecraft.fontRenderer;
                    minecraft.getTextureManager().bindTexture(WIDGETS_LOCATION);
                    RenderSystem.color4f((float)color.getRed(),(float)color.getGreen(),(float)color.getBlue(), this.alpha);
                    int i = this.getYImage(this.isHovered());
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.enableDepthTest();
                    this.blit(matrixStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
                    this.blit(matrixStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
                    this.renderBg(matrixStack, minecraft, mouseX, mouseY);
                    int j = getFGColor();
                    drawCenteredString(matrixStack, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
                    if (this.isHovered()) {
                        this.renderToolTip(matrixStack, mouseX, mouseY);
                    }
                }

            };
        }
    }
}
