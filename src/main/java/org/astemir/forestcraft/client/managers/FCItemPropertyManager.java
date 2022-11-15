package org.astemir.forestcraft.client.managers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.misc.ItemAlchemicalBag;

public class FCItemPropertyManager {

    @OnlyIn(Dist.CLIENT)
    public static void register() {
        registerBowProperties(FCItems.INSANE_BOW);
        registerBowProperties(FCItems.SOUL_CONQUEROR);
        registerBowProperties(FCItems.ELECTROBOW);
        registerShieldProperties(FCItems.ARCHAIC_SHIELD);
        registerShieldProperties(FCItems.HONEY_KEEPER);
        registerShieldProperties(FCItems.PREHISTORIC_SHIELD);
        registerFishingRod(FCItems.MOLTEN_FISHING_ROD);
        registerUsable(FCItems.FOSSIL_BRUSH);
        registerUsable(FCItems.KEEPER_OF_HEAVEN);
        registerUsable(FCItems.DARK_MATTER);
        registerUsable(FCItems.RAIN_FLUTE);
        registerGunProperties(FCItems.GHAST_CANNON);
        registerItemProperty(FCItems.WATERING_CAN,"filled",(stack, world, entity)->{
            if (entity == null){
                return 0.0f;
            }else{
                return stack.getDamage() < stack.getMaxDamage() ? 1 : 0;
            }
        });
        registerItemProperty(FCItems.IRON_WATERING_CAN,"filled",(stack, world, entity)->{
            if (entity == null){
                return 0.0f;
            }else{
                return stack.getDamage() < stack.getMaxDamage() ? 1 : 0;
            }
        });
        registerItemProperty(FCItems.ALCHEMICAL_BAG, "fullness", ItemAlchemicalBag::getFullnessPropertyOverride);
        registerItemProperty(FCItems.TIDE_DWELLER,"using", (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
        registerItemProperty(FCItems.TIDE_DWELLER, "time", (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                ItemStack itemstack = entity.getActiveItemStack();
                return !itemstack.isEmpty() ?  (((float)entity.getItemInUseCount())/((float)stack.getUseDuration())) : 0.0F;
            }
        });
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerItemProperty(Item item, String property, IItemPropertyGetter getter){
        ItemModelsProperties.registerProperty(item,new ResourceLocation(property),getter);
    }

    public static void registerGunProperties(Item item){
        registerItemProperty(item, "charged", (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                ItemStack itemstack = entity.getActiveItemStack();
                if (!itemstack.isEmpty()){
                    if (itemstack.getItem() == item){
                        return  (float) (stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
                    }
                }
                return 0.0f;
            }
        });
    }

    public static void registerBowProperties(Item item){
        registerItemProperty(item, "pull", (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                ItemStack itemstack = entity.getActiveItemStack();
                return !itemstack.isEmpty() ? (float) (stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F : 0.0F;
            }
        });
        registerItemProperty(item, "pulling", (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

    public static void registerShieldProperties(Item item){
        registerItemProperty(item,"forestcraft:blocking", (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

    public static void registerFishingRod(Item item){
        registerItemProperty(item, "cast", (stack, world, entity) -> {
            if (stack == null) {
                return 0.0F;
            } else {
                if (entity != null) {
                    boolean flag = entity.getHeldItemMainhand() == stack;
                    boolean flag1 = entity.getHeldItemOffhand() == stack;
                    if (entity.getHeldItemMainhand().getItem().equals(item)) {
                        flag1 = false;
                    }
                    return (flag || flag1) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishingBobber != null ? 1.0F : 0.0F;
                }
                return 0f;
            }
        });
    }

    public static void registerUsable(Item item){
        registerItemProperty(item,"used", (stack, world, entity) ->
                entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

}
