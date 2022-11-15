package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedEntityModel;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityKrock;

public class ModelKrock extends AnimatedEntityModel<EntityKrock> {

	private final AModelRenderer krock;
	private final AModelRenderer body;
	private final AModelRenderer wing;
	private final AModelRenderer cube_r1;
	private final AModelRenderer spikes2;
	private final AModelRenderer spike20;
	private final AModelRenderer cube_r2;
	private final AModelRenderer spike19;
	private final AModelRenderer cube_r3;
	private final AModelRenderer spike18;
	private final AModelRenderer cube_r4;
	private final AModelRenderer spike17;
	private final AModelRenderer cube_r5;
	private final AModelRenderer spike15;
	private final AModelRenderer cube_r6;
	private final AModelRenderer spike14;
	private final AModelRenderer cube_r7;
	private final AModelRenderer wing2;
	private final AModelRenderer cube_r8;
	private final AModelRenderer spikes;
	private final AModelRenderer spike12;
	private final AModelRenderer cube_r9;
	private final AModelRenderer spike13;
	private final AModelRenderer spike11;
	private final AModelRenderer cube_r10;
	private final AModelRenderer spike10;
	private final AModelRenderer cube_r11;
	private final AModelRenderer spike9;
	private final AModelRenderer cube_r12;
	private final AModelRenderer spike8;
	private final AModelRenderer cube_r13;
	private final AModelRenderer spike7;
	private final AModelRenderer cube_r14;
	private final AModelRenderer up;
	private final AModelRenderer spikes3;
	private final AModelRenderer spike3;
	private final AModelRenderer spike;
	private final AModelRenderer cube_r15;
	private final AModelRenderer spike21;
	private final AModelRenderer spike4;
	private final AModelRenderer spike23;
	private final AModelRenderer cube_r16;
	private final AModelRenderer spike6;
	private final AModelRenderer cube_r17;
	private final AModelRenderer spike5;
	private final AModelRenderer cube_r18;
	private final AModelRenderer spike22;
	private final AModelRenderer cube_r19;
	private final AModelRenderer spike2;
	private final AModelRenderer cube_r20;
	private final AModelRenderer back;
	private final AModelRenderer spikes4;
	private final AModelRenderer spike41;
	private final AModelRenderer spike40;
	private final AModelRenderer cube_r21;
	private final AModelRenderer spike39;
	private final AModelRenderer spike38;
	private final AModelRenderer spike37;
	private final AModelRenderer cube_r22;
	private final AModelRenderer spike36;
	private final AModelRenderer cube_r23;
	private final AModelRenderer spike35;
	private final AModelRenderer cube_r24;
	private final AModelRenderer spike34;
	private final AModelRenderer cube_r25;
	private final AModelRenderer spike33;
	private final AModelRenderer cube_r26;
	private final AModelRenderer down;
	private final AModelRenderer spikes5;
	private final AModelRenderer spike32;
	private final AModelRenderer spike31;
	private final AModelRenderer cube_r27;
	private final AModelRenderer spike30;
	private final AModelRenderer spike29;
	private final AModelRenderer spike28;
	private final AModelRenderer cube_r28;
	private final AModelRenderer spike27;
	private final AModelRenderer cube_r29;
	private final AModelRenderer spike26;
	private final AModelRenderer cube_r30;
	private final AModelRenderer spike25;
	private final AModelRenderer cube_r31;
	private final AModelRenderer spike24;
	private final AModelRenderer cube_r32;
	private final AModelRenderer left_leg;
	private final AModelRenderer right_leg;

	public static AnimationFile KROCK_ANIMATIONS = new AnimationFile("animations/krock_animations.json");


	public ModelKrock() {
		textureWidth = 128;
		textureHeight = 128;

		krock = new AModelRenderer(this);
		krock.setRotationPoint(0.0F, 9.0F, 0.0F);


		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		krock.addChild(body);
		body.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		wing = new AModelRenderer(this);
		wing.setRotationPoint(-8.0F, -7.0F, 0.0F);
		body.addChild(wing);


		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(-5.0F, 15.0F, 0.0F);
		wing.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -1.5708F);
		cube_r1.setTextureOffset(18, 39).addBox(1.0F, 4.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, true);

