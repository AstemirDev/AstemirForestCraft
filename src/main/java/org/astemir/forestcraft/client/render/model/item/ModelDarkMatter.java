package org.astemir.forestcraft.client.render.model.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedItemModel;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.AnimationFile;

public class ModelDarkMatter<T extends LivingEntity> extends AnimatedItemModel<T> {

	private final AModelRenderer minigun;
	private final AModelRenderer rotator;


	public static AnimationFile DARK_MATTER_ANIMATIONS = new AnimationFile("animations/dark_matter_animations.json");


	public ModelDarkMatter() {
		textureWidth = 64;
		textureHeight = 64;

		minigun = new AModelRenderer(this);
		minigun.setRotationPoint(0.0F, 24.0F, 3.0F);
		minigun.setTextureOffset(25, 23).addBox(-5.0F, -1.0F, 0.0F, 7.0F, 1.0F, 6.0F, 0.0F, false);
		minigun.setTextureOffset(0, 23).addBox(-6.0F, -6.0F, 0.0F, 9.0F, 5.0F, 7.0F, 0.0F, false);
		minigun.setTextureOffset(28, 0).addBox(-5.0F, -8.0F, 0.0F, 7.0F, 2.0F, 6.0F, 0.0F, false);
		minigun.setTextureOffset(0, 23).addBox(-2.0F, -10.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		minigun.setTextureOffset(25, 23).addBox(-2.0F, -10.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		minigun.setTextureOffset(29, 32).addBox(-2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		rotator = new AModelRenderer(this);
		rotator.setRotationPoint(-1.5F, -3.5F, 0.0F);
		minigun.addChild(rotator);
		rotator.setTextureOffset(0, 0).addBox(-2.5F, -2.5F, -18.0F, 5.0F, 5.0F, 18.0F, 0.0F, false);
		rotator.setTextureOffset(0, 0).addBox(-3.5F, -3.5F, -6.0F, 7.0F, 7.0F, 1.0F, 0.0F, false);
		rotator.setTextureOffset(0, 8).addBox(-3.5F, -3.5F, -10.0F, 7.0F, 7.0F, 1.0F, 0.0F, false);
		rotator.setTextureOffset(28, 8).addBox(-3.5F, -3.5F, -14.0F, 7.0F, 7.0F, 1.0F, 0.0F, false);
		initializeAnimator(DARK_MATTER_ANIMATIONS);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		minigun.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


	@Override
	public void animate(T entityIn, ItemStack stack, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entityIn.getActiveItemStack().getItem() == stack.getItem()) {
			rotator.customRotationZ = AstemirAPI.CLIENT.TICKS%360;
		}else{
			rotator.reset();
		}
	}
}