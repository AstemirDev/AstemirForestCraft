package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.astemir.api.common.item.AItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;

import java.util.function.Supplier;


public class FCSummonItem extends AItem {

    private Supplier<EntityType> summon;
    private Supplier<SoundEvent> sound;

    private int useTime = 20;

    public FCSummonItem(String registryName, int useTime, Supplier<EntityType> summon,Supplier<SoundEvent> sound) {
        super(registryName);
        this.useTime = useTime;
        this.sound = sound;
        this.summon = summon;
        itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useTime;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }


    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),50);
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!PlayerUtils.hasCooldown(playerIn,playerIn.getHeldItem(handIn))) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote) {
            BlockPos spawnPos = entityLiving.getPosition().add(0, 2, 0);
            if (EntityUtils.canSpawnAtPosition(worldIn,spawnPos,summon.get())) {
                MobEntity summoned = (MobEntity) summon.get().create(worldIn);
                summoned.moveToBlockPosAndAngles(spawnPos, (float) ((Math.PI) - entityLiving.rotationYaw), 0);
                if (worldIn instanceof ServerWorld) {
                    summoned.onInitialSpawn((ServerWorld) worldIn, worldIn.getDifficultyForLocation(spawnPos), SpawnReason.NATURAL, null, null);
                }
                worldIn.addEntity(summoned);
                stack.shrink(1);
                if (sound != null) {
                    entityLiving.playSound(sound.get(), 1, 1);
                }
            } else {
                ((PlayerEntity) entityLiving).sendStatusMessage(TextUtils.translate(FCStrings.NOT_ENOUGH_SPACE_BOSS, TextFormatting.RED), false);
            }
            PlayerUtils.cooldownItem((PlayerEntity)entityLiving,stack.getItem(),60);
        }
        return super.onUseFinish(stack, worldIn, entityLiving);
    }
}
