package org.astemir.forestcraft.client.render.model.entity.animals;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.animals.EntityDeer;

public class ModelDeer extends AgeableModel<EntityDeer> {

	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer ear_2;
	private final ModelRenderer ear;
	private final ModelRenderer horn_1;
	private final ModelRenderer horn_2;


	public ModelDeer() {
		super(false, 11.0F, 2.0F);
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);
		body.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, -9.0F, 10.0F, 7.0F, 16.0F, 0.0F, false);
		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(-1.5F, 19.5F, 14.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, -0.1745F, 0.0F, 0.0F);
		body_r1.setTextureOffset(33, 49).addBox(0.0F, -17.0F, -11.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(4.0F, 12.0F, 7.0F);
		leg0.setTextureOffset(24, 37).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-4.0F, 12.0F, 7.0F);
		leg1.setTextureOffset(12, 37).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(4.0F, 12.0F, -6.0F);
		leg2.setTextureOffset(0, 37).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-2.0F, 12.0F, -6.0F);
		leg3.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 7.0F, -6.5F);
		setRotationAngle(neck, 0.2182F, 0.0F, 0.0F);
		neck.setTextureOffset(36, 0).addBox(-3.0F, -4.519F, -0.8918F, 6.0F, 7.0F, 5.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 1.677F, -6.1015F);
		setRotationAngle(head, 0.0F, -0.0873F, 0.0F);
		head.setTextureOffset(42, 42).addBox(-3.0F, -3.8184F, -6.5277F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(0, 23).addBox(-5.0F, -6.8184F, -2.5277F, 8.0F, 8.0F, 6.0F, 0.0F, false);

		ear_2 = new ModelRenderer(this);
		ear_2.setRotationPoint(-4.0F, -6.8184F, 0.4723F);
		head.addChild(ear_2);
		setRotationAngle(ear_2, 0.0F, 0.0F, 0.5236F);
		ear_2.setTextureOffset(36, 12).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		ear = new ModelRenderer(this);
		ear.setRotationPoint(2.5F, -5.8184F, 1.4723F);
		head.addChild(ear);
		setRotationAngle(ear, 0.0F, 0.0F, -0.5236F);
		ear.setTextureOffset(44, 12).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		horn_1 = new ModelRenderer(this);
		horn_1.setRotationPoint(0.5F, -5.8184F, 0.4723F);
		head.addChild(horn_1);
		setRotationAngle(horn_1, 0.7854F, 0.0F, -1.0472F);
		horn_1.setTextureOffset(28, 30).addBox(-0.134F, -4.3536F, -0.6464F, 14.0F, 7.0F, 0.0F, 0.0F, false);

		horn_2 = new ModelRenderer(this);
		horn_2.setRotationPoint(-6.5F, -12.8184F, 0.4723F);
		head.addChild(horn_2);
		setRotationAngle(horn_2, 0.7854F, 0.0F, 1.0472F);
		horn_2.setTextureOffset(28, 23).addBox(-5.866F, -4.3536F, -0.6464F, 14.0F, 7.0F, 0.0F, 0.0F, false);
	}



	@Override
	public void setRotationAngles(EntityDeer entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (isChild){
			horn_1.showModel = false;
			horn_2.showModel = false;
		}else{
			horn_1.showModel = true;
			horn_2.showModel = true;
		}
		super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(head);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(body,neck,leg0,leg1,leg2,leg3);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}