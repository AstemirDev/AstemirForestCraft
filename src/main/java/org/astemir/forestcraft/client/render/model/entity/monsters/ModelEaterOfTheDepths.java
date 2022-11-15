package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.forestcraft.common.entities.monsters.EntityEaterOfTheDepths;

public class ModelEaterOfTheDepths extends AnimatedEntityModel<EntityEaterOfTheDepths> {

	private final AModelRenderer body;
	private final AModelRenderer body_fin_left;
	private final AModelRenderer body_r1;
	private final AModelRenderer body_fin_right;
	private final AModelRenderer body_r2;
	private final AModelRenderer tail;
	private final AModelRenderer tail_part;
	private final AModelRenderer tail_fin;
	private final AModelRenderer head;
	private final AModelRenderer jaw_up;
	private final AModelRenderer jaw_down;
	private final AModelRenderer jaw_left;
	private final AModelRenderer jaw_right;

	public ModelEaterOfTheDepths() {
		textureWidth = 256;
		textureHeight = 256;

		body = new AModelRenderer(this);
		body.setRotationPoint(0.5F, 16.0F, 14.0F);
		body.setTextureOffset(0, 0).addBox(-10.5F, -11.0F, 0.0F, 20.0F, 23.0F, 32.0F, 0.0F, false);
		body.setTextureOffset(64, 75).addBox(-0.5F, -34.0F, 0.0F, 0.0F, 23.0F, 32.0F, 0.0F, false);

		body_fin_left = new AModelRenderer(this);
		body_fin_left.setRotationPoint(-10.5F, 2.0F, 16.0F);
		body.addChild(body_fin_left);


		body_r1 = new AModelRenderer(this);
		body_r1.setRotationPoint(0.0F, -2.0F, -1.0F);
		body_fin_left.addChild(body_r1);
		setRotationAngle(body_r1, 0.0F, 0.0F, 0.1309F);
		body_r1.setTextureOffset(66, 23).addBox(0.3666F, -0.8929F, -15.0F, 0.0F, 23.0F, 32.0F, 0.0F, false);

		body_fin_right = new AModelRenderer(this);
		body_fin_right.setRotationPoint(9.5F, 3.0F, 16.0F);
		body.addChild(body_fin_right);


		body_r2 = new AModelRenderer(this);
		body_r2.setRotationPoint(2.0F, 15.0F, -13.0F);
		body_fin_right.addChild(body_r2);
		setRotationAngle(body_r2, 0.0F, 0.0F, -0.1309F);
		body_r2.setTextureOffset(0, 75).addBox(0.0F, -19.0F, -3.0F, 0.0F, 23.0F, 32.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 32.0F);
		body.addChild(tail);
		tail.setTextureOffset(0, 55).addBox(-8.5F, -8.0F, -1.0F, 16.0F, 18.0F, 34.0F, 0.0F, false);
		tail.setTextureOffset(48, 98).addBox(-0.5F, -31.0F, -3.0F, 0.0F, 23.0F, 32.0F, 0.0F, false);

		tail_part = new AModelRenderer(this);
		tail_part.setRotationPoint(0.0F, 1.0F, 33.0F);
		tail.addChild(tail_part);
		tail_part.setTextureOffset(128, 82).addBox(-5.5F, -5.1244F, -0.2679F, 10.0F, 11.0F, 18.0F, 0.0F, false);

		tail_fin = new AModelRenderer(this);
		tail_fin.setRotationPoint(0.0F, -0.1244F, 17.7321F);
		tail_part.addChild(tail_fin);
		tail_fin.setTextureOffset(82, 82).addBox(-9.5F, 0.0F, 0.0F, 18.0F, 0.0F, 18.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, 17.0F, 14.0F);
		head.setTextureOffset(113, 115).addBox(-7.0F, -7.0F, -32.0F, 14.0F, 14.0F, 15.0F, 0.0F, false);
		head.setTextureOffset(104, 0).addBox(-8.0F, -9.0F, -17.0F, 16.0F, 18.0F, 17.0F, 0.0F, false);
		head.setTextureOffset(104, 35).addBox(8.0F, -3.0F, -14.0F, 4.0F, 5.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(104, 35).addBox(-12.0F, -3.0F, -14.0F, 4.0F, 5.0F, 5.0F, 0.0F, false);

		jaw_up = new AModelRenderer(this);
		jaw_up.setRotationPoint(0.0F, -6.0F, -32.0F);
		head.addChild(jaw_up);
		jaw_up.setTextureOffset(0, 55).addBox(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 9.0F, 0.0F, false);

		jaw_down = new AModelRenderer(this);
		jaw_down.setRotationPoint(0.0F, 6.0F, -32.0F);
		head.addChild(jaw_down);
		jaw_down.setTextureOffset(0, 68).addBox(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 9.0F, 0.0F, false);

		jaw_left = new AModelRenderer(this);
		jaw_left.setRotationPoint(-5.0F, 0.0F, -33.0F);
		head.addChild(jaw_left);
		jaw_left.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -8.0F, 4.0F, 8.0F, 9.0F, 0.0F, false);

		jaw_right = new AModelRenderer(this);
		jaw_right.setRotationPoint(6.0F, 0.0F, -32.0F);
		head.addChild(jaw_right);
		jaw_right.setTextureOffset(72, 0).addBox(-1.0F, -4.0F, -9.0F, 4.0F, 8.0F, 9.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/eater_of_the_depths_animations.json"));
	}

	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}



}