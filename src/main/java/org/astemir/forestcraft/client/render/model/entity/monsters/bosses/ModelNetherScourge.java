package org.astemir.forestcraft.client.render.model.entity.monsters.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import org.astemir.api.math.Vector3;
import org.astemir.api.client.animator.*;
import org.astemir.forestcraft.common.entities.monsters.bosses.EntityNetherScourge;

public class ModelNetherScourge extends AnimatedEntityModel<EntityNetherScourge> {
	
	private final AModelRenderer nether_scourge;
	private final AModelRenderer leg;
	private final AModelRenderer cube_r1;
	private final AModelRenderer leg_part;
	private final AModelRenderer cube_r2;
	private final AModelRenderer leg2;
	private final AModelRenderer cube_r3;
	private final AModelRenderer leg_part2;
	private final AModelRenderer cube_r4;
	private final AModelRenderer leg5;
	private final AModelRenderer cube_r5;
	private final AModelRenderer leg_part8;
	private final AModelRenderer cube_r6;
	private final AModelRenderer leg6;
	private final AModelRenderer cube_r7;
	private final AModelRenderer leg_part7;
	private final AModelRenderer cube_r8;
	private final AModelRenderer leg3;
	private final AModelRenderer cube_r9;
	private final AModelRenderer leg_part3;
	private final AModelRenderer cube_r10;
	private final AModelRenderer leg4;
	private final AModelRenderer cube_r11;
	private final AModelRenderer leg_part4;
	private final AModelRenderer cube_r12;
	private final AModelRenderer leg7;
	private final AModelRenderer cube_r13;
	private final AModelRenderer leg_part5;
	private final AModelRenderer cube_r14;
	private final AModelRenderer leg8;
	private final AModelRenderer cube_r15;
	private final AModelRenderer leg_part6;
	private final AModelRenderer cube_r16;
	private final AModelRenderer body_body;
	private final AModelRenderer body;
	private final AModelRenderer fake_head3;
	private final AModelRenderer cube_r17;
	private final AModelRenderer spine3;
	private final AModelRenderer cube_r18;
	private final AModelRenderer spine_4;
	private final AModelRenderer cube_r19;
	private final AModelRenderer fake_head_head3;
	private final AModelRenderer cube_r20;
	private final AModelRenderer fake_head2;
	private final AModelRenderer cube_r21;
	private final AModelRenderer spine2;
	private final AModelRenderer cube_r22;
	private final AModelRenderer spine_3;
	private final AModelRenderer cube_r23;
	private final AModelRenderer fake_head_head2;
	private final AModelRenderer cube_r24;
	private final AModelRenderer fake_head;
	private final AModelRenderer cube_r25;
	private final AModelRenderer spine;
	private final AModelRenderer cube_r26;
	private final AModelRenderer spine_2;
	private final AModelRenderer cube_r27;
	private final AModelRenderer fake_head_head;
	private final AModelRenderer cube_r28;
	private final AModelRenderer head;
	private final AModelRenderer cube_r29;
	private final AModelRenderer cube_r30;

	public static AnimationFile NETHER_SCOURGE_ANIMATIONS = new AnimationFile("animations/nether_scourge_animations.json");


	public ModelNetherScourge() {
		textureWidth = 256;
		textureHeight = 256;

		nether_scourge = new AModelRenderer(this);
		nether_scourge.setRotationPoint(0.0F, 6.0F, 0.0F);
		

		leg = new AModelRenderer(this);
		leg.setRotationPoint(-28.0F, -16.0F, 3.0F);
		nether_scourge.addChild(leg);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(23.0F, 0.0F, -3.0F);
		leg.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.2182F);
		cube_r1.setTextureOffset(0, 89).addBox(-27.0F, 5.0F, 0.0F, 7.0F, 13.0F, 6.0F, 0.0F, false);

