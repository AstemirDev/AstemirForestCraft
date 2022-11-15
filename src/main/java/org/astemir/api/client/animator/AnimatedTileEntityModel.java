package org.astemir.api.client.animator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


@OnlyIn(Dist.CLIENT)
public class AnimatedTileEntityModel<T extends TileEntity> extends Model implements IAnimatedModel {

    private Set<AModelRenderer> cubes = new HashSet<>();
    public Animator<AnimatedTileEntityModel<T>> animator;


    public AnimatedTileEntityModel() {
        this(RenderType::getEntityCutoutNoCull);
    }

    public AnimatedTileEntityModel(Function<ResourceLocation, RenderType> p_i225945_1_) {
        super(p_i225945_1_);
    }

    public void animate(T entityIn, float ageInTicks) {
        float delta = Minecraft.getInstance().getRenderPartialTicks();
        if (animator != null) {
            if (entityIn != null) {
                animator.resetUnusedBones(((IAnimated) entityIn).getFactory().getActiveAnimations(), ((IAnimated) entityIn));
                ((IAnimated) entityIn).getFactory().getActiveAnimations().forEach((animation -> {
                    animator.play(this, animation, ((IAnimated) entityIn), delta);
                }));
                animate(entityIn, delta, 0, 0, 0, 0, 0);
            }
        }
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


    private boolean ignoreUsedPartsInOtherAnimation(IAnimated animated,AModelRenderer part,Animation animation){
        if (animated.getFactory().isPlaying(animation)){
            return ignorePartsOf(part,animation);
        }
        return false;
    }

    private boolean ignorePartsOf(AModelRenderer part,Animation animation){
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


    public void animate(T t, float delta, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
}
