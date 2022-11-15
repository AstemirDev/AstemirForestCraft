package org.astemir.api.common.item;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.astemir.api.common.item.vanilla.ModArmorItem;


public class AItemArmor extends AItem{

    private IArmorMaterial tier;
    private EquipmentSlotType slotType;
    private float damageReduction;
    private float toughness;
    private float knockbackResistance;


    public int armorModel = -1;

    public String armorTexture;


    public AItemArmor(String registryName, IArmorMaterial tier, EquipmentSlotType slotType,float damageReduction,float toughness, float knockbackResistance) {
        super(registryName);
        this.tier = tier;
        this.slotType = slotType;
        this.damageReduction = tier.getDamageReductionAmount(slotType)+damageReduction;
        this.toughness = tier.getToughness()+toughness;
        this.knockbackResistance = tier.getKnockbackResistance()+knockbackResistance;
    }

    public AItemArmor(String registryName, IArmorMaterial tier, EquipmentSlotType slotType,float damageReduction,float toughness) {
        this(registryName,tier,slotType,damageReduction,toughness,0);
    }

    public AItemArmor(String registryName, IArmorMaterial tier, EquipmentSlotType slotType,float damageReduction) {
        this(registryName,tier,slotType,damageReduction,0,0);
    }


    @Override
    public Item build(Item.Properties properties) {
        ModArmorItem armorItem = (ModArmorItem) new ModArmorItem(tier, slotType,properties,getDamageReduction(),getToughness(),getKnockbackResistance()).itemConstructor(this);
        if (armorModel != -1){
            armorItem.setArmorModel(armorModel);
        }
        if (armorTexture != null){
            armorItem.setArmorTexture(armorTexture);
        }
        return armorItem;
    }

    public void setArmorTexture(String armorTexture) {
        this.armorTexture = armorTexture;
    }

    public void setArmorModel(int armorModel) {
        this.armorModel = armorModel;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
       return 1.0f;
    }

    public int getAdditionalDurability(){
        return 0;
    }

    @Override
    public int getMaxDamage() {
        if (super.getMaxDamage() <= 0) {
            return tier.getDurability(slotType) + getAdditionalDurability();
        }else{
            return super.getMaxDamage();
        }
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return true;
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
        return equipmentSlot == slotType ? attributes : ImmutableMultimap.of();
    }

    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(equipmentslottype);
        if (itemstack1.isEmpty()) {
            playerIn.setItemStackToSlot(equipmentslottype, itemstack.copy());
            itemstack.setCount(0);
            return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
        } else {
            return ActionResult.resultFail(itemstack);
        }
    }

    @Override
    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return false;
    }

    public void addAttributes(ImmutableMultimap.Builder builder){}

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(1, attacker, (entity) -> {
            entity.sendBreakAnimation(slotType);
        });
        return super.onHit(stack, target, attacker);
    }


    @Override
    public boolean isRepairable(ItemStack stack) {
        return stack.isDamageable();
    }

    public IArmorMaterial getTier() {
        return tier;
    }

    public EquipmentSlotType getSlotType() {
        return slotType;
    }

    public float getDamageReduction() {
        return damageReduction;
    }

    public float getFullDamageReduction(){return damageReduction+tier.getDamageReductionAmount(slotType);}

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