		leg_part = new AModelRenderer(this);
		leg_part.setRotationPoint(-4.0F, 12.0F, 0.0F);
		leg.addChild(leg_part);
		leg_part.setTextureOffset(199, 98).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 14.0F, 5.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(27.0F, -12.0F, -3.0F);
		leg_part.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.2618F);
		cube_r2.setTextureOffset(86, 171).addBox(-34.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg2 = new AModelRenderer(this);
		leg2.setRotationPoint(28.0F, -16.0F, 3.0F);
		nether_scourge.addChild(leg2);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(-23.0F, 0.0F, -3.0F);
		leg2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.2182F);
		cube_r3.setTextureOffset(0, 89).addBox(20.0F, 5.0F, 0.0F, 7.0F, 13.0F, 6.0F, 0.0F, false);

		leg_part2 = new AModelRenderer(this);
		leg_part2.setRotationPoint(3.0F, 13.0F, 0.0F);
		leg2.addChild(leg_part2);
		leg_part2.setTextureOffset(199, 98).addBox(-2.0F, -1.0F, -2.5F, 6.0F, 14.0F, 5.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(-26.0F, -13.0F, -3.0F);
		leg_part2.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2618F);
		cube_r4.setTextureOffset(86, 171).addBox(31.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg5 = new AModelRenderer(this);
		leg5.setRotationPoint(14.0F, -19.0F, 27.0F);
		nether_scourge.addChild(leg5);
		

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(-7.0F, 3.0F, -3.0F);
		leg5.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.9163F);
		cube_r5.setTextureOffset(124, 0).addBox(3.0F, 3.0F, 0.0F, 7.0F, 28.0F, 6.0F, 0.0F, false);

		leg_part8 = new AModelRenderer(this);
		leg_part8.setRotationPoint(21.0F, 15.0F, 0.0F);
		leg5.addChild(leg_part8);
		leg_part8.setTextureOffset(199, 98).addBox(-4.0F, 0.0F, -2.5F, 6.0F, 14.0F, 5.0F, 0.0F, false);

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(-28.0F, -12.0F, -3.0F);
		leg_part8.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.2618F);
		cube_r6.setTextureOffset(86, 171).addBox(31.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg6 = new AModelRenderer(this);
		leg6.setRotationPoint(-19.0F, -20.0F, 27.0F);
		nether_scourge.addChild(leg6);
		

		cube_r7 = new AModelRenderer(this);
		cube_r7.setRotationPoint(8.0F, 4.0F, -3.0F);
		leg6.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.9163F);
		cube_r7.setTextureOffset(124, 0).addBox(-10.0F, 3.0F, 0.0F, 7.0F, 28.0F, 6.0F, 0.0F, false);

		leg_part7 = new AModelRenderer(this);
		leg_part7.setRotationPoint(-19.0F, 17.0F, 0.0F);
		leg6.addChild(leg_part7);
		leg_part7.setTextureOffset(199, 98).addBox(-3.0F, -1.0F, -2.5F, 6.0F, 14.0F, 5.0F, 0.0F, false);

		cube_r8 = new AModelRenderer(this);
		cube_r8.setRotationPoint(27.0F, -13.0F, -3.0F);
		leg_part7.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -0.2618F);
		cube_r8.setTextureOffset(86, 171).addBox(-34.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg3 = new AModelRenderer(this);
		leg3.setRotationPoint(19.0F, -9.0F, -6.0F);
		nether_scourge.addChild(leg3);
		setRotationAngle(leg3, 0.0F, 1.2217F, 0.0F);
		

		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(-25.7172F, -7.0F, -3.4098F);
		leg3.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, -0.2182F);
		cube_r9.setTextureOffset(20, 195).addBox(20.0F, 13.0F, 0.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);

		leg_part3 = new AModelRenderer(this);
		leg_part3.setRotationPoint(3.0F, 10.0F, 0.0F);
		leg3.addChild(leg_part3);
		leg_part3.setTextureOffset(22, 211).addBox(-4.7172F, 0.0F, -2.9098F, 6.0F, 9.0F, 5.0F, 0.0F, false);

		cube_r10 = new AModelRenderer(this);
		cube_r10.setRotationPoint(-28.7172F, -17.0F, -3.4098F);
		leg_part3.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.2618F);
		cube_r10.setTextureOffset(86, 171).addBox(31.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg4 = new AModelRenderer(this);
		leg4.setRotationPoint(-18.0F, -8.0F, -6.0F);
		nether_scourge.addChild(leg4);
		setRotationAngle(leg4, 0.0F, -1.2217F, 0.0F);
		

		cube_r11 = new AModelRenderer(this);
		cube_r11.setRotationPoint(25.3752F, -8.0F, -2.4702F);
		leg4.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, 0.2182F);
		cube_r11.setTextureOffset(20, 195).addBox(-27.0F, 13.0F, 0.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);

		leg_part4 = new AModelRenderer(this);
		leg_part4.setRotationPoint(-4.0F, 10.0F, 2.0F);
		leg4.addChild(leg_part4);
		leg_part4.setTextureOffset(22, 211).addBox(-0.6248F, -1.0F, -3.9702F, 6.0F, 9.0F, 5.0F, 0.0F, false);

		cube_r12 = new AModelRenderer(this);
		cube_r12.setRotationPoint(29.3752F, -18.0F, -4.4702F);
		leg_part4.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, -0.2618F);
		cube_r12.setTextureOffset(86, 171).addBox(-34.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg7 = new AModelRenderer(this);
		leg7.setRotationPoint(9.0F, -8.0F, 38.0F);
		nether_scourge.addChild(leg7);
		setRotationAngle(leg7, 0.0F, -1.2217F, 0.0F);
		

		cube_r13 = new AModelRenderer(this);
		cube_r13.setRotationPoint(-27.2059F, -8.0F, -4.1473F);
		leg7.addChild(cube_r13);
		setRotationAngle(cube_r13, 0.0F, 0.0F, -0.2182F);
		cube_r13.setTextureOffset(20, 195).addBox(20.0F, 13.0F, 0.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);

		leg_part5 = new AModelRenderer(this);
		leg_part5.setRotationPoint(-1.0F, 9.0F, -1.0F);
		leg7.addChild(leg_part5);
		leg_part5.setTextureOffset(22, 211).addBox(-2.2059F, 0.0F, -2.6473F, 6.0F, 9.0F, 5.0F, 0.0F, false);

		cube_r14 = new AModelRenderer(this);
		cube_r14.setRotationPoint(-26.2059F, -17.0F, -3.1473F);
		leg_part5.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.0F, 0.0F, 0.2618F);
		cube_r14.setTextureOffset(86, 171).addBox(31.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		leg8 = new AModelRenderer(this);
		leg8.setRotationPoint(-24.0F, -9.0F, 42.0F);
		nether_scourge.addChild(leg8);
		setRotationAngle(leg8, 0.0F, 1.2217F, 0.0F);
		

		cube_r15 = new AModelRenderer(this);
		cube_r15.setRotationPoint(26.6082F, -7.0F, -2.8656F);
		leg8.addChild(cube_r15);
		setRotationAngle(cube_r15, 0.0F, 0.0F, 0.2182F);
		cube_r15.setTextureOffset(20, 195).addBox(-27.0F, 13.0F, 0.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);

		leg_part6 = new AModelRenderer(this);
		leg_part6.setRotationPoint(0.0F, 10.0F, 0.0F);
		leg8.addChild(leg_part6);
		leg_part6.setTextureOffset(22, 211).addBox(-3.3918F, 0.0F, -2.3656F, 6.0F, 9.0F, 5.0F, 0.0F, false);

		cube_r16 = new AModelRenderer(this);
		cube_r16.setRotationPoint(26.6082F, -17.0F, -2.8656F);
		leg_part6.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.0F, 0.0F, -0.2618F);
		cube_r16.setTextureOffset(86, 171).addBox(-34.0F, 17.0F, 1.5F, 3.0F, 9.0F, 4.0F, 0.0F, false);

		body_body = new AModelRenderer(this);
		body_body.setRotationPoint(0.0F, 0.0F, 14.0F);
		nether_scourge.addChild(body_body);
		

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 5.0F);
		body_body.addChild(body);
		body.setTextureOffset(158, 0).addBox(10.0F, -31.0F, -4.0F, 10.0F, 31.0F, 14.0F, 0.0F, false);
		body.setTextureOffset(130, 119).addBox(10.0F, -15.0F, -25.0F, 10.0F, 15.0F, 21.0F, 0.0F, false);
		body.setTextureOffset(96, 40).addBox(-20.0F, -23.0F, -25.0F, 12.0F, 23.0F, 21.0F, 0.0F, false);
		body.setTextureOffset(0, 89).addBox(-28.0F, -19.0F, -25.0F, 8.0F, 19.0F, 29.0F, 0.0F, false);
		body.setTextureOffset(51, 60).addBox(20.0F, -19.0F, -25.0F, 8.0F, 19.0F, 29.0F, 0.0F, false);
		body.setTextureOffset(162, 45).addBox(-9.0F, -20.0F, -10.0F, 19.0F, 20.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-22.0F, 0.0F, -21.0F, 45.0F, 6.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(0, 137).addBox(-9.0F, -29.0F, 0.0F, 19.0F, 29.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(0, 40).addBox(-16.0F, -39.0F, 10.0F, 30.0F, 39.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(130, 155).addBox(-20.0F, -29.0F, -5.0F, 11.0F, 29.0F, 15.0F, 0.0F, false);
		body.setTextureOffset(74, 108).addBox(-29.0F, -48.0F, 10.0F, 13.0F, 48.0F, 15.0F, 0.0F, false);

		fake_head3 = new AModelRenderer(this);
		fake_head3.setRotationPoint(11.0F, -38.0F, 15.0F);
		body.addChild(fake_head3);
		

		cube_r17 = new AModelRenderer(this);
		cube_r17.setRotationPoint(-18.0F, 28.0F, -19.0F);
		fake_head3.addChild(cube_r17);
		setRotationAngle(cube_r17, 0.0F, 0.0F, 0.2618F);
		cube_r17.setTextureOffset(0, 205).addBox(8.0F, -39.0F, 17.0F, 5.0F, 10.0F, 6.0F, 0.0F, false);

		spine3 = new AModelRenderer(this);
		spine3.setRotationPoint(2.0F, -6.0F, 1.0F);
		fake_head3.addChild(spine3);
		

		cube_r18 = new AModelRenderer(this);
		cube_r18.setRotationPoint(-20.0F, 34.0F, -20.0F);
		spine3.addChild(cube_r18);
		setRotationAngle(cube_r18, 0.0F, 0.0F, 0.1309F);
		cube_r18.setTextureOffset(130, 119).addBox(13.5F, -45.0F, 17.5F, 4.0F, 8.0F, 5.0F, 0.0F, false);

		spine_4 = new AModelRenderer(this);
		spine_4.setRotationPoint(0.5F, -8.0F, 0.0F);
		spine3.addChild(spine_4);
		

		cube_r19 = new AModelRenderer(this);
		cube_r19.setRotationPoint(-20.5F, 42.0F, -20.0F);
		spine_4.addChild(cube_r19);
		setRotationAngle(cube_r19, 0.0F, 0.0F, -0.2618F);
		cube_r19.setTextureOffset(206, 28).addBox(28.75F, -43.0F, 17.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		fake_head_head3 = new AModelRenderer(this);
		fake_head_head3.setRotationPoint(-1.5F, -7.0F, 0.0F);
		spine_4.addChild(fake_head_head3);
		

		cube_r20 = new AModelRenderer(this);
		cube_r20.setRotationPoint(-19.0F, 49.0F, -20.0F);
		fake_head_head3.addChild(cube_r20);
		setRotationAngle(cube_r20, 0.0F, 0.0F, -0.2618F);
		cube_r20.setTextureOffset(50, 176).addBox(27.25F, -50.0F, 15.75F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		fake_head2 = new AModelRenderer(this);
		fake_head2.setRotationPoint(14.0F, -13.0F, -16.0F);
		body.addChild(fake_head2);
		

		cube_r21 = new AModelRenderer(this);
		cube_r21.setRotationPoint(-19.0F, 31.0F, -21.0F);
		fake_head2.addChild(cube_r21);
		setRotationAngle(cube_r21, 0.0F, 0.0F, 0.2618F);
		cube_r21.setTextureOffset(0, 205).addBox(8.0F, -39.0F, 17.0F, 5.0F, 10.0F, 6.0F, 0.0F, false);

		spine2 = new AModelRenderer(this);
		spine2.setRotationPoint(1.0F, -4.0F, -1.0F);
		fake_head2.addChild(spine2);
		

		cube_r22 = new AModelRenderer(this);
		cube_r22.setRotationPoint(-20.0F, 35.0F, -20.0F);
		spine2.addChild(cube_r22);
		setRotationAngle(cube_r22, 0.0F, 0.0F, 0.1309F);
		cube_r22.setTextureOffset(130, 119).addBox(13.5F, -45.0F, 17.5F, 4.0F, 8.0F, 5.0F, 0.0F, false);

		spine_3 = new AModelRenderer(this);
		spine_3.setRotationPoint(1.0F, -7.0F, 0.0F);
		spine2.addChild(spine_3);
		

		cube_r23 = new AModelRenderer(this);
		cube_r23.setRotationPoint(-21.0F, 42.0F, -20.0F);
		spine_3.addChild(cube_r23);
		setRotationAngle(cube_r23, 0.0F, 0.0F, -0.2618F);
		cube_r23.setTextureOffset(206, 28).addBox(28.75F, -43.0F, 17.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		fake_head_head2 = new AModelRenderer(this);
		fake_head_head2.setRotationPoint(-2.0F, -9.0F, 0.0F);
		spine_3.addChild(fake_head_head2);
		

		cube_r24 = new AModelRenderer(this);
		cube_r24.setRotationPoint(-19.0F, 51.0F, -20.0F);
		fake_head_head2.addChild(cube_r24);
		setRotationAngle(cube_r24, 0.0F, 0.0F, -0.2618F);
		cube_r24.setTextureOffset(178, 141).addBox(23.75F, -57.0F, 13.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		fake_head = new AModelRenderer(this);
		fake_head.setRotationPoint(-16.0F, -26.0F, 2.0F);
		body.addChild(fake_head);
		

		cube_r25 = new AModelRenderer(this);
		cube_r25.setRotationPoint(16.0F, 26.0F, -21.0F);
		fake_head.addChild(cube_r25);
		setRotationAngle(cube_r25, 0.0F, 0.0F, -0.2618F);
		cube_r25.setTextureOffset(0, 205).addBox(-13.0F, -39.0F, 17.0F, 5.0F, 10.0F, 6.0F, 0.0F, false);

		spine = new AModelRenderer(this);
		spine.setRotationPoint(-4.0F, -8.0F, 0.0F);
		fake_head.addChild(spine);
		

		cube_r26 = new AModelRenderer(this);
		cube_r26.setRotationPoint(20.0F, 34.0F, -21.0F);
		spine.addChild(cube_r26);
		setRotationAngle(cube_r26, 0.0F, 0.0F, -0.1309F);
		cube_r26.setTextureOffset(130, 119).addBox(-17.5F, -45.0F, 17.5F, 4.0F, 8.0F, 5.0F, 0.0F, false);

		spine_2 = new AModelRenderer(this);
		spine_2.setRotationPoint(-1.0F, -8.0F, -1.0F);
		spine.addChild(spine_2);
		

		cube_r27 = new AModelRenderer(this);
		cube_r27.setRotationPoint(21.0F, 42.0F, -20.0F);
		spine_2.addChild(cube_r27);
		setRotationAngle(cube_r27, 0.0F, 0.0F, 0.2618F);
		cube_r27.setTextureOffset(206, 28).addBox(-33.75F, -43.0F, 17.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		fake_head_head = new AModelRenderer(this);
		fake_head_head.setRotationPoint(2.0F, -7.0F, -1.0F);
		spine_2.addChild(fake_head_head);
		

		cube_r28 = new AModelRenderer(this);
		cube_r28.setRotationPoint(19.0F, 49.0F, -19.0F);
		fake_head_head.addChild(cube_r28);
		setRotationAngle(cube_r28, 0.0F, 0.0F, 0.2618F);
		cube_r28.setTextureOffset(178, 141).addBox(-37.75F, -57.0F, 13.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		head = new AModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -15.0F);
		body.addChild(head);
		head.setTextureOffset(171, 119).addBox(-10.0F, -18.0F, -15.0F, 21.0F, 6.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(125, 84).addBox(-9.0F, -17.0F, -13.0F, 19.0F, 17.0F, 18.0F, 0.0F, false);

		cube_r29 = new AModelRenderer(this);
		cube_r29.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r29);
		setRotationAngle(cube_r29, -1.3526F, 0.0F, 0.0F);
		cube_r29.setTextureOffset(0, 176).addBox(-8.0F, 14.25F, -17.0F, 17.0F, 2.0F, 7.0F, 0.0F, false);
		cube_r29.setTextureOffset(181, 89).addBox(-8.0F, 15.75F, -5.75F, 17.0F, 2.0F, 7.0F, 0.0F, false);

		cube_r30 = new AModelRenderer(this);
		cube_r30.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(cube_r30);
		setRotationAngle(cube_r30, 0.48F, 0.0F, 0.0F);
		cube_r30.setTextureOffset(161, 206).addBox(7.0F, -17.0F, -11.0F, 2.0F, 3.0F, 9.0F, 0.0F, false);
		cube_r30.setTextureOffset(112, 209).addBox(-8.5F, -17.0F, -11.0F, 18.0F, 3.0F, 9.0F, 0.0F, false);
		cube_r30.setTextureOffset(70, 40).addBox(-8.5F, -21.0F, -11.0F, 18.0F, 4.0F, 5.0F, 0.0F, false);
		cube_r30.setTextureOffset(80, 49).addBox(7.5F, -10.0F, -14.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
		cube_r30.setTextureOffset(175, 213).addBox(-8.5F, -10.0F, -14.0F, 2.0F, 3.0F, 8.0F, 0.0F, false);
		cube_r30.setTextureOffset(181, 75).addBox(-8.5F, -7.0F, -16.0F, 18.0F, 4.0F, 10.0F, 0.0F, false);
		initializeAnimator(NETHER_SCOURGE_ANIMATIONS);
	}


	@Override
	public void animate(EntityNetherScourge entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.setCustomRotation(new Vector3((float) Math.toRadians(Math.min(30, Math.max(-30, headPitch))),(float) Math.toRadians(Math.min(30, Math.max(-30, netHeadYaw))),0));
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityNetherScourge.RUN || animation == EntityNetherScourge.IDLE){
			if (ignoreUsedPartsInOtherAnimations(animated,part,EntityNetherScourge.SCREAM)){
				return false;
			}
		}
		return true;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		nether_scourge.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
		modelRenderer.setDefaultRotation(new Vector3(x,y,z));
	}
}