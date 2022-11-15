package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.astemir.forestcraft.common.entities.monsters.EntityAirSucker;

public class ModelAirSucker extends EntityModel<EntityAirSucker> {

	private final ModelRenderer body;
	private final ModelRenderer tentacle1;
	private final ModelRenderer tentacle2;
	private final ModelRenderer tentacle3;
	private final ModelRenderer tentacle4;
	private final ModelRenderer tentacle5;
	private final ModelRenderer tentacle6;
	private final ModelRenderer tentacle7;
	private final ModelRenderer tentacle8;

	public ModelAirSucker() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(0, 24).addBox(-5.0F, -25.0F, -5.0F, 10.0F, 9.0F, 10.0F, 0.0F, false);

		tentacle1 = new ModelRenderer(this);
		tentacle1.setRotationPoint(-5.0F, 21.0F, 0.0F);
		setRotationAngle(tentacle1, 0.0F, -1.5708F, 0.0F);
		tentacle1.setTextureOffset(40, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		tentacle2 = new ModelRenderer(this);
		tentacle2.setRotationPoint(-3.5F, 21.0F, 3.5F);
		setRotationAngle(tentacle2, 0.0F, -0.7854F, 0.0F);
		tentacle2.setTextureOffset(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		tentacle3 = new ModelRenderer(this);
		tentacle3.setRotationPoint(0.0F, 21.0F, 5.0F);
		tentacle3.setTextureOffset(40, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		tentacle4 = new ModelRenderer(this);
		tentacle4.setRotationPoint(3.5F, 21.0F, 3.5F);
		setRotationAngle(tentacle4, 0.0F, 0.7854F, 0.0F);
		tentacle4.setTextureOffset(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		tentacle5 = new ModelRenderer(this);
		tentacle5.setRotationPoint(5.0F, 21.0F, 0.0F);
		setRotationAngle(tentacle5, 0.0F, 1.5708F, 0.0F);
		tentacle5.setTextureOffset(40, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		tentacle6 = new ModelRenderer(this);
		tentacle6.setRotationPoint(3.5F, 21.0F, -3.5F);
		setRotationAngle(tentacle6, 0.0F, 2.3562F, 0.0F);
		tentacle6.setTextureOffset(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		tentacle7 = new ModelRenderer(this);
		tentacle7.setRotationPoint(0.0F, 21.0F, -5.0F);
		setRotationAngle(tentacle7, 0.0F, 3.1416F, 0.0F);
		tentacle7.setTextureOffset(40, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		tentacle8 = new ModelRenderer(this);
		tentacle8.setRotationPoint(-3.5F, 21.0F, -3.5F);
		setRotationAngle(tentacle8, 0.0F, 3.927F, 0.0F);
		tentacle8.setTextureOffset(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle1.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle2.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle3.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle4.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle5.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle6.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle7.render(matrixStack, buffer, packedLight, packedOverlay);
		tentacle8.render(matrixStack, buffer, packedLight, packedOverlay);

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(EntityAirSucker entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		tentacle1.rotateAngleX = ageInTicks;
		tentacle2.rotateAngleX = ageInTicks;
		tentacle3.rotateAngleX = ageInTicks;
		tentacle4.rotateAngleX = ageInTicks;
		tentacle5.rotateAngleX = ageInTicks;
		tentacle6.rotateAngleX = ageInTicks;
		tentacle7.rotateAngleX = ageInTicks;
		tentacle8.rotateAngleX = ageInTicks;
	}


}