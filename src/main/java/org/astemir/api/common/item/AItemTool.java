package org.astemir.api.common.item;


import com.google.common.collect.ImmutableMultimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.astemir.api.common.item.vanilla.ModToolItem;

import java.util.ArrayList;
import java.util.List;


public class AItemTool extends AItem{

    private IItemTier tier;
    private float damage;
    private float speed;
    private List<AItemToolType> toolTypes = new ArrayList<>();

    public AItemTool(String registryName,IItemTier tier, float damage, float speed) {
        super(registryName);
        this.tier = tier;
        this.damage = tier.getAttackDamage()+damage;
        this.speed = speed;
    }

    public AItemTool(String registryName, IItemTier tier, float damage) {
        this(registryName,tier,damage,-2.8f);
    }


    public AItemTool addToolType(AItemToolType toolType){
        toolTypes.add(toolType);
        return this;
    }

    public List<AItemToolType> getToolTypes() {
        return toolTypes;
    }

    @Override
    public Item build(Item.Properties properties) {
        ModToolItem swordItem = (ModToolItem) new ModToolItem(tier, properties,getAttackDamage(),getAttackSpeed()).itemConstructor(this);
        return swordItem;
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return AItemToolType.getDestroySpeed(stack,state);
    }

    public int getHarvestLevel(){
        return tier.getHarvestLevel();
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

    public boolean isTool(AItemToolType toolType){
        return toolTypes.contains(toolType);
    }

    @Override
    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        boolean canHarvest = false;
        if (isTool(AItemToolType.PICKAXE)){
            canHarvest = canHarvestPickaxe(blockIn);
        }
        if (isTool(AItemToolType.AXE)){
            if (!canHarvest) {
                canHarvest = canHarvestAxe(blockIn);
            }else{
                return true;
            }
        }
        if (isTool(AItemToolType.SHOVEL)){
            if (!canHarvest) {
                canHarvest = canHarvestShovel(blockIn);
            }else{
                return true;
            }
        }
        if (isTool(AItemToolType.HOE)){
            if (!canHarvest) {
                canHarvest = canHarvestHoe(blockIn);
            }else{
                return true;
            }
        }
        return canHarvest;
    }

    private boolean canHarvestPickaxe(BlockState blockIn){
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == ToolType.PICKAXE) {
            return i >= blockIn.getHarvestLevel();
        }
        Material material = blockIn.getMaterial();
        return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }

    private boolean canHarvestAxe(BlockState blockIn){
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == ToolType.AXE) {
            return i >= blockIn.getHarvestLevel();
        }
        return false;
    }


    private boolean canHarvestShovel(BlockState blockIn){
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == ToolType.SHOVEL) {
            return i >= blockIn.getHarvestLevel();
        }
        return false;
    }


    private boolean canHarvestHoe(BlockState blockIn){
        int i = this.getTier().getHarvestLevel();
        if (blockIn.getHarvestTool() == ToolType.HOE) {
            return i >= blockIn.getHarvestLevel();
        }
        return false;
    }





    public void addAttributes(ImmutableMultimap.Builder builder){}

    @Override
    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damageItem(2, attacker, (entity) -> {
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
