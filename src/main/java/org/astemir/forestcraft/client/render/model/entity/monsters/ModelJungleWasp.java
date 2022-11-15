package org.astemir.forestcraft.client.render.model.entity.monsters;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.*;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityJungleWasp;

public class ModelJungleWasp extends AnimatedEntityModel<EntityJungleWasp> {

	private final AModelRenderer wasp;
	private final AModelRenderer wing;
	private final AModelRenderer cube_r1;
	private final AModelRenderer wing2;
	private final AModelRenderer cube_r2;
	private final AModelRenderer butt;
	private final AModelRenderer cube_r3;
	private final AModelRenderer cube_r4;
	private final AModelRenderer cube_r5;
	private final AModelRenderer cube_r6;
	private final AModelRenderer head;
	private final AModelRenderer antenne;
	private final AModelRenderer antenne2;
	private final AModelRenderer legs;
	private final AModelRenderer cube_r7;
	private final AModelRenderer legs2;
	private final AModelRenderer cube_r8;
	private final AModelRenderer legs3;
	private final AModelRenderer cube_r9;

	public static AnimationFile JUNGLE_WASP_ANIMATIONS = new AnimationFile("animations/jungle_wasp_animations.json");


	public ModelJungleWasp() {
		textureWidth = 64;
		textureHeight = 64;

		wasp = new AModelRenderer(this);
		wasp.setRotationPoint(0.0F, 20.0F, 0.0F);
		

		wing = new AModelRenderer(this);
		wing.setRotationPoint(-4.0F, -3.0F, 0.0F);
		wasp.addChild(wing);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(4.0F, 7.0F, -1.0F);
		wing.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.3054F, -0.6545F);
		cube_r1.setTextureOffset(14, 21).addBox(0.5F, -16.0F, -2.0F, 0.0F, 9.0F, 7.0F, 0.0F, false);

		wing2 = new AModelRenderer(this);
		wing2.setRotationPoint(3.0F, -3.0F, 0.0F);
		wasp.addChild(wing2);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(-4.0F, 7.0F, -1.0F);
		wing2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.3054F, 0.6545F);
		cube_r2.setTextureOffset(0, 21).addBox(-0.5F, -16.0F, -2.0F, 0.0F, 9.0F, 7.0F, 0.0F, false);

		butt = new AModelRenderer(this);
		butt.setRotationPoint(-0.5F, -1.0F, 0.0F);
		wasp.addChild(butt);
		butt.setTextureOffset(0, 0).addBox(-4.0F, -2.5F, 0.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(0.5F, 2.75F, -2.0F);
		butt.addChild(cube_r3);
		setRotationAngle(cube_r3, -1.789F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(22, 0).addBox(-1.0F, -14.0F, 1.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(0.5F, 2.75F, -2.0F);
		butt.addChild(cube_r4);
		setRotationAngle(cube_r4, -1.3963F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(28, 0).addBox(-1.5F, -12.75F, 1.75F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(0.5F, 2.75F, -2.0F);
		butt.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.7418F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(28, 29).addBox(-2.5F, -9.25F, 6.25F, 4.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(0.5F, 4.5F, 2.0F);
		butt.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.2182F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(28, 21).addBox(-3.0F, -6.0F, 2.0F, 5.0F, 4.0F, 4.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(-0.5F, 1.0F, 0.0F);
		wasp.addChild(head);
		head.setTextureOffset(0, 14).addBox(-3.5F, -4.0F, -7.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);

		antenne = new AModelRenderer(this);
		antenne.setRotationPoint(-2.5F, -2.0F, -7.0F);
		head.addChild(antenne);
		antenne.setTextureOffset(28, 0).addBox(1.0F, -4.0F, -7.0F, 0.0F, 7.0F, 7.0F, 0.0F, false);

		antenne2 = new AModelRenderer(this);
		antenne2.setRotationPoint(1.5F, -2.0F, -7.0F);
		head.addChild(antenne2);
		antenne2.setTextureOffset(21, 7).addBox(0.0F, -4.0F, -7.0F, 0.0F, 7.0F, 7.0F, 0.0F, false);

		legs = new AModelRenderer(this);
		legs.setRotationPoint(0.0F, 4.0F, -5.0F);
		wasp.addChild(legs);
		

		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 4.0F);
		legs.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.5236F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(0, 38).addBox(-4.0F, -2.0F, -3.0F, 7.0F, 7.0F, 0.0F, 0.0F, false);

		legs2 = new AModelRenderer(this);
		legs2.setRotationPoint(0.0F, 4.0F, -1.0F);
		wasp.addChild(legs2);
		

		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
		legs2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.5236F, 0.0F, 0.0F);
		cube_r8.setTextureOffset(0, 38).addBox(-4.0F, -1.0F, 0.0F, 7.0F, 7.0F, 0.0F, 0.0F, false);

		legs3 = new AModelRenderer(this);
		legs3.setRotationPoint(0.0F, 4.0F, 4.0F);
		wasp.addChild(legs3);
		

		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 0.0F, -5.0F);
		legs3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.5236F, 0.0F, 0.0F);
		cube_r9.setTextureOffset(0, 38).addBox(-4.0F, 2.0F, 4.0F, 7.0F, 7.0F, 0.0F, 0.0F, false);
		initializeAnimator(JUNGLE_WASP_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		wasp.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}


	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityJungleWasp.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityJungleWasp.ATTACK)){
				return false;
			}
		}
		return true;
	}
}