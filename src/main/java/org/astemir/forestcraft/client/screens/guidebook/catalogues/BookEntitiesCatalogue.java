package org.astemir.forestcraft.client.screens.guidebook.catalogues;

import com.google.common.collect.Lists;
import org.astemir.api.client.ui.screen.UICatalogue;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.forestcraft.client.screens.guidebook.MobsSettings;
import org.astemir.forestcraft.client.screens.guidebook.pages.BookEntityListPage;

import java.util.List;

public class BookEntitiesCatalogue extends UICatalogue {

    private MobsSettings mobsSettings;

    public BookEntitiesCatalogue(IUIContainer container, MobsSettings settings) {
        super(container);
        this.mobsSettings = settings;
    }

    @Override
    public void load(IUIContainer container) {
        for (List<MobsSettings.MobSetting> setting : Lists.partition(mobsSettings.getSettings(), 18)) {
            addPage(new BookEntityListPage(container,this,setting));
        }
        super.load(container);
    }
}
