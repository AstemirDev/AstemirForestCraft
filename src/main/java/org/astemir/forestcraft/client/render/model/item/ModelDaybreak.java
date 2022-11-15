package org.astemir.forestcraft.client.render.model.item;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedItemModel;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.forestcraft.common.items.weapons.guns.ItemDaybreak;


public class ModelDaybreak<T extends LivingEntity> extends AnimatedItemModel<T> {
	
	private final AModelRenderer daybreak;
	private final AModelRenderer cube_r1;
	private final AModelRenderer cube_r2;
	private final AModelRenderer pull;
	private Animation PULL = new Animation(0,"use").time(1.04f).speed(4);

	public ModelDaybreak() {
		textureWidth = 64;
		textureHeight = 64;

		daybreak = new AModelRenderer(this);
		daybreak.setRotationPoint(0.0F, 24.0F, 1.0F);
		daybreak.setTextureOffset(0, 30).addBox(-2.0F, -12.0F, 3.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		daybreak.setTextureOffset(0, 0).addBox(-4.0F, -13.0F, -5.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		daybreak.setTextureOffset(24, 0).addBox(-4.0F, -14.0F, -4.0F, 8.0F, 1.0F, 6.0F, 0.0F, false);
		daybreak.setTextureOffset(20, 16).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 1.0F, 6.0F, 0.0F, false);

		cube_r1 = new AModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		daybreak.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.48F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(32, 7).addBox(-1.0F, -3.0F, -8.0F, 2.0F, 5.0F, 3.0F, 0.0F, false);

		cube_r2 = new AModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		daybreak.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.4363F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(16, 30).addBox(-1.0F, -5.0F, 5.0F, 2.0F, 8.0F, 3.0F, 0.0F, false);

		pull = new AModelRenderer(this);
		pull.setRotationPoint(0.0F, -9.0F, -5.0F);
		daybreak.addChild(pull);
		pull.setTextureOffset(0, 16).addBox(-3.0F, -3.0F, -8.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);
		pull.setTextureOffset(28, 23).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		initializeAnimator(new AnimationFile("animations/daybreak_animations.json"));
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		daybreak.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void animate(T entityIn, ItemStack stack, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entityIn.getActiveItemStack().getItem() == stack.getItem()) {
			int i = stack.getItem().getUseDuration(stack)-entityIn.getItemInUseCount();
			if (i >= 20){
				animator.play(this,PULL,delta);
			}else{
				pull.reset();
			}
		}else{
			pull.reset();
		}
	}
}