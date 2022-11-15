package org.astemir.forestcraft.client.render.model.entity.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.projectiles.other.EntityBubbleProjectile;

public class ModelWaterBubble extends AnimatedEntityModel<EntityBubbleProjectile> {

	private final AModelRenderer bubble;
	private final AModelRenderer bubble1;
	private final AModelRenderer bubble2;
	private final AModelRenderer bubble3;

	public static AnimationFile WATER_BUBBLE_ANIMATIONS = new AnimationFile("animations/water_bubble_animations.json");

	public ModelWaterBubble() {
		textureWidth = 32;
		textureHeight = 32;
		bubble = new AModelRenderer(this);
		bubble.setRotationPoint(0.0F, 18.0F, -1.0F);
		bubble1 = new AModelRenderer(this);
		bubble1.setRotationPoint(-1.0F, 2.0F, 6.0F);
		bubble.addChild(bubble1);
		bubble1.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		bubble2 = new AModelRenderer(this);
		bubble2.setRotationPoint(5.0F, -4.0F, -3.0F);
		bubble.addChild(bubble2);
		bubble2.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		bubble3 = new AModelRenderer(this);
		bubble3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bubble.addChild(bubble3);
		bubble3.setTextureOffset(0, 0).addBox(-9.0F, -5.0F, -7.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		initializeAnimator(WATER_BUBBLE_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bubble.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}