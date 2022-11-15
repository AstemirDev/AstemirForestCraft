package org.astemir.forestcraft.client.render.model.entity.monsters;



import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.animals.EntityGemGolem;

public class ModelGemGolem extends AnimatedEntityModel<EntityGemGolem> {

	private final AModelRenderer body;
	private final AModelRenderer cube_r1;
	private final AModelRenderer cube_r2;
	private final AModelRenderer cube_r3;
	private final AModelRenderer cube_r4;
	private final AModelRenderer cube_r5;
	private final AModelRenderer cube_r6;
	private final AModelRenderer head;
	private final AModelRenderer cube_r7;
	private final AModelRenderer left_arm;
	private final AModelRenderer cube_r8;
	private final AModelRenderer right_arm;
	private final AModelRenderer cube_r9;

	public static AnimationFile GEM_GOLEM_ANIMATIONS = new AnimationFile("animations/gem_golem_animations.json");


	public ModelGemGolem() {
		textureWidth = 64;
		textureHeight = 64;

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 13.0F, -3.0F);
		setRotationAngle(body, 0.1745F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-6.0F, -4.8843F, 1.7528F, 12.0F, 14.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(0, 38).addBox(-4.0F, -0.8843F, -2.2472F, 8.0F, 10.0F, 4.0F, 0.0F, false);

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(-3.0F, 7.1461F, 12.1001F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3927F, -0.3491F, 0.0F);
		cube_r1.setTextureOffset(29, 12).addBox(-4.1188F, -7.3052F, -4.0552F, 5.0F, 6.0F, 9.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(2.0F, 7.1461F, 12.1001F);
		body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.3927F, 0.3491F, 0.0F);
		cube_r2.setTextureOffset(0, 21).addBox(-1.8812F, -9.3052F, -4.0552F, 7.0F, 8.0F, 9.0F, 0.0F, false);

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(-5.5F, 5.1461F, 22.1001F);
		body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3927F, -0.3491F, 0.0F);
		cube_r3.setTextureOffset(0, 21).addBox(-3.1188F, -7.3052F, -4.0552F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(4.5F, 5.1461F, 22.1001F);
		body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3927F, 0.3491F, 0.0F);
		cube_r4.setTextureOffset(48, 27).addBox(1.1188F, -9.3052F, -4.0552F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(-5.5F, 6.1461F, 19.1001F);
		body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.3927F, -0.3491F, 0.0F);
		cube_r5.setTextureOffset(48, 10).addBox(-3.1188F, -8.3052F, -4.0552F, 3.0F, 4.0F, 4.0F, 0.0F, false);

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(4.5F, 6.1461F, 19.1001F);
		body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.3927F, 0.3491F, 0.0F);
		cube_r6.setTextureOffset(38, 0).addBox(-0.8812F, -10.3052F, -4.0552F, 5.0F, 6.0F, 4.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(1.0F, -0.631F, -0.7821F);
		body.addChild(head);
		

		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(3.0F, 0.0F, -7.0F);
		head.addChild(cube_r7);
		setRotationAngle(cube_r7, -0.1745F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(24, 30).addBox(-8.0F, -9.0F, 1.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		left_arm = new AModelRenderer(this);
		left_arm.setRotationPoint(-4.0F, 13.0F, -2.0F);
		

		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(-1.0F, 12.0F, -2.0F);
		left_arm.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.1745F, 0.0F, 0.1309F);
		cube_r8.setTextureOffset(38, 46).addBox(-3.0F, -13.0F, -3.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		right_arm = new AModelRenderer(this);
		right_arm.setRotationPoint(4.0F, 13.0F, -2.0F);
		

		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(4.0F, 12.0F, -2.0F);
		right_arm.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.1745F, 0.0F, -0.1309F);
		cube_r9.setTextureOffset(24, 46).addBox(-3.0F, -13.0F, -3.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
		initializeAnimator(GEM_GOLEM_ANIMATIONS);
	}


	@Override
	public void animate(EntityGemGolem entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.animate(entityIn, delta, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.setCustomRotation(new Vector3(headPitch * ((float)Math.PI / 180F),netHeadYaw * ((float)Math.PI / 180F),0));
		this.left_arm.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount,0,0));
		this.right_arm.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount,0,0));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
		right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}


}