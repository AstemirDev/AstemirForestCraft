package org.astemir.forestcraft.client.screens.guidebook.pages;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import org.astemir.api.client.ui.screen.*;
import org.astemir.api.io.LocalizedTextFile;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.forestcraft.client.screens.guidebook.*;
import java.util.function.Function;

import static org.astemir.forestcraft.client.screens.guidebook.GuideBookImages.*;

public class BookEntityPage extends UIPage {

    private TranslationTextComponent entityName;
    private CompoundNBT nbt;
    private MobsSettings.MobSetting setting;


    public BookEntityPage(MobsSettings.MobSetting setting, IUIContainer container) {
        super(container);
        this.entityName = new TranslationTextComponent("entity.forestcraft." + setting.getMobType());
        this.setting = setting;
        this.nbt = new CompoundNBT();
        nbt.putString("id", "forestcraft:" + setting.getMobType());
    }

    @Override
    public void load(IUIContainer container) {
        add(new UIButton().click((x, y, btn)->{ getContainer().setCurrentPage(BookScreen.MAIN_PAGE.get());return true; }).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16, 154)).size(new Vector2(18, 12)));        add(new UIButton().click(((mouseX, mouseY, button) -> {container.setCurrentPage(BookScreen.STRUCTURE_LIST_PAGE.get());return true;})).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16,154)).size(new Vector2(18,12)));
        add(new UIButton().click(((mouseX, mouseY, button) -> {container.setCurrentPage(BookScreen.ENTITY_LIST_PAGE.get());return true;})).hoverImage(PREVIOUS_PAGE_BUTTON_HOVERED).image(PREVIOUS_PAGE_BUTTON).position(new Vector2(16,154)).size(new Vector2(18,12)));
        add(new UILabel().text(mobDescription()).position(new Vector2(150,12)).scale(new Vector2(0.75f,0.75f)));

        add(new UISprite().image(NAMEPLATE).position(new Vector2(140,-3)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-3),0.5f));
        add(new UILabel().text(entityName).scale(new Vector2(0.9f,0.9f)).position(new Vector2(140,-5)).center().
                tweenPosition(this,new Vector2(140,5),new Vector2(140,-5),0.5f));
        super.load(container);
    }

    private TranslationTextComponent mobDescription(){
        LocalizedTextFile file = new LocalizedTextFile("assets/forestcraft/guidebook/lang","mobs/"+setting.getPath(),"en_us","ru_ru");
        return new TranslationTextComponent(file.getText());
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX, int windowPosY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, windowPosX, windowPosY, partialTicks);
        Entity entity = EntityType.loadEntityAndExecute(nbt, Minecraft.getInstance().world, Function.identity());
        RenderUtils.drawEntity((LivingEntity)entity,new Vector2(windowPosX+setting.getPosition().x,windowPosY+setting.getPosition().y),setting.getScale(),partialTicks);
    }
}
