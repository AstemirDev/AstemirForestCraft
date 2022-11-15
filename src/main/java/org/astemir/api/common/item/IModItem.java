package org.astemir.api.common.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public interface IModItem<T extends AItem> {

    T itemConstructor();

    IModItem itemConstructor(T constructor);

    int getSortPriority();

    boolean onLeftClick(ItemStack stack, PlayerEntity player);

    void onFoodEaten(ItemStack itemStack, LivingEntity entity);

    boolean onHurtByEntityWhileEquipped(ItemStack itemStack,LivingEntity entity, Entity attacker,float damage);

    boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source,float damage);

    void onJumpWhileEquipped(ItemStack stack,PlayerEntity player);

    void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity);

    void onTickWhileArmorEquippedPost(ItemStack itemStack, PlayerEntity entity);

    void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity);

    void onEquipArmor(ItemStack itemStack, LivingEntity entity);

    void onBlockHoe(ItemUseContext context);

    boolean onEntityClick(ItemStack stack, PlayerEntity playerIn, Entity target, Hand hand);

    boolean onPortalIgnite(PlayerEntity player, ItemStack itemStack, BlockState state, BlockPos pos);
}
