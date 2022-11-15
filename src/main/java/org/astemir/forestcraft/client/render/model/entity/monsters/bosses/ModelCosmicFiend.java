package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityCosmicFiend;

public class ModelCosmicFiend extends AnimatedEntityModel<EntityCosmicFiend> {

	private final AModelRenderer space_fiend;
	private final AModelRenderer head;
	private final AModelRenderer bottom;
	private final AModelRenderer cube_r1;
	private final AModelRenderer legs;
	private final AModelRenderer leg;
	private final AModelRenderer a;
	private final AModelRenderer cube_r2;
	private final AModelRenderer b;
	private final AModelRenderer cube_r3;
	private final AModelRenderer c;
	private final AModelRenderer cube_r4;
	private final AModelRenderer bone4;
	private final AModelRenderer cube_r5;
	private final AModelRenderer leg2;
	private final AModelRenderer a2;
	private final AModelRenderer cube_r6;
	private final AModelRenderer b2;
	private final AModelRenderer cube_r7;
	private final AModelRenderer c2;
	private final AModelRenderer cube_r8;
	private final AModelRenderer bone2;
	private final AModelRenderer cube_r9;
	private final AModelRenderer leg3;
	private final AModelRenderer a3;
	private final AModelRenderer cube_r10;
	private final AModelRenderer b3;
	private final AModelRenderer cube_r11;
	private final AModelRenderer c3;
	private final AModelRenderer cube_r12;
	private final AModelRenderer bone3;
	private final AModelRenderer cube_r13;
	private final AModelRenderer leg4;
	private final AModelRenderer a4;
	private final AModelRenderer cube_r14;
	private final AModelRenderer b4;
	private final AModelRenderer cube_r15;
	private final AModelRenderer c4;
	private final AModelRenderer cube_r16;
	private final AModelRenderer bone5;
	private final AModelRenderer cube_r17;
	private final AModelRenderer leg5;
	private final AModelRenderer a5;
	private final AModelRenderer cube_r18;
	private final AModelRenderer b5;
	private final AModelRenderer cube_r19;
	private final AModelRenderer c5;
	private final AModelRenderer cube_r20;
	private final AModelRenderer bone6;
	private final AModelRenderer cube_r21;
	private final AModelRenderer top;
	private final AModelRenderer cube_r22;
	private final AModelRenderer eye;
	public static AnimationFile COSMIC_FIEND_ANIMATIONS = new AnimationFile("animations/cosmic_fiend_animations.json");

