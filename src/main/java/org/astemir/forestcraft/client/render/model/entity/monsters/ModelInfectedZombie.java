package org.astemir.forestcraft.client.render.model.entity.monsters;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.monsters.EntityInfectedZombie;

public class ModelInfectedZombie extends AgeableModel<EntityInfectedZombie> {
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer mush_r1;
	private final ModelRenderer mush_r2;
	private final ModelRenderer head_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg_r1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg_r1;

	public ModelInfectedZombie() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, 0.0F);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 13.0F, 2.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, 0.1745F, 0.0F, 0.0F);
		body_r1.setTextureOffset(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -9.0F, -2.0F);
		body.addChild(head);
		

		mush_r1 = new ModelRenderer(this);
		mush_r1.setRotationPoint(0.0F, 16.0F, 6.0F);
		head.addChild(mush_r1);
		setRotationAngle(mush_r1, 0.1309F, 0.0F, 0.0F);
		mush_r1.setTextureOffset(8, 48).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		mush_r2 = new ModelRenderer(this);
		mush_r2.setRotationPoint(4.0F, 16.5F, 2.0F);
		head.addChild(mush_r2);
		setRotationAngle(mush_r2, 0.1309F, 0.0F, 0.0F);
		mush_r2.setTextureOffset(0, 32).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, 23.0F, 3.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.1309F, 0.0F, 0.0F);
		head_r1.setTextureOffset(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, -9.0F, 0.0F);
		body.addChild(leftArm);
		

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(5.0F, 21.0F, 6.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, 0.3491F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, -9.0F, 0.0F);
		body.addChild(rightArm);
		

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(-5.0F, 21.0F, 6.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, 0.3491F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(40, 16).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		

		leftLeg_r1 = new ModelRenderer(this);
		leftLeg_r1.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, 0.0F, -0.0436F, 0.0F);
		leftLeg_r1.setTextureOffset(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		

		rightLeg_r1 = new ModelRenderer(this);
		rightLeg_r1.setRotationPoint(-1.9F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, 0.0F, 0.0436F, 0.0F);
		rightLeg_r1.setTextureOffset(0, 16).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableSet.of(head);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableSet.of(body,leftArm,rightArm,leftLeg,rightLeg);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public ModelRenderer getHead() {
		return head;
	}

	@Override
	public void setRotationAngles(EntityInfectedZombie entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = MathHelper.sin(swingProgress * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - swingProgress) * (1.0F - swingProgress)) * (float)Math.PI);
		body.rotateAngleZ = MathHelper.sin(ageInTicks/8)/8;
		head.rotateAngleZ = MathHelper.sin(ageInTicks/10)/10;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightArm.rotateAngleY = -(0.1F - f * 0.6F);
		leftArm.rotateAngleY = 0.1F - f * 0.6F;

		float f2 = -(float)Math.PI / (entityIn.getAttackTicks() > 0 ? 1.5F : 2.25F);
		rightArm.rotateAngleX = f2;
		leftArm.rotateAngleX = f2;
		rightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
		leftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;

		rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}
}