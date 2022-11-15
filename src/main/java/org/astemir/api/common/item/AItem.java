package org.astemir.api.common.item;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.types.Func;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.item.ItemModelsHandler;
import org.astemir.api.common.item.vanilla.ModItem;
import org.astemir.api.math.RandomUtils;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.item.Rarity.COMMON;
import static net.minecraft.item.Rarity.EPIC;

public class AItem {

    private List<ITextComponent> defaultLore = new ArrayList<>();
    private List<Enchantment> appliableEnchantments = buildEnchantments();
    private AItemFoodProperties foodProperties;
    public Random random = new Random();
    private ItemGroup itemGroup = null;
    private int groupPriority = 1;
    private int maxItemStack = 64;
    private int maxDamage = 0;
    private String registryName;
    private Rarity defaultRarity = COMMON;
    private boolean fireImmune = false;
    private Function<Item.Properties,Item.Properties> isterFunction;


    public AItem(String registryName) {
        this.registryName = registryName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public AItem setupISTER(Function<Item.Properties,Item.Properties> renderFunction) {
        this.isterFunction = renderFunction;
        return this;
    }

    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
        if (this.isFood()) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            if (playerIn.canEat(foodProperties.isAlwaysEdible())) {
                playerIn.setActiveHand(handIn);
                return ActionResult.resultConsume(itemstack);
            } else {
                return ActionResult.resultFail(itemstack);
            }
        } else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }

    public void onUsingStop(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {}

    public boolean onEntityClick(ItemStack stack, PlayerEntity playerIn, Entity target, Hand hand) {return true;};

    public boolean onHit(ItemStack stack, LivingEntity target, LivingEntity attacker){return true;}

    public void onTick(ItemStack stack, World worldIn, Entity entityIn,int itemSlot,boolean selected){};

    public float getDestroySpeed(ItemStack stack, BlockState state){return 1.0f;}

    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count){}

    public boolean onSwing(ItemStack stack, LivingEntity entity) {return false;}

    public CompoundNBT getShareTag(ItemStack stack) {return null;}

    public void readShareTag(ItemStack stack, CompoundNBT nbt) {}

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot,Multimap<Attribute,AttributeModifier> attributes) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? attributes : ImmutableMultimap.of();
    }

    public ItemStack onUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (isFood()) {
            entityLiving.onFoodEaten(worldIn, stack);
            if (foodProperties.getContainerItem() != null) {
                if (stack.isEmpty()) {
                    return new ItemStack(foodProperties.getContainerItem().get());
                } else {
                    if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.isCreativeMode) {
                        ItemStack itemstack = new ItemStack(foodProperties.getContainerItem().get());
                        PlayerEntity playerentity = (PlayerEntity) entityLiving;
                        if (!playerentity.inventory.addItemStackToInventory(itemstack)) {
                            playerentity.dropItem(itemstack, false);
                        }
                    }

                    return stack;
                }
            }
        }
        return stack;
    }

    public int getUseDuration(ItemStack stack) {
        if (isFood()) {
            return foodProperties.getEatingTime();
        } else {
            return 0;
        }
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {return false;}

    public UseAction getUseAction(ItemStack stack) {
        if (foodProperties != null){
            if (foodProperties.isBeverage()){
                return UseAction.DRINK;
            }
        }
        return isFood() ? UseAction.EAT : UseAction.NONE;
    }

    public boolean canHarvestBlock(BlockState blockIn) {
        return false;
    }

    public List<ITextComponent> getDefaultLore() {
        return defaultLore;
    }

    public AItem lore(List<ITextComponent> list){
        this.defaultLore = list;
        return this;
    }

    public AItem lore(ITextComponent... components){
        this.defaultLore = new ArrayList(Arrays.asList(components));
        return this;
    }

    public AItem loreAdd(ITextComponent... components){
        for (ITextComponent component : components) {
            this.defaultLore.add(component);
        }
        return this;
    }

    public ActionResultType onUseOnBlock(ItemUseContext context){return ActionResultType.PASS;}

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {return false;}

    public Rarity getRarity(ItemStack stack) {
        if (!stack.isEnchanted()) {
            return getDefaultRarity();
        } else {
            switch(getDefaultRarity()) {
                case COMMON:
                case UNCOMMON:
                    return Rarity.RARE;
                case RARE:
                    return EPIC;
                case EPIC:
                default:
                    return getDefaultRarity();
            }
        }
    }

    public AItem rarity(Rarity rarity){
        this.defaultRarity = rarity;
        return this;
    }

    public Rarity getDefaultRarity(){
        return defaultRarity;
    }

    public List<Enchantment> buildEnchantments(){
        return new ArrayList<>();
    }

    public List<Enchantment> getAppliableEnchantments() {
        return appliableEnchantments;
    }

    public void dynamicLoreBuilding(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        defaultLore.forEach((line)->{
            tooltip.add(line);
        });
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) { return true; }

    public boolean isDamageable() {
        return getMaxDamage() > 0;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMaxStackSize() {
        return maxItemStack;
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean isRepairable(ItemStack stack){return false;}


    public boolean isImmuneToFire(){return fireImmune;}

    public AItem fireImmune(){
        fireImmune = true;
        return this;
    }

    public AItem itemGroup(ItemGroup group){
        itemGroup(group,1);
        return this;
    }

    public AItem itemGroup(ItemGroup group,int priority){
        this.itemGroup = group;
        this.groupPriority = priority;
        return this;
    }

    public AItem maxStack(int stack){
        this.maxItemStack = stack;
        return this;
    }

    public AItem maxDamage(int damage){
        this.maxDamage = damage;
        return this;
    }

    public ItemGroup getItemGroup(){return itemGroup;}

    public AItem food(AItemFoodProperties foodProperties){
        this.foodProperties = foodProperties;
        return this;
    }

    public boolean isFood(){
        return foodProperties != null;
    }

    public boolean isCrossbow(ItemStack stack) {
        return false;
    }

    public boolean isShield(LivingEntity entity,ItemStack stack){
        return false;
    }

    public int getFuelTicks(ItemStack itemStack){return -1;}

    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return null;
    }

    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        return MobEntity.getSlotForItemStack(stack) == armorType;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return null;
    }

    public void onFoodEaten(ItemStack itemStack,LivingEntity entity){
        if (isFood()){
            foodProperties.getEffects().forEach((effect,chance)->{
                if (RandomUtils.doWithChance(chance)){
                    entity.addPotionEffect(effect.get());
                }
            });
        }
    }

    public boolean onHurtByEntityWhileEquipped(ItemStack itemStack,LivingEntity entity, Entity attacker,float damage){
        return true;
    }

    public boolean onHurtWhileEquipped(ItemStack itemStack, LivingEntity entity, DamageSource source,float damage){
        return true;
    }

    public void onJumpWhileEquipped(ItemStack stack,PlayerEntity player){
    }

    public void onTickWhileArmorEquippedPre(ItemStack itemStack, PlayerEntity entity){}

    public void onTickWhileArmorEquippedPost(ItemStack itemStack, PlayerEntity entity){}

    public void onTickWhileHeld(ItemStack itemStack, PlayerEntity entity){}


    public void onEquipArmor(ItemStack itemStack, LivingEntity entity){}

    public void onBlockHoe(ItemUseContext context){};

    public boolean onPortalIgnite(PlayerEntity player,ItemStack itemStack, BlockState state, BlockPos pos){return true;}

    public boolean onLeftClick(ItemStack stack, PlayerEntity player){return true;}

    public int getGroupPriority(){
        return groupPriority;
    }

    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return null;
    }

    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return false;
    }

    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
    }

    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        return true;
    }

    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return ItemStack.EMPTY;
    }

    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity)
    {
        return false;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        return false;
    }

    public ActionResultType onUseFirst(ItemStack stack, ItemUseContext context)
    {
        return ActionResultType.PASS;
    }

    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {}

    public Item.Properties buildProperties(){
        Item.Properties properties = new Item.Properties();
        if (isFood()){
            Food.Builder foodBuilder = new Food.Builder().
                    hunger(foodProperties.getHungerRestore()).
                    saturation(foodProperties.getSaturation());
            if (foodProperties.isMeat()){
                foodBuilder.meat();
            }
            if (foodProperties.isAlwaysEdible()){
                foodBuilder.setAlwaysEdible();
            }
            properties.food(foodBuilder.build());
        }
        if (!isDamageable()){
            properties.maxStackSize(getMaxStackSize());
        }else{
            properties.maxDamage(getMaxDamage());
        }
        if (itemGroup != null) {
            properties.group(getItemGroup());
        }
        if (isterFunction != null){
            Item.Properties properties1 = isterFunction.apply(properties);
        }
        return properties;
    }



    public Item build(Item.Properties properties){
        ModItem resultItem = new ModItem(properties).itemConstructor(this);
        return resultItem;
    }

    public Item register(){
        Item item = build(buildProperties());
        item.setRegistryName(registryName);
        return item;
    }


    protected static BlockRayTraceResult rayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vector3d vector3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();;
        Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return worldIn.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }

    public AItemFoodProperties getFoodProperties() {
        return foodProperties;
    }
}
