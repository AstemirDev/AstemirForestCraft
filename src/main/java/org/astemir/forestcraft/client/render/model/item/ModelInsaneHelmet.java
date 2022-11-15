package org.astemir.forestcraft.client.render.model.item;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3f;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedBipedModel;

public class ModelInsaneHelmet extends AnimatedBipedModel<Entity> {

	private final AModelRenderer insane_helmet;
	private final AModelRenderer ear_r1;
	private final AModelRenderer ear_r2;

	public ModelInsaneHelmet() {
		textureWidth = 64;
		textureHeight = 64;

		insane_helmet = new AModelRenderer(this);
		insane_helmet.setRotationPoint(0.0F, 0, 0.0F);
		insane_helmet.setTextureOffset(30, 27).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);
		insane_helmet.setTextureOffset(12, 43).addBox(-5.0F, -2.0F, -5.0F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		insane_helmet.setTextureOffset(0, 38).addBox(4.0F, -2.0F, -5.0F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		insane_helmet.setTextureOffset(0, 27).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		insane_helmet.setTextureOffset(24, 38).addBox(-3.0F, -3.5F, -6.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		insane_helmet.setTextureOffset(0, 58).addBox(-4.0F, -2.0F, 4.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);

		ear_r1 = new AModelRenderer(this);
		ear_r1.setRotationPoint(0.0F, 31.0F, 0.0F);
		insane_helmet.addChild(ear_r1);
		setRotationAngle(ear_r1, 0.0F, 0.0F, 0.1309F);
		ear_r1.setTextureOffset(0, 31).addBox(-2.0F, -36.75F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		ear_r2 = new AModelRenderer(this);
		ear_r2.setRotationPoint(0.0F, 31.0F, 0.0F);
		insane_helmet.addChild(ear_r2);
		setRotationAngle(ear_r2, 0.0F, 0.0F, -0.1309F);
		ear_r2.setTextureOffset(0, 27).addBox(0.0F, -36.75F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.ZP.rotation(this.bipedHead.rotateAngleZ));
		matrixStackIn.rotate(Vector3f.YP.rotation(this.bipedHead.rotateAngleY));
		matrixStackIn.rotate(Vector3f.XP.rotation(this.bipedHead.rotateAngleX));
		matrixStackIn.translate(0,-0.3f,0);
		insane_helmet.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn,red,green,blue,alpha);
		matrixStackIn.pop();
	}

	@Override
	public void animate(Entity entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		insane_helmet.rotationPointX = bipedHead.rotationPointX;
		insane_helmet.rotationPointY = bipedHead.rotationPointY;
		insane_helmet.rotationPointZ = bipedHead.rotationPointZ;
	}
}