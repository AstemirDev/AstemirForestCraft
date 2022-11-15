package org.astemir.forestcraft.client.screens.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.configuration.FCConfiguration;
import org.astemir.forestcraft.configuration.FCConfigurationValues;


@OnlyIn(Dist.CLIENT)
public class ConfigSlidersScreen extends Screen {


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


    public ConfigSlidersScreen(Screen parent) {
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
                NEXT_BUTTON_WIDTH, BUTTON_HEIGHT,
                DialogTexts.GUI_DONE,
                button -> closeGui()
        ));

        this.addButton(new Button(
                0,
                this.height - DONE_BUTTON_TOP_OFFSET
                , BUTTON_WIDTH/2, BUTTON_HEIGHT,
                new StringTextComponent("<"),
                button -> {
                    minecraft.displayGuiScreen(new ConfigBooleansScreen(parentScreen));
                })
        );

        for (FCConfigurationValues.ConfigInteger value : FCConfigurationValues.getIntegerValues()) {
            optionsRowList.addOption(new CustomSliderOption(value));
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

    public class CustomSliderOption extends SliderPercentageOption {


        public CustomSliderOption(FCConfigurationValues.ConfigInteger value) {
            super(value.getPath(),value.getMin(),value.getMax(),1,(a)->(double)value.getValue(),(a,b)->{
                FCConfiguration.set(value,b.intValue());
            },(a,b)->{
                int stringVal = (int)b.get(a);
                return new TranslationTextComponent(value.getPath()).append(new StringTextComponent(": "+stringVal));
            });
        }

    }
}
