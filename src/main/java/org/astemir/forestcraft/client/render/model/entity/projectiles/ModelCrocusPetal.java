package org.astemir.forestcraft.client.render.model.entity.projectiles;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelCrocusPetal extends EntityModel<Entity> {

	private final ModelRenderer petal;

	public ModelCrocusPetal() {
		textureWidth = 32;
		textureHeight = 32;

		petal = new ModelRenderer(this);
		petal.setRotationPoint(0.0F, 24.0F, 0.0F);
		petal.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		petal.rotateAngleX = (float)Math.toRadians(entity.rotationPitch);
		petal.rotateAngleY = (float)Math.toRadians(90-entity.rotationYaw);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		petal.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}