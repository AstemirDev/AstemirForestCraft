package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.monsters.EntityWoodAbomination;

public class ModelWoodAbomination extends EntityModel<EntityWoodAbomination> {

	private final ModelRenderer abomination;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg_r1;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer head;
	private final ModelRenderer head_r1;

	public ModelWoodAbomination() {
		textureWidth = 64;
		textureHeight = 64;

		abomination = new ModelRenderer(this);
		abomination.setRotationPoint(0.0F, 24.0F, 11.0F);
		setRotationAngle(abomination, 1.0036F, 0.0F, 0.0F);
		

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, -12.0F, 0.0F);
		abomination.addChild(rightLeg);
		

		rightLeg_r1 = new ModelRenderer(this);
		rightLeg_r1.setRotationPoint(-1.9F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, -0.2182F, 0.0F, -0.2618F);
		rightLeg_r1.setTextureOffset(12, 41).addBox(3.9F, -12.0F, -2.0F, 3.0F, 12.0F, 3.0F, 0.0F, true);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, -22.0F, 0.0F);
		abomination.addChild(rightArm);
		

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(-5.0F, 22.0F, 0.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, -1.0908F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(40, 41).addBox(5.0F, -7.0F, -22.0F, 2.0F, 9.0F, 3.0F, 0.0F, true);
		rightArm_r1.setTextureOffset(41, 0).addBox(4.0F, -11.0F, -23.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, -12.0F, 0.0F);
		abomination.addChild(leftLeg);
		

		leftLeg_r1 = new ModelRenderer(this);
		leftLeg_r1.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, -0.2182F, 0.0F, 0.2618F);
		leftLeg_r1.setTextureOffset(0, 41).addBox(-6.9F, -12.0F, -2.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -24.0F, 0.0F);
		abomination.addChild(body);
		setRotationAngle(body, 0.2182F, 0.0F, 0.0F);
		body.setTextureOffset(32, 25).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(0, 0).addBox(-7.0F, 0.0F, 2.0F, 14.0F, 12.0F, 13.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		abomination.addChild(leftArm);
		

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, -1.0908F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(0, 0).addBox(-7.0F, -7.0F, -22.0F, 2.0F, 9.0F, 3.0F, 0.0F, false);
		leftArm_r1.setTextureOffset(41, 0).addBox(-8.0F, -11.0F, -23.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		abomination.addChild(head);
		

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.8727F, 0.0F, 0.0F);
		head_r1.setTextureOffset(0, 25).addBox(-4.0F, -23.0F, -26.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(EntityWoodAbomination entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		abomination.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}