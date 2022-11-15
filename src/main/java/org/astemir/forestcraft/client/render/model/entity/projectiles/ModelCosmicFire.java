package org.astemir.forestcraft.client.render.model.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityCosmicFire;


public class ModelCosmicFire extends AnimatedEntityModel<EntityCosmicFire> {
	
	private final AModelRenderer cosmic_fire;
	private final AModelRenderer flame;
	private final AModelRenderer flame2;
	private final AModelRenderer flame3;
	private final AModelRenderer flame4;
	private final AModelRenderer cube_r1;
	private final AModelRenderer flame5;
	private final AModelRenderer cube_r2;

	public static AnimationFile COSMIC_FIRE_ANIMATIONS = new AnimationFile("animations/cosmic_fire_animations.json");


	public ModelCosmicFire() {
		textureWidth = 16;
		textureHeight = 16;

		cosmic_fire = new AModelRenderer(this);
		cosmic_fire.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		flame = new AModelRenderer(this);
		flame.setRotationPoint(0.0F, 0.0F, 0.0F);
		cosmic_fire.addChild(flame);
		flame.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, false);

		flame2 = new AModelRenderer(this);
		flame2.setRotationPoint(0.0F, 0.0F, 0.0F);
		cosmic_fire.addChild(flame2);
		flame2.setTextureOffset(0, 0).addBox(-8.0F, -12.0F, 1.0F, 16.0F, 12.0F, 0.0F, 0.0F, false);

		flame3 = new AModelRenderer(this);
		flame3.setRotationPoint(0.0F, 0.0F, 0.0F);
		cosmic_fire.addChild(flame3);
		flame3.setTextureOffset(0, 0).addBox(-8.0F, -12.0F, -1.0F, 16.0F, 12.0F, 0.0F, 0.0F, false);

		flame4 = new AModelRenderer(this);
		flame4.setRotationPoint(0.0F, 0.0F, 0.0F);
		cosmic_fire.addChild(flame4);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		flame4.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2618F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-8.0F, -12.0F, 0.0F, 16.0F, 12.0F, 0.0F, 0.0F, false);

		flame5 = new AModelRenderer(this);
		flame5.setRotationPoint(0.0F, 0.0F, 0.0F);
		cosmic_fire.addChild(flame5);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		flame5.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.2618F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 11.0F, 0.0F, 0.0F, false);
		initializeAnimator(COSMIC_FIRE_ANIMATIONS);
	}



	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		cosmic_fire.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
}