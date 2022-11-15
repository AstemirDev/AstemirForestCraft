package org.astemir.forestcraft.client.render.model.entity.animals;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.animals.EntityKelpy;

public class ModelKelpy extends AnimatedEntityModel<EntityKelpy> {
	
	private final AModelRenderer kelpy;
	private final AModelRenderer head;
	private final AModelRenderer rotator;
	private final AModelRenderer back;
	private final AModelRenderer tailA;
	private final AModelRenderer tailB;
	private final AModelRenderer wingA;
	private final AModelRenderer wingB;

	public static AnimationFile KELPY_ANIMATIONS = new AnimationFile("animations/kelpy_animations.json");

	public ModelKelpy() {
		textureWidth = 128;
		textureHeight = 128;

		kelpy = new AModelRenderer(this);
		kelpy.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, -5.0F, -4.0F);
		kelpy.addChild(head);
		head.setTextureOffset(34, 24).addBox(-6.0F, -6.0F, -9.0F, 12.0F, 11.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(36, 0).addBox(-4.0F, -1.0F, -13.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);

		rotator = new AModelRenderer(this);
		rotator.setRotationPoint(0.0F, -5.0F, -4.0F);
		kelpy.addChild(rotator);
		

		back = new AModelRenderer(this);
		back.setRotationPoint(0.0F, 0.0F, 0.0F);
		rotator.addChild(back);
		back.setTextureOffset(0, 42).addBox(0.0F, -17.0F, -1.0F, 0.0F, 10.0F, 12.0F, 0.0F, false);
		back.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -1.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		tailA = new AModelRenderer(this);
		tailA.setRotationPoint(0.0F, -1.0F, 10.0F);
		back.addChild(tailA);
		setRotationAngle(tailA, 0.0F, 0.0F, 1.5708F);
		tailA.setTextureOffset(0, 17).addBox(0.0F, -5.0F, 1.0F, 0.0F, 10.0F, 17.0F, 0.0F, false);

		tailB = new AModelRenderer(this);
		tailB.setRotationPoint(0.0F, 0.0F, 18.0F);
		tailA.addChild(tailB);
		tailB.setTextureOffset(0, 7).addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 17.0F, 0.0F, false);

		wingA = new AModelRenderer(this);
		wingA.setRotationPoint(-6.0F, 5.0F, 5.0F);
		back.addChild(wingA);
		wingA.setTextureOffset(24, 32).addBox(0.0F, 0.0F, -6.0F, 0.0F, 10.0F, 12.0F, 0.0F, false);

		wingB = new AModelRenderer(this);
		wingB.setRotationPoint(6.0F, 5.0F, 5.0F);
		back.addChild(wingB);
		wingB.setTextureOffset(0, 32).addBox(0.0F, 0.0F, -6.0F, 0.0F, 10.0F, 12.0F, 0.0F, false);
		initializeAnimator(KELPY_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		kelpy.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer AModelRenderer, float x, float y, float z) {
		AModelRenderer.rotateAngleX = x;
		AModelRenderer.rotateAngleY = y;
		AModelRenderer.rotateAngleZ = z;
		AModelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}