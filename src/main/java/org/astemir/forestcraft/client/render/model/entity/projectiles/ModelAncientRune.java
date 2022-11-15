package org.astemir.forestcraft.client.render.model.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.projectiles.other.DaybreakProjectile;

public class ModelAncientRune extends AnimatedEntityModel<DaybreakProjectile> {

	private final AModelRenderer rune;
	private final AModelRenderer a;
	private final AModelRenderer b;
	private final AModelRenderer cube_r1;

	public ModelAncientRune() {
		textureWidth = 64;
		textureHeight = 64;

		rune = new AModelRenderer(this);
		rune.setRotationPoint(0.0F, 17.0F, 0.0F);
		rune.setTextureOffset(0, 12).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		a = new AModelRenderer(this);
		a.setRotationPoint(0.0F, 0.0F, 0.0F);
		rune.addChild(a);
		a.setTextureOffset(0, 0).addBox(-6.0F, -6.5F, 0.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(0.0F, -0.5F, 0.0F);
		rune.addChild(b);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 7.5F, 0.0F);
		b.addChild(cube_r1);
		setRotationAngle(cube_r1, 1.5708F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(24, 0).addBox(-6.0F, -6.0F, 7.0F, 12.0F, 12.0F, 0.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/ancient_rune_animations.json"));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rune.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}