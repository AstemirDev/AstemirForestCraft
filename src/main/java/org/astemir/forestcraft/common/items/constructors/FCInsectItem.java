package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.Objects;
import java.util.function.Supplier;

public class FCInsectItem<T extends Entity> extends AItem {

    private final Supplier<EntityType<T>> type;

    public FCInsectItem(String name,Supplier<EntityType<T>> type) {
        super(name);
        maxStack(8);
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
        this.type = type;
    }

    public T spawnEntity(ItemStack item,T entity){
        return entity;
    }

    public ItemStack caughtEntity(T entity){
        return null;
    }

    public ItemStack uniqueItemStack(T t){return null;}

    @Override
    public ActionResultType onUseOnBlock(ItemUseContext context) {
        World world = context.getWorld();
        if (!(world instanceof ServerWorld)) {
            return ActionResultType.SUCCESS;
        } else {
            ItemStack itemstack = context.getItem();
            BlockPos blockpos = context.getPos();
            Direction direction = context.getFace();
            BlockState blockstate = world.getBlockState(blockpos);
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.offset(direction);
            }

            EntityType<T> entitytype = this.type.get();
            Entity entity = entitytype.spawn((ServerWorld)world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (entity!= null) {
                spawnEntity(itemstack, (T) entity);
                itemstack.shrink(1);
            }
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.resultPass(itemstack);
        } else if (!(worldIn instanceof ServerWorld)) {
            return ActionResult.resultSuccess(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
            BlockPos blockpos = blockraytraceresult.getPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.resultPass(itemstack);
            } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
                EntityType<?> entitytype = this.type.get();
                Entity entity = entitytype.spawn((ServerWorld) worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false);
                if (entity == null) {
                    return ActionResult.resultPass(itemstack);
                } else {
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    spawnEntity(itemstack, (T) entity);
                    playerIn.addStat(Stats.ITEM_USED.get(itemstack.getItem()));
                    return ActionResult.resultConsume(itemstack);
                }
            } else {
                return ActionResult.resultFail(itemstack);
            }
        }
    }


}