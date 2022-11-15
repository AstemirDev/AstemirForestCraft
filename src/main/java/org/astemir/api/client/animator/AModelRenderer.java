package org.astemir.api.client.animator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.api.math.Vector3;

import java.util.Random;

public class AModelRenderer {
    
    private float textureWidth = 64.0F;
    private float textureHeight = 32.0F;

    private int textureOffsetX;
    private int textureOffsetY;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;

    public float scaleX = 1;
    public float scaleY = 1;
    public float scaleZ = 1;
    public float positionX = 0;
    public float positionY = 0;
    public float positionZ = 0;
    public float defaultRotationX = 0;
    public float defaultRotationY = 0;
    public float defaultRotationZ = 0;

    public float customRotationX = 0;
    public float customRotationY = 0;
    public float customRotationZ = 0;


    public boolean mirror;
    public boolean using = false;
    public boolean positioning = false;
    public boolean rotating = false;
    public boolean scaling = false;
    public boolean showModel = true;

    private final ObjectList<AModelRenderer.ModelBox> cubeList = new ObjectArrayList<>();
    private final ObjectList<AModelRenderer> childModels = new ObjectArrayList<>();

    private String name = "";

    public AModelRenderer(Model model) {
        this.setTextureSize(model.textureWidth, model.textureHeight);
    }

    public AModelRenderer(Model model, int texOffX, int texOffY) {
        this(model.textureWidth, model.textureHeight, texOffX, texOffY);
    }

    public AModelRenderer(int textureWidthIn, int textureHeightIn, int textureOffsetXIn, int textureOffsetYIn) {
        this.setTextureSize(textureWidthIn, textureHeightIn);
        this.setTextureOffset(textureOffsetXIn, textureOffsetYIn);
    }

    private AModelRenderer() {
    }

    public AModelRenderer getModelAngleCopy() {
        AModelRenderer testModelRenderer = new AModelRenderer();
        testModelRenderer.copyModelAngles(this);
        return testModelRenderer;
    }


    public void reset(){
        scaleX = 1;
        scaleY = 1;
        scaleZ = 1;
        positionX = 0;
        positionY = 0;
        positionZ = 0;
        rotateAngleX = defaultRotationX;
        rotateAngleY = defaultRotationY;
        rotateAngleZ = defaultRotationZ;
    }

    public void copyModelAngles(AModelRenderer modelRendererIn) {
        rotateAngleX = modelRendererIn.rotateAngleX;
        rotateAngleY = modelRendererIn.rotateAngleY;
        rotateAngleZ = modelRendererIn.rotateAngleZ;
        rotationPointX = modelRendererIn.rotationPointX;
        rotationPointY = modelRendererIn.rotationPointY;
        rotationPointZ = modelRendererIn.rotationPointZ;
        scaleX = modelRendererIn.scaleX;
        scaleY = modelRendererIn.scaleY;
        scaleZ = modelRendererIn.scaleZ;
        positionX = modelRendererIn.positionX;
        positionY = modelRendererIn.positionY;
        positionZ = modelRendererIn.positionZ;
    }

    public void resetScale(){
        scaleX = 1;
        scaleY = 1;
        scaleZ = 1;
    }

    public void resetPosition(){
        positionX = 0;
        positionY = 0;
        positionZ = 0;
    }

    public void resetRotation(){
        rotateAngleX = defaultRotationX;
        rotateAngleY = defaultRotationY;
        rotateAngleZ = defaultRotationZ;
    }

    public void setDefaultRotation(Vector3 rotation){
        this.defaultRotationX = rotation.getX();
        this.defaultRotationY = rotation.getY();
        this.defaultRotationZ = rotation.getZ();
    }

    public void setRotation(Vector3 rotation){
        this.rotateAngleX = rotation.getX();
        this.rotateAngleY = rotation.getY();
        this.rotateAngleZ = rotation.getZ();
    }

    public void setScale(Vector3 scale) {
        this.scaleX = scale.getX();
        this.scaleY = scale.getY();
        this.scaleZ = scale.getZ();
    }

    public void setPosition(Vector3 position) {
        this.positionX = position.getX();
        this.positionY = position.getY();
        this.positionZ = position.getZ();
    }

    public void setCustomRotation(Vector3 rotation){
        this.customRotationX = rotation.x;
        this.customRotationY = rotation.y;
        this.customRotationZ = rotation.z;
    }


    public Vector3 getDefaultRotation(){return new Vector3(defaultRotationX,defaultRotationY,defaultRotationZ);}

    public Vector3 getRotation() {
        return new Vector3(rotateAngleX,rotateAngleY,rotateAngleZ);
    }

    public Vector3 getScale() {
        return new Vector3(scaleX,scaleY,scaleZ);
    }

