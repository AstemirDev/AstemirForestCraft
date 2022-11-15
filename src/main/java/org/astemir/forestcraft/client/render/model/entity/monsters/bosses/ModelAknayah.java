package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.*;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityAknayah;

public class ModelAknayah extends AnimatedEntityModel<EntityAknayah> {

	private final AModelRenderer hayanka;
	private final AModelRenderer body;
	private final AModelRenderer left_arm;
	private final AModelRenderer right_arm;
	private final AModelRenderer head;
	private final AModelRenderer hair;
	private final AModelRenderer face;
	private final AModelRenderer brow2;
	private final AModelRenderer brow;
	private final AModelRenderer eye2;
	private final AModelRenderer eye_light;
	private final AModelRenderer eye;
	private final AModelRenderer eye_light2;
	private final AModelRenderer wings;
	private final AModelRenderer wing;
	private final AModelRenderer base;
	private final AModelRenderer middle;
	private final AModelRenderer up;
	private final AModelRenderer wing2;
	private final AModelRenderer base2;
	private final AModelRenderer middle2;
	private final AModelRenderer up2;
	private final AModelRenderer right_leg;
	private final AModelRenderer left_leg;

	public ModelAknayah() {
		textureWidth = 128;
		textureHeight = 128;

		hayanka = new AModelRenderer(this);
		hayanka.setRotationPoint(0.0F, 16.0F, 1.0F);


		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -3.0F, 0.0F);
		hayanka.addChild(body);
		body.setTextureOffset(28, 30).addBox(-4.0F, -12.5F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(58, 27).addBox(-4.0F, -13.03F, -2.51F, 8.0F, 13.0F, 5.0F, 0.0F, true);

		left_arm = new AModelRenderer(this);
		left_arm.setRotationPoint(4.0F, -12.0F, 0.0F);
		body.addChild(left_arm);
		left_arm.setTextureOffset(16, 42).addBox(0.0F, -0.5F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
		left_arm.setTextureOffset(0, 58).addBox(-0.5F, -1.0F, -2.5F, 4.0F, 13.0F, 5.0F, 0.0F, false);

		right_arm = new AModelRenderer(this);
		right_arm.setRotationPoint(-4.0F, -12.0F, 0.0F);
		body.addChild(right_arm);
		right_arm.setTextureOffset(44, 0).addBox(-3.0F, -0.5F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
		right_arm.setTextureOffset(35, 59).addBox(-3.5F, -1.0F, -2.5F, 4.0F, 13.0F, 5.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -13.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 18).addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-4.5F, -8.0F, -4.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);

		hair = new AModelRenderer(this);
		hair.setRotationPoint(0.0F, 0.0F, 4.0F);
		head.addChild(hair);
		hair.setTextureOffset(30, 46).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);

		face = new AModelRenderer(this);
		face.setRotationPoint(0.0F, 16.0F, 0.0F);
		head.addChild(face);


		brow2 = new AModelRenderer(this);
		brow2.setRotationPoint(-2.0F, -21.0F, -5.0F);
		face.addChild(brow2);
		brow2.setTextureOffset(0, 52).addBox(-1.0F, -1.5F, 0.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		brow = new AModelRenderer(this);
		brow.setRotationPoint(2.0F, -21.0F, -5.0F);
		face.addChild(brow);
		brow.setTextureOffset(4, 52).addBox(-1.0F, -1.5F, 0.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		eye2 = new AModelRenderer(this);
		eye2.setRotationPoint(-2.0F, -17.5F, -4.0F);
		face.addChild(eye2);
		eye2.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -0.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		eye_light = new AModelRenderer(this);
		eye_light.setRotationPoint(0.5F, 0.5F, -0.75F);
		eye2.addChild(eye_light);
		eye_light.setTextureOffset(33, 84).addBox(-5.5F, -5.5F, -0.25F, 11.0F, 11.0F, 0.0F, 0.0F, false);

		eye = new AModelRenderer(this);
		eye.setRotationPoint(2.0F, -17.5F, -4.0F);
		face.addChild(eye);
		eye.setTextureOffset(4, 0).addBox(-1.0F, -2.0F, -0.25F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		eye_light2 = new AModelRenderer(this);
		eye_light2.setRotationPoint(-0.5F, 0.5F, -0.75F);
		eye.addChild(eye_light2);
		eye_light2.setTextureOffset(33, 84).addBox(-5.5F, -5.5F, -0.25F, 11.0F, 11.0F, 0.0F, 0.0F, false);

		wings = new AModelRenderer(this);
		wings.setRotationPoint(0.5F, -7.0F, 0.0F);
		body.addChild(wings);


		wing = new AModelRenderer(this);
		wing.setRotationPoint(-3.0F, 0.0F, 1.0F);
		wings.addChild(wing);
		setRotationAngle(wing, 0.0F, 1.2217F, 0.0F);


		base = new AModelRenderer(this);
		base.setRotationPoint(0.0F, 0.0F, -1.0F);
		wing.addChild(base);
		base.setTextureOffset(92, 45).addBox(-16.0F, -7.0F, 1.0F, 16.0F, 7.0F, 0.0F, 0.0F, false);

		middle = new AModelRenderer(this);
		middle.setRotationPoint(-11.0F, -7.0F, 0.0F);
		base.addChild(middle);
		middle.setTextureOffset(89, 35).addBox(-13.0F, -10.0F, 1.0F, 19.0F, 10.0F, 0.0F, 0.0F, false);

		up = new AModelRenderer(this);
		up.setRotationPoint(-6.0F, -10.0F, 0.0F);
		middle.addChild(up);
		up.setTextureOffset(88, 26).addBox(-12.0F, -9.0F, 1.0F, 20.0F, 9.0F, 0.0F, 0.0F, false);

		wing2 = new AModelRenderer(this);
		wing2.setRotationPoint(2.0F, 0.0F, 1.0F);
		wings.addChild(wing2);
		setRotationAngle(wing2, 0.0F, -1.2217F, 0.0F);


		base2 = new AModelRenderer(this);
		base2.setRotationPoint(0.0F, 0.0F, -1.0F);
		wing2.addChild(base2);
		base2.setTextureOffset(92, 19).addBox(0.0F, -7.0F, 1.0F, 16.0F, 7.0F, 0.0F, 0.0F, false);

		middle2 = new AModelRenderer(this);
		middle2.setRotationPoint(11.0F, -7.0F, 0.0F);
		base2.addChild(middle2);
		middle2.setTextureOffset(89, 9).addBox(-6.0F, -10.0F, 1.0F, 19.0F, 10.0F, 0.0F, 0.0F, false);

		up2 = new AModelRenderer(this);
		up2.setRotationPoint(6.0F, -10.0F, 0.0F);
		middle2.addChild(up2);
		up2.setTextureOffset(88, 0).addBox(-8.0F, -9.0F, 1.0F, 20.0F, 9.0F, 0.0F, 0.0F, false);

		right_leg = new AModelRenderer(this);
		right_leg.setRotationPoint(2.0F, -3.0F, 0.0F);
		hayanka.addChild(right_leg);
		right_leg.setTextureOffset(0, 34).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 82).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		left_leg = new AModelRenderer(this);
		left_leg.setRotationPoint(-2.0F, -3.0F, 0.0F);
		hayanka.addChild(left_leg);
		left_leg.setTextureOffset(32, 14).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		left_leg.setTextureOffset(1, 99).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/hayanka_animations.json"));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		hayanka.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(EntityAknayah entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.setCustomRotation(new Vector3(headPitch * ((float)Math.PI / 180F),netHeadYaw * ((float)Math.PI / 180F),0));
		if (!entityIn.getFactory().isPlaying(EntityAknayah.ATTACK_LEG)) {
			this.right_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F, 0, 0));
			this.left_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F, 0, 0));
		}
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityAknayah.IDLE) {
			if (ignoreUsedPartsInOtherAnimations(animated, part, EntityAknayah.JUMP,EntityAknayah.ATTACK_WEAPON,EntityAknayah.ATTACK_LEG,EntityAknayah.ATTACK_HANDS)) {
				return false;
			}
		}
		return super.animatePart(animated, part, animation, delta);
	}
}