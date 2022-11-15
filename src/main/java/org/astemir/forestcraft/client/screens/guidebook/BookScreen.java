package org.astemir.forestcraft.client.screens.guidebook;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.client.ui.screen.IUIPage;
import org.astemir.api.client.ui.screen.UIScreen;
import org.astemir.api.math.Vector2;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookEntitiesCatalogue;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookItemsCatalogue;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookStructuresCatalogue;
import org.astemir.forestcraft.client.screens.guidebook.pages.*;

import java.util.function.Supplier;


@OnlyIn(Dist.CLIENT)
public class BookScreen extends UIScreen {

    public static final RenderImage BOOK_TEXTURES = new RenderImage(new ResourceLocation("forestcraft:textures/gui/guidebook.png"),null,new Vector2(512,256));
    public static final MobsSettings MOBS_SETTINGS = new MobsSettings();
    public static final ItemSettings ITEM_SETTINGS = new ItemSettings();
    public static final StructureSettings STRUCTURE_SETTINGS = new StructureSettings();

    public static Supplier<IUIPage> MAIN_PAGE;
    public static Supplier<IUIPage> ENTITY_LIST_PAGE;
    public static Supplier<IUIPage> ITEM_LIST_PAGE;
    public static Supplier<IUIPage> STRUCTURE_LIST_PAGE;

    public BookScreen() {
        super(new TranslationTextComponent("forestcraft.gui.guidebook"),BOOK_TEXTURES);
        MAIN_PAGE = ()->new BookMainPage(this);
        ENTITY_LIST_PAGE = ()->new BookEntitiesCatalogue(this,MOBS_SETTINGS);
        ITEM_LIST_PAGE = ()->new BookItemsCatalogue(this,ITEM_SETTINGS);
        STRUCTURE_LIST_PAGE = ()->new BookStructuresCatalogue(this,STRUCTURE_SETTINGS);
        setCurrentPage(MAIN_PAGE.get());
    }
}
