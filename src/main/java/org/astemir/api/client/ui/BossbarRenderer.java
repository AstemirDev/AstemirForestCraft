package org.astemir.api.client.ui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.bossbar.ABossbar;
import org.astemir.api.math.Rectangle;
import org.astemir.api.math.Vector2;
import org.astemir.api.client.RenderUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.ForestCraft;

import java.util.HashMap;
import java.util.Map;

public class BossbarRenderer {


    private static final ResourceLocation BOSSBARS_TEXTURE = new ResourceLocation(ForestCraft.MOD_ID,"textures/gui/healthbar.png");
    private static final RenderImage BOSSBAR_OUTLINE = new RenderImage(BOSSBARS_TEXTURE,new Rectangle(new Vector2(0,0),new Vector2(182,16)),new Vector2(256,256));
    private Map<ABossbar.Style,Vector2> styles = new HashMap<>();


    public BossbarRenderer() {
        this.styles.put(ABossbar.Style.DEFAULT,new Vector2(0,32));
        this.styles.put(ABossbar.Style.SEGMENTED,new Vector2(0,48));
        this.styles.put(ABossbar.Style.FLAT,new Vector2(0,64));
    }

    public void render(){
        int vanillaBossbarsCount = Minecraft.getInstance().ingameGUI.getBossOverlay().mapBossInfos.values().size();
        int renderCount = vanillaBossbarsCount;
        BossbarHandler bossbars = AstemirAPI.CLIENT.BOSSBAR_HANDLER;
        GlStateManager.pushMatrix();
        float windowWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
        if (bossbars != null && !Minecraft.getInstance().gameSettings.hideGUI){
            int y = 16+(32*vanillaBossbarsCount);
            for (ABossbar bossbar : bossbars.getRenderedBossbars()) {
                if (renderCount < 4) {
                    renderCount++;
                    if (Minecraft.getInstance().world.getEntityByID(bossbar.getBossId()) == null) {
                        bossbars.getRenderedBossbars().remove(bossbar);
                    } else {
                        float healthBarWidth = 182;
                        float healthBarHeight = 16;
                        RenderImage bar = new RenderImage(BOSSBARS_TEXTURE, new Rectangle(styles.get(bossbar.style()), new Vector2(healthBarWidth, healthBarHeight)), new Vector2(256, 256));
                        float newSize = 182 * bossbar.value();
                        bar.getImageRect().getSize().x = newSize;
                        RenderSystem.pushMatrix();
                        RenderSystem.color4f(bossbar.color().r, bossbar.color().g, bossbar.color().b, bossbar.color().a);
                        RenderUtils.drawImage(bar, new Vector2(0, y), new Vector2(windowWidth / 2, 0), new Vector2(healthBarWidth, healthBarHeight), new Vector2(1.51f, 1.51f));
                        RenderSystem.popMatrix();
                        RenderUtils.drawText(TextUtils.translate(bossbar.bossName(), TextFormatting.WHITE).append(TextUtils.text(": " + (int) bossbar.boss().getHealth() + "/" + (int) bossbar.boss().getMaxHealth())), new Vector2(0, y - 3), new Vector2(windowWidth / 2, 0), new Vector2(0.9f, 0.9f), 0, true);
                        RenderUtils.drawImage(BOSSBAR_OUTLINE, new Vector2(0, y), new Vector2(windowWidth / 2, 0), new Vector2(1.5f, 1.5f), true);
                        y += 24;
                    }
                }
            }
        }
        GlStateManager.popMatrix();
    }
}
