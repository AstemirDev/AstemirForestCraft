package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.monsters.EntityCrocusFlower;

public class ModelCrocusFlower extends AnimatedEntityModel<EntityCrocusFlower> {
	
	private final AModelRenderer crocus;
	private final AModelRenderer tentacle;
	private final AModelRenderer part;
	private final AModelRenderer tentacle2;
	private final AModelRenderer part2;
	private final AModelRenderer tentacle3;
	private final AModelRenderer part3;
	private final AModelRenderer tentacle4;
	private final AModelRenderer part4;
	private final AModelRenderer petal;
	private final AModelRenderer cube_r1;
	private final AModelRenderer petal4;
	private final AModelRenderer cube_r2;
	private final AModelRenderer petal2;
	private final AModelRenderer cube_r3;
	private final AModelRenderer petal3;
	private final AModelRenderer cube_r4;
	private final AModelRenderer flower;
	private final AModelRenderer a;
	private final AModelRenderer b;
	private final AModelRenderer c;

	public static AnimationFile CROCUS_FLOWER_ANIMATIONS = new AnimationFile("animations/crocus_flower_animations.json");


	public ModelCrocusFlower() {
		textureWidth = 128;
		textureHeight = 128;

		crocus = new AModelRenderer(this);
		crocus.setRotationPoint(8.0F, 24.0F, -8.0F);
		crocus.setTextureOffset(0, 24).addBox(-16.0F, -4.0F, 0.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);

		tentacle = new AModelRenderer(this);
		tentacle.setRotationPoint(-14.75F, -1.25F, 1.0F);
		crocus.addChild(tentacle);
		setRotationAngle(tentacle, 0.0F, 0.5236F, 0.0F);
		tentacle.setTextureOffset(76, 67).addBox(-1.5F, -1.75F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		part = new AModelRenderer(this);
		part.setRotationPoint(0.0F, 1.25F, -10.0F);
		tentacle.addChild(part);
		setRotationAngle(part, -0.4363F, 0.0F, 0.0F);
		part.setTextureOffset(78, 80).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		tentacle2 = new AModelRenderer(this);
		tentacle2.setRotationPoint(-1.5F, -1.25F, 1.0F);
		crocus.addChild(tentacle2);
		setRotationAngle(tentacle2, 0.0F, -0.5236F, 0.0F);
		tentacle2.setTextureOffset(76, 54).addBox(-1.5F, -1.75F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		part2 = new AModelRenderer(this);
		part2.setRotationPoint(0.0F, 1.25F, -10.0F);
		tentacle2.addChild(part2);
		setRotationAngle(part2, -0.4363F, 0.0F, 0.0F);
		part2.setTextureOffset(60, 80).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		tentacle3 = new AModelRenderer(this);
		tentacle3.setRotationPoint(-1.25F, -1.25F, 14.5F);
		crocus.addChild(tentacle3);
		setRotationAngle(tentacle3, 0.0F, -2.3562F, 0.0F);
		tentacle3.setTextureOffset(60, 64).addBox(-1.5F, -1.75F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		part3 = new AModelRenderer(this);
		part3.setRotationPoint(0.0F, 1.25F, -10.0F);
		tentacle3.addChild(part3);
		setRotationAngle(part3, -0.4363F, 0.0F, 0.0F);
		part3.setTextureOffset(0, 9).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		tentacle4 = new AModelRenderer(this);
		tentacle4.setRotationPoint(-15.25F, -1.25F, 15.0F);
		crocus.addChild(tentacle4);
		setRotationAngle(tentacle4, 0.0F, 2.1817F, 0.0F);
		tentacle4.setTextureOffset(62, 14).addBox(-1.5F, -1.75F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		part4 = new AModelRenderer(this);
		part4.setRotationPoint(0.0F, 1.25F, -10.0F);
		tentacle4.addChild(part4);
		setRotationAngle(part4, -0.4363F, 0.0F, 0.0F);
		part4.setTextureOffset(0, 0).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		petal = new AModelRenderer(this);
		petal.setRotationPoint(-9.0F, -3.0F, 0.0F);
		crocus.addChild(petal);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(9.0F, -13.0F, 0.0F);
		petal.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.7854F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(2, 78).addBox(-13.0F, -8.0F, -9.0F, 10.0F, 7.0F, 1.0F, 0.0F, false);
		cube_r1.setTextureOffset(0, 64).addBox(-15.0F, -1.0F, -9.0F, 14.0F, 13.0F, 1.0F, 0.0F, false);

		petal4 = new AModelRenderer(this);
		petal4.setRotationPoint(-7.75F, -3.0F, 15.0F);
		crocus.addChild(petal4);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(7.75F, -13.0F, 1.0F);
		petal4.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(2, 78).addBox(-13.0F, -8.0F, 8.0F, 10.0F, 7.0F, 1.0F, 0.0F, false);
		cube_r2.setTextureOffset(0, 64).addBox(-15.0F, -1.0F, 8.0F, 14.0F, 13.0F, 1.0F, 0.0F, false);

		petal2 = new AModelRenderer(this);
		petal2.setRotationPoint(-16.0F, -4.0F, 9.0F);
		crocus.addChild(petal2);
		setRotationAngle(petal2, 0.0F, 1.5708F, 0.0F);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(9.0F, -13.0F, 0.0F);
		petal2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.7854F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(2, 78).addBox(-13.0F, -8.0F, -9.0F, 10.0F, 7.0F, 1.0F, 0.0F, true);
		cube_r3.setTextureOffset(0, 64).addBox(-15.0F, -1.0F, -9.0F, 14.0F, 13.0F, 1.0F, 0.0F, false);

		petal3 = new AModelRenderer(this);
		petal3.setRotationPoint(0.0F, -4.0F, 7.75F);
		crocus.addChild(petal3);
		setRotationAngle(petal3, 0.0F, -1.5708F, 0.0F);
		

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(-7.75F, -13.0F, 0.0F);
		petal3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.7854F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(2, 78).addBox(3.0F, -8.0F, -9.0F, 10.0F, 7.0F, 1.0F, 0.0F, true);
		cube_r4.setTextureOffset(0, 64).addBox(1.0F, -1.0F, -9.0F, 14.0F, 13.0F, 1.0F, 0.0F, false);

		flower = new AModelRenderer(this);
		flower.setRotationPoint(-8.0F, -4.0F, 8.0F);
		crocus.addChild(flower);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(0.0F, -15.0F, 0.0F);
		flower.addChild(a);
		a.setTextureOffset(0, 44).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(0.0F, -8.0F, 0.0F);
		flower.addChild(b);
		b.setTextureOffset(0, 0).addBox(-9.0F, -4.0F, -9.0F, 18.0F, 6.0F, 18.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(0.0F, -2.0F, 0.0F);
		flower.addChild(c);
		c.setTextureOffset(50, 30).addBox(-7.0F, -4.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		initializeAnimator(CROCUS_FLOWER_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		crocus.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}