package org.astemir.forestcraft.client.render.model.entity.monsters;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import org.astemir.api.client.animator.*;
import org.astemir.api.math.MathUtils;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.common.entities.monsters.EntityChangeling;

public class ModelChangeling extends AnimatedEntityModel<EntityChangeling> {

	private final AModelRenderer changeling;
	private final AModelRenderer leg3;
	private final AModelRenderer leg2;
	private final AModelRenderer leg0;
	private final AModelRenderer leg1;
	private final AModelRenderer body;
	private final AModelRenderer head;
	private final AModelRenderer jaw;
	private final AModelRenderer jaw_up;
	private final AModelRenderer jaw_down;
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
	private final AModelRenderer overcute_eyes;
	private final AModelRenderer eye;
	private final AModelRenderer eye2;
	private final AModelRenderer ear;
	private final AModelRenderer ear2;
	private final AModelRenderer upperBody;
	private final AModelRenderer upperBody_r1;
	private final AModelRenderer lowerBody;
	private final AModelRenderer lowerBody_r1;
	private final AModelRenderer tail;
	private final AModelRenderer tail2;
	private final AModelRenderer tail_r1;
	private final AModelRenderer tail3;
	private final AModelRenderer tail_r2;
	private final AModelRenderer parahand1;
	private final AModelRenderer a2;
	private final AModelRenderer b2;
	private final AModelRenderer c2;
	private final AModelRenderer d2;
	private final AModelRenderer claw6;
	private final AModelRenderer cube_r9;
	private final AModelRenderer cube_r10;
	private final AModelRenderer parahand2;
	private final AModelRenderer a3;
	private final AModelRenderer b3;
	private final AModelRenderer c3;
	private final AModelRenderer d3;
	private final AModelRenderer claw10;
	private final AModelRenderer cube_r11;
	private final AModelRenderer cube_r12;
	private final AModelRenderer parahand3;
	private final AModelRenderer a4;
	private final AModelRenderer b4;
	private final AModelRenderer c4;
	private final AModelRenderer d4;
	private final AModelRenderer claw14;
	private final AModelRenderer cube_r13;
	private final AModelRenderer cube_r14;
	private final AModelRenderer parahand4;
	private final AModelRenderer a5;
	private final AModelRenderer b5;
	private final AModelRenderer c5;
	private final AModelRenderer d5;
	private final AModelRenderer claw18;
	private final AModelRenderer cube_r15;
	private final AModelRenderer cube_r16;


