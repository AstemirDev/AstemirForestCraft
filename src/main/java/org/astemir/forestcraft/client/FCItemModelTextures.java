package org.astemir.forestcraft.client;

import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.forestcraft.ForestCraft;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FCItemModelTextures {

    private static final List<RenderMaterial> RENDER_MATERIALS = new ArrayList<>();

    public static final RenderMaterial HONEY_KEEPER = renderMaterial("entity/shield/honey_keeper");
    public static final RenderMaterial ANCIENT_RUNESTONE_SHIELD = renderMaterial("entity/shield/ancient_runestone_shield");
    public static final RenderMaterial PREHISTORIC_SHIELD = renderMaterial("entity/shield/prehistoric_shield");
    public static final RenderMaterial ELEGANT_BALLOON = renderMaterial("entity/elegant_balloon");
    public static final RenderMaterial DARK_MATTER = renderMaterial("item/dark_matter_in_hand");
    public static final RenderMaterial VILE_TENTACLE = renderMaterial("entity/changeling/changeling");
    public static final RenderMaterial DAYBREAK = renderMaterial("item/daybreak_in_hand");
    public static final RenderMaterial OLD_COMPASS = renderMaterial("item/old_compass");

    private static RenderMaterial renderMaterial(String path) {
        RenderMaterial material = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(ForestCraft.MOD_ID, path));
        RENDER_MATERIALS.add(material);
        return material;
    }


    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            for (RenderMaterial textures : RENDER_MATERIALS) {
                event.addSprite(textures.getTextureLocation());
            }
        }
    }

}
