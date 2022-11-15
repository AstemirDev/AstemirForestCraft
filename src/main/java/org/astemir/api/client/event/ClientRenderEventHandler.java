package org.astemir.api.client.event;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.astemir.api.APIMods;
import org.astemir.api.AstemirAPI;
import org.astemir.api.client.animator.AnimatedBipedModel;
import org.astemir.api.client.animator.AnimatedItemModel;
import org.astemir.api.client.ui.BossbarRenderer;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.IAnimatedItem;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.common.item.vanilla.ModArmorItem;
import org.astemir.api.utils.EntityUtils;
import org.astemir.forestcraft.ForestCraft;
import top.theillusivec4.curios.api.CuriosApi;

@OnlyIn(Dist.CLIENT)
public class ClientRenderEventHandler {

    public static BossbarRenderer BOSSBAR_RENDERER = new BossbarRenderer();

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post e) {
        if (Minecraft.getInstance().gameSettings.hideGUI){
            return;
        }
        if (e.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            BOSSBAR_RENDERER.render();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }


    @SubscribeEvent
    public static void onTick(TickEvent.RenderTickEvent e){
        AstemirAPI.CLIENT.TICKS++;
    }

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Post e){
        ItemStack helmet = EntityUtils.helmet(e.getEntity());
        ItemStack chestplate = EntityUtils.chestplate(e.getEntity());
        ItemStack leggings = EntityUtils.leggings(e.getEntity());
        ItemStack boots = EntityUtils.boots(e.getEntity());
        ItemStack mainHand = EntityUtils.mainHand(e.getEntity());
        ItemStack offHand = EntityUtils.offhand(e.getEntity());
        animateArmor(e.getEntity(),helmet);
        animateArmor(e.getEntity(),chestplate);
        animateArmor(e.getEntity(),leggings);
        animateArmor(e.getEntity(),boots);
        if (APIMods.CURIOS.isLoaded()) {
            IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(e.getEntity()).orElse(null);
            if (itemHandler != null) {
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    animateArmor(e.getEntity(), itemHandler.getStackInSlot(i));
                }
            }
        }
        animateItem(e.getEntity(),mainHand);
        animateItem(e.getEntity(),offHand);
    }



    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent e){
        PlayerEntity player = Minecraft.getInstance().player;
        ItemStack mainHand = EntityUtils.mainHand(player);
        ItemStack offHand = EntityUtils.offhand(player);
        animateItem(player,mainHand);
        animateItem(player,offHand);
    }

    @SubscribeEvent
    public static void onWorldLastEvent(RenderWorldLastEvent e){
        AstemirAPI.CLIENT.ITEM_MODELS_HANDLER.update(AstemirAPI.CLIENT.TICKS);
    }


    private static void animateItem(LivingEntity entity, ItemStack stack){
        if (stack != null){
            if (ItemUtils.isModItem(stack.getItem())){
                AItem constructor = ItemUtils.getItemConstructor(stack);
                if (constructor instanceof IAnimatedItem){
                    Class<? extends Model> modelClass = AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.getModel(((IAnimatedItem)(ItemUtils.getItemConstructor(stack.getItem()))).getModelIndex());
                    if (modelClass != null){
                        Model model = AstemirAPI.CLIENT.ITEM_MODELS_HANDLER.getModel(stack,modelClass);
                        if (model != null) {
                            if (model instanceof AnimatedItemModel) {
                                ((AnimatedItemModel) model).animation(entity, stack);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void animateArmor(LivingEntity entity,ItemStack stack){
        if (stack != null){
            if (stack.getItem() instanceof ModArmorItem){
                Class<? extends BipedModel> modelClass = (Class<? extends BipedModel>) AstemirAPI.CLIENT.ITEM_MODELS_REGISTRY.getModel(((ModArmorItem)stack.getItem()).armorModel);
                if (modelClass != null){
                    BipedModel model = AstemirAPI.CLIENT.ITEM_MODELS_HANDLER.getModel(entity,modelClass);
                    if (model != null) {
                        if (model instanceof AnimatedBipedModel) {
                            ((AnimatedBipedModel) model).animation(entity, stack);
                        }
                    }
                }
            }
        }
    }
}
