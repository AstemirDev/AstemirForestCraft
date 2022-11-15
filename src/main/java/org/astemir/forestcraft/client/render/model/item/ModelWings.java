package org.astemir.forestcraft.client.render.model.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.Entity;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedBipedModel;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.forestcraft.common.capabilities.CapabilityFlyingOnWings;
import org.astemir.forestcraft.registries.FCCapabilities;

public class ModelWings extends AnimatedBipedModel<Entity> {

	private final AModelRenderer wings;
	private final AModelRenderer wing;
	private final AModelRenderer base;
	private final AModelRenderer middle;
	private final AModelRenderer up;
	private final AModelRenderer wing2;
	private final AModelRenderer base2;
	private final AModelRenderer middle2;
	private final AModelRenderer up2;

	public static AnimationFile WINGS_ANIMATIONS = new AnimationFile("animations/wings_animations.json");
	private Animation FLAP = new Animation(0,"flap").time(0.4f).speed(8);


	public ModelWings() {
		textureWidth = 128;
		textureHeight = 128;

		wings = new AModelRenderer(this);
		wings.setRotationPoint(0.5F, 24.0F, 0.0F);


		wing = new AModelRenderer(this);
		wing.setRotationPoint(-2.0F, 0.0F, 1.0F);
		wings.addChild(wing);
		setRotationAngle(wing, 0.0F, 1.2217F, 0.0F);


		base = new AModelRenderer(this);
		base.setRotationPoint(0.0F, 0.0F, -1.0F);
		wing.addChild(base);
		base.setTextureOffset(4, 45).addBox(-16.0F, -7.0F, 1.0F, 16.0F, 7.0F, 0.0F, 0.0F, false);

		middle = new AModelRenderer(this);
		middle.setRotationPoint(-11.0F, -7.0F, 0.0F);
		base.addChild(middle);
		middle.setTextureOffset(1, 35).addBox(-13.0F, -10.0F, 1.0F, 19.0F, 10.0F, 0.0F, 0.0F, false);

		up = new AModelRenderer(this);
		up.setRotationPoint(-6.0F, -10.0F, 0.0F);
		middle.addChild(up);
		up.setTextureOffset(0, 26).addBox(-12.0F, -9.0F, 1.0F, 20.0F, 9.0F, 0.0F, 0.0F, false);

		wing2 = new AModelRenderer(this);
		wing2.setRotationPoint(1.0F, 0.0F, 1.0F);
		wings.addChild(wing2);
		setRotationAngle(wing2, 0.0F, -1.2217F, 0.0F);


		base2 = new AModelRenderer(this);
		base2.setRotationPoint(0.0F, 0.0F, -1.0F);
		wing2.addChild(base2);
		base2.setTextureOffset(4, 19).addBox(0.0F, -7.0F, 1.0F, 16.0F, 7.0F, 0.0F, 0.0F, false);

		middle2 = new AModelRenderer(this);
		middle2.setRotationPoint(11.0F, -7.0F, 0.0F);
		base2.addChild(middle2);
		middle2.setTextureOffset(1, 9).addBox(-6.0F, -10.0F, 1.0F, 19.0F, 10.0F, 0.0F, 0.0F, false);

		up2 = new AModelRenderer(this);
		up2.setRotationPoint(6.0F, -10.0F, 0.0F);
		middle2.addChild(up2);
		up2.setTextureOffset(0, 0).addBox(-8.0F, -9.0F, 1.0F, 20.0F, 9.0F, 0.0F, 0.0F, false);
		initializeAnimator(WINGS_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		super.render(matrixStack,buffer,packedLight,packedOverlay,red,green,blue,alpha);
		matrixStack.push();
		matrixStack.translate(0.025,0.25f,0.05);
		wings.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		matrixStack.pop();
	}


	@Override
	public void animate(Entity entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		CapabilityFlyingOnWings flyingOnWings = CapabilityUtils.getCapability(FCCapabilities.CAPABILITY_FLYING_ON_WINGS,entityIn);
		if (flyingOnWings != null) {
			if (flyingOnWings.getFlyingTicks() > 0) {
				animator.play(this,FLAP, delta);
			} else {
				for (AModelRenderer cube : getCubes()) {
					cube.reset();
				}
			}
		}
		wings.rotationPointX = bipedBody.rotationPointX;
		wings.rotationPointY = bipedBody.rotationPointY;
		wings.rotationPointZ = bipedBody.rotationPointZ;
		wings.customRotationX = bipedBody.rotateAngleX;
		wings.customRotationY = bipedBody.rotateAngleY;
		wings.customRotationZ = bipedBody.rotateAngleZ;
	}
}