    public Vector3 getPosition() {
        return new Vector3(positionX,positionY,positionZ);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(AModelRenderer renderer) {
        this.childModels.add(renderer);
    }

    public AModelRenderer setTextureOffset(int x, int y) {
        this.textureOffsetX = x;
        this.textureOffsetY = y;
        return this;
    }

    public AModelRenderer addBox(String partName, float x, float y, float z, int width, int height, int depth, float delta, int texX, int texY) {
        this.setTextureOffset(texX, texY);
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, (float)width, (float)height, (float)depth, delta, delta, delta, this.mirror, false);
        return this;
    }

    public AModelRenderer addBox(float x, float y, float z, float width, float height, float depth) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F, 0.0F, 0.0F, this.mirror, false);
        return this;
    }

    public AModelRenderer addBox(float x, float y, float z, float width, float height, float depth, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F, 0.0F, 0.0F, mirrorIn, false);
        return this;
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, mirrorIn, false);
    }

    private void addBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, boolean p_228305_13_) {
        this.cubeList.add(new AModelRenderer.ModelBox(texOffX, texOffY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, mirorIn, this.textureWidth, this.textureHeight));
    }

    public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn) {
        this.rotationPointX = rotationPointXIn;
        this.rotationPointY = rotationPointYIn;
        this.rotationPointZ = rotationPointZIn;
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
        this.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.showModel) {
            matrixStackIn.push();
            matrixStackIn.scale(scaleX,scaleY,scaleZ);
            matrixStackIn.translate(positionX/16.0f,-positionY/16.0f,positionZ/16.0f);
            if (!this.cubeList.isEmpty() || !this.childModels.isEmpty()) {
                matrixStackIn.push();
                this.translateRotate(matrixStackIn);
                this.doRender(matrixStackIn.getLast(), bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
                for(AModelRenderer modelRenderer : this.childModels) {
                    modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
                }
                matrixStackIn.pop();
            }
            matrixStackIn.pop();
        }

    }

    public void translateRotate(MatrixStack matrixStackIn) {
        matrixStackIn.translate((double)(this.rotationPointX / 16.0F)/scaleX, (double)(this.rotationPointY / 16.0F)/scaleY, (double)(this.rotationPointZ / 16.0F)/scaleZ);
        if (this.rotateAngleZ+customRotationZ != 0.0F) {
            matrixStackIn.rotate(Vector3f.ZP.rotation(this.rotateAngleZ+customRotationZ));
        }
        if (this.rotateAngleY+customRotationY != 0.0F) {
            matrixStackIn.rotate(Vector3f.YP.rotation(this.rotateAngleY+customRotationY));
        }
        if (this.rotateAngleX+customRotationX != 0.0F) {
            matrixStackIn.rotate(Vector3f.XP.rotation(this.rotateAngleX+customRotationX));
        }
    }

    public void doRender(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Matrix4f matrix4f = matrixEntryIn.getMatrix();
        Matrix3f matrix3f = matrixEntryIn.getNormal();
        for(AModelRenderer.ModelBox modelBox : this.cubeList) {
            for(AModelRenderer.TexturedQuad textureQuad : modelBox.quads) {
                Vector3f vector3f = textureQuad.normal.copy();
                vector3f.transform(matrix3f);
                float f = vector3f.getX();
                float f1 = vector3f.getY();
                float f2 = vector3f.getZ();
                for(int i = 0; i < 4; ++i){
                    AModelRenderer.PositionTextureVertex vertexPosition = textureQuad.vertexPositions[i];
                    float f3 = vertexPosition.position.getX() / 16.0F;
                    float f4 = vertexPosition.position.getY() / 16.0F;
                    float f5 = vertexPosition.position.getZ() / 16.0F;
                    Vector4f vector4f = new Vector4f(f3, f4, f5, 1.0F);
                    vector4f.transform(matrix4f);
                    float textureU = vertexPosition.textureU;
                    float textureV = vertexPosition.textureV;
                    bufferIn.addVertex(vector4f.getX(), vector4f.getY(), vector4f.getZ(), red, green, blue, alpha, textureU,textureV , packedOverlayIn, packedLightIn, f, f1, f2);
                }
            }
        }

    }

    public AModelRenderer setTextureSize(int textureWidthIn, int textureHeightIn) {
        this.textureWidth = (float)textureWidthIn;
        this.textureHeight = (float)textureHeightIn;
        return this;
    }

    public AModelRenderer.ModelBox getRandomCube(Random randomIn) {
        return this.cubeList.get(randomIn.nextInt(this.cubeList.size()));
    }

    @OnlyIn(Dist.CLIENT)
    public static class ModelBox {
        private final AModelRenderer.TexturedQuad[] quads;
        public final float posX1;
        public final float posY1;
        public final float posZ1;
        public final float posX2;
        public final float posY2;
        public final float posZ2;

        public ModelBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirorIn, float texWidth, float texHeight) {
            this.posX1 = x;
            this.posY1 = y;
            this.posZ1 = z;
            this.posX2 = x + width;
            this.posY2 = y + height;
            this.posZ2 = z + depth;
            this.quads = new AModelRenderer.TexturedQuad[6];
            float f = x + width;
            float f1 = y + height;
            float f2 = z + depth;
            x = x - deltaX;
            y = y - deltaY;
            z = z - deltaZ;
            f = f + deltaX;
            f1 = f1 + deltaY;
            f2 = f2 + deltaZ;
            if (mirorIn) {
                float f3 = f;
                f = x;
                x = f3;
            }

            AModelRenderer.PositionTextureVertex TU$positiontexturevertex7 = new AModelRenderer.PositionTextureVertex(x, y, z, 0.0F, 0.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex = new AModelRenderer.PositionTextureVertex(f, y, z, 0.0F, 8.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex1 = new AModelRenderer.PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex2 = new AModelRenderer.PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex3 = new AModelRenderer.PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex4 = new AModelRenderer.PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex5 = new AModelRenderer.PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
            AModelRenderer.PositionTextureVertex TU$positiontexturevertex6 = new AModelRenderer.PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
            float f4 = (float)texOffX;
            float f5 = (float)texOffX + depth;
            float f6 = (float)texOffX + depth + width;
            float f7 = (float)texOffX + depth + width + width;
            float f8 = (float)texOffX + depth + width + depth;
            float f9 = (float)texOffX + depth + width + depth + width;
            float f10 = (float)texOffY;
            float f11 = (float)texOffY + depth;
            float f12 = (float)texOffY + depth + height;
            this.quads[2] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex4, TU$positiontexturevertex3, TU$positiontexturevertex7, TU$positiontexturevertex}, f5, f10, f6, f11, texWidth, texHeight, mirorIn, Direction.DOWN);
            this.quads[3] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex1, TU$positiontexturevertex2, TU$positiontexturevertex6, TU$positiontexturevertex5}, f6, f11, f7, f10, texWidth, texHeight, mirorIn, Direction.UP);
            this.quads[1] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex7, TU$positiontexturevertex3, TU$positiontexturevertex6, TU$positiontexturevertex2}, f4, f11, f5, f12, texWidth, texHeight, mirorIn, Direction.WEST);
            this.quads[4] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex, TU$positiontexturevertex7, TU$positiontexturevertex2, TU$positiontexturevertex1}, f5, f11, f6, f12, texWidth, texHeight, mirorIn, Direction.NORTH);
            this.quads[0] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex4, TU$positiontexturevertex, TU$positiontexturevertex1, TU$positiontexturevertex5}, f6, f11, f8, f12, texWidth, texHeight, mirorIn, Direction.EAST);
            this.quads[5] = new AModelRenderer.TexturedQuad(new AModelRenderer.PositionTextureVertex[]{TU$positiontexturevertex3, TU$positiontexturevertex4, TU$positiontexturevertex5, TU$positiontexturevertex6}, f8, f11, f9, f12, texWidth, texHeight, mirorIn, Direction.SOUTH);
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class PositionTextureVertex {
        public final Vector3f position;
        public final float textureU;
        public final float textureV;

        public PositionTextureVertex(float x, float y, float z, float texU, float texV) {
            this(new Vector3f(x, y, z), texU, texV);
        }

        public AModelRenderer.PositionTextureVertex setTextureUV(float texU, float texV) {
            return new AModelRenderer.PositionTextureVertex(this.position, texU, texV);
        }

        public PositionTextureVertex(Vector3f posIn, float texU, float texV) {
            this.position = posIn;
            this.textureU = texU;
            this.textureV = texV;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class TexturedQuad {
        public final AModelRenderer.PositionTextureVertex[] vertexPositions;
        public final Vector3f normal;

        public TexturedQuad(AModelRenderer.PositionTextureVertex[] positionsIn, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirrorIn, Direction directionIn) {
            this.vertexPositions = positionsIn;
            float f = 0.0F / texWidth;
            float f1 = 0.0F / texHeight;
            positionsIn[0] = positionsIn[0].setTextureUV(u2 / texWidth - f, v1 / texHeight + f1);
            positionsIn[1] = positionsIn[1].setTextureUV(u1 / texWidth + f, v1 / texHeight + f1);
            positionsIn[2] = positionsIn[2].setTextureUV(u1 / texWidth + f, v2 / texHeight - f1);
            positionsIn[3] = positionsIn[3].setTextureUV(u2 / texWidth - f, v2 / texHeight - f1);
            if (mirrorIn) {
                int i = positionsIn.length;

                for(int j = 0; j < i / 2; ++j) {
                    AModelRenderer.PositionTextureVertex TU$positiontexturevertex = positionsIn[j];
                    positionsIn[j] = positionsIn[i - 1 - j];
                    positionsIn[i - 1 - j] = TU$positiontexturevertex;
                }
            }

            this.normal = directionIn.toVector3f();
            if (mirrorIn) {
                this.normal.mul(-1.0F, 1.0F, 1.0F);
            }

        }
    }
}
