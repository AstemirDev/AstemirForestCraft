package org.astemir.api.client.ui.screen;

import org.astemir.api.client.Tween;

public interface IUIContainer {

    void setCurrentPage(IUIPage page);

    Tween getTween();

    IUIPage getCurrentPage();
}
