package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityBeeQueen;


@OnlyIn(Dist.CLIENT)
public class ModelBeeQueen extends AnimatedEntityModel<EntityBeeQueen> {

	private final AModelRenderer BeeQueen;
	private final AModelRenderer Body;
	private final AModelRenderer MiddlePart;
	private final AModelRenderer BottomPart;
	private final AModelRenderer cube_r2_r1;
	private final AModelRenderer cube_r1_r1;
	private final AModelRenderer BottomBelly;
	private final AModelRenderer cube_r3_r1;
	private final AModelRenderer MiddleBelly;
	private final AModelRenderer cube_r4_r1;
	private final AModelRenderer UpperPart;
	private final AModelRenderer RightSecondArm;
	private final AModelRenderer cube_r6_r1;
	private final AModelRenderer cube_r5_r1;
	private final AModelRenderer LeftSecondArm;
	private final AModelRenderer cube_r8_r1;
	private final AModelRenderer cube_r7_r1;
	private final AModelRenderer LeftWing;
	private final AModelRenderer cube_r9_r1;
	private final AModelRenderer RightWing;
	private final AModelRenderer cube_r10_r1;
	private final AModelRenderer LeftArm;
	private final AModelRenderer cube_r12_r1;
	private final AModelRenderer cube_r11_r1;
	private final AModelRenderer RightArm;
	private final AModelRenderer cube_r13;
	private final AModelRenderer cube_r14;
	private final AModelRenderer Neck;
	private final AModelRenderer cube_r16_r1;
	private final AModelRenderer cube_r15_r1;
	private final AModelRenderer Head;
	private final AModelRenderer cube_r22_r1;
	private final AModelRenderer cube_r21_r1;
	private final AModelRenderer cube_r20_r1;
	private final AModelRenderer cube_r19_r1;
	private final AModelRenderer cube_r18_r1;
	private final AModelRenderer cube_r17_r1;
	public static AnimationFile BEEQUEEN_ANIMATIONS = new AnimationFile("animations/beequeen_animations.json");

