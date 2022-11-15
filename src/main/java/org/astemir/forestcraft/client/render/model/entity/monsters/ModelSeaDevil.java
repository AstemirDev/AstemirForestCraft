package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.EntitySeaDevil;

public class ModelSeaDevil extends AnimatedEntityModel<EntitySeaDevil> {
	
	private final AModelRenderer sea_devil;
	private final AModelRenderer head;
	private final AModelRenderer jaw_up;
	private final AModelRenderer cube_r1;
	private final AModelRenderer bulb;
	private final AModelRenderer cube_r2;
	private final AModelRenderer bulb_a;
	private final AModelRenderer cube_r3;
	private final AModelRenderer light;
	private final AModelRenderer jaw_down;
	private final AModelRenderer cube_r4;
	private final AModelRenderer body;
	private final AModelRenderer tail;
	private final AModelRenderer tail_fin;
	private final AModelRenderer fin;
	private final AModelRenderer fin2;

	public ModelSeaDevil() {
		textureWidth = 128;
		textureHeight = 128;

		sea_devil = new AModelRenderer(this);
		sea_devil.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -6.0F, 2.0F);
		sea_devil.addChild(head);
		

		jaw_up = new AModelRenderer(this);
		jaw_up.setRotationPoint(0.0F, -3.0F, 0.0F);
		head.addChild(jaw_up);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 10.0F, -2.0F);
		jaw_up.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(44, 24).addBox(7.75F, -12.5F, -9.75F, 0.0F, 6.0F, 12.0F, 0.0F, false);
		cube_r1.setTextureOffset(41, 0).addBox(-8.0F, -12.5F, -9.75F, 16.0F, 5.0F, 0.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 22).addBox(-8.0F, -14.5F, -9.75F, 16.0F, 2.0F, 12.0F, 0.0F, false);
		cube_r1.setTextureOffset(44, 30).addBox(-8.0F, -12.5F, -9.75F, 0.0F, 6.0F, 12.0F, 0.0F, false);

		bulb = new AModelRenderer(this);
		bulb.setRotationPoint(0.0F, -4.0F, -7.25F);
		jaw_up.addChild(bulb);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 14.0F, 5.25F);
		bulb.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.2182F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(4, 0).addBox(-1.0F, -22.0F, -2.0F, 2.0F, 8.0F, 0.0F, 0.0F, false);

		bulb_a = new AModelRenderer(this);
		bulb_a.setRotationPoint(0.0F, -7.0F, -1.5F);
		bulb.addChild(bulb_a);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bulb_a.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.1345F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(0, 0).addBox(-1.0F, -8.0368F, 0.0448F, 2.0F, 8.0F, 0.0F, 0.0F, false);

		light = new AModelRenderer(this);
		light.setRotationPoint(-0.25F, -3.0F, -6.25F);
		bulb_a.addChild(light);
		light.setTextureOffset(0, 22).addBox(-0.75F, -1.0368F, -4.2052F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		jaw_down = new AModelRenderer(this);
		jaw_down.setRotationPoint(0.0F, 2.0F, 0.0F);
		head.addChild(jaw_down);
		

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 5.0F, -2.0F);
		jaw_down.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.0873F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(50, 38).addBox(-8.0F, -6.25F, -11.0F, 0.0F, 4.0F, 12.0F, 0.0F, false);
		cube_r4.setTextureOffset(0, 36).addBox(-8.0F, -2.25F, -11.0F, 16.0F, 2.0F, 12.0F, 0.0F, false);
		cube_r4.setTextureOffset(50, 42).addBox(8.0F, -6.25F, -11.0F, 0.0F, 4.0F, 12.0F, 0.0F, false);
		cube_r4.setTextureOffset(41, 5).addBox(-8.0F, -6.25F, -11.0F, 16.0F, 4.0F, 0.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -5.0F, 0.0F);
		sea_devil.addChild(body);
		body.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, 1.0F, 16.0F, 13.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(0, 50).addBox(-8.0F, -8.0F, 1.0F, 16.0F, 13.0F, 0.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 10.0F);
		body.addChild(tail);
		tail.setTextureOffset(44, 13).addBox(-3.0F, -5.0F, 0.0F, 6.0F, 9.0F, 9.0F, 0.0F, false);

		tail_fin = new AModelRenderer(this);
		tail_fin.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail.addChild(tail_fin);
		tail_fin.setTextureOffset(32, 41).addBox(0.0F, -9.0F, 9.0F, 0.0F, 15.0F, 9.0F, 0.0F, false);

		fin = new AModelRenderer(this);
		fin.setRotationPoint(-8.25F, -3.0F, 6.0F);
		body.addChild(fin);
		setRotationAngle(fin, 0.0F, 0.0F, 0.1745F);
		fin.setTextureOffset(0, 55).addBox(0.3318F, 0.0803F, -4.0F, 0.0F, 11.0F, 8.0F, 0.0F, false);

		fin2 = new AModelRenderer(this);
		fin2.setRotationPoint(8.0F, -2.0F, 4.0F);
		body.addChild(fin2);
		setRotationAngle(fin2, 0.0F, 0.0F, -0.1745F);
		fin2.setTextureOffset(50, 50).addBox(0.088F, -0.8611F, -2.0F, 0.0F, 11.0F, 8.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/sea_devil_animations.json"));
	}


	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntitySeaDevil.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntitySeaDevil.BITE)){
				return false;
			}
		}
		return true;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		sea_devil.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}