	public ModelCosmicFiend() {
		textureWidth = 256;
		textureHeight = 256;

		space_fiend = new AModelRenderer(this);
		space_fiend.setRotationPoint(3.0F, 6.0F, 16.0F);
		

		head = new AModelRenderer(this);
		head.setRotationPoint(-4.0F, -24.0F, -16.0F);
		space_fiend.addChild(head);
		head.setTextureOffset(0, 115).addBox(-20.0F, -32.0F, 4.0F, 40.0F, 23.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(104, 125).addBox(-14.0F, -32.0F, 16.0F, 29.0F, 23.0F, 12.0F, 0.0F, false);

		bottom = new AModelRenderer(this);
		bottom.setRotationPoint(0.0F, -10.0F, 5.0F);
		head.addChild(bottom);
		bottom.setTextureOffset(0, 42).addBox(-20.0F, 1.0F, -22.0F, 40.0F, 9.0F, 33.0F, 0.0F, false);

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(4.0F, 34.0F, 11.0F);
		bottom.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.3054F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(112, 97).addBox(-25.0F, -27.0F, -43.0F, 42.0F, 10.0F, 18.0F, 0.0F, false);

		legs = new AModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		bottom.addChild(legs);
		

		leg = new AModelRenderer(this);
		leg.setRotationPoint(-19.0F, 3.0F, 2.0F);
		legs.addChild(leg);
		setRotationAngle(leg, 0.0F, -1.5708F, 0.5672F);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(0.0F, -1.0F, -1.0F);
		leg.addChild(a);
		setRotationAngle(a, -0.3491F, 0.0F, 0.0F);
		

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 26.0F, 4.0F);
		a.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.3927F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(0, 150).addBox(-7.0F, -26.0F, 0.0F, 14.0F, 14.0F, 9.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(0.0F, 12.0F, 6.0F);
		a.addChild(b);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 14.0F, -2.0F);
		b.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(0, 150).addBox(-6.0F, -14.0F, -1.75F, 12.0F, 14.0F, 7.0F, 0.0F, false);

		c = new AModelRenderer(this);
		c.setRotationPoint(0.0F, 14.0F, 2.0F);
		b.addChild(c);
		

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, -4.0F);
		c.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0436F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -0.75F, 10.0F, 14.0F, 5.0F, 0.0F, false);

		bone4 = new AModelRenderer(this);
		bone4.setRotationPoint(0.0F, 14.0F, -2.0F);
		c.addChild(bone4);
		

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -14.0F, -2.0F);
		bone4.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.0873F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(0, 19).addBox(-4.0F, 11.0F, 1.0F, 8.0F, 6.0F, 4.0F, 0.0F, false);

		leg2 = new AModelRenderer(this);
		leg2.setRotationPoint(21.0F, 3.0F, 2.0F);
		legs.addChild(leg2);
		setRotationAngle(leg2, 0.0F, 1.5708F, -0.5672F);
		

		a2 = new AModelRenderer(this);
		a2.setRotationPoint(0.0F, -1.0F, -1.0F);
		leg2.addChild(a2);
		setRotationAngle(a2, -0.3491F, 0.0F, 0.0F);
		

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 26.0F, 4.0F);
		a2.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.3927F, 0.0F, 0.0F);
		cube_r6.setTextureOffset(0, 150).addBox(-7.0F, -26.0F, 0.0F, 14.0F, 14.0F, 9.0F, 0.0F, true);

		b2 = new AModelRenderer(this);
		b2.setRotationPoint(0.0F, 12.0F, 6.0F);
		a2.addChild(b2);
		

		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 14.0F, -2.0F);
		b2.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.1745F, 0.0F, 0.0F);
		cube_r7.setTextureOffset(0, 150).addBox(-6.0F, -14.0F, -1.75F, 12.0F, 14.0F, 7.0F, 0.0F, true);

		c2 = new AModelRenderer(this);
		c2.setRotationPoint(0.0F, 14.0F, 2.0F);
		b2.addChild(c2);
		

		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(0.0F, 0.0F, -4.0F);
		c2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0436F, 0.0F, 0.0F);
		cube_r8.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -0.75F, 10.0F, 14.0F, 5.0F, 0.0F, true);

		bone2 = new AModelRenderer(this);
		bone2.setRotationPoint(0.0F, 14.0F, -2.0F);
		c2.addChild(bone2);
		

		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, -14.0F, -2.0F);
		bone2.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.0873F, 0.0F, 0.0F);
		cube_r9.setTextureOffset(0, 19).addBox(-4.0F, 11.0F, 1.0F, 8.0F, 6.0F, 4.0F, 0.0F, true);

		leg3 = new AModelRenderer(this);
		leg3.setRotationPoint(2.0F, 9.0F, 8.0F);
		legs.addChild(leg3);
		setRotationAngle(leg3, 0.0F, 1.5708F, -0.5672F);
		

		a3 = new AModelRenderer(this);
		a3.setRotationPoint(5.0F, 3.2235F, 9.3519F);
		leg3.addChild(a3);
		setRotationAngle(a3, -0.3491F, 0.0F, 0.0F);
		

		cube_r10 = new AModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 26.0F, -16.0F);
		a3.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.3927F, 0.0F, 0.0F);
		cube_r10.setTextureOffset(0, 150).addBox(-7.0F, -26.0F, -9.0F, 14.0F, 14.0F, 9.0F, 0.0F, true);

		b3 = new AModelRenderer(this);
		b3.setRotationPoint(0.0F, 12.0F, 6.0F);
		a3.addChild(b3);
		

		cube_r11 = new AModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 14.0F, -22.0F);
		b3.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.1745F, 0.0F, 0.0F);
		cube_r11.setTextureOffset(0, 150).addBox(-6.0F, -14.0F, -5.25F, 12.0F, 14.0F, 7.0F, 0.0F, true);

		c3 = new AModelRenderer(this);
		c3.setRotationPoint(0.0F, 14.0F, 2.0F);
		b3.addChild(c3);
		

		cube_r12 = new AModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 0.0F, -24.0F);
		c3.addChild(cube_r12);
		setRotationAngle(cube_r12, -0.0436F, 0.0F, 0.0F);
		cube_r12.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -4.25F, 10.0F, 14.0F, 5.0F, 0.0F, true);

		bone3 = new AModelRenderer(this);
		bone3.setRotationPoint(0.0F, 14.0F, -2.0F);
		c3.addChild(bone3);
		

		cube_r13 = new AModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, -14.0F, -22.0F);
		bone3.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0873F, 0.0F, 0.0F);
		cube_r13.setTextureOffset(0, 19).addBox(-4.0F, 11.0F, -5.0F, 8.0F, 6.0F, 4.0F, 0.0F, true);

		leg4 = new AModelRenderer(this);
		leg4.setRotationPoint(12.0F, 10.0F, 6.0F);
		legs.addChild(leg4);
		setRotationAngle(leg4, 0.0F, -1.5708F, 0.5672F);
		

		a4 = new AModelRenderer(this);
		a4.setRotationPoint(-3.0F, -0.1501F, 11.5011F);
		leg4.addChild(a4);
		setRotationAngle(a4, -0.3491F, 0.0F, 0.0F);
		

		cube_r14 = new AModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 26.0F, -16.0F);
		a4.addChild(cube_r14);
		setRotationAngle(cube_r14, -0.3927F, 0.0F, 0.0F);
		cube_r14.setTextureOffset(0, 150).addBox(-7.0F, -26.0F, -9.0F, 14.0F, 14.0F, 9.0F, 0.0F, false);

		b4 = new AModelRenderer(this);
		b4.setRotationPoint(0.0F, 12.0F, 6.0F);
		a4.addChild(b4);
		

		cube_r15 = new AModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 14.0F, -22.0F);
		b4.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.1745F, 0.0F, 0.0F);
		cube_r15.setTextureOffset(0, 150).addBox(-6.0F, -14.0F, -5.25F, 12.0F, 14.0F, 7.0F, 0.0F, false);

		c4 = new AModelRenderer(this);
		c4.setRotationPoint(0.0F, 14.0F, 2.0F);
		b4.addChild(c4);
		

		cube_r16 = new AModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 0.0F, -24.0F);
		c4.addChild(cube_r16);
		setRotationAngle(cube_r16, -0.0436F, 0.0F, 0.0F);
		cube_r16.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -4.25F, 10.0F, 14.0F, 5.0F, 0.0F, false);

		bone5 = new AModelRenderer(this);
		bone5.setRotationPoint(0.0F, 14.0F, -2.0F);
		c4.addChild(bone5);
		

		cube_r17 = new AModelRenderer(this);
		cube_r17.setRotationPoint(0.0F, -14.0F, -22.0F);
		bone5.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0873F, 0.0F, 0.0F);
		cube_r17.setTextureOffset(0, 19).addBox(-4.0F, 11.0F, -5.0F, 8.0F, 6.0F, 4.0F, 0.0F, false);

		leg5 = new AModelRenderer(this);
		leg5.setRotationPoint(-13.0F, 9.0F, 8.0F);
		legs.addChild(leg5);
		setRotationAngle(leg5, 0.0F, -1.5708F, 0.5672F);
		

		a5 = new AModelRenderer(this);
		a5.setRotationPoint(-5.0F, 0.6933F, 10.9638F);
		leg5.addChild(a5);
		setRotationAngle(a5, -0.3491F, 0.0F, 0.0F);
		

		cube_r18 = new AModelRenderer(this);
		cube_r18.setRotationPoint(0.0F, 26.0F, -16.0F);
		a5.addChild(cube_r18);
		setRotationAngle(cube_r18, -0.3927F, 0.0F, 0.0F);
		cube_r18.setTextureOffset(0, 150).addBox(-7.0F, -26.0F, -9.0F, 14.0F, 14.0F, 9.0F, 0.0F, false);

		b5 = new AModelRenderer(this);
		b5.setRotationPoint(0.0F, 12.0F, 6.0F);
		a5.addChild(b5);
		

		cube_r19 = new AModelRenderer(this);
		cube_r19.setRotationPoint(0.0F, 14.0F, -22.0F);
		b5.addChild(cube_r19);
		setRotationAngle(cube_r19, -0.1745F, 0.0F, 0.0F);
		cube_r19.setTextureOffset(0, 150).addBox(-6.0F, -14.0F, -5.25F, 12.0F, 14.0F, 7.0F, 0.0F, false);

		c5 = new AModelRenderer(this);
		c5.setRotationPoint(0.0F, 14.0F, 2.0F);
		b5.addChild(c5);
		

		cube_r20 = new AModelRenderer(this);
		cube_r20.setRotationPoint(0.0F, 0.0F, -24.0F);
		c5.addChild(cube_r20);
		setRotationAngle(cube_r20, -0.0436F, 0.0F, 0.0F);
		cube_r20.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -4.25F, 10.0F, 14.0F, 5.0F, 0.0F, false);

		bone6 = new AModelRenderer(this);
		bone6.setRotationPoint(0.0F, 14.0F, -2.0F);
		c5.addChild(bone6);
		

		cube_r21 = new AModelRenderer(this);
		cube_r21.setRotationPoint(0.0F, -14.0F, -22.0F);
		bone6.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0873F, 0.0F, 0.0F);
		cube_r21.setTextureOffset(0, 19).addBox(-4.0F, 11.0F, -5.0F, 8.0F, 6.0F, 4.0F, 0.0F, false);

		top = new AModelRenderer(this);
		top.setRotationPoint(4.0F, -32.0F, 6.0F);
		head.addChild(top);
		top.setTextureOffset(0, 0).addBox(-24.0F, -9.0F, -23.0F, 40.0F, 9.0F, 33.0F, 0.0F, false);

		cube_r22 = new AModelRenderer(this);
		cube_r22.setRotationPoint(0.0F, 56.0F, 10.0F);
		top.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.3054F, 0.0F, 0.0F);
		cube_r22.setTextureOffset(0, 84).addBox(-25.0F, -64.0F, -17.0F, 42.0F, 8.0F, 23.0F, 0.0F, false);

		eye = new AModelRenderer(this);
		eye.setRotationPoint(0.0F, -20.0F, -5.0F);
		head.addChild(eye);
		eye.setTextureOffset(113, 0).addBox(-6.0F, -6.0F, -6.0F, 13.0F, 12.0F, 12.0F, 0.0F, false);
		initializeAnimator(COSMIC_FIEND_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		space_fiend.render(matrixStack, buffer, packedLight, packedOverlay,red,green,blue,alpha);
	}

	@Override
	public void setRotationAngles(EntityCosmicFiend entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.eye.setCustomRotation(new Vector3((headPitch * ((float)Math.PI / 180)),(netHeadYaw * ((float)Math.PI / 180)),0));
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}

	@Override
	public void animate(EntityCosmicFiend entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}