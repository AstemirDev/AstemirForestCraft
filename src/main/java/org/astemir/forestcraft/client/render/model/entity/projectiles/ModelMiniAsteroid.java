// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports
//
//
//public class ModelMiniAsteroid extends EntityModel<Entity> {
//	private final ModelRenderer asteroid;
//
//	public ModelMiniAsteroid() {
//		textureWidth = 256;
//		textureHeight = 256;
//
//		asteroid = new ModelRenderer(this);
//		asteroid.setRotationPoint(-1.0F, 14.0F, 2.0F);
//		asteroid.setTextureOffset(0, 42).addBox(-10.0F, -10.0F, -10.0F, 20.0F, 20.0F, 20.0F, 0.0F, false);
//		asteroid.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -13.0F, 16.0F, 16.0F, 26.0F, 0.0F, false);
//		asteroid.setTextureOffset(64, 66).addBox(-13.0F, -8.0F, -8.0F, 26.0F, 16.0F, 16.0F, 0.0F, false);
//		asteroid.setTextureOffset(0, 82).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 26.0F, 16.0F, 0.0F, false);
//	}
//
//	@Override
//	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//		//previously the render function, render code was moved to a method below
//	}
//
//	@Override
//	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//		asteroid.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//	}
//
//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//}