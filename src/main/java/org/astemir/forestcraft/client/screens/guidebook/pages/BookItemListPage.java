package org.astemir.forestcraft.client.screens.guidebook.pages;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.api.client.ui.screen.UIButton;
import org.astemir.api.client.ui.screen.UIPage;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.forestcraft.client.screens.guidebook.*;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookItemsCatalogue;

import java.util.List;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookItemListPage extends UIPage {

    private List<ItemSettings.ItemSetting> items;
    private BookItemsCatalogue catalogue;

    public BookItemListPage(IUIContainer container, BookItemsCatalogue catalogue, List<ItemSettings.ItemSetting> items) {
        super(container);
        this.items = items;
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
        loadItemList(items);
        super.load(container);
    }

    private void loadItemList(List<ItemSettings.ItemSetting> items) {
        int x = 12;
        int y = 16;
        int count = 0;
        for (ItemSettings.ItemSetting setting : items) {
            count++;
            BookItemPage itemPage = new BookItemPage(setting,getContainer());
            TranslationTextComponent itemNameComponent = new TranslationTextComponent(setting.getName());
            add(new UIButton() {
                @Override
                public void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
                    if (isHovered()) {
                        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(setting.getItemMaterial()));
                        RenderUtils.drawItemStack(item.getDefaultInstance(),new Vector2(mouseX, mouseY),new Vector2(0,0), 2);
                    }
                    super.render(matrixStack, fontRenderer, mouseX, mouseY, windowPosX, windowPosY, partialTicks);
                }
            }.click((mouseX,mouseY,btn)->{getContainer().setCurrentPage(itemPage);return true;}).text(itemNameComponent).position(new Vector2(x, y)).size(new Vector2(16,14)));

            if (count % 9 == 0) {
                x += 136;
                y = 0;
            }
            y += 16;
        }
    }


}
