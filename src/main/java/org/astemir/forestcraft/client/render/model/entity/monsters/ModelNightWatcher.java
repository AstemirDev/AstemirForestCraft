package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.monsters.EntityNightWatcher;

public class ModelNightWatcher extends EntityModel<EntityNightWatcher> {

	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer tentacle;
	private final ModelRenderer body_r2;
	private final ModelRenderer tentacle_part;
	private final ModelRenderer body_r3;
	private final ModelRenderer tentacle2;
	private final ModelRenderer body_r4;
	private final ModelRenderer tentacle_part2;
	private final ModelRenderer body_r5;
	private final ModelRenderer tentacle3;
	private final ModelRenderer body_r6;
	private final ModelRenderer tentacle_part3;
	private final ModelRenderer body_r7;
	private final ModelRenderer front_tentacle;
	private final ModelRenderer body_r8;
	private final ModelRenderer front_tentacle_part;
	private final ModelRenderer body_r9;
	private final ModelRenderer tentacle4;
	private final ModelRenderer body_r10;
	private final ModelRenderer tentacle_part4;
	private final ModelRenderer body_r11;
	private final ModelRenderer back_tentacle;
	private final ModelRenderer body_r12;
	private final ModelRenderer back_tentacle_part;
	private final ModelRenderer body_r13;

