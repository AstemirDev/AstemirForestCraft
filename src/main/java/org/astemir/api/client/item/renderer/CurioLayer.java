package org.astemir.api.client.item.renderer;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.AItemArmor;
import org.astemir.api.common.item.ItemUtils;
import top.theillusivec4.curios.api.CuriosApi;


public class CurioLayer extends BipedArmorLayer {

    public CurioLayer(IEntityRenderer renderer) {
        super(renderer, new BipedModel(0.5F), new BipedModel(1.0F));
    }


    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderEquipment(matrixStackIn, bufferIn, entitylivingbaseIn, packedLightIn);
    }

    private void renderEquipment(MatrixStack matrixStack, IRenderTypeBuffer buffer, LivingEntity entity,int light) {
        IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(entity).orElse(null);
        if (itemHandler != null) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (ItemUtils.isModItem(stack)){
                    AItem item = ItemUtils.getItemConstructor(stack);
                    if (item instanceof AItemArmor){
                        EquipmentSlotType slotType = ((AItemArmor)item).getSlotType();
                        BipedModel armorModel = getArmorModelHook(entity, stack, slotType, null);
                        ((BipedModel)this.getEntityModel()).setModelAttributes(armorModel);
                        this.setModelSlotVisible(armorModel, slotType);
                        boolean flag1 = stack.hasEffect();
                        this.renderModel(matrixStack, buffer, light, flag1, armorModel, 1.0F, 1.0F, 1.0F, this.getArmorResource(entity, stack, slotType, null));
                    }
                }
            }
        }
    }

    private void renderModel(MatrixStack p_241738_1_, IRenderTypeBuffer p_241738_2_, int p_241738_3_, boolean p_241738_5_, BipedModel p_241738_6_, float p_241738_8_, float p_241738_9_, float p_241738_10_, ResourceLocation armorResource) {
        IVertexBuilder ivertexbuilder = ItemRenderer.getArmorVertexBuilder(p_241738_2_, RenderType.getArmorCutoutNoCull(armorResource), false, p_241738_5_);
        p_241738_6_.render(p_241738_1_, ivertexbuilder, p_241738_3_, OverlayTexture.NO_OVERLAY, p_241738_8_, p_241738_9_, p_241738_10_, 1.0F);
    }
}
