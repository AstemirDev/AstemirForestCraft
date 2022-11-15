package org.astemir.forestcraft.client.screens.guidebook.pages;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.client.ui.screen.*;
import org.astemir.api.io.LocalizedTextFile;
import org.astemir.api.math.Vector2;
import org.astemir.forestcraft.client.screens.guidebook.*;
import org.astemir.api.math.Rectangle;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookStructurePage extends UIPage {

    private TranslationTextComponent structureName;
    private StructureSettings.StructureSetting setting;


    public BookStructurePage(StructureSettings.StructureSetting setting, IUIContainer container) {
        super(container);
        this.structureName = new TranslationTextComponent(setting.getName());
        this.setting = setting;
    }


    @Override
    public void load(IUIContainer container) {
        RenderImage image = new RenderImage(new ResourceLocation("forestcraft:textures/gui/"+setting.getImagePath()),new Rectangle(new Vector2(0,0),setting.getSize()),setting.getSize());
        add(new UIButton().click((x, y, btn)->{ getContainer().setCurrentPage(BookScreen.MAIN_PAGE.get());return true; }).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16, 154)).size(new Vector2(18, 12)));        add(new UIButton().click(((mouseX, mouseY, button) -> {container.setCurrentPage(BookScreen.STRUCTURE_LIST_PAGE.get());return true;})).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16,154)).size(new Vector2(18,12)));
        add(new UILabel().text(structureDescription()).position(new Vector2(150,12)).scale(new Vector2(0.75f,0.75f)));

        add(new UISprite().image(NAMEPLATE).position(new Vector2(140,-3)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-3),0.5f));
        add(new UILabel().text(structureName).scale(new Vector2(0.9f,0.9f)).position(new Vector2(140,-5)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-5),0.5f));

        add(new UISprite().image(image).position(new Vector2(72,85)).scale(new Vector2((float)setting.getScale(),(float)setting.getScale())).center());
        super.load(container);
    }

    private TranslationTextComponent structureDescription(){
        LocalizedTextFile file = new LocalizedTextFile("assets/forestcraft/guidebook/lang","structures/"+setting.getPath(),"en_us","ru_ru");
        return new TranslationTextComponent(file.getText());
    }


}
