package org.astemir.api.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.astemir.api.APIMods;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.network.LeftClickMessage;
import org.astemir.api.common.item.IModItem;
import org.astemir.api.utils.EntityUtils;
import top.theillusivec4.curios.api.CuriosApi;

public class ItemEventHandler {


    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickEmpty e){
        onLeftClick(e.getPlayer(),e.getItemStack());
        if (e.getWorld().isRemote) {
            AstemirAPI.PACKET_HANDLER.getNetwork().sendToServer(new LeftClickMessage(e.getHand()));
        }
    }


    @SubscribeEvent
    public static void onClickEntity(PlayerInteractEvent.EntityInteract e){
        boolean click = onEntityClick(e.getPlayer(),e.getTarget(),e.getItemStack(),e.getHand());
        if (!click) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPortalLight(BlockEvent.PortalSpawnEvent e) {
        e.getWorld().getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(e.getPos()).grow(4,4,4)).forEach((player)->{
            ItemStack mainHand = EntityUtils.mainHand(player);
            ItemStack offHand = EntityUtils.offhand(player);
            if (ItemUtils.isModItem(mainHand)){
                if (!ItemUtils.getItemConstructor(mainHand).onPortalIgnite(player,mainHand,e.getState(),e.getPos())){
                    e.setCanceled(true);
                }
            }
            if (ItemUtils.isModItem(offHand)){
                if (!ItemUtils.getItemConstructor(offHand).onPortalIgnite(player,offHand,e.getState(),e.getPos())){
                    e.setCanceled(true);
                }
            }
        });
    }


    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e) {
        ItemStack helmet = EntityUtils.helmet(e.player);
        ItemStack chestplate = EntityUtils.chestplate(e.player);
        ItemStack leggings = EntityUtils.leggings(e.player);
        ItemStack boots = EntityUtils.boots(e.player);
        ItemStack mainHand = EntityUtils.mainHand(e.player);
        ItemStack offHand = EntityUtils.offhand(e.player);
        if (e.phase == TickEvent.Phase.START) {
            if (APIMods.CURIOS.isLoaded()) {
                IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(e.player).orElse(null);
                if (itemHandler != null) {
                    for (int i = 0; i < itemHandler.getSlots(); i++) {
                        itemEquippedTickPre(itemHandler.getStackInSlot(i),e.player);
                    }
                }
            }
            itemEquippedTickPre(helmet,e.player);
            itemEquippedTickPre(chestplate,e.player);
            itemEquippedTickPre(leggings,e.player);
            itemEquippedTickPre(boots,e.player);
            itemHeldTick(mainHand,e.player);
            itemHeldTick(offHand,e.player);
            if (e.player.isJumping){
                jumpWhileEquipped(helmet,e.player);
                jumpWhileEquipped(chestplate,e.player);
                jumpWhileEquipped(leggings,e.player);
                jumpWhileEquipped(boots,e.player);
                if (APIMods.CURIOS.isLoaded()) {
                    IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(e.player).orElse(null);
                    if (itemHandler != null) {
                        for (int i = 0; i < itemHandler.getSlots(); i++) {
                            jumpWhileEquipped(itemHandler.getStackInSlot(i),e.player);
                        }
                    }
                }
            }
        }
        if (e.phase == TickEvent.Phase.END){
            if (APIMods.CURIOS.isLoaded()) {
                IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(e.player).orElse(null);
                if (itemHandler != null) {
                    for (int i = 0; i < itemHandler.getSlots(); i++) {
                        itemEquippedTickPost(itemHandler.getStackInSlot(i),e.player);
                    }
                }
            }
            itemEquippedTickPost(helmet,e.player);
            itemEquippedTickPost(chestplate,e.player);
            itemEquippedTickPost(leggings,e.player);
            itemEquippedTickPost(boots,e.player);
        }
    }

    @SubscribeEvent
    public static void onHit(LivingHurtEvent e) {
        if (e.getSource().getTrueSource() != null) {
            if (e.getSource().getTrueSource() instanceof LivingEntity) {
                LivingEntity damaged = e.getEntityLiving();
                Entity damager = e.getSource().getTrueSource();
                ItemStack mainHand = EntityUtils.mainHand(damaged);
                ItemStack offHand = EntityUtils.offhand(damaged);
                ItemStack helmet = EntityUtils.helmet(damaged);
                ItemStack chestplate = EntityUtils.chestplate(damaged);
                ItemStack leggings = EntityUtils.leggings(damaged);
                ItemStack boots = EntityUtils.boots(damaged);
                boolean b1 = hurtByEntityWhileEquippedArmor(mainHand,damaged,damager,e.getAmount());
                boolean b2 = hurtByEntityWhileEquippedArmor(offHand,damaged,damager,e.getAmount());
                boolean b3 = hurtByEntityWhileEquippedArmor(helmet,damaged,damager,e.getAmount());
                boolean b4 = hurtByEntityWhileEquippedArmor(chestplate,damaged,damager,e.getAmount());
                boolean b5 = hurtByEntityWhileEquippedArmor(leggings,damaged,damager,e.getAmount());
                boolean b6 = hurtByEntityWhileEquippedArmor(boots,damaged,damager,e.getAmount());
                if (b1 == true || b2 == true || b3 == true || b4 == true || b5 == true || b6 == true){
                    e.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttacked(LivingAttackEvent e) {
        LivingEntity livingEntity = e.getEntityLiving();
        ItemStack mainHand = EntityUtils.mainHand(livingEntity);
        ItemStack offHand = EntityUtils.offhand(livingEntity);
        ItemStack helmet = EntityUtils.helmet(livingEntity);
        ItemStack chestplate = EntityUtils.chestplate(livingEntity);
        ItemStack leggings = EntityUtils.leggings(livingEntity);
        ItemStack boots = EntityUtils.boots(livingEntity);
        boolean b1 = hurtWhileEquippedArmor(mainHand,livingEntity,e.getSource(),e.getAmount());
        boolean b2 = hurtWhileEquippedArmor(offHand,livingEntity,e.getSource(),e.getAmount());
        boolean b3 = hurtWhileEquippedArmor(helmet,livingEntity,e.getSource(),e.getAmount());
        boolean b4 = hurtWhileEquippedArmor(chestplate,livingEntity,e.getSource(),e.getAmount());
        boolean b5 = hurtWhileEquippedArmor(leggings,livingEntity,e.getSource(),e.getAmount());
        boolean b6 = hurtWhileEquippedArmor(boots,livingEntity,e.getSource(),e.getAmount());
        if (b1 == true || b2 == true || b3 == true || b4 == true || b5 == true || b6 == true){
            e.setCanceled(true);
        }
    }


    @SubscribeEvent
    public static void onEatFood(LivingEntityUseItemEvent.Finish e){
        if (e.getItem().isFood()) {
            if (ItemUtils.isModItem(e.getItem())){
                ItemUtils.getItemConstructor(e.getItem()).onFoodEaten(e.getItem(), e.getEntityLiving());
            }
        }
    }


    @SubscribeEvent
    public static void onArmorEquip(LivingEquipmentChangeEvent e){
        if (e.getEntityLiving() instanceof PlayerEntity){
            if (ItemUtils.isModItem(e.getTo())){
                ItemUtils.getItemConstructor(e.getTo()).onEquipArmor(e.getTo(),e.getEntityLiving());
            }
        }
    }

    private static void jumpWhileEquipped(ItemStack itemStack, PlayerEntity entity){
        if (!itemStack.isEmpty()) {
            if (ItemUtils.isModItem(itemStack)){
                ItemUtils.getItemConstructor(itemStack).onJumpWhileEquipped(itemStack,entity);
            }
        }
    }

    private static boolean hurtWhileEquippedArmor(ItemStack armorStack, LivingEntity damaged, DamageSource source, float damage){
        if (!armorStack.isEmpty()) {
            if (ItemUtils.isModItem(armorStack)) {
                ItemUtils.getItemConstructor(armorStack).onHurtWhileEquipped(armorStack,damaged,source,damage);
            }
        }
        return false;
    }

    private static boolean hurtByEntityWhileEquippedArmor(ItemStack armorStack, LivingEntity damaged, Entity damager,float damage){
        if (!armorStack.isEmpty()) {
            if (ItemUtils.isModItem(armorStack)) {
                ItemUtils.getItemConstructor(armorStack).onHurtByEntityWhileEquipped(armorStack,damaged,damager,damage);
            }
        }
        return false;
    }


    private static void itemEquippedTickPost(ItemStack armorStack, PlayerEntity entity){
        if (!armorStack.isEmpty()) {
            if (ItemUtils.isModItem(armorStack)) {
                ItemUtils.getItemConstructor(armorStack).onTickWhileArmorEquippedPost(armorStack,entity);
            }
        }
    }

    private static void itemHeldTick(ItemStack armorStack, PlayerEntity entity){
        if (!armorStack.isEmpty()) {
            if (ItemUtils.isModItem(armorStack)) {
                ItemUtils.getItemConstructor(armorStack).onTickWhileHeld(armorStack,entity);
            }
        }
    }

    private static void itemEquippedTickPre(ItemStack armorStack, PlayerEntity entity){
        if (!armorStack.isEmpty()) {
            if (ItemUtils.isModItem(armorStack)) {
                ItemUtils.getItemConstructor(armorStack).onTickWhileArmorEquippedPre(armorStack,entity);
            }
        }
    }

    public static void onLeftClick(PlayerEntity playerEntity, ItemStack stack){
        if (ItemUtils.isModItem(stack)) {
            ItemUtils.getItemConstructor(stack).onLeftClick(stack,playerEntity);
        }
    }


    public static boolean onEntityClick(PlayerEntity playerEntity, Entity target,ItemStack stack,Hand hand){
        if (ItemUtils.isModItem(stack)) {
            ItemUtils.getItemConstructor(stack).onEntityClick(stack, playerEntity,target,hand);
        }
        return true;
    }

}
