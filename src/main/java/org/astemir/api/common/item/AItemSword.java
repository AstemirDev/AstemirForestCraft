package org.astemir.api.common.item;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModSwordItem;


public class AItemSword extends AItem{

    private IItemTier tier;
    private float damage;
    private float speed;

    public AItemSword(String registryName,IItemTier tier, float damage, float speed) {
        super(registryName);
        this.tier = tier;
        this.damage = tier.getAttackDamage()+damage;
        this.speed = speed;
    }

    public AItemSword(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,-2.4f);
    }

    @Override
    public Item build(Item.Properties properties) {
        ModSwordItem swordItem = (ModSwordItem) new ModSwordItem(tier, properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return swordItem;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.isIn(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    @Override
    public int getMaxDamage() {
        if (super.getMaxDamage() <= 0) {
            return tier.getMaxUses();
        }else{
            return super.getMaxDamage();
        }
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getBlockHardness(worldIn, pos) != 0.0F) {
            stack.damageItem(2, entityLiving, (entity) -> {
                entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, Multimap<Attribute, AttributeModifier> attributes) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? attributes : ImmutableMultimap.of();
    }

    @Override
    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return blockIn.isIn(Blocks.COBWEB);
    }

    public void addAttributes(ImmutableMultimap.Builder builder){}

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return super.onHit(stack, target, attacker);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return stack.isDamageable();
    }

    public float getAttackDamage() {
        return damage;
    }

    public float getAttackSpeed() {
        return speed;
    }

    public IItemTier getTier() {
        return tier;
    }
}
