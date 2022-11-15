package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityIguanaKing;

public class ModelIguanaKing extends AnimatedEntityModel<EntityIguanaKing> {

	private final AModelRenderer iguana_king;
	private final AModelRenderer leftLeg;
	private final AModelRenderer leftLeg_r1;
	private final AModelRenderer leftLeg_part;
	private final AModelRenderer leftLeg_r2;
	private final AModelRenderer rightLeg;
	private final AModelRenderer rightLeg_r1;
	private final AModelRenderer rightLeg_part;
	private final AModelRenderer rightLeg_r2;
	private final AModelRenderer body;
	private final AModelRenderer body_r1;
	private final AModelRenderer tail;
	private final AModelRenderer body_r2;
	private final AModelRenderer tail_part;
	private final AModelRenderer body_r3;
	private final AModelRenderer torso;
	private final AModelRenderer body_r4;
	private final AModelRenderer body_r5;
	private final AModelRenderer body_r6;
	private final AModelRenderer breath;
	private final AModelRenderer body_r7;
	private final AModelRenderer head;
	private final AModelRenderer head_r1;
	private final AModelRenderer head_r2;
	private final AModelRenderer head_r3;
	private final AModelRenderer earRight;
	private final AModelRenderer earLeft;
	private final AModelRenderer smallEarRight;
	private final AModelRenderer smallEarLeft;
	private final AModelRenderer leftArm;
	private final AModelRenderer leftArm_r1;
	private final AModelRenderer leftArm_part;
	private final AModelRenderer leftArm_r2;
	private final AModelRenderer leftArm_r3;
	private final AModelRenderer leftArm_r4;
	private final AModelRenderer leftArm_r5;
	private final AModelRenderer leftArm_r6;
	private final AModelRenderer leftArm_r7;
	private final AModelRenderer rightArm;
	private final AModelRenderer leftArm_r8;
	private final AModelRenderer rightArm_part;
	private final AModelRenderer leftArm_r9;
	private final AModelRenderer leftArm_r10;
	private final AModelRenderer leftArm_r11;
	private final AModelRenderer leftArm_r12;
	private final AModelRenderer leftArm_r13;
	private final AModelRenderer leftArm_r14;

	public static AnimationFile IGUANA_KING_ANIMATIONS = new AnimationFile("animations/iguana_king_animations.json");

