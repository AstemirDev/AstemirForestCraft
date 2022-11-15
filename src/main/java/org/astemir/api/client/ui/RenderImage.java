package org.astemir.api.client.ui;

import net.minecraft.util.ResourceLocation;
import org.astemir.api.math.Rectangle;
import org.astemir.api.math.Vector2;

public class RenderImage {

    private ResourceLocation texture;
    private Rectangle imageRect;
    private Vector2 atlasSize;

    public RenderImage(ResourceLocation texture, Rectangle rect, Vector2 atlasSize) {
        this.texture = texture;
        this.imageRect = rect;
        this.atlasSize = atlasSize;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public Rectangle getImageRect() {
        return imageRect;
    }

    public Vector2 getAtlasSize() {
        return atlasSize;
    }
}
