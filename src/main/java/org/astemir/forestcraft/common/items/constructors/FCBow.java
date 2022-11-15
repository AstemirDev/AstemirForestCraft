package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.ABowItem;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.utils.EntityUtils;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.weapons.bows.FCBowProperties;

import java.util.List;

public class FCBow extends ABowItem {

    private FCBowProperties properties;

    public FCBow(String registryName,FCBowProperties properties) {
        super(registryName);
        this.properties = properties;
        ammo((itemStack)->{
            if (!properties.isUsingArrows()) {
                return itemStack.getItem() == properties.ammo().get();
            }else{
                return ARROWS.test(itemStack);
            }
        });
        if (!properties.isUsingArrows()) {
            loreAdd(FCStrings.consumes(properties.ammo()));
        }else{
            loreAdd(FCStrings.consumesArrows());
        }
        maxStack(1);
    }

    @Override
    public void dynamicLoreBuilding(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(FCStrings.rangedDamageTooltip(calculateDamage(stack)));
        super.dynamicLoreBuilding(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entityLiving;
            ItemStack ammoStack = getAmmoStack(entityLiving,stack);
            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, i, !ammoStack.isEmpty() || canShoot((PlayerEntity) entityLiving,stack));
            if (i < 0) return;
            if (canShoot((PlayerEntity) entityLiving,stack)) {
                float arrowVelocity = getArrowVelocity(i);
                boolean infinite = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY,stack) > 0;
                if (!((double)arrowVelocity < 0.1D)) {
                    if (!worldIn.isRemote) {
                        if (properties.isUsingArrows()) {
                            ArrowItem arrowitem = (ArrowItem) (ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
                            AbstractArrowEntity arrowEntity = arrowitem.createArrow(worldIn, ammoStack, player);
                            arrowEntity = createArrow(worldIn,player,arrowEntity);
                            if (arrowEntity != null) {
                                EntityUtils.shootProjectileIgnoreMotion(arrowEntity, player, player.rotationPitch, player.rotationYaw, 0, arrowVelocity * 3.0F, 1.0F);
                                if (arrowVelocity == 1.0F) {
                                    arrowEntity.setIsCritical(true);
                                }
                                arrowEntity.setDamage(calculateDamage(stack));
                                arrowEntity.setKnockbackStrength((int) calculateKnockback(stack));
                                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                                    arrowEntity.setFire(100);
                                }
                                if (infinite || player.abilities.isCreativeMode && (ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW)) {
                                    arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                                }
                                worldIn.addEntity(arrowEntity);
                            }
                        }
                        PlayerUtils.damageItem(player,stack,player.getActiveHand(),1);
                    }
                    afterShot(worldIn,player,stack,arrowVelocity);
                    SoundUtils.playSound(worldIn,properties.shotSound().getSound().get(),SoundCategory.PLAYERS,player.getPosition(), 1.0F,1.0F / (random.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);
                    if (!infinite && !player.abilities.isCreativeMode) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            player.inventory.deleteStack(ammoStack);
                        }
                    }
                    player.addStat(Stats.ITEM_USED.get(stack.getItem()));
                }
            }
        }
    }

    public boolean canShoot(PlayerEntity shooter,ItemStack bowStack){
        boolean infinite = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bowStack) > 0;
        return shooter.abilities.isCreativeMode || infinite || hasEnoughAmmo(shooter,bowStack);
    }

    public float calculateDamage(ItemStack bowStack){
        int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
        return (float) (properties.damage()+(double)power * 0.5D + 0.5D);
    }

    public float calculateKnockback(ItemStack bowStack){
        int knockback = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);
        return knockback;
    }

    public AbstractArrowEntity createArrow(World world,PlayerEntity player,AbstractArrowEntity original) {
        return original;
    }

    public boolean hasEnoughAmmo(LivingEntity entity,ItemStack stack){
        PlayerEntity playerIn = (PlayerEntity) entity;
        boolean flag = playerIn.abilities.isCreativeMode;
        ItemStack ammoStack = playerIn.findAmmo(stack);
        if (!ammoStack.isEmpty() || flag) {
            return true;
        }
        return false;
    }

    public ItemStack getAmmoStack(LivingEntity entity,ItemStack stack){
        PlayerEntity playerIn = (PlayerEntity) entity;
        if (playerIn.abilities.isCreativeMode) {
            return properties.ammo().get().getDefaultInstance();
        } else {
            ItemStack ammoStack = playerIn.findAmmo(stack);
            return ammoStack;
        }
    }

    public void afterShot(World world, PlayerEntity player,ItemStack bowStack,float velocity) {}

    @Override
    public float getArrowVelocity(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f*properties.arrowSpeed();
    }

    public FCBowProperties getProperties() {
        return properties;
    }

    public float getDamage(){
        return properties.getDamage();
    }
}
