package org.astemir.forestcraft.client.screens.guidebook;

import org.astemir.api.client.ui.RenderImage;
import org.astemir.api.math.Rectangle;
import org.astemir.api.math.Vector2;

public class GuideBookImages {

    public static final RenderImage ENTITIES_BUTTON = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(294,46),new Vector2(26,26)),new Vector2(512,256));
    public static final RenderImage ITEMS_BUTTON = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(294,78),new Vector2(26,26)),new Vector2(512,256));
    public static final RenderImage LOCATIONS_BUTTON = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(294,110),new Vector2(26,26)),new Vector2(512,256));
    public static final RenderImage NEXT_PAGE_BUTTON = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(2,193),new Vector2(19,12)),new Vector2(512,256));
    public static final RenderImage PREVIOUS_PAGE_BUTTON = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(2,206),new Vector2(19,12)),new Vector2(512,256));
    public static final RenderImage NEXT_PAGE_BUTTON_HOVERED = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(25,193),new Vector2(19,12)),new Vector2(512,256));
    public static final RenderImage PREVIOUS_PAGE_BUTTON_HOVERED = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(25,206),new Vector2(19,12)),new Vector2(512,256));
    public static final RenderImage NAMEPLATE = new RenderImage(BookScreen.BOOK_TEXTURES.getTexture(),new Rectangle(new Vector2(80,197),new Vector2(122,22)),new Vector2(512,256));

}
