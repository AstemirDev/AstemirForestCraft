package org.astemir.api.client.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class UICatalogue implements IUIPage {

    private IUIContainer container;
    private List<IUIPage> pages;
    private int currentPageIndex = 0;

    public UICatalogue(IUIContainer container) {
        this.container = container;
        pages = new ArrayList<>();
    }

    public UICatalogue addPage(IUIPage page){
        pages.add(page);
        return this;
    }

    public void nextPage(){
        if (currentPageIndex +1 < pages.size()){
            getCurrentPage().unload(container);
            currentPageIndex++;
            getCurrentPage().load(container);
        }
    }

    public void previousPage(){
        if (currentPageIndex - 1 >= 0){
            getCurrentPage().unload(container);
            currentPageIndex--;
            getCurrentPage().load(container);
        }
    }

    public IUIPage getCurrentPage(){
        return pages.get(currentPageIndex);
    }

    public IUIPage getNextPage(){
        return pages.get(currentPageIndex+1);
    }

    public IUIPage getPreviousPage(){
        return pages.get(currentPageIndex-1);
    }

    public boolean hasNextPage(){
        return (currentPageIndex + 1)<pages.size();
    }

    public boolean hasPreviousPage(){
        return (currentPageIndex - 1) >= 0;
    }

    @Override
    public void load(IUIContainer container) {
        getCurrentPage().load(container);
    }

    @Override
    public void unload(IUIContainer container) {
        pages.forEach((page)->page.unload(container));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, int windowPosX,int windowPosY,float partialTicks){
        getCurrentPage().render(matrixStack,mouseX,mouseY,windowPosX,windowPosY,partialTicks);
    }

    @Override
    public void clicked(double mouseX, double mouseY, int button) {
        getCurrentPage().clicked(mouseX,mouseY,button);
    }

    @Override
    public IUIContainer getContainer() {
        return container;
    }
}
