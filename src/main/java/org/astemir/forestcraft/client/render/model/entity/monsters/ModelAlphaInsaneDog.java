package org.astemir.forestcraft.client.render.model.entity.monsters;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.*;
import org.astemir.api.math.Vector3;
import org.astemir.api.math.MathUtils;
import org.astemir.forestcraft.common.entities.monsters.EntityAlphaInsaneDog;

public class ModelAlphaInsaneDog extends AnimatedEntityModel<EntityAlphaInsaneDog> {

	private final AModelRenderer alpha_insane_dog;
	private final AModelRenderer body;
	private final AModelRenderer bodyPart;
	private final AModelRenderer tail;
	private final AModelRenderer minitail;
	private final AModelRenderer tail_r1;
	private final AModelRenderer minitail2;
	private final AModelRenderer tail_r2;
	private final AModelRenderer minitail3;
	private final AModelRenderer tail_r3;
	private final AModelRenderer bodyPart2;
	private final AModelRenderer head;
	private final AModelRenderer head_r1;
	private final AModelRenderer head_r2;
	private final AModelRenderer down_jaw;
	private final AModelRenderer up_jaw;
	private final AModelRenderer leg0;
	private final AModelRenderer leg1_r1;
	private final AModelRenderer leg0_r1;
	private final AModelRenderer leg3;
	private final AModelRenderer leg4_r1;
	private final AModelRenderer leg3_r1;
	private final AModelRenderer leg2;
	private final AModelRenderer leg3_r2;
	private final AModelRenderer leg2_r1;
	private final AModelRenderer leg1;
	private final AModelRenderer leg2_r2;
	private final AModelRenderer leg1_r2;

	public static AnimationFile ALPHA_INSANE_DOG_ANIMATIONS = new AnimationFile("animations/alpha_insane_dog_animations.json");

	public ModelAlphaInsaneDog() {
		textureWidth = 128;
		textureHeight = 128;

		alpha_insane_dog = new AModelRenderer(this);
		alpha_insane_dog.setRotationPoint(0.0F, 24.0F, 0.0F);


		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -17.0F, 0.0F);
		alpha_insane_dog.addChild(body);


		bodyPart = new AModelRenderer(this);
		bodyPart.setRotationPoint(2.0F, -2.0F, 4.0F);
		body.addChild(bodyPart);
		bodyPart.setTextureOffset(0, 27).addBox(-7.0F, -5.0F, -6.0F, 14.0F, 10.0F, 16.0F, 0.0F, false);
		bodyPart.setTextureOffset(44, 17).addBox(0.0F, -15.0F, 0.0F, 0.0F, 10.0F, 10.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, -2.0F, 10.0F);
		bodyPart.addChild(tail);


		minitail = new AModelRenderer(this);
		minitail.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail.addChild(minitail);