	public ModelIguanaKing() {
		textureWidth = 256;
		textureHeight = 256;

		iguana_king = new AModelRenderer(this);
		iguana_king.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leftLeg = new AModelRenderer(this);
		leftLeg.setRotationPoint(-13.9F, -23.0F, 7.0F);
		iguana_king.addChild(leftLeg);
		

		leftLeg_r1 = new AModelRenderer(this);
		leftLeg_r1.setRotationPoint(1.9F, 12.0F, -9.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, -0.1745F, 0.0F, 0.0F);
		leftLeg_r1.setTextureOffset(90, 0).addBox(-0.9F, -16.0F, -2.0F, 8.0F, 16.0F, 10.0F, 0.0F, false);

		leftLeg_part = new AModelRenderer(this);
		leftLeg_part.setRotationPoint(5.0F, 12.0F, -9.0F);
		leftLeg.addChild(leftLeg_part);
		leftLeg_part.setTextureOffset(98, 56).addBox(-4.5F, 6.75F, -2.75F, 8.0F, 4.0F, 12.0F, 0.0F, false);
		leftLeg_part.setTextureOffset(138, 64).addBox(-4.5F, 7.75F, -6.75F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		leftLeg_part.setTextureOffset(136, 136).addBox(0.5F, 7.75F, -6.75F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		leftLeg_r2 = new AModelRenderer(this);
		leftLeg_r2.setRotationPoint(-3.1F, 13.0F, 8.25F);
		leftLeg_part.addChild(leftLeg_r2);
		setRotationAngle(leftLeg_r2, 0.5236F, 0.0F, 0.0F);
		leftLeg_r2.setTextureOffset(114, 121).addBox(-0.9F, -17.0F, -2.0F, 7.0F, 12.0F, 6.0F, 0.0F, false);

		rightLeg = new AModelRenderer(this);
		rightLeg.setRotationPoint(0.1F, -23.0F, 7.0F);
		iguana_king.addChild(rightLeg);
		

		rightLeg_r1 = new AModelRenderer(this);
		rightLeg_r1.setRotationPoint(1.9F, 12.0F, -9.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, -0.1745F, 0.0F, 0.0F);
		rightLeg_r1.setTextureOffset(0, 88).addBox(-1.9F, -16.0F, -2.0F, 8.0F, 16.0F, 10.0F, 0.0F, false);

		rightLeg_part = new AModelRenderer(this);
		rightLeg_part.setRotationPoint(5.0F, 12.0F, -9.0F);
		rightLeg.addChild(rightLeg_part);
		rightLeg_part.setTextureOffset(17, 114).addBox(0.5F, 7.75F, -6.75F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		rightLeg_part.setTextureOffset(105, 105).addBox(-4.5F, 6.75F, -2.75F, 8.0F, 4.0F, 12.0F, 0.0F, false);
		rightLeg_part.setTextureOffset(116, 0).addBox(-4.5F, 7.75F, -6.75F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		rightLeg_r2 = new AModelRenderer(this);
		rightLeg_r2.setRotationPoint(-3.1F, 13.0F, 8.25F);
		rightLeg_part.addChild(rightLeg_r2);
		setRotationAngle(rightLeg_r2, 0.5236F, 0.0F, 0.0F);
		rightLeg_r2.setTextureOffset(88, 121).addBox(-0.9F, -17.0F, -2.0F, 7.0F, 12.0F, 6.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(-2.0F, -25.0F, 0.0F);
		iguana_king.addChild(body);
		setRotationAngle(body, 0.2182F, 0.0F, 0.0F);
		

		body_r1 = new AModelRenderer(this);
		body_r1.setRotationPoint(2.0F, 29.2263F, 6.5336F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, 0.2182F, 0.0F, 0.0F);
		body_r1.setTextureOffset(0, 32).addBox(-13.0F, -43.0F, -4.0F, 21.0F, 16.0F, 15.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(-1.0F, -3.0237F, 8.7836F);
		body.addChild(tail);
		setRotationAngle(tail, -0.6981F, 0.0F, 0.0F);
		

		body_r2 = new AModelRenderer(this);
		body_r2.setRotationPoint(1.0F, 38.25F, -2.25F);
		tail.addChild(body_r2);
		setRotationAngle(body_r2, -0.1745F, 0.0F, 0.0F);
		body_r2.setTextureOffset(24, 112).addBox(-3.0F, -41.0F, -4.0F, 5.0F, 5.0F, 12.0F, 0.0F, false);

		tail_part = new AModelRenderer(this);
		tail_part.setRotationPoint(0.0F, 1.0F, 13.0F);
		tail.addChild(tail_part);
		

		body_r3 = new AModelRenderer(this);
		body_r3.setRotationPoint(1.0F, 39.5F, -3.5F);
		tail_part.addChild(body_r3);
		setRotationAngle(body_r3, -0.1745F, 0.0F, 0.0F);
		body_r3.setTextureOffset(58, 112).addBox(-2.0F, -40.0F, -4.0F, 3.0F, 4.0F, 12.0F, 0.0F, false);

		torso = new AModelRenderer(this);
		torso.setRotationPoint(0.0F, -12.0237F, -4.2164F);
		body.addChild(torso);
		

		body_r4 = new AModelRenderer(this);
		body_r4.setRotationPoint(-10.0F, 20.0F, -2.0F);
		torso.addChild(body_r4);
		setRotationAngle(body_r4, -0.5236F, 0.0F, 0.0F);
		body_r4.setTextureOffset(73, 32).addBox(9.0F, -37.0F, -3.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		body_r5 = new AModelRenderer(this);
		body_r5.setRotationPoint(-10.0F, 15.0F, 0.0F);
		torso.addChild(body_r5);
		setRotationAngle(body_r5, -0.3927F, 0.0F, 0.0F);
		body_r5.setTextureOffset(14, 134).addBox(9.0F, -39.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		body_r6 = new AModelRenderer(this);
		body_r6.setRotationPoint(-10.0F, 10.0F, -1.0F);
		torso.addChild(body_r6);
		setRotationAngle(body_r6, -0.2618F, 0.0F, 0.0F);
		body_r6.setTextureOffset(48, 63).addBox(9.0F, -43.0F, -3.0F, 2.0F, 16.0F, 3.0F, 0.0F, false);

		breath = new AModelRenderer(this);
		breath.setRotationPoint(0.0F, -7.0F, 2.0F);
		torso.addChild(breath);
		

		body_r7 = new AModelRenderer(this);
		body_r7.setRotationPoint(2.0F, 33.0F, 5.0F);
		breath.addChild(body_r7);
		setRotationAngle(body_r7, 0.2182F, 0.0F, 0.0F);
		body_r7.setTextureOffset(0, 0).addBox(-17.0F, -43.0F, -5.0F, 29.0F, 16.0F, 16.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(1.0F, -16.0F, -1.0F);
		torso.addChild(head);
		setRotationAngle(head, -0.2182F, 0.0F, 0.0F);
		head.setTextureOffset(72, 32).addBox(-7.0F, -11.0F, -8.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(117, 90).addBox(-6.0F, -9.0F, -11.0F, 10.0F, 10.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(159, 0).addBox(-10.0F, -9.5F, -11.25F, 17.0F, 6.0F, 0.0F, 0.0F, false);

		head_r1 = new AModelRenderer(this);
		head_r1.setRotationPoint(-4.5F, 33.75F, -19.25F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.1745F, 0.0F, -0.0873F);
		head_r1.setTextureOffset(147, 93).addBox(2.0F, -31.0F, 2.0F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		head_r2 = new AModelRenderer(this);
		head_r2.setRotationPoint(2.5F, 33.75F, -19.25F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, -0.1745F, 0.0F, 0.0873F);
		head_r2.setTextureOffset(147, 93).addBox(-3.0F, -31.0F, 2.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		head_r3 = new AModelRenderer(this);
		head_r3.setRotationPoint(5.0F, 29.0F, -17.5F);
		head.addChild(head_r3);
		setRotationAngle(head_r3, -0.1745F, 0.0F, 0.0F);
		head_r3.setTextureOffset(144, 90).addBox(-4.0F, -31.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		head_r3.setTextureOffset(144, 90).addBox(-10.0F, -31.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		earRight = new AModelRenderer(this);
		earRight.setRotationPoint(5.0F, -6.0F, -4.0F);
		head.addChild(earRight);
		earRight.setTextureOffset(109, 72).addBox(0.0F, -11.0F, 0.0F, 18.0F, 18.0F, 0.0F, 0.0F, false);

		earLeft = new AModelRenderer(this);
		earLeft.setRotationPoint(-7.0F, -6.0F, -4.0F);
		head.addChild(earLeft);
		earLeft.setTextureOffset(108, 26).addBox(-18.0F, -11.0F, 0.0F, 18.0F, 18.0F, 0.0F, 0.0F, false);

		smallEarRight = new AModelRenderer(this);
		smallEarRight.setRotationPoint(4.0F, -2.0F, -10.0F);
		head.addChild(smallEarRight);
		smallEarRight.setTextureOffset(89, 139).addBox(0.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		smallEarLeft = new AModelRenderer(this);
		smallEarLeft.setRotationPoint(-6.0F, -2.0F, -9.0F);
		head.addChild(smallEarLeft);
		smallEarLeft.setTextureOffset(26, 88).addBox(-8.0F, -3.0F, -1.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		leftArm = new AModelRenderer(this);
		leftArm.setRotationPoint(-14.0F, -11.0F, 3.0F);
		torso.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, -1.3963F, 0.5236F);
		leftArm.setTextureOffset(126, 0).addBox(-1.7863F, -2.8436F, -5.0F, 5.0F, 13.0F, 7.0F, 0.0F, false);

		leftArm_r1 = new AModelRenderer(this);
		leftArm_r1.setRotationPoint(1.2137F, 34.1564F, 9.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, -0.1309F, 0.0F, 0.0F);
		leftArm_r1.setTextureOffset(77, 86).addBox(-5.3806F, -27.4776F, -17.9059F, 9.0F, 15.0F, 11.0F, 0.0F, false);

		leftArm_part = new AModelRenderer(this);
		leftArm_part.setRotationPoint(0.0F, 19.0F, -1.0F);
		leftArm.addChild(leftArm_part);
		

		leftArm_r2 = new AModelRenderer(this);
		leftArm_r2.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r2);
		setRotationAngle(leftArm_r2, -0.829F, 0.0F, 0.0F);
		leftArm_r2.setTextureOffset(126, 57).addBox(-0.8093F, 7.5206F, -13.4163F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		leftArm_r2.setTextureOffset(24, 129).addBox(-6.1941F, 7.5649F, -13.3678F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		leftArm_r3 = new AModelRenderer(this);
		leftArm_r3.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r3);
		setRotationAngle(leftArm_r3, -1.8326F, 0.0F, 0.0F);
		leftArm_r3.setTextureOffset(53, 128).addBox(-7.56F, 15.274F, -12.924F, 6.0F, 8.0F, 5.0F, 0.0F, false);
		leftArm_r3.setTextureOffset(0, 134).addBox(-1.3777F, 15.4362F, 3.7524F, 4.0F, 9.0F, 3.0F, 0.0F, false);
		leftArm_r3.setTextureOffset(75, 136).addBox(-6.124F, 16.1137F, 3.4843F, 4.0F, 9.0F, 3.0F, 0.0F, false);

		leftArm_r4 = new AModelRenderer(this);
		leftArm_r4.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r4);
		setRotationAngle(leftArm_r4, -1.8326F, 0.0F, -0.5236F);
		leftArm_r4.setTextureOffset(37, 137).addBox(5.868F, 20.0124F, 3.0946F, 4.0F, 6.0F, 3.0F, 0.0F, false);

		leftArm_r5 = new AModelRenderer(this);
		leftArm_r5.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r5);
		setRotationAngle(leftArm_r5, -0.829F, 0.0F, -0.5236F);
		leftArm_r5.setTextureOffset(76, 112).addBox(5.7254F, 9.0066F, -17.7152F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		leftArm_r6 = new AModelRenderer(this);
		leftArm_r6.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r6);
		setRotationAngle(leftArm_r6, -1.0036F, 0.0F, 0.0F);
		leftArm_r6.setTextureOffset(74, 0).addBox(-6.7802F, 6.338F, -25.2761F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		leftArm_r7 = new AModelRenderer(this);
		leftArm_r7.setRotationPoint(1.2137F, 15.1564F, 10.0F);
		leftArm_part.addChild(leftArm_r7);
		setRotationAngle(leftArm_r7, -0.5236F, 0.0F, 0.0F);
		leftArm_r7.setTextureOffset(0, 63).addBox(-7.7248F, -8.595F, -22.4167F, 13.0F, 14.0F, 11.0F, 0.0F, false);

		rightArm = new AModelRenderer(this);
		rightArm.setRotationPoint(14.3474F, -12.2206F, 3.3337F);
		torso.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 1.3963F, -0.5236F);
		rightArm.setTextureOffset(0, 114).addBox(-3.3491F, -2.3473F, -5.4921F, 5.0F, 13.0F, 7.0F, 0.0F, false);

		leftArm_r8 = new AModelRenderer(this);
		leftArm_r8.setRotationPoint(-1.3491F, 34.6527F, 8.5079F);
		rightArm.addChild(leftArm_r8);
		setRotationAngle(leftArm_r8, -0.1309F, 0.0F, 0.0F);
		leftArm_r8.setTextureOffset(37, 86).addBox(-3.6194F, -27.4776F, -17.9059F, 9.0F, 15.0F, 11.0F, 0.0F, false);

		rightArm_part = new AModelRenderer(this);
		rightArm_part.setRotationPoint(0.0F, 20.0F, 0.0F);
		rightArm.addChild(rightArm_part);
		

		leftArm_r9 = new AModelRenderer(this);
		leftArm_r9.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r9);
		setRotationAngle(leftArm_r9, -1.0036F, 0.0F, 0.0F);
		leftArm_r9.setTextureOffset(0, 0).addBox(2.7802F, 6.338F, -25.2761F, 4.0F, 10.0F, 4.0F, 0.0F, false);

		leftArm_r10 = new AModelRenderer(this);
		leftArm_r10.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r10);
		setRotationAngle(leftArm_r10, -1.8326F, 0.0F, 0.0F);
		leftArm_r10.setTextureOffset(126, 44).addBox(1.56F, 15.274F, -12.924F, 6.0F, 8.0F, 5.0F, 0.0F, false);
		leftArm_r10.setTextureOffset(0, 32).addBox(-2.6223F, 15.4362F, 3.7524F, 4.0F, 9.0F, 3.0F, 0.0F, false);
		leftArm_r10.setTextureOffset(133, 103).addBox(2.7625F, 15.4192F, 3.8159F, 4.0F, 9.0F, 3.0F, 0.0F, false);

		leftArm_r11 = new AModelRenderer(this);
		leftArm_r11.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r11);
		setRotationAngle(leftArm_r11, -1.8326F, 0.0F, 0.5236F);
		leftArm_r11.setTextureOffset(58, 63).addBox(-9.868F, 20.0124F, 3.0946F, 4.0F, 6.0F, 3.0F, 0.0F, false);

		leftArm_r12 = new AModelRenderer(this);
		leftArm_r12.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r12);
		setRotationAngle(leftArm_r12, -0.829F, 0.0F, 0.5236F);
		leftArm_r12.setTextureOffset(57, 32).addBox(-9.7254F, 9.0066F, -17.7152F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		leftArm_r13 = new AModelRenderer(this);
		leftArm_r13.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r13);
		setRotationAngle(leftArm_r13, -0.829F, 0.0F, 0.0F);
		leftArm_r13.setTextureOffset(66, 86).addBox(-3.1907F, 7.5206F, -13.4163F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		leftArm_r13.setTextureOffset(46, 112).addBox(2.1941F, 7.5649F, -13.3678F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		leftArm_r14 = new AModelRenderer(this);
		leftArm_r14.setRotationPoint(-1.3491F, 14.6527F, 8.5079F);
		rightArm_part.addChild(leftArm_r14);
		setRotationAngle(leftArm_r14, -0.5236F, 0.0F, 0.0F);
		leftArm_r14.setTextureOffset(61, 61).addBox(-5.2752F, -8.595F, -22.4167F, 13.0F, 14.0F, 11.0F, 0.0F, false);
		initializeAnimator(IGUANA_KING_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		iguana_king.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(EntityIguanaKing entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.setCustomRotation(new Vector3((float) Math.toRadians(Math.min(30, Math.max(-30, headPitch))),(float) Math.toRadians(Math.min(30, Math.max(-30, netHeadYaw))),0));
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityIguanaKing.RUN || animation == EntityIguanaKing.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityIguanaKing.HIT_GROUND,EntityIguanaKing.HIT,EntityIguanaKing.HIT2,EntityIguanaKing.ROAR)){
				return false;
			}
		}
		return true;
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}