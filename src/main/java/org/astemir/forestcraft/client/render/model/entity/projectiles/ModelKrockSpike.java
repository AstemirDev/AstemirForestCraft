package org.astemir.forestcraft.client.render.model.entity.projectiles;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelKrockSpike extends EntityModel<Entity> {
	private final ModelRenderer spike;
	private final ModelRenderer cube_r1;

	public ModelKrockSpike() {
		textureWidth = 128;
		textureHeight = 128;

		spike = new ModelRenderer(this);
		spike.setRotationPoint(-1.0F, 25.0F, 3.0F);
		setRotationAngle(spike, 0.0F, 3.1416F, 0.0F);
		spike.setTextureOffset(0, 57).addBox(-1.0F, -6.0F, 1.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		spike.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 1.5708F);
		cube_r1.setTextureOffset(0, 57).addBox(-3.5F, -1.5F, 1.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		spike.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}


}