	public ModelChangeling() {
		textureWidth = 128;
		textureHeight = 128;

		changeling = new AModelRenderer(this);
		changeling.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leg3 = new AModelRenderer(this);
		leg3.setRotationPoint(-0.5F, -8.0F, -4.0F);
		changeling.addChild(leg3);
		leg3.setTextureOffset(10, 65).addBox(-2.0F, 4.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		leg3.setTextureOffset(70, 27).addBox(-2.5F, 0.0F, -0.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		leg2 = new AModelRenderer(this);
		leg2.setRotationPoint(2.5F, -8.0F, -4.0F);
		changeling.addChild(leg2);
		leg2.setTextureOffset(70, 34).addBox(0.0F, 4.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		leg2.setTextureOffset(70, 43).addBox(-0.5F, 0.0F, -0.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		leg0 = new AModelRenderer(this);
		leg0.setRotationPoint(2.5F, -8.0F, 7.0F);
		changeling.addChild(leg0);
		leg0.setTextureOffset(0, 29).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		leg1 = new AModelRenderer(this);
		leg1.setRotationPoint(-0.5F, -8.0F, 7.0F);
		changeling.addChild(leg1);
		leg1.setTextureOffset(0, 0).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		body = new AModelRenderer(this);
		body.setRotationPoint(0.0F, -10.0F, 2.0F);
		changeling.addChild(body);
		

		head = new AModelRenderer(this);
		head.setRotationPoint(1.0F, -0.5F, -9.0F);
		body.addChild(head);
		head.setTextureOffset(24, 45).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 5.0F, 0.0F, false);

		jaw = new AModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(jaw);
		

		jaw_up = new AModelRenderer(this);
		jaw_up.setRotationPoint(0.0F, 1.0F, -2.25F);
		jaw.addChild(jaw_up);
		jaw_up.setTextureOffset(20, 34).addBox(-1.5F, -1.02F, -1.75F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		jaw_down = new AModelRenderer(this);
		jaw_down.setRotationPoint(0.0F, 2.5F, -2.0F);
		jaw.addChild(jaw_down);
		jaw_down.setTextureOffset(42, 25).addBox(-1.5F, -0.52F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		parahead = new AModelRenderer(this);
		parahead.setRotationPoint(0.0F, 1.75F, -2.0F);
		head.addChild(parahead);
		

		a = new AModelRenderer(this);
		a.setRotationPoint(0.25F, 0.0F, 0.0F);
		parahead.addChild(a);
		a.setTextureOffset(12, 56).addBox(-1.25F, -1.25F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		b = new AModelRenderer(this);
		b.setRotationPoint(-0.25F, -0.25F, -4.0F);
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
		claw.setRotationPoint(-3.0F, 0.0F, -4.0F);
		mega_jaw.addChild(claw);
		

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(3.0F, 0.0F, 11.0F);
		claw.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.2182F, 0.0F);
		cube_r1.setTextureOffset(58, 48).addBox(-2.0F, -1.5F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(3.0F, 0.0F, 11.0F);
		claw.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.3054F, 0.0F);
		cube_r2.setTextureOffset(50, 66).addBox(-9.25F, -1.0F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		claw3 = new AModelRenderer(this);
		claw3.setRotationPoint(0.0F, -3.0F, -4.0F);
		mega_jaw.addChild(claw3);
		

		cube_r3 = new AModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 3.0F, 11.0F);
		claw3.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.2182F, 0.0F, 0.0F);
		cube_r3.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r4 = new AModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 3.0F, 11.0F);
		claw3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.3054F, 0.0F, 0.0F);
		cube_r4.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		claw4 = new AModelRenderer(this);
		claw4.setRotationPoint(0.0F, 3.0F, -4.0F);
		mega_jaw.addChild(claw4);
		

		cube_r5 = new AModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, -3.0F, 11.0F);
		claw4.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.2182F, 0.0F, 0.0F);
		cube_r5.setTextureOffset(18, 56).addBox(-1.5F, -1.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r6 = new AModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, -3.0F, 11.0F);
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

		overcute_eyes = new AModelRenderer(this);
		overcute_eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(overcute_eyes);
		

		eye = new AModelRenderer(this);
		eye.setRotationPoint(2.0F, 0.0F, -2.25F);
		overcute_eyes.addChild(eye);
		eye.setTextureOffset(77, 55).addBox(-2.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, false);

		eye2 = new AModelRenderer(this);
		eye2.setRotationPoint(-2.0F, 0.0F, -2.25F);
		overcute_eyes.addChild(eye2);
		eye2.setTextureOffset(77, 55).addBox(-2.0F, -4.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, false);

		ear = new AModelRenderer(this);
		ear.setRotationPoint(2.5F, -3.0F, 0.5F);
		head.addChild(ear);
		ear.setTextureOffset(46, 45).addBox(-1.5F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		ear.setTextureOffset(32, 34).addBox(0.5F, -4.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		ear2 = new AModelRenderer(this);
		ear2.setRotationPoint(-2.5F, -3.0F, 0.5F);
		head.addChild(ear2);
		ear2.setTextureOffset(25, 15).addBox(-1.5F, -4.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		ear2.setTextureOffset(0, 15).addBox(-0.5F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		upperBody = new AModelRenderer(this);
		upperBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(upperBody);
		

		upperBody_r1 = new AModelRenderer(this);
		upperBody_r1.setRotationPoint(0.0F, 10.0F, -2.0F);
		upperBody.addChild(upperBody_r1);
		setRotationAngle(upperBody_r1, -0.0436F, 0.0F, 0.0F);
		upperBody_r1.setTextureOffset(34, 0).addBox(-3.5F, -13.5F, -5.0F, 9.0F, 7.0F, 6.0F, 0.0F, false);

		lowerBody = new AModelRenderer(this);
		lowerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(lowerBody);
		

		lowerBody_r1 = new AModelRenderer(this);
		lowerBody_r1.setRotationPoint(0.0F, 10.0F, -2.0F);
		lowerBody.addChild(lowerBody_r1);
		setRotationAngle(lowerBody_r1, -0.3054F, 0.0F, 0.0F);
		lowerBody_r1.setTextureOffset(0, 0).addBox(-3.0F, -12.5F, -3.0F, 8.0F, 6.0F, 9.0F, 0.0F, false);

		tail = new AModelRenderer(this);
		tail.setRotationPoint(1.0F, 0.0F, 5.0F);
		lowerBody.addChild(tail);
		setRotationAngle(tail, 2.2253F, 0.0F, 0.0F);
		tail.setTextureOffset(66, 68).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		tail2 = new AModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail.addChild(tail2);
		

		tail_r1 = new AModelRenderer(this);
		tail_r1.setRotationPoint(-1.0F, 12.0F, -8.0F);
		tail2.addChild(tail_r1);
		setRotationAngle(tail_r1, 0.4363F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(30, 34).addBox(-3.0F, -5.0F, 7.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);

		tail3 = new AModelRenderer(this);
		tail3.setRotationPoint(0.0F, 8.0F, 0.0F);
		tail2.addChild(tail3);
		

		tail_r2 = new AModelRenderer(this);
		tail_r2.setRotationPoint(-1.0F, 4.0F, -8.0F);
		tail3.addChild(tail_r2);
		setRotationAngle(tail_r2, 0.4363F, 0.0F, 0.0F);
		tail_r2.setTextureOffset(0, 15).addBox(-3.5F, 0.0F, 6.25F, 9.0F, 7.0F, 7.0F, 0.0F, false);

		parahand1 = new AModelRenderer(this);
		parahand1.setRotationPoint(5.0F, 1.25F, 3.25F);
		body.addChild(parahand1);
		setRotationAngle(parahand1, 0.0F, -0.829F, 0.0F);
		

		a2 = new AModelRenderer(this);
		a2.setRotationPoint(0.1274F, 0.0F, 1.244F);
		parahand1.addChild(a2);
		a2.setTextureOffset(12, 56).addBox(-1.25F, -1.25F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		b2 = new AModelRenderer(this);
		b2.setRotationPoint(-0.25F, -0.25F, -4.0F);
		a2.addChild(b2);
		b2.setTextureOffset(28, 69).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		c2 = new AModelRenderer(this);
		c2.setRotationPoint(0.0F, 0.0F, -4.0F);
		b2.addChild(c2);
		c2.setTextureOffset(52, 34).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		d2 = new AModelRenderer(this);
		d2.setRotationPoint(0.0F, 0.0F, -4.0F);
		c2.addChild(d2);
		

		claw6 = new AModelRenderer(this);
		claw6.setRotationPoint(0.0F, -3.0F, -11.0F);
		d2.addChild(claw6);
		

		cube_r9 = new AModelRenderer(this);
		cube_r9.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw6.addChild(cube_r9);
		setRotationAngle(cube_r9, -0.2182F, 0.0F, 0.0F);
		cube_r9.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r10 = new AModelRenderer(this);
		cube_r10.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw6.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.3054F, 0.0F, 0.0F);
		cube_r10.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		parahand2 = new AModelRenderer(this);
		parahand2.setRotationPoint(-3.0F, 1.25F, 3.25F);
		body.addChild(parahand2);
		setRotationAngle(parahand2, 0.0F, 0.829F, 0.0F);
		

		a3 = new AModelRenderer(this);
		a3.setRotationPoint(-0.1274F, 0.0F, 1.244F);
		parahand2.addChild(a3);
		a3.setTextureOffset(12, 56).addBox(-0.75F, -1.25F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

		b3 = new AModelRenderer(this);
		b3.setRotationPoint(0.25F, -0.25F, -4.0F);
		a3.addChild(b3);
		b3.setTextureOffset(28, 69).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, true);

		c3 = new AModelRenderer(this);
		c3.setRotationPoint(0.0F, 0.0F, -4.0F);
		b3.addChild(c3);
		c3.setTextureOffset(52, 34).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

		d3 = new AModelRenderer(this);
		d3.setRotationPoint(0.0F, 0.0F, -4.0F);
		c3.addChild(d3);
		

		claw10 = new AModelRenderer(this);
		claw10.setRotationPoint(0.0F, -3.0F, -11.0F);
		d3.addChild(claw10);
		

		cube_r11 = new AModelRenderer(this);
		cube_r11.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw10.addChild(cube_r11);
		setRotationAngle(cube_r11, -0.2182F, 0.0F, 0.0F);
		cube_r11.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, true);

		cube_r12 = new AModelRenderer(this);
		cube_r12.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw10.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.3054F, 0.0F, 0.0F);
		cube_r12.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, true);

		parahand3 = new AModelRenderer(this);
		parahand3.setRotationPoint(-1.0F, 1.25F, -3.75F);
		body.addChild(parahand3);
		setRotationAngle(parahand3, 0.5236F, 0.829F, 0.0F);
		

		a4 = new AModelRenderer(this);
		a4.setRotationPoint(-0.1274F, 0.0F, 1.244F);
		parahand3.addChild(a4);
		a4.setTextureOffset(12, 56).addBox(-0.75F, -1.25F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

		b4 = new AModelRenderer(this);
		b4.setRotationPoint(0.25F, -0.25F, -4.0F);
		a4.addChild(b4);
		b4.setTextureOffset(28, 69).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, true);

		c4 = new AModelRenderer(this);
		c4.setRotationPoint(0.0F, 0.0F, -4.0F);
		b4.addChild(c4);
		c4.setTextureOffset(52, 34).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

		d4 = new AModelRenderer(this);
		d4.setRotationPoint(0.0F, 0.0F, -4.0F);
		c4.addChild(d4);
		

		claw14 = new AModelRenderer(this);
		claw14.setRotationPoint(0.0F, -3.0F, -11.0F);
		d4.addChild(claw14);
		

		cube_r13 = new AModelRenderer(this);
		cube_r13.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw14.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.2182F, 0.0F, 0.0F);
		cube_r13.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, true);

		cube_r14 = new AModelRenderer(this);
		cube_r14.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw14.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.3054F, 0.0F, 0.0F);
		cube_r14.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, true);

		parahand4 = new AModelRenderer(this);
		parahand4.setRotationPoint(3.0F, 1.25F, -3.75F);
		body.addChild(parahand4);
		setRotationAngle(parahand4, 0.5236F, -0.829F, 0.0F);
		

		a5 = new AModelRenderer(this);
		a5.setRotationPoint(0.1274F, 0.0F, 1.244F);
		parahand4.addChild(a5);
		a5.setTextureOffset(12, 56).addBox(-1.25F, -1.25F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		b5 = new AModelRenderer(this);
		b5.setRotationPoint(-0.25F, -0.25F, -4.0F);
		a5.addChild(b5);
		b5.setTextureOffset(28, 69).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		c5 = new AModelRenderer(this);
		c5.setRotationPoint(0.0F, 0.0F, -4.0F);
		b5.addChild(c5);
		c5.setTextureOffset(52, 34).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		d5 = new AModelRenderer(this);
		d5.setRotationPoint(0.0F, 0.0F, -4.0F);
		c5.addChild(d5);
		

		claw18 = new AModelRenderer(this);
		claw18.setRotationPoint(0.0F, -3.0F, -11.0F);
		d5.addChild(claw18);
		

		cube_r15 = new AModelRenderer(this);
		cube_r15.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw18.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.2182F, 0.0F, 0.0F);
		cube_r15.setTextureOffset(52, 57).addBox(-1.5F, -2.0F, -16.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		cube_r16 = new AModelRenderer(this);
		cube_r16.setRotationPoint(0.0F, 6.0F, 22.0F);
		claw18.addChild(cube_r16);
		setRotationAngle(cube_r16, 0.3054F, 0.0F, 0.0F);
		cube_r16.setTextureOffset(50, 66).addBox(-1.0F, -9.25F, -19.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/changeling_animations.json"));
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		changeling.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(EntityChangeling entityIn, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.setCustomRotation(new Vector3(MathUtils.rad(Math.min(30, Math.max(-30, headPitch))),MathUtils.rad(Math.min(30, Math.max(-30, netHeadYaw))),0));
		this.leg0.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount, 0, 0));
		this.leg1.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount, 0, 0));
		this.leg2.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount, 0, 0));
		this.leg3.setCustomRotation(new Vector3(MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount, 0, 0));
		if (entityIn.getFactory().isPlaying(EntityChangeling.CUTE_EYES)){
			overcute_eyes.showModel = true;
		}else{
			overcute_eyes.showModel = false;
		}
		if (entityIn.getFactory().isPlaying(EntityChangeling.TRANSFORM)){
			if (entityIn.getFactory().getAnimationData(EntityChangeling.TRANSFORM).getTicks() >= 2){
				parahand1.showModel = true;
				parahand2.showModel = true;
				parahand3.showModel = true;
				parahand4.showModel = true;
				parahead.showModel = true;
			}
		}else{
			if (entityIn.isTransformed()){
				parahand1.showModel = true;
				parahand2.showModel = true;
				parahand3.showModel = true;
				parahand4.showModel = true;
				parahead.showModel = true;
			}else{
				parahand1.showModel = false;
				parahand2.showModel = false;
				parahand3.showModel = false;
				parahand4.showModel = false;
				parahead.showModel = false;
			}
		}
	}

	@Override
	public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta) {
		if (animation == EntityChangeling.IDLE) {
			if (ignoreUsedPartsInOtherAnimations(animated, part, EntityChangeling.WHINING)) {
				return false;
			}
		}
		if (animation == EntityChangeling.IDLE_PARASITE) {
			if (ignoreUsedPartsInOtherAnimations(animated, part, EntityChangeling.ATTACK_0, EntityChangeling.ATTACK_1,EntityChangeling.ATTACK_2)) {
				return false;
			}
		}
		return true;
	}
}