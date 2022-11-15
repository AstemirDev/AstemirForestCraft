package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.*;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityTerrorBringer;

public class ModelTerrorBringer extends AnimatedEntityModel<EntityTerrorBringer> {

	private final AModelRenderer terror_bringer;
	private final AModelRenderer body;
	private final AModelRenderer body_r3_r1;
	private final AModelRenderer body_r2_r1;
	private final AModelRenderer tail;
	private final AModelRenderer body_r4_r1;
	private final AModelRenderer body_up;
	private final AModelRenderer body_r1_r1;
	private final AModelRenderer neck;
	private final AModelRenderer neck_r1_r1;
	private final AModelRenderer neck2;
	private final AModelRenderer neck_r3_r1;
	private final AModelRenderer neck_r2_r1;
	private final AModelRenderer head;
	private final AModelRenderer head_r1_r1;
	private final AModelRenderer head_r1;
	private final AModelRenderer beak;
	private final AModelRenderer up;
	private final AModelRenderer up_r2_r1;
	private final AModelRenderer up_r1_r1;
	private final AModelRenderer down;
	private final AModelRenderer down_r1_r1;
	private final AModelRenderer arm_1;
	private final AModelRenderer arm_r1;
	private final AModelRenderer arm_part_3;
	private final AModelRenderer arm_r2;
	private final AModelRenderer arm_part_4;
	private final AModelRenderer arm_0;
	private final AModelRenderer arm_r3;
	private final AModelRenderer arm_part_1;
	private final AModelRenderer arm_r4;
	private final AModelRenderer arm_part_2;
	private final AModelRenderer leg_0;
	private final AModelRenderer leg1_r3_r1;
	private final AModelRenderer leg_part_0;
	private final AModelRenderer leg1_r2_r1;
	private final AModelRenderer leg_part_1;
	private final AModelRenderer leg_1;
	private final AModelRenderer leg1_r4_r1;
	private final AModelRenderer leg_part_2;
	private final AModelRenderer leg1_r3_r2;
	private final AModelRenderer leg_part_3;

	public static AnimationFile TERROR_BRINGER_ANIMATIONS = new AnimationFile("animations/terror_bringer_animations.json");

	public ModelTerrorBringer() {
		textureWidth = 128;
		textureHeight = 128;

		terror_bringer = new AModelRenderer(this);
		terror_bringer.setRotationPoint(0.0F, 24.0F, -9.0F);


		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -19.0F, 14.0F);
		terror_bringer.addChild(body);


