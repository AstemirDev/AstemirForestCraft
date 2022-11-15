package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityBakudan;


public class ModelBakudan extends AnimatedEntityModel<EntityBakudan> {

	private final AModelRenderer bakudan;
	private final AModelRenderer body;
	private final AModelRenderer arms;
	private final AModelRenderer right_arm;
	private final AModelRenderer cube_r1;
	private final AModelRenderer left_arm;
	private final AModelRenderer cube_r2;
	private final AModelRenderer head;
	private final AModelRenderer cube_r3;
	private final AModelRenderer ball;
	private final AModelRenderer right_leg;
	private final AModelRenderer cube_r4;
	private final AModelRenderer left_leg;
	private final AModelRenderer cube_r5;

	public static AnimationFile BAKUDAN_ANIMATIONS = new AnimationFile("animations/bakudan_animations.json");

	public ModelBakudan() {
		textureWidth = 64;
		textureHeight = 64;

		bakudan = new AModelRenderer(this);
		bakudan.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -8.0F, 0.0F);
		bakudan.addChild(body);
		body.setTextureOffset(24, 7).addBox(-3.5F, -7.75F, -2.75F, 7.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 13).addBox(-4.0F, -4.0F, -3.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(24, 27).addBox(0.0F, -6.0F, 3.25F, 0.0F, 9.0F, 7.0F, 0.0F, false);

		arms = new AModelRenderer(this);
		arms.setRotationPoint(0.0F, -4.0F, 0.0F);
		body.addChild(arms);
		setRotationAngle(arms, -0.6981F, 0.0F, 0.0F);
		

		right_arm = new AModelRenderer(this);
		right_arm.setRotationPoint(4.0F, -1.0F, 1.0F);
		arms.addChild(right_arm);
		setRotationAngle(right_arm, -0.9599F, 0.0F, 0.0F);
		right_arm.setTextureOffset(12, 50).addBox(-0.491F, -1.3091F, -1.4681F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(-1.25F, 13.7828F, 2.6543F);
		right_arm.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.2182F);
		cube_r1.setTextureOffset(38, 38).addBox(-1.991F, -12.6307F, -4.4096F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		left_arm = new AModelRenderer(this);
		left_arm.setRotationPoint(-4.0F, -1.0F, 1.0F);
		arms.addChild(left_arm);
		setRotationAngle(left_arm, -0.9599F, 0.0F, 0.0F);
		left_arm.setTextureOffset(0, 47).addBox(-2.491F, -1.3126F, -1.4082F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(1.25F, 13.7828F, 2.6543F);
		left_arm.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.2182F);
		cube_r2.setTextureOffset(10, 39).addBox(-2.009F, -12.6307F, -4.4096F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(25, 57).addBox(-4.5F, -6.0F, -3.55F, 9.0F, 6.0F, 0.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, -3.5F, 8.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(0, 29).addBox(0.0F, -4.0F, 3.25F, 0.0F, 6.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(40, 49).addBox(-10.0F, -9.0F, -0.5F, 7.0F, 7.0F, 0.0F, 0.0F, false);
		head.setTextureOffset(26, 49).addBox(3.0F, -9.0F, -0.5F, 7.0F, 7.0F, 0.0F, 0.0F, false);

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(2.0F, -5.5F, -0.5F);
		head.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.0873F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(0, 28).addBox(-5.0F, -2.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);

		ball = new AModelRenderer(this);
		ball.setRotationPoint(0.0F, -5.0F, -6.0F);
		body.addChild(ball);
		ball.setTextureOffset(22, 22).addBox(-3.0F, -3.6303F, -3.499F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		right_leg = new AModelRenderer(this);
		right_leg.setRotationPoint(3.0F, -6.0F, 0.0F);
		bakudan.addChild(right_leg);
		right_leg.setTextureOffset(23, 0).addBox(-1.75F, 5.0F, -2.75F, 4.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(2.75F, 11.5F, 2.0F);
		right_leg.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.0873F);
		cube_r4.setTextureOffset(40, 17).addBox(-4.0F, -13.0F, -4.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		left_leg = new AModelRenderer(this);
		left_leg.setRotationPoint(-3.0F, -6.0F, 0.0F);
		bakudan.addChild(left_leg);
		left_leg.setTextureOffset(41, 29).addBox(-2.25F, 5.0F, -2.75F, 4.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(2.25F, 12.0F, 2.0F);
		left_leg.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.0873F);
		cube_r5.setTextureOffset(44, 0).addBox(-5.0F, -13.0F, -4.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
		initializeAnimator(BAKUDAN_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bakudan.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

	@Override
	public void animate(EntityBakudan entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.setCustomRotation(new Vector3((float) Math.toRadians(Math.min(30, Math.max(-30, headPitch))),(float) Math.toRadians(Math.min(30, Math.max(-30, netHeadYaw))),0));
		ball.showModel = entityIn.hasBall();
		if (!entityIn.getFactory().isPlaying(EntityBakudan.IDLE3) && !entityIn.getFactory().isPlaying(EntityBakudan.IDLE2)){
			left_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing* 0.6662F)*1.4F * limbSwingAmount,0,0));
			right_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing* 0.6662F + (float)Math.PI)*1.4f * limbSwingAmount,0,0));
		}
	}
}