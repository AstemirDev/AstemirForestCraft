package org.astemir.forestcraft.client.render.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.common.blocks.tileentities.BlockAncientChest;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.client.render.model.block.ModelAncientRunestoneChest;
import org.astemir.forestcraft.common.tileentity.TileEntityAncientChest;

@OnlyIn(Dist.CLIENT)
public class RendererAncientChest extends TileEntityRenderer<TileEntityAncientChest> {


    private static final ResourceLocation ANCIENT_CHEST_TEXTURE = new ResourceLocation("forestcraft:textures/entity/chest/ancient_runestone_chest.png");

    private ModelAncientRunestoneChest chestModel = new ModelAncientRunestoneChest();


    public RendererAncientChest() {
        super(TileEntityRendererDispatcher.instance);
    }



    private void renderModels(MatrixStack matrixStackIn, IVertexBuilder bufferIn, float lidAngle, int combinedLightIn, int combinedOverlayIn) {
        chestModel.chest_up.rotateAngleX = (lidAngle * ((float)Math.PI / 2F));
        chestModel.render(matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn,1,1,1,1);
    }


    @Override
    public void render(TileEntityAncientChest tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tileEntityIn.getWorld();
        boolean flag = world != null;
        BlockState blockstate = flag ? tileEntityIn.getBlockState() : FCBlocks.ANCIENT_CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();
        if (block instanceof BlockAncientChest) {
            BlockAncientChest ancientChest = (BlockAncientChest)block;
            if (blockstate.get(BlockAncientChest.LOCKED)){
                chestModel.lock.showModel = true;
            }else{
                chestModel.lock.showModel = false;
            }
            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(180));
            matrixStackIn.translate(0.5f,-1.5f,-0.5f);
            float f = blockstate.get(ChestBlock.FACING).getHorizontalAngle();
            if (blockstate.get(ChestBlock.FACING).equals(Direction.NORTH) || blockstate.get(ChestBlock.FACING).equals(Direction.SOUTH)){
                f+=180;
            }
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-f));
            TileEntityMerger.ICallbackWrapper<? extends TileEntityAncientChest> icallbackwrapper;
            if (flag) {
                icallbackwrapper = ancientChest.combine(blockstate, world, tileEntityIn.getPos(), true);
            } else {
                icallbackwrapper = TileEntityMerger.ICallback::func_225537_b_;
            }
            float f1 = icallbackwrapper.apply(ChestBlock.getLidRotationCallback(tileEntityIn)).get(partialTicks);
            renderModels(matrixStackIn,bufferIn.getBuffer(RenderType.getEntityCutout(ANCIENT_CHEST_TEXTURE)),f1,combinedLightIn,combinedOverlayIn);
            matrixStackIn.pop();
        }
    }
}