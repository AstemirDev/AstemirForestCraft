package org.astemir.forestcraft.client.screens.guidebook.pages;

import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.api.client.ui.screen.UIButton;
import org.astemir.api.client.ui.screen.UIPage;
import org.astemir.api.math.Vector2;
import org.astemir.forestcraft.client.screens.guidebook.*;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookStructuresCatalogue;

import java.util.List;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookStructureListPage extends UIPage {

    private List<StructureSettings.StructureSetting> structures;
    public BookStructuresCatalogue catalogue;


    public BookStructureListPage(IUIContainer container, BookStructuresCatalogue catalogue, List<StructureSettings.StructureSetting> structures) {
        super(container);
        this.structures = structures;
        this.catalogue = catalogue;
    }

    @Override
    public void load(IUIContainer container) {
        if (catalogue.hasNextPage()) {
            add(new UIButton().click((x, y, btn)->{
                catalogue.nextPage();
                return true;
            }).hoverImage(NEXT_PAGE_BUTTON_HOVERED).image(NEXT_PAGE_BUTTON).position(new Vector2(243, 154)).size(new Vector2(18, 12)));
        }
        if (catalogue.hasPreviousPage()) {
            add(new UIButton().click((x, y, btn)->{
                catalogue.previousPage();
                return true;
            }).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16, 154)).size(new Vector2(18, 12)));
        }else{
            add(new UIButton().click((x, y, btn)->{
                getContainer().setCurrentPage(BookScreen.MAIN_PAGE.get());
                return true;
            }).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16, 154)).size(new Vector2(18, 12)));
        }
        loadStructureList(structures);
        super.load(container);
    }

    private void loadStructureList(List<StructureSettings.StructureSetting> items) {
        int x = 12;
        int y = 16;
        int count = 0;
        for (StructureSettings.StructureSetting setting : items) {
            count++;
            BookStructurePage structurePage = new BookStructurePage(setting,getContainer());
            TranslationTextComponent itemNameComponent = new TranslationTextComponent(setting.getName());
            add(new UIButton().click(((mouseX, mouseY, button) -> {getContainer().setCurrentPage(structurePage);return true;})).text(itemNameComponent).position(new Vector2(x, y)).size(new Vector2(16,14)));
            if (count % 9 == 0) {
                x += 136;
                y = 0;
            }
            y += 16;
        }
    }


}
