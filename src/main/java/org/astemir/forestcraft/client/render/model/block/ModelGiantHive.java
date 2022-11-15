package org.astemir.forestcraft.client.render.model.block;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedTileEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;

public class ModelGiantHive extends AnimatedTileEntityModel {

	private final AModelRenderer block;
	private final AModelRenderer down;
	private final AModelRenderer middle;
	private final AModelRenderer a;
	private final AModelRenderer cube_r1;
	private final AModelRenderer b;
	private final AModelRenderer cube_r2;
	private final AModelRenderer cube_r3;
	private final AModelRenderer c;
	private final AModelRenderer cube_r4;

	public static AnimationFile GIANT_HIVE_ANIMATIONS = new AnimationFile("animations/giant_hive_animations.json");

	public ModelGiantHive() {
		textureWidth = 256;
		textureHeight = 256;

		block = new AModelRenderer(this);
		block.setRotationPoint(-1.0F, 14.0F, 0.0F);


		down = new AModelRenderer(this);
		down.setRotationPoint(-2.0F, 2.0F, 3.0F);
		block.addChild(down);
		down.setTextureOffset(0, 0).addBox(-11.0F, -16.0F, -17.0F, 28.0F, 24.0F, 28.0F, 0.0F, false);

		middle = new AModelRenderer(this);
		middle.setRotationPoint(0.0F, -20.0F, 0.0F);
		block.addChild(middle);
		middle.setTextureOffset(0, 52).addBox(-11.0F, -10.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);

		a = new AModelRenderer(this);
		a.setRotationPoint(4.0F, -13.0F, 12.0F);
		block.addChild(a);


		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(8.25F, 6.25F, 4.0F);
		a.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3927F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(96, 52).addBox(-14.0F, -14.0F, -8.0F, 14.0F, 15.0F, 12.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(-2.0F, -31.0F, 1.0F);
		block.addChild(b);


		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(-7.75F, -0.75F, 6.0F);
		b.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.3927F);
		cube_r2.setTextureOffset(84, 0).addBox(-1.0F, -4.0F, -14.0F, 15.0F, 12.0F, 14.0F, 0.0F, false);

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(-7.75F, -2.75F, 6.0F);
		b.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.3927F);
		cube_r3.setTextureOffset(0, 100).addBox(1.0F, -4.0F, -12.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(10.0F, -25.0F, -1.0F);
		block.addChild(c);


		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(-1.0F, -5.0F, 8.0F);
		c.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.7854F);
		cube_r4.setTextureOffset(76, 80).addBox(-4.0F, -8.0F, -17.0F, 19.0F, 17.0F, 20.0F, 0.0F, false);
		initializeAnimator(GIANT_HIVE_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		block.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}