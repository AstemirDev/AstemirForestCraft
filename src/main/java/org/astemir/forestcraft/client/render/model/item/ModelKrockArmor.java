package org.astemir.forestcraft.client.render.model.item;

import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedBipedModel;

public class ModelKrockArmor extends AnimatedBipedModel {

	private final AModelRenderer Head;
	private final AModelRenderer Body;
	private final AModelRenderer RightArm;
	private final AModelRenderer LeftArm;
	private final AModelRenderer RightLeg;
	public final AModelRenderer RightFeet;
	private final AModelRenderer LeftLeg;
	public final AModelRenderer LeftFeet;

	public ModelKrockArmor() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new AModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.setTextureOffset(1, 70).addBox(-4.0F, -8.0F, -4.0F, 9.0F, 9.0F, 8.0F, 0.0F, false);

		Body = new AModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.setTextureOffset(0, 87).addBox(-3.5F, 0.0F, -2.5F, 8.0F, 13.0F, 5.0F, 0.0F, false);

		RightArm = new AModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		RightArm.setTextureOffset(36, 69).addBox(-3.5F, -2.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		LeftArm = new AModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		LeftArm.setTextureOffset(36, 69).addBox(-1.0F, -2.0F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		RightLeg = new AModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		RightLeg.setTextureOffset(0, 105).addBox(-2.0F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);

		RightFeet = new AModelRenderer(this);
		RightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightLeg.addChild(RightFeet);
		RightFeet.setTextureOffset(0, 113).addBox(-2.0F, 9.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		LeftLeg = new AModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		LeftLeg.setTextureOffset(0, 105).addBox(-2.0F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);

		LeftFeet = new AModelRenderer(this);
		LeftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.addChild(LeftFeet);
		LeftFeet.setTextureOffset(0, 113).addBox(-2.0F, 9.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

	}

}