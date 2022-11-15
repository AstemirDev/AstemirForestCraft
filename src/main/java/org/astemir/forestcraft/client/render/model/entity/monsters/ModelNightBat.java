package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.monsters.EntityNightBat;

public class ModelNightBat extends SegmentedModel<EntityNightBat> {

	private final ModelRenderer head;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer body;
	private final ModelRenderer rightWing;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWingTip;
	private final ModelRenderer leftWingTip;

	public ModelNightBat() {
		textureWidth = 64;
		textureHeight = 64;
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 35).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(44, 15).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);

		leftEar = new ModelRenderer(this);
		leftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(leftEar);
		leftEar.setTextureOffset(44, 20).addBox(-4.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		rightEar = new ModelRenderer(this);
		rightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(rightEar);
		rightEar.setTextureOffset(44, 20).addBox(1.0F, -6.0F, -2.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-3.0F, 4.0F, -3.0F, 6.0F, 12.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(24, 0).addBox(-5.0F, 16.0F, 0.0F, 10.0F, 16.0F, 1.0F, 0.0F, false);

		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(rightWing);
		rightWing.setTextureOffset(0, 18).addBox(2.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F, 0.0F, true);

		rightWingTip = new ModelRenderer(this);
		rightWingTip.setRotationPoint(12.0F, 1.0F, 1.5F);
		rightWing.addChild(rightWingTip);
		rightWingTip.setTextureOffset(24, 35).addBox(0.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F, 0.0F, true);

		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(leftWing);
		leftWing.setTextureOffset(0, 18).addBox(-12.0F, 1.0F, 1.5F, 10.0F, 16.0F, 1.0F, 0.0F, false);

		leftWingTip = new ModelRenderer(this);
		leftWingTip.setRotationPoint(-12.0F, 1.0F, 1.5F);
		leftWing.addChild(leftWingTip);
		leftWingTip.setTextureOffset(24, 35).addBox(-8.0F, 1.0F, 0.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(EntityNightBat entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleZ = 0.0F;
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.rotateAngleX = ((float)Math.PI / 4F) + MathHelper.cos(ageInTicks * 0.1F) * 0.15F;
		this.body.rotateAngleY = 0.0F;
		this.rightWing.rotateAngleY = MathHelper.cos(ageInTicks * 1.3F) * (float)Math.PI * 0.25F;
		this.leftWing.rotateAngleY = -this.rightWing.rotateAngleY;
		this.rightWingTip.rotateAngleY = this.rightWing.rotateAngleY * 0.5F;
		this.leftWingTip.rotateAngleY = -this.rightWing.rotateAngleY * 0.5F;
	}


	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.head, this.body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}