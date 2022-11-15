package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityArchaicSentinel;

public class ModelRunestoneLord extends AnimatedEntityModel<EntityArchaicSentinel> {

	private final AModelRenderer ancient_hero;
	private final AModelRenderer leftLeg;
	private final AModelRenderer leftLeg_r1_r1;
	private final AModelRenderer leftLegPart;
	private final AModelRenderer leftLeg_r1;
	private final AModelRenderer rightLeg;
	private final AModelRenderer rightLeg_r1;
	private final AModelRenderer rightLegPart;
	private final AModelRenderer rightLeg_r2;
	private final AModelRenderer body;
	private final AModelRenderer rightArm;
	private final AModelRenderer rightArm_r1;
	private final AModelRenderer rightArm_r3_r1;
	private final AModelRenderer leftArm;
	private final AModelRenderer leftArm_r;
	private final AModelRenderer rightArm_r4_r1;
	private final AModelRenderer head;

	public static AnimationFile ANCIENT_RUNESTONE_HERO_ANIMATIONS = new AnimationFile("animations/ancient_runestone_hero_animations.json");

	public ModelRunestoneLord() {
		textureWidth = 256;
		textureHeight = 256;

		ancient_hero = new AModelRenderer(this);
		ancient_hero.setRotationPoint(0.0F, 24.0F, 0.0F);


		leftLeg = new AModelRenderer(this);
		leftLeg.setRotationPoint(-7.9F, -33.0F, 4.0F);
		ancient_hero.addChild(leftLeg);
		leftLeg.setTextureOffset(78, 100).addBox(-5.5F, -0.8565F, -3.7857F, 10.0F, 10.0F, 9.0F, 0.0F, false);

		leftLeg_r1_r1 = new AModelRenderer(this);
		leftLeg_r1_r1.setRotationPoint(3.9F, 30.1435F, 0.7143F);
		leftLeg.addChild(leftLeg_r1_r1);
		setRotationAngle(leftLeg_r1_r1, -0.0873F, 0.0F, 0.0F);
		leftLeg_r1_r1.setTextureOffset(36, 113).addBox(-7.4F, -21.1744F, -4.5076F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		leftLegPart = new AModelRenderer(this);
		leftLegPart.setRotationPoint(-0.1F, 17.0F, -3.0F);
		leftLeg.addChild(leftLegPart);
		setRotationAngle(leftLegPart, -0.0873F, 0.0F, 0.0F);


		leftLeg_r1 = new AModelRenderer(this);
		leftLeg_r1.setRotationPoint(4.0F, 12.5454F, 3.4077F);
		leftLegPart.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, 0.0873F, 0.0F, 0.0F);
		leftLeg_r1.setTextureOffset(91, 0).addBox(-8.9F, -12.0F, -4.0F, 9.0F, 15.0F, 9.0F, 0.0F, false);

		rightLeg = new AModelRenderer(this);
		rightLeg.setRotationPoint(6.1F, -33.0F, 4.0F);
		ancient_hero.addChild(rightLeg);
		rightLeg.setTextureOffset(78, 100).addBox(-5.5F, -0.8565F, -3.7857F, 10.0F, 10.0F, 9.0F, 0.0F, false);

		rightLeg_r1 = new AModelRenderer(this);
		rightLeg_r1.setRotationPoint(3.9F, 30.1435F, 0.7143F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, -0.0873F, 0.0F, 0.0F);
		rightLeg_r1.setTextureOffset(36, 113).addBox(-7.4F, -21.1744F, -4.5076F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		rightLegPart = new AModelRenderer(this);
		rightLegPart.setRotationPoint(-0.1F, 17.0F, -3.0F);
		rightLeg.addChild(rightLegPart);
		setRotationAngle(rightLegPart, -0.0873F, 0.0F, 0.0F);


		rightLeg_r2 = new AModelRenderer(this);
		rightLeg_r2.setRotationPoint(4.0F, 12.5454F, 3.4077F);
		rightLegPart.addChild(rightLeg_r2);
		setRotationAngle(rightLeg_r2, 0.0873F, 0.0F, 0.0F);
		rightLeg_r2.setTextureOffset(91, 0).addBox(-8.9F, -12.0F, -4.0F, 9.0F, 15.0F, 9.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(-1.0F, -32.75F, 6.0F);
		ancient_hero.addChild(body);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-7.0F, -17.5003F, -12.204F, 15.0F, 17.0F, 16.0F, 0.0F, false);
		body.setTextureOffset(0, 33).addBox(-8.0F, -2.5003F, -13.204F, 17.0F, 6.0F, 18.0F, 0.0F, false);

		rightArm = new AModelRenderer(this);
		rightArm.setRotationPoint(8.0F, -10.5003F, -3.454F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, -0.5236F, 0.0F, -0.3491F);
		rightArm.setTextureOffset(109, 112).addBox(-0.1206F, -4.4076F, -2.658F, 8.0F, 12.0F, 7.0F, 0.0F, false);

		rightArm_r1 = new AModelRenderer(this);
		rightArm_r1.setRotationPoint(1.8794F, 6.5924F, -0.658F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, -0.0873F, 0.0F, 0.0F);


		rightArm_r3_r1 = new AModelRenderer(this);
		rightArm_r3_r1.setRotationPoint(-9.0F, 21.7005F, 2.1335F);
		rightArm_r1.addChild(rightArm_r3_r1);
		setRotationAngle(rightArm_r3_r1, -0.2182F, 0.0F, 0.0F);
		rightArm_r3_r1.setTextureOffset(0, 57).addBox(4.5F, 0.25F, -13.5F, 13.0F, 12.0F, 13.0F, 0.0F, false);
		rightArm_r3_r1.setTextureOffset(0, 82).addBox(5.5F, -21.75F, -10.5F, 11.0F, 22.0F, 10.0F, 0.0F, false);

		leftArm = new AModelRenderer(this);
		leftArm.setRotationPoint(-9.0F, -10.5003F, -3.454F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, -0.5236F, 0.0F, 0.3491F);
		leftArm.setTextureOffset(104, 24).addBox(-6.0F, -5.0F, -3.0F, 8.0F, 12.0F, 7.0F, 0.0F, false);

		leftArm_r = new AModelRenderer(this);
		leftArm_r.setRotationPoint(0.0F, 5.0F, -1.0F);
		leftArm.addChild(leftArm_r);
		setRotationAngle(leftArm_r, -0.0873F, 0.0F, 0.0F);


		rightArm_r4_r1 = new AModelRenderer(this);
		rightArm_r4_r1.setRotationPoint(9.0F, 22.6967F, 2.2206F);
		leftArm_r.addChild(rightArm_r4_r1);
		setRotationAngle(rightArm_r4_r1, -0.2182F, 0.0F, 0.0F);
		rightArm_r4_r1.setTextureOffset(52, 20).addBox(-17.5F, 0.25F, -13.5F, 13.0F, 12.0F, 13.0F, 0.0F, false);
		rightArm_r4_r1.setTextureOffset(52, 57).addBox(-16.5F, -21.75F, -10.5F, 11.0F, 22.0F, 10.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(1.0F, -17.5003F, -5.454F);
		body.addChild(head);
		head.setTextureOffset(83, 78).addBox(-6.0F, -11.0F, -4.75F, 11.0F, 11.0F, 11.0F, 0.0F, false);
		initializeAnimator(ANCIENT_RUNESTONE_HERO_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		ancient_hero.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void animate(EntityArchaicSentinel entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityArchaicSentinel.RUN || animation == EntityArchaicSentinel.IDLE || animation == EntityArchaicSentinel.TWIRLING) {
			if (ignoreUsedPartsInOtherAnimations(animated, part, EntityArchaicSentinel.HIT, EntityArchaicSentinel.HIT2)) {
				return false;
			}
		}
		return true;
	}


}