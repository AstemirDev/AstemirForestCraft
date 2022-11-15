package org.astemir.forestcraft.client.screens.guidebook.pages;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.client.ui.screen.IUIContainer;
import org.astemir.api.client.ui.screen.UIButton;
import org.astemir.api.client.ui.screen.UIPage;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.forestcraft.client.screens.guidebook.*;
import org.astemir.forestcraft.client.screens.guidebook.catalogues.BookEntitiesCatalogue;

import java.util.List;
import java.util.function.Function;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookEntityListPage extends UIPage {

    private List<MobsSettings.MobSetting> mobs;
    private BookEntitiesCatalogue catalogue;

    public BookEntityListPage(IUIContainer container, BookEntitiesCatalogue catalogue, List<MobsSettings.MobSetting> mobs) {
        super(container);
        this.mobs = mobs;
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
        loadMobList(mobs);
        super.load(container);
    }

    private void loadMobList(List<MobsSettings.MobSetting> mobs) {
        int x = 12;
        int y = 16;
        int count = 0;
        for (MobsSettings.MobSetting setting : mobs) {
            count++;
            BookEntityPage entityPage = new BookEntityPage(setting,getContainer());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("id", "forestcraft:" + setting.getMobType());
            TranslationTextComponent entityName = new TranslationTextComponent("entity.forestcraft." + setting.getMobType());
            add(new UIButton(){
                @Override
                public void render(MatrixStack matrixStack, FontRenderer fontRenderer, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
                    Entity entity = EntityType.loadEntityAndExecute(nbt, Minecraft.getInstance().world, Function.identity());
                    if (isHovered()) {
                        RenderUtils.drawEntity((LivingEntity) entity,new Vector2(mouseX, mouseY), 10, 0);
                    }
                    super.render(matrixStack, fontRenderer, mouseX, mouseY, windowPosX, windowPosY, partialTicks);
                }
            }.click((mouseX,mouseY,btn)->{getContainer().setCurrentPage(entityPage);return true;}).text(entityName).position(new Vector2(x, y)).size(new Vector2(16,14)));
            if (count % 9 == 0) {
                x += 136;
                y = 0;
            }
            y += 16;
        }
    }


}
