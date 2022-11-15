package org.astemir.forestcraft.client.screens.guidebook.pages;

import net.minecraft.util.text.TextFormatting;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.api.client.ui.screen.UIButton;
import org.astemir.api.client.ui.screen.UILabel;
import org.astemir.api.client.ui.screen.UIPage;
import org.astemir.api.client.AColor;
import org.astemir.api.math.Vector2;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.client.screens.guidebook.BookScreen;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;


public class BookMainPage extends UIPage {

    public BookMainPage(IUIContainer container) {
        super(container);
    }

    @Override
    public void load(IUIContainer container) {
        add(new UILabel().text(TextUtils.translate("guidebook.forestcraft.title",TextFormatting.BOLD)).position(new Vector2(72,16)).center().scale(new Vector2(1.75f,1.75f)));
        add(new UIButton().click((x, y, button)->{container.setCurrentPage(BookScreen.ENTITY_LIST_PAGE.get());return true;}).image(ENTITIES_BUTTON).position(new Vector2(70,68)).size(new Vector2(26,26)).hoverEvent((ui,b)->{
            if (b) {
                ui.color(new AColor(0.8f,0.8f,0.8f,1));
                ui.tweenScale(BookMainPage.this, Vector2.DEFAULT_SCALE, new Vector2(1.1f, 1.1f), 0.5f);
            }else{
                ui.color(AColor.WHITE);
                ui.tweenScale(BookMainPage.this, new Vector2(1.1f, 1.1f),  Vector2.DEFAULT_SCALE,0.5f);
            }
        }).center());
        add(new UIButton().click((x, y, button)->{container.setCurrentPage(BookScreen.ITEM_LIST_PAGE.get());return true;}).image(ITEMS_BUTTON).position(new Vector2(37,109)).center().size(new Vector2(26,26)).hoverEvent((ui,b)->{
            if (b) {
                ui.color(new AColor(0.8f,0.8f,0.8f,1));
                ui.tweenScale(BookMainPage.this, Vector2.DEFAULT_SCALE, new Vector2(1.1f, 1.1f), 0.5f);
            }else{
                ui.color(AColor.WHITE);
                ui.tweenScale(BookMainPage.this, new Vector2(1.1f, 1.1f),  Vector2.DEFAULT_SCALE,0.5f);
            }
        }));
        add(new UIButton().click((x, y, button)->{container.setCurrentPage(BookScreen.STRUCTURE_LIST_PAGE.get());return true;}).image(LOCATIONS_BUTTON).position(new Vector2(100,109)).center().size(new Vector2(26,26)).hoverEvent((ui,b)->{
            if (b) {
                ui.color(new AColor(0.8f,0.8f,0.8f,1));
                ui.tweenScale(BookMainPage.this, Vector2.DEFAULT_SCALE, new Vector2(1.1f, 1.1f), 0.5f);
            }else{
                ui.color(AColor.WHITE);
                ui.tweenScale(BookMainPage.this, new Vector2(1.1f, 1.1f),  Vector2.DEFAULT_SCALE,0.5f);
            }
        }));
        super.load(container);
    }
}
