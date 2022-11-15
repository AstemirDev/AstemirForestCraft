package org.astemir.forestcraft.client.render.model.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.Entity;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.client.event.ClientEvents;

public class ModelPossesedSkull extends AnimatedBipedModel<Entity> {

	private final AModelRenderer fake_head;
	private final AModelRenderer cube_r1;
	private final AModelRenderer spine;
	private final AModelRenderer cube_r2;
	private final AModelRenderer spine_2;
	private final AModelRenderer cube_r3;
	private final AModelRenderer fake_head_head;
	private final AModelRenderer cube_r4;

	public static AnimationFile POSSESED_SKULL_ANIMATIONS = new AnimationFile("animations/possesed_skull_animations.json");
	public Animation IDLE = new Animation(0,"fake_heads_idle").time(1.04f).speed(4).loop();

	public ModelPossesedSkull() {
		textureWidth = 256;
		textureHeight = 256;

		fake_head = new AModelRenderer(this);
		fake_head.setRotationPoint(1.0F, 9.0F, 3.0F);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(16.0F, 26.0F, -21.0F);
		fake_head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.2618F);
		cube_r1.setTextureOffset(0, 205).addBox(-13.0F, -39.0F, 17.0F, 5.0F, 10.0F, 6.0F, 0.0F, false);

		spine = new AModelRenderer(this);
		spine.setRotationPoint(-4.0F, -8.0F, 0.0F);
		fake_head.addChild(spine);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(20.0F, 34.0F, -21.0F);
		spine.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.1309F);
		cube_r2.setTextureOffset(130, 119).addBox(-17.5F, -45.0F, 17.5F, 4.0F, 8.0F, 5.0F, 0.0F, false);

		spine_2 = new AModelRenderer(this);
		spine_2.setRotationPoint(-1.0F, -8.0F, -1.0F);
		spine.addChild(spine_2);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(21.0F, 42.0F, -20.0F);
		spine_2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.setTextureOffset(206, 28).addBox(-33.75F, -43.0F, 17.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		fake_head_head = new AModelRenderer(this);
		fake_head_head.setRotationPoint(2.0F, -7.0F, -1.0F);
		spine_2.addChild(fake_head_head);
		

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(19.0F, 49.0F, -19.0F);
		fake_head_head.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2618F);
		cube_r4.setTextureOffset(178, 141).addBox(-37.75F, -57.0F, 13.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		initializeAnimator(POSSESED_SKULL_ANIMATIONS);
	}


	@Override
	public void animate(Entity entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		fake_head.rotationPointX = bipedBody.rotationPointX;
		fake_head.rotationPointY = bipedBody.rotationPointY;
		fake_head.rotationPointZ = bipedBody.rotationPointZ;
		fake_head.customRotationX = bipedBody.rotateAngleX;
		fake_head.customRotationY = bipedBody.rotateAngleY;
		fake_head.customRotationZ = bipedBody.rotateAngleZ;
		animator.play(this,IDLE,delta);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		matrixStack.push();
		matrixStack.scale(0.5f,0.5f,0.5f);
		matrixStack.translate(-0.4f,0,0);
		fake_head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		matrixStack.pop();
	}

}