	public ModelBeeQueen() {
		textureWidth = 256;
		textureHeight = 256;
		BeeQueen = new AModelRenderer(this);
		BeeQueen.setRotationPoint(0.0F, -11.0F, 10.0F);
		Body = new AModelRenderer(this);
		Body.setRotationPoint(0.0F, -8.0F, 7.0F);
		BeeQueen.addChild(Body);
		MiddlePart = new AModelRenderer(this);
		MiddlePart.setRotationPoint(0.0F, 0.0F, -5.0F);
		Body.addChild(MiddlePart);
		BottomPart = new AModelRenderer(this);
		BottomPart.setRotationPoint(0.0F, 43.0F, -12.0F);
		MiddlePart.addChild(BottomPart);
		cube_r2_r1 = new AModelRenderer(this);
		cube_r2_r1.setRotationPoint(0.0F, 4.5061F, -15.1214F);
		BottomPart.addChild(cube_r2_r1);
		setRotationAngle(cube_r2_r1, -0.7854F, 0.0F, 0.0F);
		cube_r2_r1.setTextureOffset(98, 109).addBox(-5.5F, -18.0F, -15.0F, 13.0F, 3.0F, 13.0F, 0.0F, false);
		cube_r1_r1 = new AModelRenderer(this);
		cube_r1_r1.setRotationPoint(0.0F, 4.1421F, -10.0F);
		BottomPart.addChild(cube_r1_r1);
		setRotationAngle(cube_r1_r1, -0.7854F, 0.0F, 0.0F);
		cube_r1_r1.setTextureOffset(72, 57).addBox(-2.5F, -12.0F, -12.0F, 7.0F, 11.0F, 0.0F, 0.0F, false);
		BottomBelly = new AModelRenderer(this);
		BottomBelly.setRotationPoint(0.0F, 0.0F, 0.0F);
		BottomPart.addChild(BottomBelly);
		cube_r3_r1 = new AModelRenderer(this);
		cube_r3_r1.setRotationPoint(0.0F, -16.0797F, -24.8198F);
		BottomBelly.addChild(cube_r3_r1);
		setRotationAngle(cube_r3_r1, -0.7854F, 0.0F, 0.0F);
		cube_r3_r1.setTextureOffset(0, 0).addBox(-14.0F, -40.0F, -1.0F, 30.0F, 30.0F, 27.0F, 0.0F, false);
		MiddleBelly = new AModelRenderer(this);
		MiddleBelly.setRotationPoint(0.0F, 0.0F, 0.0F);
		MiddlePart.addChild(MiddleBelly);
		cube_r4_r1 = new AModelRenderer(this);
		cube_r4_r1.setRotationPoint(0.0F, 36.1304F, -28.5357F);
		MiddleBelly.addChild(cube_r4_r1);
		setRotationAngle(cube_r4_r1, -0.3927F, 0.0F, 0.0F);
		cube_r4_r1.setTextureOffset(0, 57).addBox(-12.0F, -47.0F, 5.0F, 26.0F, 15.0F, 20.0F, 0.0F, false);
		UpperPart = new AModelRenderer(this);
		UpperPart.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(UpperPart);
		UpperPart.setTextureOffset(0, 92).addBox(-8.0F, -11.0F, -9.0F, 18.0F, 13.0F, 14.0F, 0.0F, false);
		RightSecondArm = new AModelRenderer(this);
		RightSecondArm.setRotationPoint(10.0F, -6.0F, 3.0F);
		UpperPart.addChild(RightSecondArm);
		setRotationAngle(RightSecondArm, 0.0F, -1.0908F, 0.0F);
		cube_r6_r1 = new AModelRenderer(this);
		cube_r6_r1.setRotationPoint(-15.9848F, 53.5417F, -1.5923F);
		RightSecondArm.addChild(cube_r6_r1);
		setRotationAngle(cube_r6_r1, 0.3927F, 0.2618F, 0.0F);
		cube_r6_r1.setTextureOffset(50, 92).addBox(11.1755F, -49.2821F, 18.5357F, 4.0F, 4.0F, 8.0F, 0.0F, false);
		cube_r5_r1 = new AModelRenderer(this);
		cube_r5_r1.setRotationPoint(-10.5501F, 47.994F, 14.427F);
		RightSecondArm.addChild(cube_r5_r1);
		setRotationAngle(cube_r5_r1, 0.7854F, 0.2618F, 0.0F);
		cube_r5_r1.setTextureOffset(38, 125).addBox(10.9128F, -45.7507F, 9.8804F, 2.0F, 2.0F, 12.0F, 0.0F, false);
		LeftSecondArm = new AModelRenderer(this);
		LeftSecondArm.setRotationPoint(-8.0F, -6.0F, 3.0F);
		UpperPart.addChild(LeftSecondArm);
		setRotationAngle(LeftSecondArm, 0.0F, 1.0908F, 0.0F);
		cube_r8_r1 = new AModelRenderer(this);
		cube_r8_r1.setRotationPoint(8.8489F, 50.9889F, 2.1765F);
		LeftSecondArm.addChild(cube_r8_r1);
		setRotationAngle(cube_r8_r1, 0.3927F, -0.2618F, 0.0F);
		cube_r8_r1.setTextureOffset(116, 93).addBox(-8.5208F, -49.2821F, 11.8649F, 4.0F, 4.0F, 8.0F, 0.0F, false);
		cube_r7_r1 = new AModelRenderer(this);
		cube_r7_r1.setRotationPoint(3.7884F, 43.277F, 16.799F);
		LeftSecondArm.addChild(cube_r7_r1);
		setRotationAngle(cube_r7_r1, 0.7854F, -0.2618F, 0.0F);
		cube_r7_r1.setTextureOffset(112, 125).addBox(-6.2581F, -45.7507F, 3.2096F, 2.0F, 2.0F, 12.0F, 0.0F, false);
		LeftWing = new AModelRenderer(this);
		LeftWing.setRotationPoint(-9.0F, -12.0F, 5.0F);
		UpperPart.addChild(LeftWing);


		cube_r9_r1 = new AModelRenderer(this);
		cube_r9_r1.setRotationPoint(-29.0671F, 45.4271F, -23.8862F);
		LeftWing.addChild(cube_r9_r1);
		setRotationAngle(cube_r9_r1, 0.0F, 0.48F, 0.6109F);
		cube_r9_r1.setTextureOffset(72, 57).addBox(-41.0F, -53.0F, 12.0F, 34.0F, 1.0F, 15.0F, 0.0F, false);

		RightWing = new AModelRenderer(this);
		RightWing.setRotationPoint(11.0F, -12.0F, 5.0F);
		UpperPart.addChild(RightWing);


		cube_r10_r1 = new AModelRenderer(this);
		cube_r10_r1.setRotationPoint(27.0671F, 45.4271F, -23.8862F);
		RightWing.addChild(cube_r10_r1);
		setRotationAngle(cube_r10_r1, 0.0F, -0.48F, -0.6109F);
		cube_r10_r1.setTextureOffset(72, 57).addBox(7.0F, -53.0F, 12.0F, 34.0F, 1.0F, 15.0F, 0.0F, true);

		LeftArm = new AModelRenderer(this);
		LeftArm.setRotationPoint(-10.0F, -9.0F, -5.0F);
		UpperPart.addChild(LeftArm);
		LeftArm.setTextureOffset(60, 135).addBox(-4.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r12_r1 = new AModelRenderer(this);
		cube_r12_r1.setRotationPoint(4.6197F, 50.8727F, 9.3816F);
		LeftArm.addChild(cube_r12_r1);
		setRotationAngle(cube_r12_r1, 0.3927F, -0.3927F, 0.0F);
		cube_r12_r1.setTextureOffset(132, 97).addBox(-12.0F, -52.0F, 5.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		cube_r11_r1 = new AModelRenderer(this);
		cube_r11_r1.setRotationPoint(-2.5576F, 40.0624F, 22.3111F);
		LeftArm.addChild(cube_r11_r1);
		setRotationAngle(cube_r11_r1, 0.7854F, -0.3927F, 0.0F);
		cube_r11_r1.setTextureOffset(18, 121).addBox(-9.0F, -48.0F, -7.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);

		RightArm = new AModelRenderer(this);
		RightArm.setRotationPoint(12.0F, -9.0F, -5.0F);
		UpperPart.addChild(RightArm);
		RightArm.setTextureOffset(0, 135).addBox(-2.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		cube_r13 = new AModelRenderer(this);
		cube_r13.setRotationPoint(-5.0F, 4.0F, -10.0F);
		RightArm.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.7854F, 0.3927F, 0.0F);
		cube_r13.setTextureOffset(0, 119).addBox(2.0F, 0.0F, -10.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);

		cube_r14 = new AModelRenderer(this);
		cube_r14.setRotationPoint(-3.0F, 2.0F, -5.0F);
		RightArm.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.3927F, 0.3927F, 0.0F);
		cube_r14.setTextureOffset(128, 125).addBox(1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		Neck = new AModelRenderer(this);
		Neck.setRotationPoint(0.0F, -11.0F, -2.0F);
		UpperPart.addChild(Neck);
		Neck.setTextureOffset(87, 0).addBox(-10.0F, -3.0F, -9.0F, 22.0F, 3.0F, 19.0F, 0.0F, false);
		Neck.setTextureOffset(64, 93).addBox(-9.0F, -7.0F, 0.0F, 20.0F, 4.0F, 12.0F, 0.0F, false);

		cube_r16_r1 = new AModelRenderer(this);
		cube_r16_r1.setRotationPoint(0.0F, 9.9998F, 65.0001F);
		Neck.addChild(cube_r16_r1);
		setRotationAngle(cube_r16_r1, 1.5708F, 0.0F, 0.0F);
		cube_r16_r1.setTextureOffset(64, 93).addBox(-9.0F, -65.0F, 17.0F, 20.0F, 4.0F, 12.0F, 0.0F, false);

		cube_r15_r1 = new AModelRenderer(this);
		cube_r15_r1.setRotationPoint(0.0F, 15.9998F, 67.0001F);
		Neck.addChild(cube_r15_r1);
		setRotationAngle(cube_r15_r1, 1.5708F, 0.0F, 0.0F);
		cube_r15_r1.setTextureOffset(64, 93).addBox(-9.0F, -63.0F, 21.0F, 20.0F, 4.0F, 12.0F, 0.0F, false);

		Head = new AModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Neck.addChild(Head);
		Head.setTextureOffset(88, 125).addBox(-7.0F, -17.0F, -10.0F, 2.0F, 14.0F, 10.0F, 0.0F, false);
		Head.setTextureOffset(54, 109).addBox(-5.0F, -19.0F, -10.0F, 12.0F, 16.0F, 10.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(7.0F, -17.0F, -10.0F, 2.0F, 14.0F, 10.0F, 0.0F, false);
		Head.setTextureOffset(114, 33).addBox(-5.0F, -17.0F, -16.0F, 12.0F, 8.0F, 6.0F, 0.0F, false);
		Head.setTextureOffset(114, 22).addBox(-6.0F, -9.0F, -16.0F, 14.0F, 5.0F, 6.0F, 0.0F, false);

		cube_r22_r1 = new AModelRenderer(this);
		cube_r22_r1.setRotationPoint(-34.1879F, 48.5907F, -15.0F);
		Head.addChild(cube_r22_r1);
		setRotationAngle(cube_r22_r1, 0.0F, 0.0F, 0.5236F);
		cube_r22_r1.setTextureOffset(24, 139).addBox(-11.0F, -73.5F, 7.0F, 4.0F, 7.0F, 7.0F, 0.0F, false);

		cube_r21_r1 = new AModelRenderer(this);
		cube_r21_r1.setRotationPoint(34.4558F, 49.5907F, -15.0F);
		Head.addChild(cube_r21_r1);
		setRotationAngle(cube_r21_r1, 0.0F, 0.0F, -0.5236F);
		cube_r21_r1.setTextureOffset(133, 137).addBox(9.0F, -73.5F, 7.0F, 4.0F, 7.0F, 7.0F, 0.0F, false);

		cube_r20_r1 = new AModelRenderer(this);
		cube_r20_r1.setRotationPoint(36.0443F, 39.5188F, -15.0F);
		Head.addChild(cube_r20_r1);
		setRotationAngle(cube_r20_r1, 0.0F, 0.0F, -0.5236F);
		cube_r20_r1.setTextureOffset(0, 68).addBox(-12.0F, -77.5F, 9.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r19_r1 = new AModelRenderer(this);
		cube_r19_r1.setRotationPoint(-35.7764F, 38.5188F, -15.0F);
		Head.addChild(cube_r19_r1);
		setRotationAngle(cube_r19_r1, 0.0F, 0.0F, 0.5236F);
		cube_r19_r1.setTextureOffset(14, 0).addBox(11.0F, -77.5F, 9.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r18_r1 = new AModelRenderer(this);
		cube_r18_r1.setRotationPoint(-64.1063F, 28.5351F, -15.0F);
		Head.addChild(cube_r18_r1);
		setRotationAngle(cube_r18_r1, 0.0F, 0.0F, 1.0472F);
		cube_r18_r1.setTextureOffset(137, 109).addBox(-15.0F, -74.25F, 8.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		cube_r17_r1 = new AModelRenderer(this);
		cube_r17_r1.setRotationPoint(65.1063F, 30.2672F, -15.0F);
		Head.addChild(cube_r17_r1);
		setRotationAngle(cube_r17_r1, 0.0F, 0.0F, -1.0472F);
		cube_r17_r1.setTextureOffset(0, 57).addBox(12.0F, -74.25F, 8.0F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		initializeAnimator(BEEQUEEN_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		BeeQueen.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setRotationAngles(EntityBeeQueen entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.Head.setCustomRotation(new Vector3((headPitch * ((float)Math.PI / 180))/2,(netHeadYaw * ((float)Math.PI / 180))/2,0));
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

	@Override
	public void animate(EntityBeeQueen entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}