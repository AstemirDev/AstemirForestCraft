package org.astemir.api.common.item.vanilla;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.astemir.api.common.item.ABucketItem;
import org.astemir.api.common.item.AMobBucketItem;
import org.astemir.api.common.item.IModItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModMobBucketItem extends FishBucketItem implements IModItem<AMobBucketItem> {

    private AMobBucketItem constructor;

    public ModMobBucketItem(Supplier<? extends EntityType<?>> mobType, Supplier<Fluid> fluid,Properties properties) {
        super(mobType,fluid,properties);
    }

    @Override
    public AMobBucketItem itemConstructor() {
        return constructor;
    }

    @Override
    public ModMobBucketItem itemConstructor(AMobBucketItem constructor) {
        this.constructor = constructor;
        return this;
    }

    @Override
    public void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity) {
        constructor.onTickWhileHeld(itemStack,entity);
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        if (constructor.getShareTag(stack) != null){
            return constructor.getShareTag(stack);
        }
        return super.getShareTag(stack);
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        super.readShareTag(stack, nbt);
        constructor.readShareTag(stack,nbt);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return constructor.onSwing(stack, entity);
    }

    @Override
    public int getSortPriority() {
        return constructor.getGroupPriority();
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return constructor.updateItemStackNBT(nbt);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        constructor.onCreated(stack,worldIn,playerIn);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        return constructor.onDroppedByPlayer(item, player);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return constructor.getContainerItem(itemStack);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack,enchantment) || constructor.getAppliableEnchantments().contains(enchantment);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack,LivingEntity entity){
        constructor.onFoodEaten(itemStack,entity);
    }

    @Override
    public boolean onHurtByEntityWhileEquipped(ItemStack itemStack, LivingEntity entity, Entity attacker, float damage) {
        return constructor.onHurtByEntityWhileEquipped(itemStack,entity,attacker,damage);
    }

    @Override
    public boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source, float damage) {
        return constructor.onHurtWhileEquipped(itemStack,entity,source,damage);
    }

    @Override
    public void onJumpWhileEquipped(ItemStack stack, PlayerEntity player) {
        constructor.onJumpWhileEquipped(stack,player);
    }

    @Override
    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity) {
        constructor.onTickWhileArmorEquippedPre(itemStack,entity);
    }

    @Override
    public void onTickWhileArmorEquippedPost(ItemStack itemStack, PlayerEntity entity) {
        constructor.onTickWhileArmorEquippedPost(itemStack,entity);
    }

    @Override
    public void onEquipArmor(ItemStack itemStack, LivingEntity entity) {
        constructor.onEquipArmor(itemStack,entity);
    }

    @Override
    public void onBlockHoe(ItemUseContext context){
        constructor.onBlockHoe(context);
    }

    @Override
    public boolean onEntityClick(ItemStack stack, PlayerEntity playerIn, Entity target, Hand hand) {
        return constructor.onEntityClick(stack,playerIn,target,hand);
    }

    @Override
    public boolean onPortalIgnite(PlayerEntity player,ItemStack itemStack, BlockState state, BlockPos pos){
        return constructor.onPortalIgnite(player,itemStack,state,pos);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return constructor.getDestroySpeed(stack,state);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        return constructor.onUseFirst(stack, context);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        return constructor.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        constructor.onUsingTick(stack, player, count);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        return constructor.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return constructor.getRarity(stack);
    }

    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        constructor.onUse(worldIn, livingEntityIn, stack, count);
    }

    @Override
    public boolean isImmuneToFire() {
        return constructor.isImmuneToFire();
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return constructor.getUseDuration(stack);
    }

    @Override
    public int getItemEnchantability() {
        return constructor.getItemEnchantability();
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return constructor.isRepairable(stack);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return constructor.canPlayerBreakBlockWhileHolding(state, worldIn, pos, player);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        constructor.onUsingStop(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public boolean isCrossbow(ItemStack stack) {
        return constructor.isCrossbow(stack);
    }


    @Override
    public boolean isFood() {
        return constructor.isFood();
    }

    @Override
    public SoundEvent getDrinkSound() {
        if (constructor.getFoodProperties() != null) {
            return constructor.getFoodProperties().getDrinkSound().get();
        }else{
            return constructor.getDrinkSound();
        }
    }

    @Override
    public SoundEvent getEatSound() {
        if (constructor.getFoodProperties() != null) {
            return constructor.getFoodProperties().getEatingSound().get();
        }else{
            return constructor.getEatSound();
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return constructor.onHit(stack, target, attacker);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return constructor.onUseOnBlock(context);
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return constructor.canHarvestBlock(blockIn);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return constructor.getUseAction(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        constructor.dynamicLoreBuilding(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return constructor.onRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        constructor.onTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean isDamageable() {
        return constructor.isDamageable();
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return constructor.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }


    @Override
    public boolean onLeftClick(ItemStack stack, PlayerEntity player) {
        return constructor.onLeftClick(stack,player);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        return constructor.onUseFinish(stack, worldIn, entityLiving);
    }


    @Override
    public int getBurnTime(ItemStack itemStack) {
        return constructor.getFuelTicks(itemStack);
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return constructor.isShield(entity,stack);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        return constructor.canEquip(stack, armorType, entity);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return constructor.getArmorTexture(stack, entity, slot, type);
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return constructor.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return constructor.initCapabilities(stack, nbt);
    }

}