		spikes2 = new AModelRenderer(this);
		spikes2.setRotationPoint(0.0F, 8.0F, 0.0F);
		wing.addChild(spikes2);


		spike20 = new AModelRenderer(this);
		spike20.setRotationPoint(-1.5F, 3.0F, 0.0F);
		spikes2.addChild(spike20);


		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(8.0F, 15.0F, -6.0F);
		spike20.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -1.5708F);
		cube_r2.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike19 = new AModelRenderer(this);
		spike19.setRotationPoint(-1.0F, -5.5F, 0.0F);
		spikes2.addChild(spike19);


		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(7.5F, 15.5F, -6.0F);
		spike19.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -1.5708F);
		cube_r3.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike18 = new AModelRenderer(this);
		spike18.setRotationPoint(-0.5F, -6.0F, 6.0F);
		spikes2.addChild(spike18);


		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(5.0F, 16.0F, -8.0F);
		spike18.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.3054F, 0.0F, -1.5708F);
		cube_r4.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike17 = new AModelRenderer(this);
		spike17.setRotationPoint(-1.0F, -5.5F, -7.0F);
		spikes2.addChild(spike17);


		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(9.5F, 15.5F, -3.0F);
		spike17.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.3054F, 0.0F, -1.5708F);
		cube_r5.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike15 = new AModelRenderer(this);
		spike15.setRotationPoint(-1.0F, 2.5F, 6.0F);
		spikes2.addChild(spike15);


		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(5.5F, 15.5F, -8.0F);
		spike15.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.3054F, 0.0F, -1.5708F);
		cube_r6.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike14 = new AModelRenderer(this);
		spike14.setRotationPoint(0.0F, 2.5F, -7.0F);
		spikes2.addChild(spike14);


		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(8.5F, 15.5F, -3.0F);
		spike14.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.3054F, 0.0F, -1.5708F);
		cube_r7.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		wing2 = new AModelRenderer(this);
		wing2.setRotationPoint(8.0F, -7.0F, 0.0F);
		body.addChild(wing2);


		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(5.0F, 15.0F, 0.0F);
		wing2.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 1.5708F);
		cube_r8.setTextureOffset(18, 39).addBox(-15.0F, 4.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

		spikes = new AModelRenderer(this);
		spikes.setRotationPoint(0.0F, 8.0F, 0.0F);
		wing2.addChild(spikes);


		spike12 = new AModelRenderer(this);
		spike12.setRotationPoint(1.5F, 3.0F, 0.0F);
		spikes.addChild(spike12);


		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(-8.0F, 15.0F, -6.0F);
		spike12.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 1.5708F);
		cube_r9.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike13 = new AModelRenderer(this);
		spike13.setRotationPoint(1.0F, -6.0F, 0.0F);
		spikes.addChild(spike13);


		spike11 = new AModelRenderer(this);
		spike11.setRotationPoint(2.0F, -5.5F, 0.0F);
		spikes.addChild(spike11);


		cube_r10 = new AModelRenderer(this);
		cube_r10.setRotationPoint(-8.5F, 15.5F, -6.0F);
		spike11.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 1.5708F);
		cube_r10.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike10 = new AModelRenderer(this);
		spike10.setRotationPoint(0.5F, -6.0F, 6.0F);
		spikes.addChild(spike10);


		cube_r11 = new AModelRenderer(this);
		cube_r11.setRotationPoint(-5.0F, 16.0F, -8.0F);
		spike10.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.3054F, 0.0F, 1.5708F);
		cube_r11.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike9 = new AModelRenderer(this);
		spike9.setRotationPoint(1.0F, -5.5F, -7.0F);
		spikes.addChild(spike9);


		cube_r12 = new AModelRenderer(this);
		cube_r12.setRotationPoint(-9.5F, 15.5F, -3.0F);
		spike9.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.3054F, 0.0F, 1.5708F);
		cube_r12.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike8 = new AModelRenderer(this);
		spike8.setRotationPoint(1.0F, 2.5F, 6.0F);
		spikes.addChild(spike8);


		cube_r13 = new AModelRenderer(this);
		cube_r13.setRotationPoint(-5.5F, 15.5F, -8.0F);
		spike8.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.3054F, 0.0F, 1.5708F);
		cube_r13.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike7 = new AModelRenderer(this);
		spike7.setRotationPoint(1.0F, 2.5F, -7.0F);
		spikes.addChild(spike7);


		cube_r14 = new AModelRenderer(this);
		cube_r14.setRotationPoint(-9.5F, 15.5F, -3.0F);
		spike7.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.3054F, 0.0F, 1.5708F);
		cube_r14.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		up = new AModelRenderer(this);
		up.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(up);
		up.setTextureOffset(18, 39).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

		spikes3 = new AModelRenderer(this);
		spikes3.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(spikes3);


		spike3 = new AModelRenderer(this);
		spike3.setRotationPoint(0.0F, -9.0F, -5.0F);
		spikes3.addChild(spike3);
		spike3.setTextureOffset(0, 41).addBox(-2.5F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike = new AModelRenderer(this);
		spike.setRotationPoint(8.0F, -8.0F, -5.0F);
		spikes3.addChild(spike);


		cube_r15 = new AModelRenderer(this);
		cube_r15.setRotationPoint(11.5F, 12.0F, -6.0F);
		spike.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.3054F);
		cube_r15.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike21 = new AModelRenderer(this);
		spike21.setRotationPoint(-0.5F, -9.0F, 5.0F);
		spikes3.addChild(spike21);
		spike21.setTextureOffset(0, 41).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike4 = new AModelRenderer(this);
		spike4.setRotationPoint(-0.5F, -9.0F, 0.0F);
		spikes3.addChild(spike4);
		spike4.setTextureOffset(0, 41).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike23 = new AModelRenderer(this);
		spike23.setRotationPoint(-7.5F, -7.0F, 5.0F);
		spikes3.addChild(spike23);


		cube_r16 = new AModelRenderer(this);
		cube_r16.setRotationPoint(-12.0F, 11.0F, -6.0F);
		spike23.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -0.3054F);
		cube_r16.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike6 = new AModelRenderer(this);
		spike6.setRotationPoint(-6.5F, -6.0F, 0.0F);
		spikes3.addChild(spike6);


		cube_r17 = new AModelRenderer(this);
		cube_r17.setRotationPoint(-13.0F, 10.0F, -6.0F);
		spike6.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, -0.3054F);
		cube_r17.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike5 = new AModelRenderer(this);
		spike5.setRotationPoint(-7.5F, -8.0F, -5.0F);
		spikes3.addChild(spike5);


		cube_r18 = new AModelRenderer(this);
		cube_r18.setRotationPoint(-12.0F, 12.0F, -6.0F);
		spike5.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, -0.3054F);
		cube_r18.setTextureOffset(0, 41).addBox(13.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike22 = new AModelRenderer(this);
		spike22.setRotationPoint(7.0F, -8.0F, 5.0F);
		spikes3.addChild(spike22);


		cube_r19 = new AModelRenderer(this);
		cube_r19.setRotationPoint(12.5F, 12.0F, -6.0F);
		spike22.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, 0.3054F);
		cube_r19.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike2 = new AModelRenderer(this);
		spike2.setRotationPoint(7.0F, -8.0F, 0.0F);
		spikes3.addChild(spike2);


		cube_r20 = new AModelRenderer(this);
		cube_r20.setRotationPoint(12.5F, 12.0F, -6.0F);
		spike2.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, 0.3054F);
		cube_r20.setTextureOffset(0, 41).addBox(-18.0F, -12.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		back = new AModelRenderer(this);
		back.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(back);
		back.setTextureOffset(31, 38).addBox(-7.0F, -7.0F, 8.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);

		spikes4 = new AModelRenderer(this);
		spikes4.setRotationPoint(0.0F, 0.0F, 0.0F);
		back.addChild(spikes4);


		spike41 = new AModelRenderer(this);
		spike41.setRotationPoint(6.0F, 0.0F, 9.0F);
		spikes4.addChild(spike41);
		spike41.setTextureOffset(0, 57).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike40 = new AModelRenderer(this);
		spike40.setRotationPoint(6.0F, 7.0F, 8.0F);
		spikes4.addChild(spike40);


		cube_r21 = new AModelRenderer(this);
		cube_r21.setRotationPoint(6.0F, 12.5F, -12.0F);
		spike40.addChild(cube_r21);
		setRotationAngle(cube_r21, -0.3054F, 0.0F, 0.0F);
		cube_r21.setTextureOffset(0, 57).addBox(-6.0F, -18.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, true);

		spike39 = new AModelRenderer(this);
		spike39.setRotationPoint(-6.5F, 0.0F, 9.0F);
		spikes4.addChild(spike39);
		spike39.setTextureOffset(0, 57).addBox(0.5F, -2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike38 = new AModelRenderer(this);
		spike38.setRotationPoint(-0.5F, 0.0F, 9.0F);
		spikes4.addChild(spike38);
		spike38.setTextureOffset(0, 57).addBox(0.5F, -2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike37 = new AModelRenderer(this);
		spike37.setRotationPoint(-5.5F, -7.0F, 8.0F);
		spikes4.addChild(spike37);


		cube_r22 = new AModelRenderer(this);
		cube_r22.setRotationPoint(5.5F, -12.5F, -12.0F);
		spike37.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.3054F, 0.0F, 0.0F);
		cube_r22.setTextureOffset(0, 57).addBox(-6.0F, 13.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike36 = new AModelRenderer(this);
		spike36.setRotationPoint(0.5F, -7.0F, 8.0F);
		spikes4.addChild(spike36);


		cube_r23 = new AModelRenderer(this);
		cube_r23.setRotationPoint(5.5F, -12.5F, -12.0F);
		spike36.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.3054F, 0.0F, 0.0F);
		cube_r23.setTextureOffset(0, 57).addBox(-6.0F, 13.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike35 = new AModelRenderer(this);
		spike35.setRotationPoint(5.5F, -8.0F, 8.0F);
		spikes4.addChild(spike35);


		cube_r24 = new AModelRenderer(this);
		cube_r24.setRotationPoint(6.5F, -11.5F, -12.0F);
		spike35.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.3054F, 0.0F, 0.0F);
		cube_r24.setTextureOffset(0, 57).addBox(-6.0F, 13.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);

		spike34 = new AModelRenderer(this);
		spike34.setRotationPoint(-6.0F, 7.0F, 7.0F);
		spikes4.addChild(spike34);


		cube_r25 = new AModelRenderer(this);
		cube_r25.setRotationPoint(6.0F, 12.5F, -11.0F);
		spike34.addChild(cube_r25);
		setRotationAngle(cube_r25, -0.3054F, 0.0F, 0.0F);
		cube_r25.setTextureOffset(0, 57).addBox(-6.0F, -18.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, true);

		spike33 = new AModelRenderer(this);
		spike33.setRotationPoint(0.0F, 7.0F, 7.0F);
		spikes4.addChild(spike33);


		cube_r26 = new AModelRenderer(this);
		cube_r26.setRotationPoint(6.0F, 12.5F, -11.0F);
		spike33.addChild(cube_r26);
		setRotationAngle(cube_r26, -0.3054F, 0.0F, 0.0F);
		cube_r26.setTextureOffset(0, 57).addBox(-6.0F, -18.0F, 7.0F, 0.0F, 5.0F, 5.0F, 0.0F, true);

		down = new AModelRenderer(this);
		down.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(down);
		down.setTextureOffset(18, 39).addBox(-7.0F, 8.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

		spikes5 = new AModelRenderer(this);
		spikes5.setRotationPoint(0.0F, 0.0F, 0.0F);
		down.addChild(spikes5);


		spike32 = new AModelRenderer(this);
		spike32.setRotationPoint(0.0F, 9.0F, -5.0F);
		spikes5.addChild(spike32);
		spike32.setTextureOffset(0, 56).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike31 = new AModelRenderer(this);
		spike31.setRotationPoint(7.0F, 8.0F, -5.0F);
		spikes5.addChild(spike31);


		cube_r27 = new AModelRenderer(this);
		cube_r27.setRotationPoint(12.5F, -12.0F, -6.0F);
		spike31.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, -0.3054F);
		cube_r27.setTextureOffset(0, 56).addBox(-18.0F, 7.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike30 = new AModelRenderer(this);
		spike30.setRotationPoint(0.5F, 8.0F, 5.0F);
		spikes5.addChild(spike30);
		spike30.setTextureOffset(0, 56).addBox(-3.0F, 1.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike29 = new AModelRenderer(this);
		spike29.setRotationPoint(0.5F, 9.0F, 0.0F);
		spikes5.addChild(spike29);
		spike29.setTextureOffset(0, 56).addBox(-3.0F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike28 = new AModelRenderer(this);
		spike28.setRotationPoint(-7.5F, 7.0F, 5.0F);
		spikes5.addChild(spike28);


		cube_r28 = new AModelRenderer(this);
		cube_r28.setRotationPoint(1.0F, 0.0F, 0.0F);
		spike28.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, 0.3054F);
		cube_r28.setTextureOffset(0, 56).addBox(-2.7061F, 0.4183F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike27 = new AModelRenderer(this);
		spike27.setRotationPoint(-7.5F, 8.0F, 0.0F);
		spikes5.addChild(spike27);


		cube_r29 = new AModelRenderer(this);
		cube_r29.setRotationPoint(-12.0F, -12.0F, -6.0F);
		spike27.addChild(cube_r29);
		setRotationAngle(cube_r29, 0.0F, 0.0F, 0.3054F);
		cube_r29.setTextureOffset(0, 56).addBox(13.0F, 7.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike26 = new AModelRenderer(this);
		spike26.setRotationPoint(-6.5F, 8.0F, -5.0F);
		spikes5.addChild(spike26);


		cube_r30 = new AModelRenderer(this);
		cube_r30.setRotationPoint(-13.0F, -12.0F, -6.0F);
		spike26.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.0F, 0.0F, 0.3054F);
		cube_r30.setTextureOffset(0, 56).addBox(13.0F, 7.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);

		spike25 = new AModelRenderer(this);
		spike25.setRotationPoint(7.0F, 7.0F, 5.0F);
		spikes5.addChild(spike25);


		cube_r31 = new AModelRenderer(this);
		cube_r31.setRotationPoint(12.5F, -11.0F, -6.0F);
		spike25.addChild(cube_r31);
		setRotationAngle(cube_r31, 0.0F, 0.0F, -0.3054F);
		cube_r31.setTextureOffset(0, 56).addBox(-18.0F, 7.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		spike24 = new AModelRenderer(this);
		spike24.setRotationPoint(7.0F, 8.0F, 0.0F);
		spikes5.addChild(spike24);


		cube_r32 = new AModelRenderer(this);
		cube_r32.setRotationPoint(12.5F, -12.0F, -6.0F);
		spike24.addChild(cube_r32);
		setRotationAngle(cube_r32, 0.0F, 0.0F, -0.3054F);
		cube_r32.setTextureOffset(0, 56).addBox(-18.0F, 7.0F, 6.0F, 5.0F, 5.0F, 0.0F, 0.0F, true);

		left_leg = new AModelRenderer(this);
		left_leg.setRotationPoint(-5.0F, 9.0F, 0.0F);
		krock.addChild(left_leg);
		left_leg.setTextureOffset(0, 47).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		left_leg.setTextureOffset(9, 54).addBox(-2.0F, 6.0F, -5.0F, 4.0F, 0.0F, 9.0F, 0.0F, false);

		right_leg = new AModelRenderer(this);
		right_leg.setRotationPoint(5.0F, 9.0F, 0.0F);
		krock.addChild(right_leg);
		right_leg.setTextureOffset(0, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		right_leg.setTextureOffset(17, 54).addBox(-2.0F, 6.0F, -5.0F, 4.0F, 0.0F, 9.0F, 0.0F, false);
		initializeAnimator(KROCK_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		krock.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(EntityKrock entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!entityIn.isSpiked()){
			spikes.showModel = false;
			spikes2.showModel = false;
			spikes3.showModel = false;
			spikes4.showModel = false;
			spikes5.showModel = false;
		}else{
			spikes.showModel = true;
			spikes2.showModel = true;
			spikes3.showModel = true;
			spikes4.showModel = true;
			spikes5.showModel = true;
		}
		if (!entityIn.getFactory().isPlaying(EntityKrock.ROLL) && !entityIn.getFactory().isPlaying(EntityKrock.START_ROLL)) {
			left_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount,0,0));
			right_leg.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount,0,0));
			wing.setCustomRotation(new Vector3(0,0,MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount));
			wing2.setCustomRotation(new Vector3(0,0,MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount));
		}
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}