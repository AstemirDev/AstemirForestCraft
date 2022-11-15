package org.astemir.forestcraft.client.managers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.forestcraft.ForestCraft;
import java.util.List;
import java.util.Map;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FCItemModelManager {

    @SubscribeEvent
    public static void init(ModelRegistryEvent modelRegistryEvent) {
        ModelLoader.addSpecialModel(new ModelResourceLocation("forestcraft:elegant_balloon_in_hand", "inventory"));
        ModelLoader.addSpecialModel(new ModelResourceLocation("forestcraft:dark_matter_in_hand", "inventory"));
        ModelLoader.addSpecialModel(new ModelResourceLocation("forestcraft:daybreak_in_hand", "inventory"));
    }
    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> map = event.getModelRegistry();
        addModelRenderedItem(map,"forestcraft:elegant_balloon","forestcraft:elegant_balloon_in_hand");
        addModelRenderedItem(map,"forestcraft:dark_matter","forestcraft:dark_matter_in_hand");
        addModelRenderedItem(map,"forestcraft:daybreak","forestcraft:daybreak_in_hand");
    }


    private static void addModelRenderedItem(Map<ResourceLocation, IBakedModel> map,String inventoryPath,String inHandPath){
        ResourceLocation modelInventory = new ModelResourceLocation(inventoryPath, "inventory");
        ResourceLocation modelHand = new ModelResourceLocation(inHandPath, "inventory");
        IBakedModel bakedModelDefault = map.get(modelInventory);
        IBakedModel bakedModelHand = map.get(modelHand);
        IBakedModel modelWrapper = new IBakedModel() {
            @Override
            public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
                return bakedModelDefault.getQuads(state, side, rand);
            }

            @Override
            public boolean isAmbientOcclusion() {
                return bakedModelDefault.isAmbientOcclusion();
            }

            @Override
            public boolean isGui3d() {
                return bakedModelDefault.isGui3d();
            }

            @Override
            public boolean isSideLit() {
                return false;
            }

            @Override
            public boolean isBuiltInRenderer() {
                return bakedModelDefault.isBuiltInRenderer();
            }

            @Override
            public TextureAtlasSprite getParticleTexture() {
                return bakedModelDefault.getParticleTexture();
            }

            @Override
            public ItemOverrideList getOverrides() {
                return bakedModelDefault.getOverrides();
            }

            @Override
            public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack mat) {
                IBakedModel modelToUse = bakedModelDefault;
                if (cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
                        || cameraTransformType == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
                    modelToUse = bakedModelHand;
                }
                return ForgeHooksClient.handlePerspective(modelToUse, cameraTransformType, mat);
            }
        };
        map.put(modelInventory, modelWrapper);
    }

}