	public ModelNightWatcher() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 4.0F);
		body.setTextureOffset(0, 41).addBox(-8.0F, -19.0F, -7.0F, 16.0F, 19.0F, 13.0F, 0.0F, false);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 43.0F, -15.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, -0.2182F, 0.0F, 0.0F);
		body_r1.setTextureOffset(58, 41).addBox(-7.0F, -47.0F, -1.0F, 14.0F, 5.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -17.0F, 1.0F);
		body.addChild(head);
		head.setTextureOffset(58, 58).addBox(-8.0F, -19.0F, -21.0F, 15.0F, 15.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-12.0F, -21.0F, -13.0F, 23.0F, 19.0F, 22.0F, 0.0F, false);

		tentacle = new ModelRenderer(this);
		tentacle.setRotationPoint(-6.0F, 1.0F, -3.0F);
		body.addChild(tentacle);
		

		body_r2 = new ModelRenderer(this);
		body_r2.setRotationPoint(-7.0F, 43.0F, -17.0F);
		tentacle.addChild(body_r2);
		setRotationAngle(body_r2, -0.2182F, 0.0F, 0.2618F);
		body_r2.setTextureOffset(0, 103).addBox(-8.0F, -47.0F, 3.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		tentacle_part = new ModelRenderer(this);
		tentacle_part.setRotationPoint(-6.0F, 20.0F, -6.0F);
		tentacle.addChild(tentacle_part);
		

		body_r3 = new ModelRenderer(this);
		body_r3.setRotationPoint(-6.0F, 42.0F, -15.5F);
		tentacle_part.addChild(body_r3);
		setRotationAngle(body_r3, -0.2182F, 0.0F, 0.2618F);
		body_r3.setTextureOffset(80, 111).addBox(-7.0F, -47.0F, 4.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);

		tentacle2 = new ModelRenderer(this);
		tentacle2.setRotationPoint(7.0F, 0.0F, -4.0F);
		body.addChild(tentacle2);
		

		body_r4 = new ModelRenderer(this);
		body_r4.setRotationPoint(6.0F, 44.0F, -16.0F);
		tentacle2.addChild(body_r4);
		setRotationAngle(body_r4, -0.2182F, 0.0F, -0.2618F);
		body_r4.setTextureOffset(90, 0).addBox(2.0F, -47.0F, 3.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		tentacle_part2 = new ModelRenderer(this);
		tentacle_part2.setRotationPoint(4.0F, 19.0F, -5.0F);
		tentacle2.addChild(tentacle_part2);
		

		body_r5 = new ModelRenderer(this);
		body_r5.setRotationPoint(7.0F, 44.0F, -15.5F);
		tentacle_part2.addChild(body_r5);
		setRotationAngle(body_r5, -0.2182F, 0.0F, -0.2618F);
		body_r5.setTextureOffset(62, 111).addBox(3.0F, -47.0F, 4.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);

		tentacle3 = new ModelRenderer(this);
		tentacle3.setRotationPoint(7.0F, 0.0F, 3.0F);
		body.addChild(tentacle3);
		

		body_r6 = new ModelRenderer(this);
		body_r6.setRotationPoint(6.0F, 44.0F, 16.0F);
		tentacle3.addChild(body_r6);
		setRotationAngle(body_r6, 0.2182F, 0.0F, -0.2618F);
		body_r6.setTextureOffset(78, 81).addBox(2.0F, -47.0F, -10.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		tentacle_part3 = new ModelRenderer(this);
		tentacle_part3.setRotationPoint(5.0F, 19.0F, 4.0F);
		tentacle3.addChild(tentacle_part3);
		

		body_r7 = new ModelRenderer(this);
		body_r7.setRotationPoint(6.0F, 44.0F, 16.5F);
		tentacle_part3.addChild(body_r7);
		setRotationAngle(body_r7, 0.2182F, 0.0F, -0.2618F);
		body_r7.setTextureOffset(44, 111).addBox(3.0F, -47.0F, -9.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);

		front_tentacle = new ModelRenderer(this);
		front_tentacle.setRotationPoint(0.0F, 2.0F, -5.0F);
		body.addChild(front_tentacle);
		

		body_r8 = new ModelRenderer(this);
		body_r8.setRotationPoint(5.0F, 46.0F, -5.0F);
		front_tentacle.addChild(body_r8);
		setRotationAngle(body_r8, -0.2618F, 0.0F, 0.0F);
		body_r8.setTextureOffset(0, 73).addBox(-8.0F, -47.0F, -10.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		front_tentacle_part = new ModelRenderer(this);
		front_tentacle_part.setRotationPoint(0.0F, 18.0F, -5.0F);
		front_tentacle.addChild(front_tentacle_part);
		

		body_r9 = new ModelRenderer(this);
		body_r9.setRotationPoint(5.0F, 47.0F, -5.5F);
		front_tentacle_part.addChild(body_r9);
		setRotationAngle(body_r9, -0.2618F, 0.0F, 0.0F);
		body_r9.setTextureOffset(26, 103).addBox(-7.0F, -47.0F, -9.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);

		tentacle4 = new ModelRenderer(this);
		tentacle4.setRotationPoint(-7.0F, 1.0F, 3.0F);
		body.addChild(tentacle4);
		

		body_r10 = new ModelRenderer(this);
		body_r10.setRotationPoint(-6.0F, 43.0F, 16.0F);
		tentacle4.addChild(body_r10);
		setRotationAngle(body_r10, 0.2182F, 0.0F, 0.2618F);
		body_r10.setTextureOffset(52, 81).addBox(-8.0F, -47.0F, -10.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		tentacle_part4 = new ModelRenderer(this);
		tentacle_part4.setRotationPoint(-5.0F, 18.0F, 5.0F);
		tentacle4.addChild(tentacle_part4);
		

		body_r11 = new ModelRenderer(this);
		body_r11.setRotationPoint(-6.0F, 44.0F, 15.5F);
		tentacle_part4.addChild(body_r11);
		setRotationAngle(body_r11, 0.2182F, 0.0F, 0.2618F);
		body_r11.setTextureOffset(104, 51).addBox(-7.0F, -47.0F, -9.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);

		back_tentacle = new ModelRenderer(this);
		back_tentacle.setRotationPoint(0.0F, -1.0F, 5.0F);
		body.addChild(back_tentacle);
		

		body_r12 = new ModelRenderer(this);
		body_r12.setRotationPoint(5.0F, 45.0F, 17.0F);
		back_tentacle.addChild(body_r12);
		setRotationAngle(body_r12, 0.2618F, 0.0F, 0.0F);
		body_r12.setTextureOffset(26, 73).addBox(-8.0F, -47.0F, -10.0F, 6.0F, 23.0F, 7.0F, 0.0F, false);

		back_tentacle_part = new ModelRenderer(this);
		back_tentacle_part.setRotationPoint(0.0F, 20.0F, 5.0F);
		back_tentacle.addChild(back_tentacle_part);
		

		body_r13 = new ModelRenderer(this);
		body_r13.setRotationPoint(5.0F, 44.0F, 17.5F);
		back_tentacle_part.addChild(body_r13);
		setRotationAngle(body_r13, 0.2618F, 0.0F, 0.0F);
		body_r13.setTextureOffset(104, 104).addBox(-7.0F, -47.0F, -9.0F, 4.0F, 23.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(EntityNightWatcher entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

		tentacle.rotateAngleZ = -MathHelper.sin(ageInTicks/5)/3;
		tentacle_part.rotateAngleZ = -MathHelper.sin(ageInTicks/8)/6;

		tentacle4.rotateAngleZ = -MathHelper.sin(ageInTicks/5.5f)/3.5f;
		tentacle_part4.rotateAngleZ = -MathHelper.sin(ageInTicks/8.5f)/6.5f;

		tentacle2.rotateAngleZ = MathHelper.sin(ageInTicks/4.5f)/2.5f;
		tentacle_part2.rotateAngleZ = MathHelper.sin(ageInTicks/7.5f)/5.5f;

		tentacle3.rotateAngleZ = MathHelper.sin(ageInTicks/5)/3;
		tentacle_part3.rotateAngleZ = MathHelper.sin(ageInTicks/8)/6;

		front_tentacle.rotateAngleX = -MathHelper.sin(ageInTicks/6.5f)/4.5f;
		front_tentacle_part.rotateAngleX = -MathHelper.sin(ageInTicks/9.5f)/7.5f;

		back_tentacle.rotateAngleX = MathHelper.sin(ageInTicks/6)/4;
		back_tentacle_part.rotateAngleX= MathHelper.sin(ageInTicks/9)/7;
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}