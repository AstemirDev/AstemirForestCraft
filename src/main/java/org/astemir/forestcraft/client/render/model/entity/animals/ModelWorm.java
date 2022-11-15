package org.astemir.forestcraft.client.render.model.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.animals.EntityWorm;

public class ModelWorm extends AnimatedEntityModel<EntityWorm> {
	
	private final AModelRenderer worm;
	private final AModelRenderer body;
	private final AModelRenderer a;
	private final AModelRenderer b;
	private final AModelRenderer c;

	public ModelWorm() {
		textureWidth = 32;
		textureHeight = 32;

		worm = new AModelRenderer(this);
		worm.setRotationPoint(0.0F, 23.75F, 0.0F);
		

		body = new AModelRenderer(this);
		body.setRotationPoint(0.5F, 0.25F, 4.0F);
		worm.addChild(body);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(-0.5F, -1.25F, -8.0F);
		body.addChild(a);
		a.setTextureOffset(8, 9).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(-0.5F, -1.25F, 0.0F);
		body.addChild(b);
		b.setTextureOffset(0, 7).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(-0.5F, 0.0F, -4.0F);
		body.addChild(c);
		c.setTextureOffset(0, 0).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/worm_animations.json"));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		worm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}