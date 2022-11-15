package org.astemir.api.client.ui.screen;

@FunctionalInterface
public interface ClickEvent {

    public boolean click(double mouseX, double mouseY, int button);

}
