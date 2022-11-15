package org.astemir.forestcraft.client.render.model.item;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.astemir.api.client.animator.*;

public class ModelVileTentacle<T extends LivingEntity> extends AnimatedItemModel<T> {
	
	private final AModelRenderer parahead;
	private final AModelRenderer a;
	private final AModelRenderer b;
	private final AModelRenderer c;
	private final AModelRenderer d;
	private final AModelRenderer mega_jaw;
	private final AModelRenderer claw;
	private final AModelRenderer cube_r1;
	private final AModelRenderer cube_r2;
	private final AModelRenderer claw3;
	private final AModelRenderer cube_r3;
	private final AModelRenderer cube_r4;
	private final AModelRenderer claw4;
	private final AModelRenderer cube_r5;
	private final AModelRenderer cube_r6;
	private final AModelRenderer claw2;
	private final AModelRenderer cube_r7;
	private final AModelRenderer cube_r8;

	public static AnimationFile VILE_TENTACLE_ANIMATIONS = new AnimationFile("animations/vile_tentacle_animations.json");

	private Animation IDLE = new Animation(0,"parasite_idle").time(1.04f).speed(6f).loop();
	private Animation ATTACK = new Animation(1,"attack").time(1.04f).speed(2f);


	public ModelVileTentacle() {
		textureWidth = 128;
		textureHeight = 128;

		parahead = new AModelRenderer(this);
		parahead.setRotationPoint(0.0F, 21.25F, -1.0F);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(0.0F, 0.0F, 0.0F);
		parahead.addChild(a);
		a.setTextureOffset(12, 56).addBox(-1.0F, -1.25F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(0.0F, -0.25F, -2.0F);
		a.addChild(b);
		b.setTextureOffset(28, 69).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(0.0F, 0.0F, -4.0F);
		b.addChild(c);
		c.setTextureOffset(52, 34).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		d = new AModelRenderer(this);
		d.setRotationPoint(0.0F, 0.0F, -4.0F);
		c.addChild(d);
		d.setTextureOffset(0, 29).addBox(-2.5F, -2.5F, -10.0F, 5.0F, 5.0F, 10.0F, 0.0F, false);

		mega_jaw = new AModelRenderer(this);
		mega_jaw.setRotationPoint(0.0F, 0.0F, -7.0F);
		d.addChild(mega_jaw);
		mega_jaw.setTextureOffset(0, 44).addBox(-3.5F, -3.5F, -5.0F, 7.0F, 7.0F, 5.0F, 0.0F, false);

		claw = new AModelRenderer(this);
		claw.setRotationPoint(-3.0F, 0.0F, -3.0F);
		mega_jaw.addChild(claw);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(3.0F, 0.0F, 10.0F);
		claw.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.2182F, 0.0F);
		cube_r1.setTextureOffset(58, 48).addBox(-2.0F, -1.5F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(3.0F, 0.0F, 10.0F);
		claw.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.3054F, 0.0F);
		cube_r2.setTextureOffset(50, 66).addBox(-9.25F, -1.0F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		claw3 = new AModelRenderer(this);
		claw3.setRotationPoint(-0.25F, -3.0F, -4.0F);
		mega_jaw.addChild(claw3);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(0.25F, 3.0F, 11.0F);
		claw3.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.2182F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(0.25F, 3.0F, 11.0F);
		claw3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3054F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		claw4 = new AModelRenderer(this);
		claw4.setRotationPoint(0.25F, 3.0F, -4.0F);
		mega_jaw.addChild(claw4);
		

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(-0.25F, -3.0F, 11.0F);
		claw4.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.2182F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(18, 56).addBox(-1.5F, -1.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(-0.25F, -3.0F, 11.0F);
		claw4.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.3054F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(50, 66).addBox(-1.0F, 7.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		claw2 = new AModelRenderer(this);
		claw2.setRotationPoint(3.0F, 0.0F, -4.0F);
		mega_jaw.addChild(claw2);
		

		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(-3.0F, 0.0F, 11.0F);
		claw2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, -0.2182F, 0.0F);
		cube_r7.setTextureOffset(58, 34).addBox(-1.0F, -1.5F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(-3.0F, 0.0F, 11.0F);
		claw2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.3054F, 0.0F);
		cube_r8.setTextureOffset(50, 66).addBox(7.25F, -1.0F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		initializeAnimator(VILE_TENTACLE_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		parahead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	private boolean attacking = false;

	@Override
	public void animate(LivingEntity entityIn, ItemStack stack, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entityIn.getActiveItemStack().getItem() == stack.getItem()) {
			animator.play(this,ATTACK,delta);
			attacking = true;
		}else{
			attacking = false;
		}
		animator.play(this,IDLE,delta);
	}

	@Override
	public boolean animatePart(AModelRenderer part, Animation animation, float delta) {
		if (animation == IDLE && attacking) {
			if (ignorePartsOf(part, ATTACK)) {
				return false;
			}
		}
		return true;
	}
}