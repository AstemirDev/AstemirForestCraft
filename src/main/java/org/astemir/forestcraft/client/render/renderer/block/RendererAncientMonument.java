package org.astemir.forestcraft.client.render.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientMonument;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class RendererAncientMonument extends TileEntityRenderer<TileEntityAncientMonument> {


    private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
    private Random random;

    public RendererAncientMonument() {
        super(TileEntityRendererDispatcher.instance);
        random = new Random();
    }

    @Override
    public void render(TileEntityAncientMonument tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tileEntityIn.getWorld();
        if (world != null) {
            renderItem(tileEntityIn,tileEntityIn.getDisplayItem(),matrixStackIn,bufferIn,partialTicks,combinedOverlayIn);
        }
    }

    public int getLight(BlockPos pos,World world){
        int val = world.getLight(pos.up());
        return val*13;
    }

    public void renderItem(TileEntityAncientMonument tileEntityIn,ItemStack itemStack, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, float partialTicks, int combinedOverlayIn) {
        if (itemStack != ItemStack.EMPTY) {
            matrixStackIn.push();
            Item gemCrystalItem = FCBlocks.GEM_CRYSTAL.asItem();
            Item iguanaEggItem = FCBlocks.IGUANA_KING_EGG.asItem();
            if (itemStack.getItem().equals(gemCrystalItem) || itemStack.getItem().equals(iguanaEggItem)) {
                matrixStackIn.translate(0.5D, 1.5, 0.5D);
                Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED, getLight(tileEntityIn.getPos(),tileEntityIn.getWorld()), combinedOverlayIn, matrixStackIn, bufferIn);
            }else{
                matrixStackIn.translate(0.5D, 1.1, 0.5D);
                renderItemFloat(itemStack,tileEntityIn.getPos(),tileEntityIn.getWorld(),tileEntityIn.getTicks(),tileEntityIn.getHoverStart(),partialTicks,matrixStackIn,bufferIn,combinedOverlayIn);
                //Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.GROUND, getLight(tileEntityIn.getPos(),tileEntityIn.getWorld()), combinedOverlayIn, matrixStackIn, bufferIn);
            }
            matrixStackIn.pop();
        }
    }

    public void renderItemFloat(ItemStack itemstack,BlockPos pos,World world,long ticks,int hoverStart,float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,int overlay) {
        matrixStackIn.push();
        int i = itemstack.isEmpty() ? 187 : Item.getIdFromItem(itemstack.getItem()) + itemstack.getDamage();
        this.random.setSeed((long)i);
        IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(itemstack, world, (LivingEntity)null);
        boolean flag = ibakedmodel.isGui3d();
        int j = 1;
        float f = 0.25F;
        float f1 = MathHelper.sin(((float)ticks + partialTicks) / 10.0F + hoverStart) * 0.1F + 0.1F;
        float f2 = ibakedmodel.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.getY();
        matrixStackIn.translate(0.0D, (double)(f1 + 0.25F * f2), 0.0D);
        float f3 = ((float)ticks + partialTicks) / 20.0F + hoverStart;
        matrixStackIn.rotate(Vector3f.YP.rotation(f3));
        if (!flag) {
            float f7 = -0.0F * (float)(j - 1) * 0.5F;
            float f8 = -0.0F * (float)(j - 1) * 0.5F;
            float f9 = -0.09375F * (float)(j - 1) * 0.5F;
            matrixStackIn.translate((double)f7, (double)f8, (double)f9);
        }

        for(int k = 0; k < j; ++k) {
            matrixStackIn.push();
            if (k > 0) {
                if (flag) {
                    float f11 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    float f13 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    float f10 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                    matrixStackIn.translate(f11,f13,f10);
                } else {
                    float f12 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                    float f14 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                    matrixStackIn.translate(f12,f14, 0.0D);
                }
            }

            Minecraft.getInstance().getItemRenderer().renderItem(itemstack, ItemCameraTransforms.TransformType.GROUND, getLight(pos,world), overlay, matrixStackIn, bufferIn);
            matrixStackIn.pop();
            if (!flag) {
                matrixStackIn.translate(0.0, 0.0, 0.09375F);
            }
        }
        matrixStackIn.pop();
    }

}
