package org.astemir.forestcraft.client.render.model.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.tileentity.TileEntity;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.*;

public class ModelCosmicBeaconModel extends AnimatedTileEntityModel {

	private final AModelRenderer cosmic_beacon;
	private final AModelRenderer base;
	private final AModelRenderer trapdoor;

	public static AnimationFile COSMIC_BEACON_ANIMATIONS = new AnimationFile("animations/cosmic_beacon_animations.json");


	public ModelCosmicBeaconModel() {
		textureWidth = 128;
		textureHeight = 128;

		cosmic_beacon = new AModelRenderer(this);
		cosmic_beacon.setRotationPoint(0.0F, 24.0F, 0.0F);
		cosmic_beacon.setTextureOffset(0, 51).addBox(8.0F, -6.0F, -8.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(48, 32).addBox(8.0F, -6.0F, 4.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(48, 32).addBox(-11.0F, -6.0F, 4.0F, 3.0F, 6.0F, 4.0F, 0.0F, true);
		cosmic_beacon.setTextureOffset(48, 32).addBox(-11.0F, -6.0F, -8.0F, 3.0F, 6.0F, 4.0F, 0.0F, true);
		cosmic_beacon.setTextureOffset(56, 51).addBox(4.0F, -6.0F, -11.0F, 4.0F, 6.0F, 3.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(42, 51).addBox(4.0F, -6.0F, 8.0F, 4.0F, 6.0F, 3.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(42, 51).addBox(-8.0F, -6.0F, 8.0F, 4.0F, 6.0F, 3.0F, 0.0F, true);
		cosmic_beacon.setTextureOffset(56, 51).addBox(-8.0F, -6.0F, -11.0F, 4.0F, 6.0F, 3.0F, 0.0F, true);
		cosmic_beacon.setTextureOffset(59, 39).addBox(8.0F, -3.0F, -11.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(48, 42).addBox(8.0F, -3.0F, 8.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		cosmic_beacon.setTextureOffset(48, 42).addBox(-11.0F, -3.0F, 8.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);
		cosmic_beacon.setTextureOffset(59, 39).addBox(-11.0F, -3.0F, -11.0F, 3.0F, 3.0F, 3.0F, 0.0F, true);

		base = new AModelRenderer(this);
		base.setRotationPoint(0.0F, -8.0F, 0.0F);
		cosmic_beacon.addChild(base);
		base.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		trapdoor = new AModelRenderer(this);
		trapdoor.setRotationPoint(0.0F, -8.0F, 8.0F);
		base.addChild(trapdoor);
		trapdoor.setTextureOffset(0, 32).addBox(-8.0F, -3.0F, -16.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		initializeAnimator(COSMIC_BEACON_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		cosmic_beacon.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(TileEntity tileEntity, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}