		tail_r1 = new AModelRenderer(this);
		tail_r1.setRotationPoint(-2.0F, 25.0F, 12.0F);
		minitail.addChild(tail_r1);
		setRotationAngle(tail_r1, 1.0908F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(28, 53).addBox(0.0F, -24.0F, 14.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		minitail2 = new AModelRenderer(this);
		minitail2.setRotationPoint(-2.0F, 0.0F, 0.0F);
		tail.addChild(minitail2);


		tail_r2 = new AModelRenderer(this);
		tail_r2.setRotationPoint(-7.0F, 24.0F, 12.0F);
		minitail2.addChild(tail_r2);
		setRotationAngle(tail_r2, 1.0908F, 0.0F, 0.1745F);
		tail_r2.setTextureOffset(64, 0).addBox(0.0F, -24.0F, 14.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);

		minitail3 = new AModelRenderer(this);
		minitail3.setRotationPoint(2.0F, 0.0F, 0.0F);
		tail.addChild(minitail3);


		tail_r3 = new AModelRenderer(this);
		tail_r3.setRotationPoint(3.0F, 25.0F, 12.0F);
		minitail3.addChild(tail_r3);
		setRotationAngle(tail_r3, 1.0908F, 0.0F, -0.1745F);
		tail_r3.setTextureOffset(64, 21).addBox(0.0F, -24.0F, 14.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);

		bodyPart2 = new AModelRenderer(this);
		bodyPart2.setRotationPoint(2.0F, -2.0F, -4.0F);
		body.addChild(bodyPart2);
		bodyPart2.setTextureOffset(0, 0).addBox(-9.0F, -7.0F, -6.0F, 18.0F, 13.0F, 14.0F, 0.0F, false);
		bodyPart2.setTextureOffset(0, 39).addBox(0.0F, -19.0F, -6.0F, 0.0F, 12.0F, 14.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(3.0F, -3.5F, -10.0F);
		body.addChild(head);
		head.setTextureOffset(48, 48).addBox(-7.0F, -6.5F, -12.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(0, 27).addBox(-5.0F, -14.5F, -8.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		head_r1 = new AModelRenderer(this);
		head_r1.setRotationPoint(3.0F, 20.5F, 10.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 0.0F, -0.2182F);
		head_r1.setTextureOffset(0, 0).addBox(-4.0F, -33.0F, -14.0F, 4.0F, 6.0F, 2.0F, 0.0F, false);

		head_r2 = new AModelRenderer(this);
		head_r2.setRotationPoint(-9.0F, 20.0F, 10.0F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 0.0F, 0.0F, 0.2182F);
		head_r2.setTextureOffset(0, 35).addBox(4.0F, -33.0F, -14.0F, 4.0F, 6.0F, 2.0F, 0.0F, false);

		down_jaw = new AModelRenderer(this);
		down_jaw.setRotationPoint(-1.0F, 1.5F, -12.0F);
		head.addChild(down_jaw);
		down_jaw.setTextureOffset(61, 83).addBox(-3.0F, -0.04F, -7.0F, 6.0F, 2.0F, 7.0F, 0.0F, false);

		up_jaw = new AModelRenderer(this);
		up_jaw.setRotationPoint(-1.0F, 0.5F, -12.0F);
		head.addChild(up_jaw);
		up_jaw.setTextureOffset(68, 72).addBox(-3.0F, -3.04F, -7.0F, 6.0F, 4.0F, 7.0F, 0.0F, false);

		leg0 = new AModelRenderer(this);
		leg0.setRotationPoint(6.5F, -15.0F, 11.0F);
		alpha_insane_dog.addChild(leg0);


		leg1_r1 = new AModelRenderer(this);
		leg1_r1.setRotationPoint(-13.5F, 17.0F, -5.0F);
		leg0.addChild(leg1_r1);
		setRotationAngle(leg1_r1, 0.1745F, 0.7854F, 0.0F);
		leg1_r1.setTextureOffset(84, 50).addBox(3.0F, -16.0F, 12.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		leg0_r1 = new AModelRenderer(this);
		leg0_r1.setRotationPoint(-13.75F, 16.0F, -6.5F);
		leg0.addChild(leg0_r1);
		setRotationAngle(leg0_r1, 0.0F, 0.7854F, 0.0F);
		leg0_r1.setTextureOffset(80, 20).addBox(3.0F, -16.0F, 12.0F, 4.0F, 16.0F, 4.0F, 0.0F, false);

		leg3 = new AModelRenderer(this);
		leg3.setRotationPoint(-3.5F, -14.0F, -7.0F);
		alpha_insane_dog.addChild(leg3);


		leg4_r1 = new AModelRenderer(this);
		leg4_r1.setRotationPoint(5.0F, 15.0F, 4.5F);
		leg3.addChild(leg4_r1);
		setRotationAngle(leg4_r1, 0.0F, 0.7854F, 0.0F);
		leg4_r1.setTextureOffset(16, 78).addBox(-3.0F, -16.0F, -9.25F, 4.0F, 16.0F, 4.0F, 0.0F, false);

		leg3_r1 = new AModelRenderer(this);
		leg3_r1.setRotationPoint(4.5F, 15.0F, 2.25F);
		leg3.addChild(leg3_r1);
		setRotationAngle(leg3_r1, -0.1745F, 0.7854F, 0.0F);
		leg3_r1.setTextureOffset(0, 65).addBox(-5.0F, -16.0F, -10.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		leg2 = new AModelRenderer(this);
		leg2.setRotationPoint(7.5F, -14.0F, -7.0F);
		alpha_insane_dog.addChild(leg2);


		leg3_r2 = new AModelRenderer(this);
		leg3_r2.setRotationPoint(-10.0F, 15.0F, 2.0F);
		leg2.addChild(leg3_r2);
		setRotationAngle(leg3_r2, 0.0F, -0.7854F, 0.0F);
		leg3_r2.setTextureOffset(0, 78).addBox(4.0F, -16.0F, -11.0F, 4.0F, 16.0F, 4.0F, 0.0F, false);

		leg2_r1 = new AModelRenderer(this);
		leg2_r1.setRotationPoint(-7.5F, 15.0F, 1.0F);
		leg2.addChild(leg2_r1);
		setRotationAngle(leg2_r1, -0.1745F, -0.7854F, 0.0F);
		leg2_r1.setTextureOffset(44, 72).addBox(2.0F, -16.0F, -11.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);

		leg1 = new AModelRenderer(this);
		leg1.setRotationPoint(-3.0F, -15.0F, 11.0F);
		alpha_insane_dog.addChild(leg1);


		leg2_r2 = new AModelRenderer(this);
		leg2_r2.setRotationPoint(9.25F, 17.0F, -9.25F);
		leg1.addChild(leg2_r2);
		setRotationAngle(leg2_r2, 0.1745F, -0.7854F, 0.0F);
		leg2_r2.setTextureOffset(84, 40).addBox(-3.0F, -16.0F, 12.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		leg1_r2 = new AModelRenderer(this);
		leg1_r2.setRotationPoint(10.75F, 16.0F, -9.25F);
		leg1.addChild(leg1_r2);
		setRotationAngle(leg1_r2, 0.0F, -0.7854F, 0.0F);
		leg1_r2.setTextureOffset(80, 0).addBox(-3.0F, -16.0F, 12.0F, 4.0F, 16.0F, 4.0F, 0.0F, false);
		initializeAnimator(ALPHA_INSANE_DOG_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		alpha_insane_dog.render(matrixStack, buffer, packedLight, packedOverlay);
	}


	@Override
	public void animate(EntityAlphaInsaneDog entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.setCustomRotation(new Vector3(MathUtils.rad(Math.min(30, Math.max(-30, headPitch))),MathUtils.rad(Math.min(30, Math.max(-30, netHeadYaw))),0));
		if (!entityIn.getFactory().isPlaying(EntityAlphaInsaneDog.RUN)) {
			this.leg0.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount, 0, 0));
			this.leg1.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount, 0, 0));
			this.leg2.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount, 0, 0));
			this.leg3.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount, 0, 0));
		}else{
			this.leg0.setCustomRotation(new Vector3(0, 0, 0));
			this.leg1.setCustomRotation(new Vector3(0, 0, 0));
			this.leg2.setCustomRotation(new Vector3(0, 0, 0));
			this.leg3.setCustomRotation(new Vector3(0, 0, 0));
		}
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityAlphaInsaneDog.IDLE || animation == EntityAlphaInsaneDog.RUN){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityAlphaInsaneDog.LASER,EntityAlphaInsaneDog.ATTACK,EntityAlphaInsaneDog.HOWL)){
				return false;
			}
		}
		return true;
	}
}