package org.astemir.forestcraft.client.render.model.block;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAncientRunestoneChest extends EntityModel<Entity> {

	public final ModelRenderer chest;
	public final ModelRenderer chest_up;
	public final ModelRenderer lock;

	public ModelAncientRunestoneChest() {
		textureWidth = 64;
		textureHeight = 64;

		chest = new ModelRenderer(this);
		chest.setRotationPoint(0.0F, 24.0F, 0.0F);
		chest.setTextureOffset(0, 24).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F, 0.0F, false);

		chest_up = new ModelRenderer(this);
		chest_up.setRotationPoint(0.0F, -8.0F, -8.0F);
		chest.addChild(chest_up);
		chest_up.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, 0.0F, 16.0F, 8.0F, 16.0F, 0.0F, false);

		lock = new ModelRenderer(this);

		lock.setRotationPoint(0.0F, 0.0F, 0.0F);
		chest.addChild(lock);
		lock.setTextureOffset(0, 8).addBox(-3.0F, -15.0F, 8.25F, 6.0F, 5.0F, 0.0F, 0.0F, false);
		lock.setTextureOffset(0, 0).addBox(-3.0F, -10.0F, 8.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		chest.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}