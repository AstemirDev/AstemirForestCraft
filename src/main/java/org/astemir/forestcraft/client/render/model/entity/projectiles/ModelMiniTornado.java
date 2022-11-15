package org.astemir.forestcraft.client.render.model.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityMiniTornadoProjectile;

public class ModelMiniTornado extends AnimatedEntityModel<EntityMiniTornadoProjectile> {

	private final AModelRenderer projectile;
	private final AModelRenderer a;
	private final AModelRenderer b;
	private final AModelRenderer c;

	public static AnimationFile TORNADO_ANIMATIONS = new AnimationFile("animations/mini_tornado_animations.json");

	public ModelMiniTornado() {
		textureWidth = 64;
		textureHeight = 64;

		projectile = new AModelRenderer(this);
		projectile.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(0.0F, -2.0F, 0.0F);
		projectile.addChild(a);
		a.setTextureOffset(0, 34).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(0.0F, -8.0F, 0.0F);
		projectile.addChild(b);
		b.setTextureOffset(0, 19).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(0.0F, -12.0F, 0.0F);
		projectile.addChild(c);
		c.setTextureOffset(0, 0).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 5.0F, 14.0F, 0.0F, false);
		initializeAnimator(TORNADO_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		projectile.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}