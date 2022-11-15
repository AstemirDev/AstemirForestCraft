package org.astemir.forestcraft.client.screens.guidebook.catalogues;

import com.google.common.collect.Lists;
import org.astemir.api.client.ui.screen.UICatalogue;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.forestcraft.client.screens.guidebook.ItemSettings;
import org.astemir.forestcraft.client.screens.guidebook.pages.BookItemListPage;
import java.util.List;

public class BookItemsCatalogue extends UICatalogue {

    private ItemSettings settings;

    public BookItemsCatalogue(IUIContainer container, ItemSettings settings) {
        super(container);
        this.settings = settings;
    }

    @Override
    public void load(IUIContainer container) {
        for (List<ItemSettings.ItemSetting> itemSettings : Lists.partition(settings.getSettings(), 18)) {
            BookItemListPage page = new BookItemListPage(container,this,itemSettings);
            addPage(page);
        }
        super.load(container);
    }
}
