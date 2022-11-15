package org.astemir.forestcraft.common.items.constructors;


import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.RandomUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.common.blocks.building.BlockSheafOfGrass;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.registries.FCItemGroups;

public class FCWateringCan extends AItem {

    private static Particle3D WATERING_CAN_PARTICLES = new Particle3D(ParticleTypes.FALLING_WATER).renderTimes(10).size(0.5f,0,0.5f);

    public FCWateringCan(String registryName,int maxUses) {
        super(registryName);
        maxDamage(maxUses);
        itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.MISC);
        maxStack(1);
    }

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        BlockPos pos = context.getPos();
        if (context.getItem().getDamage() != getMaxDamage()) {
            if (useItem(context.getWorld(), pos, context.getWorld().getBlockState(pos).getBlock())) {
                if (context.getItem().getDamage() < getMaxDamage()) {
                    context.getItem().setDamage(context.getItem().getDamage() + 1);
                }
                SoundUtils.playSound(context.getWorld(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.PLAYERS, pos, 1, 2);
                WATERING_CAN_PARTICLES.play(context.getWorld(), pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f);
            }
        }
        return super.onUseOnBlock(context);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        BlockRayTraceResult rayTraceResult = EntityUtils.rayTraceFluid(worldIn, playerIn);
        if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
            if (heldItem.getDamage() > 0) {
                BlockPos pos = rayTraceResult.getPos();
                BlockState blockState = worldIn.getBlockState(pos);
                if (blockState.getBlock().equals(Blocks.WATER)) {
                    Fluid fluid = blockState.getFluidState().getFluid();
                    if (fluid != Fluids.EMPTY) {
                        SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
                        playerIn.playSound(soundevent, 1.0F, 1.5F);
                        heldItem.setDamage(0);
                        AstemirAPI.TASK_MANAGER.createTask(()->{
                            heldItem.setDamage(0);
                        },1);
                        return ActionResult.resultConsume(heldItem);
                    }
                }
            }
        }else{
            return ActionResult.resultPass(heldItem);
        }
        return ActionResult.resultPass(heldItem);
    }

    public boolean useItem(World world, BlockPos pos, Block block) {
        if (block.equals(Blocks.DIRT)) {
            if (RandomUtils.doWithChance(20)) {
                if (!world.isRemote) {
                    BlockState newState = Blocks.GRASS_BLOCK.getDefaultState();
                    world.setBlockState(pos, newState, 11);
                    world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
                }
            }
            return true;
        } else if (block.equals(Blocks.FARMLAND)) {
            if (!world.isRemote) {
                BlockState newState = Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7);
                world.setBlockState(pos, newState, 11);
                world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
            }
            return true;
        } else if (block.equals(Blocks.FIRE) || block.equals(Blocks.SOUL_FIRE)) {
            if (!world.isRemote) {
                BlockState newState = Blocks.AIR.getDefaultState();
                world.setBlockState(pos, newState, 11);
                world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
            }
            return true;
        } else if (block.equals(Blocks.ICE)) {
            if (!world.isRemote) {
                if (RandomUtils.doWithChance(30)) {
                    BlockState newState = Blocks.PACKED_ICE.getDefaultState();
                    world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState(), 11);
                    world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
                }
            }
            return true;
        } else if (block.equals(Blocks.PACKED_ICE)) {
            if (!world.isRemote) {
                if (RandomUtils.doWithChance(15)) {
                    BlockState newState = Blocks.BLUE_ICE.getDefaultState();
                    world.setBlockState(pos, Blocks.BLUE_ICE.getDefaultState(), 11);
                    world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
                }
            }
            return true;
        }else if (block.equals(FCBlocks.SHEAF_OF_GRASS)) {
            if (!world.isRemote) {
                BlockState newState = FCBlocks.SHEAF_OF_GRASS.getDefaultState().with(BlockSheafOfGrass.WATERED, true).with(BlockSheafOfGrass.AXIS, world.getBlockState(pos).get(BlockSheafOfGrass.AXIS));
                world.setBlockState(pos, newState, 11);
                world.notifyBlockUpdate(pos, block.getDefaultState(), newState, 4);
            }
            return true;
        }
        return false;
    }

}
