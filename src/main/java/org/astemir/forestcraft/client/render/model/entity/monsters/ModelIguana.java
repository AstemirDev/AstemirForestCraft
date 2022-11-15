package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityIguana;

public class ModelIguana extends AnimatedEntityModel<EntityIguana> {

	private final AModelRenderer lizard;
	private final AModelRenderer leftLeg;
	private final AModelRenderer leftLeg_r1;
	private final AModelRenderer leftLeg_r2;
	private final AModelRenderer rightLeg;
	private final AModelRenderer rightLeg_r1;
	private final AModelRenderer rightLeg_r2;
	private final AModelRenderer body;
	private final AModelRenderer leftArm;
	private final AModelRenderer hand;
	private final AModelRenderer leftArm_r1;
	private final AModelRenderer rightArm;
	private final AModelRenderer hand2;
	private final AModelRenderer rightArm_r1;
	private final AModelRenderer head;
	private final AModelRenderer ear;
	private final AModelRenderer ear2;
	private final AModelRenderer tail;
	private final AModelRenderer tailB;

	public static AnimationFile IGUANA_ANIMATIONS = new AnimationFile("animations/iguana_animations.json");


	public ModelIguana() {
		textureWidth = 64;
		textureHeight = 64;

		lizard = new AModelRenderer(this);
		lizard.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leftLeg = new AModelRenderer(this);
		leftLeg.setRotationPoint(-2.9F, -12.0F, 0.0F);
		lizard.addChild(leftLeg);
		

		leftLeg_r1 = new AModelRenderer(this);
		leftLeg_r1.setRotationPoint(2.9F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, 0.0F, 0.0873F, 0.0F);
		leftLeg_r1.setTextureOffset(31, 35).addBox(-5.4F, -2.0F, -4.25F, 5.0F, 2.0F, 5.0F, 0.0F, false);
		leftLeg_r1.setTextureOffset(48, 0).addBox(-4.4F, -6.0F, -2.75F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		leftLeg_r2 = new AModelRenderer(this);
		leftLeg_r2.setRotationPoint(2.9F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r2);
		setRotationAngle(leftLeg_r2, -0.1745F, 0.0873F, 0.0F);
		leftLeg_r2.setTextureOffset(0, 32).addBox(-4.9F, -12.0F, -4.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		rightLeg = new AModelRenderer(this);
		rightLeg.setRotationPoint(2.9F, -12.0F, 0.0F);
		lizard.addChild(rightLeg);
		

		rightLeg_r1 = new AModelRenderer(this);
		rightLeg_r1.setRotationPoint(-2.9F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, 0.0F, -0.0873F, 0.0F);
		rightLeg_r1.setTextureOffset(31, 35).addBox(0.4F, -2.0F, -4.25F, 5.0F, 2.0F, 5.0F, 0.0F, false);
		rightLeg_r1.setTextureOffset(48, 0).addBox(1.4F, -6.0F, -2.75F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		rightLeg_r2 = new AModelRenderer(this);
		rightLeg_r2.setRotationPoint(-2.9F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r2);
		setRotationAngle(rightLeg_r2, -0.1745F, -0.0873F, 0.0F);
		rightLeg_r2.setTextureOffset(0, 32).addBox(0.9F, -12.0F, -4.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -13.0F, 0.0F);
		lizard.addChild(body);
		setRotationAngle(body, 0.2618F, 0.0F, 0.0F);
		body.setTextureOffset(0, 16).addBox(-4.0F, -10.4357F, -2.3096F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(24, 10).addBox(0.0F, -12.4357F, 0.6904F, 0.0F, 12.0F, 6.0F, 0.0F, false);

		leftArm = new AModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, -8.4357F, -0.3096F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, -0.5236F, 0.0F, 0.0F);
		leftArm.setTextureOffset(32, 0).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		hand = new AModelRenderer(this);
		hand.setRotationPoint(-1.0F, 6.0F, 1.0F);
		leftArm.addChild(hand);
		

		leftArm_r1 = new AModelRenderer(this);
		leftArm_r1.setRotationPoint(6.0F, 17.0F, 0.0F);
		hand.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, -0.1309F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(48, 8).addBox(-7.5F, -17.0F, -3.75F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		leftArm_r1.setTextureOffset(0, 44).addBox(-8.5F, -13.0F, -4.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		rightArm = new AModelRenderer(this);
		rightArm.setRotationPoint(4.0F, -8.4357F, 0.6904F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, -0.48F, 0.0F, 0.0F);
		rightArm.setTextureOffset(32, 0).addBox(0.0F, -1.113F, -1.5383F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		hand2 = new AModelRenderer(this);
		hand2.setRotationPoint(2.0F, 6.0F, 1.0F);
		rightArm.addChild(hand2);
		

		rightArm_r1 = new AModelRenderer(this);
		rightArm_r1.setRotationPoint(-6.0F, 16.887F, -0.5383F);
		hand2.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, -0.1309F, 0.0F, 0.0F);
		rightArm_r1.setTextureOffset(48, 8).addBox(4.5F, -17.0F, -3.75F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		rightArm_r1.setTextureOffset(34, 42).addBox(4.5F, -13.0F, -4.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -10.4357F, -1.3096F);
		body.addChild(head);
		setRotationAngle(head, -0.2618F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(14, 42).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);

		ear = new AModelRenderer(this);
		ear.setRotationPoint(-4.0F, -4.0F, -2.0F);
		head.addChild(ear);
		ear.setTextureOffset(37, 15).addBox(-8.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, true);

		ear2 = new AModelRenderer(this);
		ear2.setRotationPoint(4.0F, -4.0F, -2.0F);
		head.addChild(ear2);
		ear2.setTextureOffset(37, 15).addBox(0.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, 1.5643F, 1.6904F);
		body.addChild(tail);
		tail.setTextureOffset(46, 30).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		tailB = new AModelRenderer(this);
		tailB.setRotationPoint(0.0F, 6.0F, -1.0F);
		tail.addChild(tailB);
		tailB.setTextureOffset(28, 48).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		initializeAnimator(IGUANA_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		lizard.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


	@Override
	public void animate(EntityIguana entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.setCustomRotation(new Vector3((float) Math.toRadians(Math.min(30, Math.max(-30, headPitch))),(float) Math.toRadians(Math.min(30, Math.max(-30, netHeadYaw))),0));
		if (entityIn.getFactory().isPlaying(EntityIguana.IDLE)){
			leftLeg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing* 0.6662F)*1.4F * limbSwingAmount,0,0));
			rightLeg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing* 0.6662F + (float)Math.PI)*1.4f * limbSwingAmount,0,0));
		}
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}