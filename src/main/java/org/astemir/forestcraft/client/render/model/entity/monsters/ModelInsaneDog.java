package org.astemir.forestcraft.client.render.model.entity.monsters;// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import org.astemir.forestcraft.common.entities.monsters.EntityInsaneDog;

public class ModelInsaneDog extends EntityModel<EntityInsaneDog> {

	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer head_r2;
	private final ModelRenderer down_jaw;
	private final ModelRenderer up_jaw;
	private final ModelRenderer body;
	private final ModelRenderer upperBody;
	private final ModelRenderer leg0;
	private final ModelRenderer leg0_r1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg1_r1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg2_r1;
	private final ModelRenderer leg3;
	private final ModelRenderer leg3_r1;
	private final ModelRenderer tail;
	private final ModelRenderer tail_r1;

	public ModelInsaneDog(){
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 13.5F, -7.0F);
		head.setTextureOffset(22, 22).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(1.0F, 10.5F, 7.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 0.0F, -0.1309F);
		head_r1.setTextureOffset(0, 0).addBox(-2.0F, -16.5F, -7.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		head_r2 = new ModelRenderer(this);
		head_r2.setRotationPoint(-3.0F, 10.25F, 7.0F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 0.0F, 0.0F, 0.1309F);
		head_r2.setTextureOffset(0, 13).addBox(2.0F, -16.5F, -7.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		down_jaw = new ModelRenderer(this);
		down_jaw.setRotationPoint(-1.0F, 1.5F, -3.0F);
		head.addChild(down_jaw);
		down_jaw.setTextureOffset(26, 14).addBox(-0.5F, -0.52F, -5.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		up_jaw = new ModelRenderer(this);
		up_jaw.setRotationPoint(0.0F, 0.5F, -3.0F);
		head.addChild(up_jaw);
		up_jaw.setTextureOffset(23, 0).addBox(-1.5F, -1.52F, -5.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 14.0F, 2.0F);
		body.setTextureOffset(0, 13).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 4.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(20, 8).addBox(1.0F, -7.0F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		upperBody = new ModelRenderer(this);
		upperBody.setRotationPoint(1.0F, 14.0F, 2.0F);
		upperBody.setTextureOffset(0, 0).addBox(-4.0F, -3.0F, -7.0F, 8.0F, 6.0F, 7.0F, 0.0F, false);
		upperBody.setTextureOffset(0, 18).addBox(0.0F, -9.0F, -7.0F, 0.0F, 6.0F, 7.0F, 0.0F, false);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(2.5F, 16.0F, 6.0F);


		leg0_r1 = new ModelRenderer(this);
		leg0_r1.setRotationPoint(-5.5F, 9.0F, -3.0F);
		leg0.addChild(leg0_r1);
		setRotationAngle(leg0_r1, 0.1745F, 0.7854F, 0.0F);
		leg0_r1.setTextureOffset(28, 34).addBox(1.5F, -8.0F, 6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-0.5F, 16.0F, 6.0F);


		leg1_r1 = new ModelRenderer(this);
		leg1_r1.setRotationPoint(4.0F, 9.0F, -4.5F);
		leg1.addChild(leg1_r1);
		setRotationAngle(leg1_r1, 0.1745F, -0.7854F, 0.0F);
		leg1_r1.setTextureOffset(20, 34).addBox(-1.5F, -8.0F, 6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.5F, 16.0F, -3.0F);


		leg2_r1 = new ModelRenderer(this);
		leg2_r1.setRotationPoint(-3.5F, 8.5F, 0.0F);
		leg2.addChild(leg2_r1);
		setRotationAngle(leg2_r1, -0.1745F, -0.7854F, 0.0F);
		leg2_r1.setTextureOffset(8, 33).addBox(1.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-1.5F, 16.0F, -3.0F);


		leg3_r1 = new ModelRenderer(this);
		leg3_r1.setRotationPoint(2.0F, 8.5F, 1.5F);
		leg3.addChild(leg3_r1);
		setRotationAngle(leg3_r1, -0.1745F, 0.7854F, 0.0F);
		leg3_r1.setTextureOffset(0, 31).addBox(-1.5F, -8.0F, -5.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(1.0F, 13.0F, 6.0F);


		tail_r1 = new ModelRenderer(this);
		tail_r1.setRotationPoint(-1.0F, 13.0F, 7.0F);
		tail.addChild(tail_r1);
		setRotationAngle(tail_r1, 1.0908F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(14, 25).addBox(0.0F, -12.0F, 7.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(EntityInsaneDog entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void setLivingAnimations(EntityInsaneDog entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		if (entityIn.getLazerTicks() <= 0) {
			if (entityIn.getAttackTicks() > 0) {
				up_jaw.rotateAngleX = -(float) Math.sin(((float)10-entityIn.getAttackTicks())/2.9f);
				down_jaw.rotateAngleX = (float) Math.sin(((float)10-entityIn.getAttackTicks())/2.9f);
			} else {
				up_jaw.rotateAngleX = 0;
				down_jaw.rotateAngleX = 0;
			}
		}else{
			up_jaw.rotateAngleX = -45;
			down_jaw.rotateAngleX = 45;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		upperBody.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}