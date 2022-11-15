package org.astemir.forestcraft.client.render.model.entity.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.animals.EntityCicada;

public class ModelCicada extends AnimatedEntityModel<EntityCicada> {


	private final AModelRenderer cicada;
	private final AModelRenderer elytra_left;
	private final AModelRenderer wing_r1;
	private final AModelRenderer wing_left;
	private final AModelRenderer wing_r2;
	private final AModelRenderer wing_right;
	private final AModelRenderer wing_r3;
	private final AModelRenderer elytra_right;
	private final AModelRenderer wing_r4;
	private final AModelRenderer body;
	private final AModelRenderer tail;
	private final AModelRenderer head;

	public static AnimationFile CICADA_ANIMATIONS = new AnimationFile("animations/cicada_animations.json");

	public ModelCicada() {
		textureWidth = 64;
		textureHeight = 64;

		cicada = new AModelRenderer(this);
		cicada.setRotationPoint(0.0F, 22.0F, -3.5F);
		

		elytra_left = new AModelRenderer(this);
		elytra_left.setRotationPoint(-2.0F, -0.5F, 5.0F);
		cicada.addChild(elytra_left);
		

		wing_r1 = new AModelRenderer(this);
		wing_r1.setRotationPoint(1.75F, -0.25F, -0.5F);
		elytra_left.addChild(wing_r1);
		setRotationAngle(wing_r1, 0.0F, 0.3491F, 0.0F);
		wing_r1.setTextureOffset(11, 18).addBox(-6.4397F, 0.0F, -2.842F, 7.0F, 0.0F, 5.0F, 0.0F, false);

		wing_left = new AModelRenderer(this);
		wing_left.setRotationPoint(-2.0F, -1.0F, 5.0F);
		cicada.addChild(wing_left);
		

		wing_r2 = new AModelRenderer(this);
		wing_r2.setRotationPoint(0.0F, 0.0F, -1.5F);
		wing_left.addChild(wing_r2);
		setRotationAngle(wing_r2, 0.0F, 0.3491F, 0.0F);
		wing_r2.setTextureOffset(11, 0).addBox(-6.4397F, 0.0F, -2.842F, 7.0F, 0.0F, 5.0F, 0.0F, false);

		wing_right = new AModelRenderer(this);
		wing_right.setRotationPoint(2.0F, -1.0F, 5.0F);
		cicada.addChild(wing_right);
		

		wing_r3 = new AModelRenderer(this);
		wing_r3.setRotationPoint(1.0F, 0.0F, -1.5F);
		wing_right.addChild(wing_r3);
		setRotationAngle(wing_r3, 0.0F, -0.3491F, 0.0F);
		wing_r3.setTextureOffset(0, 9).addBox(-1.5F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, 0.0F, false);

		elytra_right = new AModelRenderer(this);
		elytra_right.setRotationPoint(2.0F, -0.5F, 5.0F);
		cicada.addChild(elytra_right);
		

		wing_r4 = new AModelRenderer(this);
		wing_r4.setRotationPoint(-1.75F, -0.25F, -0.5F);
		elytra_right.addChild(wing_r4);
		setRotationAngle(wing_r4, 0.0F, -0.3491F, 0.0F);
		wing_r4.setTextureOffset(11, 18).addBox(-0.5603F, 0.0F, -2.842F, 7.0F, 0.0F, 5.0F, 0.0F, true);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 1.0F, 4.0F);
		cicada.addChild(body);
		body.setTextureOffset(0, 0).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, 1.0F, 7.0F);
		cicada.addChild(tail);
		tail.setTextureOffset(0, 14).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		tail.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 1.0F);
		cicada.addChild(head);
		head.setTextureOffset(12, 14).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 4).addBox(-2.5F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 2).addBox(1.5F, 0.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		initializeAnimator(CICADA_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		cicada.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}