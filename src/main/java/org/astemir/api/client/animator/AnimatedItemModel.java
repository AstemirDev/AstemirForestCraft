package org.astemir.api.client.animator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.math.Vector3;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@OnlyIn(Dist.CLIENT)
public class AnimatedItemModel<T> extends Model implements IAnimatedModel {

    protected Animator<AnimatedItemModel> animator;
    private Set<AModelRenderer> cubes = new HashSet<>();

    public AnimatedItemModel() {
        super(RenderType::getEntityCutoutNoCull);
    }


    public void animation(LivingEntity entity, ItemStack stack){
        float delta = Minecraft.getInstance().getRenderPartialTicks();
        animate((T)entity,stack,delta,0,0,entity.ticksExisted,0,0);
    }


    public void initializeAnimator(AnimationFile file){
        animator = new Animator(file,this);
        for (Field declaredField : getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                Object object = declaredField.get(this);
                if (object instanceof AModelRenderer){
                    ((AModelRenderer)object).setName(declaredField.getName());
                    cubes.add((AModelRenderer)object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset(){
        for (AModelRenderer cube : cubes) {
            cube.reset();
        }
    }

    public AModelRenderer getModelRenderer(String name) {
        for (AModelRenderer cube : cubes) {
            if (cube.getName().equals(name)){
                return cube;
            }
        }
        return null;
    }

    @Override
    public Set<AModelRenderer> getCubes() {
        return cubes;
    }


    public boolean ignoreUsedPartsInOtherAnimations(IAnimated animated,AModelRenderer part,Animation... animations){
        for (Animation animation : animations) {
            return ignoreUsedPartsInOtherAnimation(animated,part,animation);
        }
        return false;
    }


    public boolean ignoreUsedPartsInOtherAnimation(IAnimated animated,AModelRenderer part,Animation animation){
        if (animated.getFactory().isPlaying(animation)){
            return ignorePartsOf(part,animation);
        }
        return false;
    }

    public boolean ignorePartsOf(AModelRenderer part,Animation animation){
        AnimationTrack track = animator.getAnimation(animation);
        if (track != null) {
            List<AModelRenderer> ignored = track.getModelRenderers(this);
            for (AModelRenderer modelRenderer : ignored) {
                if (modelRenderer.getName().equals(part.getName())) {
                    return true;
                }
            }
        }
        return false;
    }


    public void animate(T entityIn, ItemStack stack,float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    }


    public boolean animatePart(IAnimated animated, AModelRenderer part, Animation animation, float delta){
        return true;
    }

    @Override
    public boolean animatePart(AModelRenderer part, Animation animation, float delta) {
        return true;
    }

    public void setRotationAngle(AModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        modelRenderer.setDefaultRotation(new Vector3(x,y,z));
    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
