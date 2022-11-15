package org.astemir.forestcraft.client.render.model.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.animals.EntityDandelioneer;

public class ModelDandelioneer extends AnimatedEntityModel<EntityDandelioneer> {

	private final AModelRenderer dandelioneer;
	private final AModelRenderer whole_body;
	private final AModelRenderer body;
	private final AModelRenderer cap;
	private final AModelRenderer hand0;
	private final AModelRenderer hand1;
	private final AModelRenderer leg0;
	private final AModelRenderer leg1;

	public static AnimationFile DANDELIONEER_ANIMATIONS = new AnimationFile("animations/dandelioneer_animations.json");


	public ModelDandelioneer() {
		textureWidth = 32;
		textureHeight = 32;

		dandelioneer = new AModelRenderer(this);
		dandelioneer.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		whole_body = new AModelRenderer(this);
		whole_body.setRotationPoint(0.0F, 0.0F, 0.0F);
		dandelioneer.addChild(whole_body);
		

		body = new AModelRenderer(this);
		body.setRotationPoint(-1.0F, -3.0F, 0.0F);
		whole_body.addChild(body);
		body.setTextureOffset(0, 12).addBox(-2.0F, -10.0F, -2.0F, 5.0F, 10.0F, 4.0F, 0.0F, false);

		cap = new AModelRenderer(this);
		cap.setRotationPoint(1.0F, 3.0F, 0.0F);
		body.addChild(cap);
		cap.setTextureOffset(0, 0).addBox(-4.0F, -19.0F, -3.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

		hand0 = new AModelRenderer(this);
		hand0.setRotationPoint(-2.0F, -6.0F, 0.0F);
		body.addChild(hand0);
		hand0.setTextureOffset(18, 19).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		hand1 = new AModelRenderer(this);
		hand1.setRotationPoint(3.0F, -6.0F, 0.0F);
		body.addChild(hand1);
		hand1.setTextureOffset(18, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		leg0 = new AModelRenderer(this);
		leg0.setRotationPoint(-2.0F, -3.0F, 0.0F);
		whole_body.addChild(leg0);
		leg0.setTextureOffset(24, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg1 = new AModelRenderer(this);
		leg1.setRotationPoint(1.0F, -3.0F, 0.0F);
		whole_body.addChild(leg1);
		leg1.setTextureOffset(20, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		initializeAnimator(DANDELIONEER_ANIMATIONS);
	}


	@Override
	public void animate(EntityDandelioneer entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		body.setCustomRotation(new Vector3(headPitch * ((float)Math.PI / 180F)/2,netHeadYaw * ((float)Math.PI / 180F)/2,0));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		dandelioneer.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

}