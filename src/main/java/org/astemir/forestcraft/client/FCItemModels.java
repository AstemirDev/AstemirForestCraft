package org.astemir.forestcraft.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.AstemirAPI;
import org.astemir.forestcraft.client.render.model.item.*;

public class FCItemModels {

    public static final int WINGS_MODEL = 1;
    public static final int POSSESED_SKULL_MODEL = 2;
    public static final int INSANE_HELMET_MODEL = 3;
    public static final int DARK_MATTER = 4;
    public static final int VILE_TENTACLE = 5;
    public static final int DAYBREAK = 6;
    public static final int OLD_COMPASS = 7;



    @OnlyIn(Dist.CLIENT)
    public void registerModels() {
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(WINGS_MODEL, ModelWings.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(POSSESED_SKULL_MODEL, ModelPossesedSkull.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(INSANE_HELMET_MODEL, ModelInsaneHelmet.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(DARK_MATTER,ModelDarkMatter.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(VILE_TENTACLE, ModelVileTentacle.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(DAYBREAK, ModelDaybreak.class);
        AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.addModel(OLD_COMPASS, ModelOldCompass.class);

    }
}
