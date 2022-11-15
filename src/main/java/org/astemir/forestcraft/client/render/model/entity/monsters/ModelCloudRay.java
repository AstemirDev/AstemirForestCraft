package org.astemir.forestcraft.client.render.model.entity.monsters;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.monsters.EntityCloudRay;

public class ModelCloudRay extends AnimatedEntityModel<EntityCloudRay> {

	private final AModelRenderer cloudray;
	private final AModelRenderer body;
	private final AModelRenderer head;
	private final AModelRenderer wing;
	private final AModelRenderer wing2;
	private final AModelRenderer tail;
	private final AModelRenderer tailpart;

	public static AnimationFile CLOUD_RAY_ANIMATIONS = new AnimationFile("animations/cloudray_animations.json");

	public ModelCloudRay() {
		textureWidth = 128;
		textureHeight = 128;

		cloudray = new AModelRenderer(this);
		cloudray.setRotationPoint(0.0F, 24.0F, -2.0F);
		

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 8.0F);
		cloudray.addChild(body);
		body.setTextureOffset(35, 35).addBox(-8.0F, -2.0F, -6.0F, 16.0F, 3.0F, 13.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -6.0F);
		body.addChild(head);
		head.setTextureOffset(0, 4).addBox(-7.0F, -2.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(3.0F, -2.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(48, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 2.0F, 8.0F, 0.0F, false);

		wing = new AModelRenderer(this);
		wing.setRotationPoint(-8.0F, 1.0F, -1.0F);
		body.addChild(wing);
		wing.setTextureOffset(0, 16).addBox(-16.0F, -0.5F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, false);

		wing2 = new AModelRenderer(this);
		wing2.setRotationPoint(8.0F, 1.0F, -1.0F);
		body.addChild(wing2);
		wing2.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, -0.25F, 6.5F);
		body.addChild(tail);
		tail.setTextureOffset(0, 48).addBox(-2.0F, -1.75F, 0.5F, 4.0F, 3.0F, 13.0F, 0.0F, false);

		tailpart = new AModelRenderer(this);
		tailpart.setRotationPoint(0.0F, 0.25F, 13.5F);
		tail.addChild(tailpart);
		tailpart.setTextureOffset(0, 32).addBox(-8.0F, -0.5F, 0.0F, 16.0F, 0.0F, 16.0F, 0.0F, false);
		initializeAnimator(CLOUD_RAY_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		cloudray.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}