package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.EntityTerrorBringer;
import org.astemir.forestcraft.common.entities.monsters.EntityWaterBug;

public class ModelWaterBug extends AnimatedEntityModel<EntityWaterBug> {
	
	private final AModelRenderer water_bug;
	private final AModelRenderer head;
	private final AModelRenderer head_r1;
	private final AModelRenderer head_r2;
	private final AModelRenderer jaw;
	private final AModelRenderer jaw_r1;
	private final AModelRenderer jaw_r2;
	private final AModelRenderer jaw2;
	private final AModelRenderer jaw2_r1;
	private final AModelRenderer jaw2_r2;
	private final AModelRenderer body;
	private final AModelRenderer body_r1;
	private final AModelRenderer leg2;
	private final AModelRenderer leg2_r1;
	private final AModelRenderer leg2_r2;
	private final AModelRenderer leg;
	private final AModelRenderer leg_r1;
	private final AModelRenderer leg_r2;
	private final AModelRenderer arm2;
	private final AModelRenderer arm2_r1;
	private final AModelRenderer arm2_r2;
	private final AModelRenderer arm;
	private final AModelRenderer arm_r1;
	private final AModelRenderer arm_r2;

	public ModelWaterBug() {
		textureWidth = 64;
		textureHeight = 64;

		water_bug = new AModelRenderer(this);
		water_bug.setRotationPoint(2.0F, 24.0F, 1.0F);
		

		head = new AModelRenderer(this);
		head.setRotationPoint(-3.0F, -2.0F, -5.0F);
		water_bug.addChild(head);
		

		head_r1 = new AModelRenderer(this);
		head_r1.setRotationPoint(3.0F, 2.0F, 5.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 1.5708F, 0.0F);
		head_r1.setTextureOffset(0, 0).addBox(6.0F, -5.0F, -2.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head_r1.setTextureOffset(0, 4).addBox(6.0F, -5.0F, -5.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		head_r2 = new AModelRenderer(this);
		head_r2.setRotationPoint(3.0F, 1.0F, 6.0F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 1.5708F, 1.4399F, 1.5708F);
		head_r2.setTextureOffset(0, 31).addBox(4.0F, -4.0F, -6.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);

		jaw = new AModelRenderer(this);
		jaw.setRotationPoint(2.0F, 1.0F, -1.0F);
		head.addChild(jaw);
		

		jaw_r1 = new AModelRenderer(this);
		jaw_r1.setRotationPoint(-1.0F, 0.0F, 7.0F);
		jaw.addChild(jaw_r1);
		setRotationAngle(jaw_r1, 3.0096F, -0.1298F, -3.1244F);
		jaw_r1.setTextureOffset(42, 16).addBox(-7.0F, -1.0F, 7.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		jaw_r2 = new AModelRenderer(this);
		jaw_r2.setRotationPoint(-1.0F, 0.0F, 7.0F);
		jaw.addChild(jaw_r2);
		setRotationAngle(jaw_r2, 2.8638F, 1.0746F, 2.8959F);
		jaw_r2.setTextureOffset(43, 24).addBox(4.25F, -2.0F, 4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		jaw2 = new AModelRenderer(this);
		jaw2.setRotationPoint(-2.0F, 1.0F, -1.0F);
		head.addChild(jaw2);
		

		jaw2_r1 = new AModelRenderer(this);
		jaw2_r1.setRotationPoint(1.0F, 0.0F, 7.0F);
		jaw2.addChild(jaw2_r1);
		setRotationAngle(jaw2_r1, 3.0096F, 0.1298F, 3.1244F);
		jaw2_r1.setTextureOffset(36, 31).addBox(6.0F, -1.0F, 7.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		jaw2_r2 = new AModelRenderer(this);
		jaw2_r2.setRotationPoint(1.0F, 0.0F, 7.0F);
		jaw2.addChild(jaw2_r2);
		setRotationAngle(jaw2_r2, 2.8638F, -1.0746F, -2.8959F);
		jaw2_r2.setTextureOffset(31, 38).addBox(-6.25F, -2.0F, 4.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		water_bug.addChild(body);
		

		body_r1 = new AModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, 0.0F, 1.5708F, 0.0F);
		body_r1.setTextureOffset(20, 23).addBox(2.0F, -6.0F, -7.0F, 3.0F, 6.0F, 8.0F, 0.0F, false);
		body_r1.setTextureOffset(2, 49).addBox(-23.0F, -5.0F, -5.5F, 7.0F, 0.0F, 5.0F, 0.0F, false);
		body_r1.setTextureOffset(15, 37).addBox(-16.0F, -6.0F, -5.5F, 3.0F, 6.0F, 5.0F, 0.0F, false);
		body_r1.setTextureOffset(23, 9).addBox(-13.0F, -6.0F, -6.5F, 5.0F, 6.0F, 7.0F, 0.0F, false);
		body_r1.setTextureOffset(0, 0).addBox(-3.0F, -6.0F, -8.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
		body_r1.setTextureOffset(0, 16).addBox(-8.0F, -6.0F, -7.5F, 5.0F, 6.0F, 9.0F, 0.0F, false);

		leg2 = new AModelRenderer(this);
		leg2.setRotationPoint(-7.75F, -1.0F, 5.0F);
		body.addChild(leg2);
		

		leg2_r1 = new AModelRenderer(this);
		leg2_r1.setRotationPoint(10.0F, 3.0F, -5.0F);
		leg2.addChild(leg2_r1);
		setRotationAngle(leg2_r1, 0.1314F, -0.0865F, -0.0114F);
		leg2_r1.setTextureOffset(20, 0).addBox(-14.6485F, -2.908F, 11.4394F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		leg2_r2 = new AModelRenderer(this);
		leg2_r2.setRotationPoint(2.0F, 0.0F, 7.0F);
		leg2.addChild(leg2_r2);
		setRotationAngle(leg2_r2, 0.2256F, -0.9478F, -0.1843F);
		leg2_r2.setTextureOffset(34, 0).addBox(-7.25F, -3.0F, -3.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		leg2_r2.setTextureOffset(44, 31).addBox(-6.75F, -2.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		leg = new AModelRenderer(this);
		leg.setRotationPoint(2.0F, -1.0F, 5.0F);
		body.addChild(leg);
		

		leg_r1 = new AModelRenderer(this);
		leg_r1.setRotationPoint(-2.0F, 0.0F, 7.0F);
		leg.addChild(leg_r1);
		setRotationAngle(leg_r1, 0.1314F, 0.0865F, 0.0114F);
		leg_r1.setTextureOffset(39, 39).addBox(6.75F, -1.5F, -1.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		leg_r2 = new AModelRenderer(this);
		leg_r2.setRotationPoint(-2.0F, 0.0F, 7.0F);
		leg.addChild(leg_r2);
		setRotationAngle(leg_r2, 0.2256F, 0.9478F, 0.1843F);
		leg_r2.setTextureOffset(40, 7).addBox(4.25F, -3.0F, -3.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		leg_r2.setTextureOffset(39, 46).addBox(4.75F, -2.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		arm2 = new AModelRenderer(this);
		arm2.setRotationPoint(-7.5F, -1.0F, 0.0F);
		body.addChild(arm2);
		

		arm2_r1 = new AModelRenderer(this);
		arm2_r1.setRotationPoint(2.0F, 0.0F, 7.0F);
		arm2.addChild(arm2_r1);
		setRotationAngle(arm2_r1, 2.5148F, -1.0746F, -2.8959F);
		arm2_r1.setTextureOffset(34, 22).addBox(-5.75F, -4.25F, 6.75F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		arm2_r2 = new AModelRenderer(this);
		arm2_r2.setRotationPoint(2.0F, 0.0F, 7.0F);
		arm2.addChild(arm2_r2);
		setRotationAngle(arm2_r2, 2.8638F, -1.0746F, -2.8959F);
		arm2_r2.setTextureOffset(27, 45).addBox(-6.25F, -2.0F, 4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		arm = new AModelRenderer(this);
		arm.setRotationPoint(2.0F, -1.0F, 0.0F);
		body.addChild(arm);
		

		arm_r1 = new AModelRenderer(this);
		arm_r1.setRotationPoint(-2.0F, 0.0F, 7.0F);
		arm.addChild(arm_r1);
		setRotationAngle(arm_r1, 2.5148F, 1.0746F, 2.8959F);
		arm_r1.setTextureOffset(0, 41).addBox(4.75F, -4.25F, 6.75F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		arm_r2 = new AModelRenderer(this);
		arm_r2.setRotationPoint(-2.0F, 0.0F, 7.0F);
		arm.addChild(arm_r2);
		setRotationAngle(arm_r2, 2.8638F, 1.0746F, 2.8959F);
		arm_r2.setTextureOffset(47, 37).addBox(4.25F, -2.0F, 4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/water_bug_animations.json"));
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		water_bug.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityWaterBug.IDLE_WATER | animation == EntityWaterBug.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityWaterBug.MOVE)){
				return false;
			}
		}
		if (animation == EntityWaterBug.MOVE || animation == EntityWaterBug.IDLE || animation == EntityWaterBug.IDLE_WATER){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityWaterBug.BITE)){
				return false;
			}
		}
		return true;
	}
}