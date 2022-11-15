package org.astemir.forestcraft.client.render.model.entity.projectiles;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityBakudanBall;

public class ModelBakudanBall extends EntityModel<EntityBakudanBall> {

	private final ModelRenderer ball;

	public ModelBakudanBall() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 64;
		ball = new ModelRenderer(this);
		ball.setRotationPoint(0.0F, 21.0F, 0.0F);
		ball.setTextureOffset(22, 22).addBox(-3.0F, -2.6303F, -2.999F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(EntityBakudanBall entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public ModelRenderer getBall() {
		return ball;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		ball.render(matrixStack, buffer, packedLight, packedOverlay);
	}

}