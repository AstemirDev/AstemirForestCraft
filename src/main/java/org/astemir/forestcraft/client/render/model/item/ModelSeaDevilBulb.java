package org.astemir.forestcraft.client.render.model.item;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedItemModel;

public class ModelSeaDevilBulb extends AnimatedItemModel {
	
	private final AModelRenderer bulb;
	private final AModelRenderer cube_r1;
	private final AModelRenderer bulb_a;
	private final AModelRenderer cube_r2;
	private final AModelRenderer light;

	public ModelSeaDevilBulb() {
		textureWidth = 128;
		textureHeight = 128;

		bulb = new AModelRenderer(this);
		bulb.setRotationPoint(0.0F, 23.0F, -0.25F);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 14.0F, 5.25F);
		bulb.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2182F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(4, 0).addBox(-1.0F, -22.0F, -2.0F, 2.0F, 8.0F, 0.0F, 0.0F, false);

		bulb_a = new AModelRenderer(this);
		bulb_a.setRotationPoint(0.0F, -7.0F, -1.5F);
		bulb.addChild(bulb_a);
		setRotationAngle(bulb_a, -0.6109F, 0.0F, 0.0F);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bulb_a.addChild(cube_r2);
		setRotationAngle(cube_r2, 1.1345F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 0).addBox(-1.0F, -8.0368F, 0.0448F, 2.0F, 8.0F, 0.0F, 0.0F, false);

		light = new AModelRenderer(this);
		light.setRotationPoint(-0.25F, -3.0F, -6.25F);
		bulb_a.addChild(light);
		light.setTextureOffset(0, 22).addBox(-0.75F, -1.0368F, -4.2052F, 2.0F, 2.0F, 4.0F, 0.0F, false);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bulb.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}