		body_r3_r1 = new AModelRenderer(this);
		body_r3_r1.setRotationPoint(0.0F, 19.0F, -14.0F);
		body.addChild(body_r3_r1);
		setRotationAngle(body_r3_r1, -0.6982F, 0.0F, 0.0F);
		body_r3_r1.setTextureOffset(32, 12).addBox(-4.0F, -30.0F, -2.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		body_r2_r1 = new AModelRenderer(this);
		body_r2_r1.setRotationPoint(0.0F, 19.0F, -14.0F);
		body.addChild(body_r2_r1);
		setRotationAngle(body_r2_r1, -0.1746F, 0.0F, 0.0F);
		body_r2_r1.setTextureOffset(0, 0).addBox(-5.0F, -29.0F, 3.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, 2.0F, 8.0F);
		body.addChild(tail);


		body_r4_r1 = new AModelRenderer(this);
		body_r4_r1.setRotationPoint(0.0F, 17.0F, -22.0F);
		tail.addChild(body_r4_r1);
		setRotationAngle(body_r4_r1, -0.9163F, 0.0F, 0.0F);
		body_r4_r1.setTextureOffset(20, 0).addBox(-4.0F, -28.0F, 0.0F, 8.0F, 0.0F, 10.0F, 0.0F, false);

		body_up = new AModelRenderer(this);
		body_up.setRotationPoint(0.0F, -6.0F, -6.0F);
		body.addChild(body_up);


		body_r1_r1 = new AModelRenderer(this);
		body_r1_r1.setRotationPoint(0.0F, 25.0F, -8.0F);
		body_up.addChild(body_r1_r1);
		setRotationAngle(body_r1_r1, 0.1745F, 0.0F, 0.0F);
		body_r1_r1.setTextureOffset(0, 20).addBox(-4.0F, -26.0F, 2.0F, 8.0F, 9.0F, 11.0F, 0.0F, false);

		neck = new AModelRenderer(this);
		neck.setRotationPoint(0.0F, 1.25F, -8.0F);
		body_up.addChild(neck);
		setRotationAngle(neck, 0.2618F, 0.0F, 0.0F);


		neck_r1_r1 = new AModelRenderer(this);
		neck_r1_r1.setRotationPoint(0.0F, 25.8749F, 3.2385F);
		neck.addChild(neck_r1_r1);
		setRotationAngle(neck_r1_r1, 0.2618F, 0.0F, 0.0F);
		neck_r1_r1.setTextureOffset(0, 40).addBox(-3.75F, -29.0F, 0.0F, 7.0F, 8.0F, 6.0F, 0.0F, false);

		neck2 = new AModelRenderer(this);
		neck2.setRotationPoint(0.0F, -2.8434F, -0.8105F);
		neck.addChild(neck2);


		neck_r3_r1 = new AModelRenderer(this);
		neck_r3_r1.setRotationPoint(0.0F, 28.7183F, 4.0491F);
		neck2.addChild(neck_r3_r1);
		setRotationAngle(neck_r3_r1, 0.5236F, 0.0F, 0.0F);
		neck_r3_r1.setTextureOffset(0, 18).addBox(-0.25F, -36.0F, 12.0F, 0.0F, 11.0F, 2.0F, 0.0F, false);

		neck_r2_r1 = new AModelRenderer(this);
		neck_r2_r1.setRotationPoint(0.0F, 28.7183F, 4.0491F);
		neck2.addChild(neck_r2_r1);
		setRotationAngle(neck_r2_r1, 0.4363F, 0.0F, 0.0F);
		neck_r2_r1.setTextureOffset(0, 54).addBox(-2.75F, -33.0F, 5.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(-0.25F, -3.0F, -3.0F);
		neck2.addChild(head);
		setRotationAngle(head, -0.2182F, 0.0F, 0.0F);


		head_r1_r1 = new AModelRenderer(this);
		head_r1_r1.setRotationPoint(0.0F, 27.0958F, 2.1384F);
		head.addChild(head_r1_r1);
		setRotationAngle(head_r1_r1, 0.0436F, 0.0F, 0.0F);
		head_r1_r1.setTextureOffset(31, 33).addBox(-3.0F, -33.6395F, -6.1216F, 6.0F, 7.0F, 7.0F, 0.0F, false);

		head_r1 = new AModelRenderer(this);
		head_r1.setRotationPoint(0.25F, 29.9186F, 6.2653F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.3055F, 0.0F, 0.0F);
		head_r1.setTextureOffset(38, 22).addBox(-0.25F, -35.0F, -21.0F, 0.0F, 5.0F, 6.0F, 0.0F, false);

		beak = new AModelRenderer(this);
		beak.setRotationPoint(0.0F, -1.8895F, -5.1216F);
		head.addChild(beak);
		setRotationAngle(beak, 0.3054F, 0.0F, 0.0F);


		up = new AModelRenderer(this);
		up.setRotationPoint(0.0F, -0.4912F, 0.306F);
		beak.addChild(up);


		up_r2_r1 = new AModelRenderer(this);
		up_r2_r1.setRotationPoint(0.0F, 32.537F, 9.6906F);
		up.addChild(up_r2_r1);
		setRotationAngle(up_r2_r1, 0.3491F, 0.0F, 0.0F);
		up_r2_r1.setTextureOffset(20, 40).addBox(-0.5F, -35.9046F, -4.7071F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		up_r2_r1.setTextureOffset(43, 8).addBox(-1.0F, -34.9046F, -3.9571F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		up_r1_r1 = new AModelRenderer(this);
		up_r1_r1.setRotationPoint(0.0F, 30.0289F, 8.2609F);
		up.addChild(up_r1_r1);
		setRotationAngle(up_r1_r1, 0.0436F, 0.0F, 0.0F);
		up_r1_r1.setTextureOffset(60, 12).addBox(-2.0F, -30.6395F, -10.1216F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		up_r1_r1.setTextureOffset(62, 24).addBox(-1.0F, -31.6395F, -10.1216F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		down = new AModelRenderer(this);
		down.setRotationPoint(0.0F, 1.5088F, 0.306F);
		beak.addChild(down);


		down_r1_r1 = new AModelRenderer(this);
		down_r1_r1.setRotationPoint(0.0F, 28.0289F, 8.2609F);
		down.addChild(down_r1_r1);
		setRotationAngle(down_r1_r1, 0.0436F, 0.0F, 0.0F);
		down_r1_r1.setTextureOffset(58, 0).addBox(-2.0F, -29.6395F, -10.1216F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		down_r1_r1.setTextureOffset(36, 47).addBox(-1.0F, -28.6395F, -10.1216F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		down_r1_r1.setTextureOffset(4, 20).addBox(-0.5F, -29.3897F, -12.1325F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		arm_1 = new AModelRenderer(this);
		arm_1.setRotationPoint(-4.5F, -1.0F, -3.5F);
		body_up.addChild(arm_1);


		arm_r1 = new AModelRenderer(this);
		arm_r1.setRotationPoint(-4.5F, 16.1326F, -5.6363F);
		arm_1.addChild(arm_r1);
		setRotationAngle(arm_r1, -0.3054F, 0.3927F, 0.0F);
		arm_r1.setTextureOffset(44, 59).addBox(1.0F, -17.75F, 0.0F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		arm_part_3 = new AModelRenderer(this);
		arm_part_3.setRotationPoint(-1.0F, 8.25F, -3.5F);
		arm_1.addChild(arm_part_3);


		arm_r2 = new AModelRenderer(this);
		arm_r2.setRotationPoint(-2.75F, 6.75F, 0.0F);
		arm_part_3.addChild(arm_r2);
		setRotationAngle(arm_r2, 0.0F, 0.3927F, 0.0F);
		arm_r2.setTextureOffset(62, 29).addBox(1.5F, -7.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		arm_part_4 = new AModelRenderer(this);
		arm_part_4.setRotationPoint(0.5F, 4.75F, 1.0F);
		arm_part_3.addChild(arm_part_4);
		setRotationAngle(arm_part_4, 1.6581F, 0.3927F, 0.0F);
		arm_part_4.setTextureOffset(38, 0).addBox(-3.25F, -0.25F, -7.5F, 6.0F, 0.0F, 8.0F, 0.0F, false);

		arm_0 = new AModelRenderer(this);
		arm_0.setRotationPoint(4.5F, -1.0F, -3.5F);
		body_up.addChild(arm_0);


		arm_r3 = new AModelRenderer(this);
		arm_r3.setRotationPoint(4.5F, 16.1326F, -5.6363F);
		arm_0.addChild(arm_r3);
		setRotationAngle(arm_r3, -0.3054F, -0.3927F, 0.0F);
		arm_r3.setTextureOffset(56, 59).addBox(-4.0F, -17.75F, 0.0F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		arm_part_1 = new AModelRenderer(this);
		arm_part_1.setRotationPoint(1.0F, 8.25F, -3.5F);
		arm_0.addChild(arm_part_1);


		arm_r4 = new AModelRenderer(this);
		arm_r4.setRotationPoint(2.75F, 6.75F, 0.0F);
		arm_part_1.addChild(arm_r4);
		setRotationAngle(arm_r4, 0.0F, -0.3927F, 0.0F);
		arm_r4.setTextureOffset(64, 5).addBox(-3.5F, -7.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		arm_part_2 = new AModelRenderer(this);
		arm_part_2.setRotationPoint(-0.5F, 4.75F, 1.0F);
		arm_part_1.addChild(arm_part_2);
		setRotationAngle(arm_part_2, 1.6581F, -0.3927F, 0.0F);
		arm_part_2.setTextureOffset(42, 28).addBox(-2.75F, -0.25F, -7.5F, 6.0F, 0.0F, 8.0F, 0.0F, false);

		leg_0 = new AModelRenderer(this);
		leg_0.setRotationPoint(-3.5F, -17.0F, 12.0F);
		terror_bringer.addChild(leg_0);


		leg1_r3_r1 = new AModelRenderer(this);
		leg1_r3_r1.setRotationPoint(2.5F, 16.1326F, -6.6363F);
		leg_0.addChild(leg1_r3_r1);
		setRotationAngle(leg1_r3_r1, -0.3054F, 0.0F, 0.0F);
		leg1_r3_r1.setTextureOffset(43, 47).addBox(-5.0F, -20.75F, -1.0F, 5.0F, 5.0F, 7.0F, 0.0F, false);

		leg_part_0 = new AModelRenderer(this);
		leg_part_0.setRotationPoint(0.0F, 1.0F, 0.0F);
		leg_0.addChild(leg_part_0);


		leg1_r2_r1 = new AModelRenderer(this);
		leg1_r2_r1.setRotationPoint(2.5F, 15.1326F, -6.6363F);
		leg_part_0.addChild(leg1_r2_r1);
		setRotationAngle(leg1_r2_r1, -0.3054F, 0.0F, 0.0F);
		leg1_r2_r1.setTextureOffset(32, 59).addBox(-4.0F, -17.75F, 0.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leg_part_1 = new AModelRenderer(this);
		leg_part_1.setRotationPoint(0.0F, 10.0F, -3.0F);
		leg_part_0.addChild(leg_part_1);
		leg_part_1.setTextureOffset(49, 36).addBox(-2.0F, 6.0F, -5.0F, 4.0F, 0.0F, 8.0F, 0.0F, false);
		leg_part_1.setTextureOffset(60, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		leg_1 = new AModelRenderer(this);
		leg_1.setRotationPoint(3.5F, -17.0F, 12.0F);
		terror_bringer.addChild(leg_1);


		leg1_r4_r1 = new AModelRenderer(this);
		leg1_r4_r1.setRotationPoint(2.5F, 16.1326F, -6.6363F);
		leg_1.addChild(leg1_r4_r1);
		setRotationAngle(leg1_r4_r1, -0.3054F, 0.0F, 0.0F);
		leg1_r4_r1.setTextureOffset(19, 47).addBox(-5.0F, -20.75F, -1.0F, 5.0F, 5.0F, 7.0F, 0.0F, false);

		leg_part_2 = new AModelRenderer(this);
		leg_part_2.setRotationPoint(0.0F, 1.0F, 0.0F);
		leg_1.addChild(leg_part_2);


		leg1_r3_r2 = new AModelRenderer(this);
		leg1_r3_r2.setRotationPoint(2.5F, 15.1326F, -6.6363F);
		leg_part_2.addChild(leg1_r3_r2);
		setRotationAngle(leg1_r3_r2, -0.3054F, 0.0F, 0.0F);
		leg1_r3_r2.setTextureOffset(20, 59).addBox(-4.0F, -17.75F, 0.0F, 3.0F, 12.0F, 3.0F, 0.0F, false);

		leg_part_3 = new AModelRenderer(this);
		leg_part_3.setRotationPoint(0.0F, 10.0F, -3.0F);
		leg_part_2.addChild(leg_part_3);
		leg_part_3.setTextureOffset(48, 8).addBox(-2.0F, 6.0F, -5.0F, 4.0F, 0.0F, 8.0F, 0.0F, false);
		leg_part_3.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		initializeAnimator(TERROR_BRINGER_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		terror_bringer.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(AModelRenderer AModelRenderer, float x, float y, float z) {
		AModelRenderer.rotateAngleX = x;
		AModelRenderer.rotateAngleY = y;
		AModelRenderer.rotateAngleZ = z;
		AModelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

	@Override
	public void setRotationAngles(EntityTerrorBringer entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.setCustomRotation(new Vector3((headPitch * ((float)Math.PI / 180)),(netHeadYaw * ((float)Math.PI / 180)),0));
	}


	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityTerrorBringer.RUN || animation == EntityTerrorBringer.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityTerrorBringer.ATTACK)){
				return false;
			}
		}
		return true;
	}
}