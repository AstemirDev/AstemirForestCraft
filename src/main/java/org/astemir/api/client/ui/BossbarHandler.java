package org.astemir.api.client.ui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.common.bossbar.ABossbar;

import java.util.concurrent.CopyOnWriteArrayList;


@OnlyIn(Dist.CLIENT)
public class BossbarHandler {

    private CopyOnWriteArrayList<ABossbar> renderedBossbars = new CopyOnWriteArrayList<>();


    private boolean containsBossbar(ABossbar bossbar){
        for (ABossbar renderedBossbar : renderedBossbars) {
            if (renderedBossbar.isSameBossbar(bossbar)){
                return true;
            }
        }
        return false;
    }

    public void addBossbar(ABossbar bossbar){
        if (!containsBossbar(bossbar)) {
            renderedBossbars.add(bossbar);
        }
    }

    public void removeBossbar(ABossbar bossbar){
        for (ABossbar renderedBossbar : renderedBossbars) {
            if (renderedBossbar.isSameBossbar(bossbar)){
                renderedBossbars.remove(renderedBossbar);
            }
        }
    }

    public ABossbar getBossbar(ABossbar bossbar){
        for (ABossbar renderedBossbar : renderedBossbars) {
            if (renderedBossbar.isSameBossbar(bossbar)){
                return renderedBossbar;
            }
        }
        return null;
    }

    public CopyOnWriteArrayList<ABossbar> getRenderedBossbars() {
        return renderedBossbars;
    }
}
