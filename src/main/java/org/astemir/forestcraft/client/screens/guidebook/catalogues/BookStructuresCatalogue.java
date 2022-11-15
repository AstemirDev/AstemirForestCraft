package org.astemir.forestcraft.client.screens.guidebook.catalogues;

import com.google.common.collect.Lists;
import org.astemir.api.client.ui.screen.UICatalogue;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.forestcraft.client.screens.guidebook.StructureSettings;
import org.astemir.forestcraft.client.screens.guidebook.pages.BookStructureListPage;

import java.util.List;

public class BookStructuresCatalogue extends UICatalogue {

    private StructureSettings settings;

    public BookStructuresCatalogue(IUIContainer container, StructureSettings settings) {
        super(container);
        this.settings = settings;
    }

    @Override
    public void load(IUIContainer container) {
        for (List<StructureSettings.StructureSetting> structureSettings : Lists.partition(settings.getSettings(), 18)) {
            addPage(new BookStructureListPage(container,this,structureSettings));
        }
        super.load(container);
    }
}
