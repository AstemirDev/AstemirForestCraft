package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.EntityDesecratedSoul;

public class ModelDesecratedSoul extends AnimatedEntityModel<EntityDesecratedSoul> {

	private final AModelRenderer soul;
	private final AModelRenderer tail;
	private final AModelRenderer body;
	private final AModelRenderer leftArm;
	private final AModelRenderer rightArm;
	private final AModelRenderer head;

	public static AnimationFile DESECRATED_SOUL_ANIMATIONS = new AnimationFile("animations/desecrated_soul_animations.json");


	public ModelDesecratedSoul() {
		textureWidth = 64;
		textureHeight = 64;

		soul = new AModelRenderer(this);
		soul.setRotationPoint(0.0F, 7.0F, 0.0F);
		setRotationAngle(soul, 0.4363F, 0.0F, 0.0F);


		tail = new AModelRenderer(this);
		tail.setRotationPoint(-1.9F, 5.0F, -1.0F);
		soul.addChild(tail);
		tail.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 0.0F);
		soul.addChild(body);
		setRotationAngle(body, -0.3054F, 0.0F, 0.0F);
		body.setTextureOffset(24, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm = new AModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, -10.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, -0.9599F, 0.0F, 0.0F);
		leftArm.setTextureOffset(32, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new AModelRenderer(this);
		rightArm.setRotationPoint(5.0F, -10.0F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, -1.0036F, 0.0F, 0.0F);
		rightArm.setTextureOffset(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -12.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		initializeAnimator(DESECRATED_SOUL_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		soul.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

	@Override
	public void animate(EntityDesecratedSoul entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.setCustomRotation(new Vector3((float) Math.toRadians(Math.min(30, Math.max(-30, headPitch))),(float) Math.toRadians(Math.min(30, Math.max(-30, netHeadYaw))),0));
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityDesecratedSoul.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityDesecratedSoul.ATTACK)){
				return false;
			}
		}
		return true;
	}
}