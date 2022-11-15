package org.astemir.forestcraft.client.render.model.item;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.astemir.api.AstemirAPI;
import org.astemir.forestcraft.client.event.ClientEvents;

public class ModelElegantBalloon extends Model {

	private final ModelRenderer rope;
	private final ModelRenderer up_cube;

	public ModelElegantBalloon() {
		super(RenderType::getEntityCutoutNoCull);
		textureWidth = 64;
		textureHeight = 64;

		rope = new ModelRenderer(this);
		rope.setRotationPoint(0.0F, 24.0F, 0.0F);
		rope.setTextureOffset(14, 20).addBox(-3.5F, -21.0F, 0.0F, 6.0F, 21.0F, 0.0F, 0.0F, false);
		rope.setTextureOffset(0, 13).addBox(-0.5F, -21.0F, -3.5F, 0.0F, 21.0F, 7.0F, 0.0F, false);

		up_cube = new ModelRenderer(this);
		up_cube.setRotationPoint(0.0F, 24.0F, 0.0F);
		up_cube.setTextureOffset(0, 0).addBox(-5.0F, -31.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
	}


	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight,int packedOverlay, float red, float green, float blue, float alpha){
		rope.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.push();
		matrixStack.translate(0,0.25-Math.sin(AstemirAPI.CLIENT.TICKS/10)/20,0);
		up_cube.render(matrixStack, buffer, packedLight, packedOverlay);
		matrixStack.pop();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}