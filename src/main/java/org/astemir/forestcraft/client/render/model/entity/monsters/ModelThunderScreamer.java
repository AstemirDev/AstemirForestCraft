package org.astemir.forestcraft.client.render.model.entity.monsters;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.forestcraft.common.entities.monsters.EntityThunderScreamer;

public class ModelThunderScreamer extends AnimatedEntityModel<EntityThunderScreamer> {

	public static AnimationFile THUNDERSCREAMER_ANIMATIONS = new AnimationFile("animations/thunderscreamer_animations.json");
	private final AModelRenderer thunderscreamer;
	private final AModelRenderer head;
	private final AModelRenderer head_r1;
	private final AModelRenderer head_r2;
	private final AModelRenderer left_wing;
	private final AModelRenderer left_wing_element;
	private final AModelRenderer right_wing;
	private final AModelRenderer right_wing_element;
	private final AModelRenderer tail;
	private final AModelRenderer tail_element;
	private final AModelRenderer left_leg;
	private final AModelRenderer right_leg;

	public ModelThunderScreamer() {
		textureWidth = 128;
		textureHeight = 128;

		thunderscreamer = new AModelRenderer(this);
		thunderscreamer.setRotationPoint(0.0F, 11.0F, -1.0F);
		thunderscreamer.setTextureOffset(30, 23).addBox(-6.0F, -5.0F, -8.0F, 12.0F, 9.0F, 19.0F, 0.0F, false);
		thunderscreamer.setTextureOffset(32, 70).addBox(-4.0F, -4.0F, -16.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, 1.0F, 10.0F);
		thunderscreamer.addChild(head);
		head.setTextureOffset(55, 51).addBox(-10.0F, -15.0F, 6.0F, 20.0F, 19.0F, 0.0F, 0.0F, false);
		head.setTextureOffset(0, 66).addBox(-4.0F, -8.0F, 6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 10).addBox(-1.5F, -4.0F, 13.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		head_r1 = new AModelRenderer(this);
		head_r1.setRotationPoint(6.0F, 4.0F, -19.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -0.1745F, 0.0F, 0.0F);
		head_r1.setTextureOffset(14, 14).addBox(-6.5F, -13.0F, 34.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		head_r2 = new AModelRenderer(this);
		head_r2.setRotationPoint(6.0F, 3.0F, -18.0F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 0.2618F, 0.0F, 0.0F);
		head_r2.setTextureOffset(0, 34).addBox(-9.0F, -3.0F, 18.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);

		left_wing = new AModelRenderer(this);
		left_wing.setRotationPoint(-6.0F, -5.0F, 3.0F);
		thunderscreamer.addChild(left_wing);
		left_wing.setTextureOffset(0, 51).addBox(-20.0F, 0.0F, -8.0F, 20.0F, 0.0F, 15.0F, 0.0F, false);

		left_wing_element = new AModelRenderer(this);
		left_wing_element.setRotationPoint(-20.0F, 1.0F, -3.0F);
		left_wing.addChild(left_wing_element);
		left_wing_element.setTextureOffset(63, 25).addBox(-12.0F, -1.0F, -4.5F, 12.0F, 0.0F, 10.0F, 0.0F, false);

		right_wing = new AModelRenderer(this);
		right_wing.setRotationPoint(6.0F, -5.0F, 3.0F);
		thunderscreamer.addChild(right_wing);
		right_wing.setTextureOffset(46, 0).addBox(0.0F, 0.0F, -8.0F, 20.0F, 0.0F, 15.0F, 0.0F, false);

		right_wing_element = new AModelRenderer(this);
		right_wing_element.setRotationPoint(20.0F, 0.0F, -3.0F);
		right_wing.addChild(right_wing_element);
		right_wing_element.setTextureOffset(63, 15).addBox(0.0F, 0.0F, -4.5F, 12.0F, 0.0F, 10.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, -1.0F, -16.0F);
		thunderscreamer.addChild(tail);
		tail.setTextureOffset(0, 0).addBox(-10.0F, 0.0F, -21.0F, 20.0F, 0.0F, 21.0F, 0.0F, false);

		tail_element = new AModelRenderer(this);
		tail_element.setRotationPoint(0.0F, 0.0F, -21.0F);
		tail.addChild(tail_element);
		tail_element.setTextureOffset(0, 21).addBox(-7.0F, 0.0F, -21.0F, 14.0F, 0.0F, 21.0F, 0.0F, false);

		left_leg = new AModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 4.0F, -6.0F);
		thunderscreamer.addChild(left_leg);
		left_leg.setTextureOffset(13, 7).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		left_leg.setTextureOffset(0, 5).addBox(-0.5F, 5.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		left_leg.setTextureOffset(0, 5).addBox(-2.0F, 9.0F, -1.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);

		right_leg = new AModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 4.0F, -6.0F);
		thunderscreamer.addChild(right_leg);
		right_leg.setTextureOffset(13, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 0).addBox(-0.5F, 5.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 0).addBox(-2.0F, 9.0F, -1.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
		initializeAnimator(THUNDERSCREAMER_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		thunderscreamer.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void animate(EntityThunderScreamer entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}


	public void setRotationAngle(AModelRenderer AModelRenderer, float x, float y, float z) {
		AModelRenderer.rotateAngleX = x;
		AModelRenderer.rotateAngleY = y;
		AModelRenderer.rotateAngleZ = z;
		AModelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}