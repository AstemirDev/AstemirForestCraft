package org.astemir.forestcraft.client.render.model.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.client.animator.AnimatedItemModel;
import org.astemir.api.client.animator.Animation;
import org.astemir.api.client.animator.AnimationFile;
import org.astemir.api.math.Vector2;
import org.astemir.api.math.Vector3;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;

public class ModelOldCompass<T extends LivingEntity> extends AnimatedItemModel<T> {

	public static AnimationFile OLD_COMPASS_ANIMATIONS = new AnimationFile("animations/old_compass_animations.json");

	private Animation IDLE = new Animation(0,"idle").time(1.04f).speed(6f).loop();
	private Animation OPEN = new Animation(1,"open").time(0.52f).speed(2f);

	private final AModelRenderer compass;
	private final AModelRenderer lid;
	private final AModelRenderer arrow;
	private boolean opened = false;

	public ModelOldCompass() {
		textureWidth = 128;
		textureHeight = 128;

		compass = new AModelRenderer(this);
		compass.setRotationPoint(0.0F, 24.0F, 0.0F);
		compass.setTextureOffset(0, 18).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		compass.setTextureOffset(0, 36).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		compass.setTextureOffset(0, 52).addBox(-8.0F, -4.0F, -8.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		compass.setTextureOffset(48, 30).addBox(-8.0F, -4.0F, 6.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
		compass.setTextureOffset(50, 0).addBox(-8.0F, -4.0F, -6.0F, 2.0F, 2.0F, 12.0F, 0.0F, false);
		compass.setTextureOffset(42, 36).addBox(6.0F, -4.0F, -6.0F, 2.0F, 2.0F, 12.0F, 0.0F, false);

		lid = new AModelRenderer(this);
		lid.setRotationPoint(0.0F, -4.0F, 7.0F);
		compass.addChild(lid);
		lid.setTextureOffset(0, 0).addBox(-8.5F, -2.0F, -15.0F, 17.0F, 2.0F, 16.0F, 0.0F, false);

		arrow = new AModelRenderer(this);
		arrow.setRotationPoint(0.0F, -4.0F, 0.0F);
		compass.addChild(arrow);
		arrow.setTextureOffset(36, 18).addBox(-5.5F, 0.0F, -5.5F, 12.0F, 0.0F, 12.0F, 0.0F, false);
		arrow.setTextureOffset(57, 18).addBox(-5.5F, 1.5F, -5.5F, 12.0F, 0.0F, 12.0F, 0.0F, false);
		initializeAnimator(OLD_COMPASS_ANIMATIONS);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		compass.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}



	@Override
	public void animate(T entityIn, ItemStack stack, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.play(this,IDLE,delta);
		CompoundNBT tag = stack.getOrCreateTag();
		if (tag.contains(("StructurePosition"))) {
			Vector3 structurePos = Vector3.from(NBTUtil.readBlockPos(tag.getCompound("StructurePosition")));
			Vector3 startPos = new Vector3(entityIn.getPosX(), entityIn.getPosYEye(), entityIn.getPosZ()).add(Vector3.from(EntityUtils.direction(entityIn).mul(2, 2, 2)));
			Vector3 direction = startPos.direction(structurePos).normalize();
			float rot = (float) ((float)Math.atan2(direction.x,direction.z));
			arrow.setCustomRotation(new Vector3(0,rot, 0));
		}
	}
}