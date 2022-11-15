package org.astemir.forestcraft.client.screens.guidebook.pages;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.api.client.ui.screen.*;
import org.astemir.api.io.LocalizedTextFile;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.forestcraft.client.screens.guidebook.*;


import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookItemPage extends UIPage {

    private TranslationTextComponent itemName;
    private ItemSettings.ItemSetting setting;

    public BookItemPage(ItemSettings.ItemSetting setting, IUIContainer container) {
        super(container);
        this.itemName = new TranslationTextComponent(setting.getName());
        this.setting = setting;
    }

    @Override
    public void load(IUIContainer container) {
        add(new UIButton().click((x, y, btn)->{ getContainer().setCurrentPage(BookScreen.MAIN_PAGE.get());return true; }).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16, 154)).size(new Vector2(18, 12)));        add(new UIButton().click(((mouseX, mouseY, button) -> {container.setCurrentPage(BookScreen.STRUCTURE_LIST_PAGE.get());return true;})).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16,154)).size(new Vector2(18,12)));
        add(new UIButton().click(((mouseX, mouseY, button) -> {container.setCurrentPage(BookScreen.ITEM_LIST_PAGE.get());return true;})).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16,154)).size(new Vector2(18,12)));
        add(new UILabel().text(itemDescription()).position(new Vector2(150,12)).scale(new Vector2(0.75f,0.75f)));
        add(new UISprite().image(NAMEPLATE).position(new Vector2(140,-3)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-3),0.5f));
        add(new UILabel().text(itemName).scale(new Vector2(0.9f,0.9f)).position(new Vector2(140,-5)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-5),0.5f));
        super.load(container);
    }



    private TranslationTextComponent itemDescription(){
        LocalizedTextFile file = new LocalizedTextFile("assets/forestcraft/guidebook/lang","items/"+setting.getPath(),"en_us","ru_ru");
        return new TranslationTextComponent(file.getText());
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, windowPosX, windowPosY, partialTicks);
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(setting.getItemMaterial()));
        RenderUtils.drawItemStack(item.getDefaultInstance(),new Vector2(44,48),new Vector2(windowPosX, windowPosY),setting.getScale());
    }
}
