package org.astemir.forestcraft.client.event;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import org.astemir.api.AstemirAPI;
import org.astemir.api.ClientAPI;
import org.astemir.api.client.RenderUtils;
import org.astemir.api.client.animator.AModelRenderer;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.api.math.Vector2;
import org.astemir.api.utils.*;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.client.FCClientAPI;
import org.astemir.forestcraft.client.ScreenShaker;
import org.astemir.forestcraft.common.items.constructors.FCGun;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.common.capabilities.CapabilityCosmoFog;
import org.astemir.forestcraft.registries.FCCapabilities;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.items.constructors.FCMusicInstrument;
import org.astemir.api.math.Range;
import org.astemir.forestcraft.registries.FCItems;
import org.lwjgl.opengl.GL11;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {


    private static final ResourceLocation MUSIC_BAR = new ResourceLocation(ForestCraft.MOD_ID,"textures/gui/music_bar.png");
    private static final ResourceLocation SLEEPING_SCREEN = new ResourceLocation(ForestCraft.MOD_ID,"textures/effect/sleeping_screen.png");

    private static final Range KEEPER_OF_HEAVEN = new Range(-30,120,10f);
    private static final Range GALAXIA = new Range(-30,120,10f);
    private static final Range COSMOFOG_DENSITY = new Range(0.05f*1000,0.15f*1000);


    private static AModelRenderer rightArm;
    private static AModelRenderer leftArm;

    public static AModelRenderer createArms(PlayerModel model){
        if (rightArm == null) {
            rightArm = new AModelRenderer(model, 40, 16);
            rightArm.addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0);
            rightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
        }
        if (leftArm == null){
            leftArm = new AModelRenderer(model, 32, 48);
            leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0);
            leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        }
        return rightArm;
    }

    @SubscribeEvent
    public static  void onPlayerRender(RenderPlayerEvent.Post e){
//       PlayerRenderer renderer = e.getRenderer();
//       PlayerModel model = renderer.getEntityModel();
//       createArms(model);
//       boolean animated = false;
//       if (e.getPlayer().getActiveItemStack().getItem().equals(FCItems.KEEPER_OF_HEAVEN)){
//            if (e.getPlayer().getItemInUseCount() > 0) {
//                if (e.getPlayer().getActiveHand() == Hand.MAIN_HAND) {
//                    rightArm.setRotation(new Vector3(0,MathUtils.rad(-180),0));
//                }else{
//                    leftArm.setRotation(new Vector3(0,MathUtils.rad(180),0));
//                }
//                animated = true;
//            }
//       }
//       if (!animated){
//           rightArm.setRotation(new Vector3(model.bipedRightArm.rotateAngleX,model.bipedRightArm.rotateAngleY,model.bipedRightArm.rotateAngleZ));
//           leftArm.setRotation(new Vector3(model.bipedLeftArm.rotateAngleX,model.bipedLeftArm.rotateAngleY,model.bipedLeftArm.rotateAngleZ));
//           rightArm.showModel = false;
//           leftArm.showModel = false;
//           model.bipedRightArm.showModel = true;
//           model.bipedLeftArm.showModel = true;
//       }else{
//           rightArm.showModel = true;
//           leftArm.showModel = true;
//           model.bipedRightArm.showModel = false;
//           model.bipedLeftArm.showModel = false;
//       }
    }

    @SubscribeEvent
    public static void onRenderCamera(EntityViewRenderEvent.CameraSetup e) {
        PlayerEntity player = Minecraft.getInstance().player;
        float ticksExistedDelta = (float) (((float)ClientAPI.TICKS) + e.getRenderPartialTicks()/20);
        ScreenShaker screenShaker = FCClientAPI.SCREEN_SHAKER;
        if (player != null) {
            if ((player.isPotionActive(FCEffects.FEAR.get()) || screenShaker.getTicks()>0) && ConfigUtils.isEnabledInConfig(FCConfigurationValues.SCREEN_SHAKE)) {
                EffectInstance effect = player.getActivePotionEffect(FCEffects.FEAR.get());
                int effectAmplifier = 0;
                if (effect != null){
                    effectAmplifier = effect.getAmplifier();
                }
                int power = effectAmplifier+screenShaker.getPower();
                float shakeAmplitude = ((float)power)/20;
                if (shakeAmplitude > 1.0F){
                    shakeAmplitude = 1.0F;
                }
                e.setPitch((float)((double)e.getPitch() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 1.5F + 2.0F)));
                e.setYaw((float)((double)e.getYaw() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 2.5F + 1.0F)));
                e.setRoll((float)((double)e.getRoll() + (double)shakeAmplitude * Math.cos(ticksExistedDelta * 2.0F)));
            }
        }
    }


    @SubscribeEvent
    public static void onRender(EntityViewRenderEvent.FOVModifier e) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getActiveItemStack().isItemEqualIgnoreDurability(FCItems.SOUL_CONQUEROR.getDefaultInstance())){
                float f = 1.0F;
                int i = player.getItemInUseMaxCount();
                float f1 = (float)i / 20.0F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                } else {
                    f1 = f1 * f1;
                }
                f *= 1.0F - f1 * 0.35F;
                e.setFOV((float)e.getFOV()*f);
            }
        }
    }


    @SubscribeEvent
    public static void onRenderFog(EntityViewRenderEvent.RenderFogEvent.FogDensity e){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.COSMIC_FIEND_FOG) && !Minecraft.getInstance().player.isCreative() && !Minecraft.getInstance().player.isSpectator()) {
            CapabilityCosmoFog fog = CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG, Minecraft.getInstance().player);
            if (fog != null) {
                if (fog.hasFog()) {
                    GlStateManager.fogStart(0.1f);
                    GlStateManager.fogEnd(0.5f);
                    COSMOFOG_DENSITY.update((float) e.getRenderPartialTicks());
                    e.setDensity(COSMOFOG_DENSITY.getValue() / 1000);
                    e.setCanceled(true);
                }
            } else {
                e.setDensity(0.0001f);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderFogColor(EntityViewRenderEvent.RenderFogEvent.FogColors e){
        if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.COSMIC_FIEND_FOG) && !Minecraft.getInstance().player.isCreative()) {
            CapabilityCosmoFog fog = CapabilityUtils.getCapability(FCCapabilities.COSMO_FOG, Minecraft.getInstance().player);
            if (fog != null) {
                if (fog.hasFog()) {
                    e.setRed(24f / 255f);
                    e.setGreen(0 / 255f);
                    e.setBlue(46f / 255f);
                }
            }
        }
    }



    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent e){
        PlayerEntity player = Minecraft.getInstance().player;
        if (e.getItemStack().getItem().equals(FCItems.KEEPER_OF_HEAVEN)){
            if (player.getItemInUseCount() > 0) {
                float j = KEEPER_OF_HEAVEN.getValue();
                e.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(j));
                KEEPER_OF_HEAVEN.update();
            }else{
                KEEPER_OF_HEAVEN.setValue(-30);
            }
        }else
        if (e.getItemStack().getItem().equals(FCItems.GALAXIA)){
            if (player.getItemInUseCount() > 0) {
                float j = GALAXIA.getValue();
                e.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(j));
                e.getMatrixStack().rotate(Vector3f.XP.rotationDegrees(-90));
                GALAXIA.update();
            }else{
                GALAXIA.setValue(-30);
            }
        }else
        if (e.getItemStack().getItem().equals(FCItems.KEEPER_OF_CLOUDS)){
            if (player.getItemInUseCount() > 0) {
                float j = KEEPER_OF_HEAVEN.getValue();
                e.getMatrixStack().rotate(Vector3f.YP.rotationDegrees(j));
                KEEPER_OF_HEAVEN.update();
            }else{
                KEEPER_OF_HEAVEN.setValue(-30);
            }
        }
    }



    @SubscribeEvent
    public static void onTick(TickEvent.RenderTickEvent e){
        FCClientAPI.SCREEN_SHAKER.tick();
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Post e) {
        PlayerEntity playerEntity = Minecraft.getInstance().player;
        ItemStack mainHand = EntityUtils.mainHand(playerEntity);
        ItemStack offHand = EntityUtils.offhand(playerEntity);
        if (Minecraft.getInstance().gameSettings.hideGUI){
            return;
        }
        if (e.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            boolean mainHandMusic = ItemUtils.isModItem(mainHand) ? ItemUtils.getItemConstructor(mainHand) instanceof FCMusicInstrument ? true : false : false;
            boolean offHandMusic = ItemUtils.isModItem(offHand) ? ItemUtils.getItemConstructor(offHand) instanceof FCMusicInstrument ? true : false : false;
            if (mainHandMusic || offHandMusic) {
                int note = SoundUtils.turnToNote(playerEntity.rotationPitch);
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                Minecraft.getInstance().getTextureManager().bindTexture(MUSIC_BAR);
                GlStateManager.color4f(1, 1, 1, 1);
                int x = 0;
                int y = 46;
                GuiUtils.drawTexturedModalRect(e.getMatrixStack(), x, y - 2, 0, 0, 34, 214, 0);
                GuiUtils.drawTexturedModalRect(e.getMatrixStack(), x + 17, y + 8 * (24 - note), 40, 0, 21, 12, 0);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
        if (e.getType().equals(RenderGameOverlayEvent.ElementType.HOTBAR)){
            ItemStack heldStack = ItemUtils.isModItem(mainHand) ? mainHand : ItemUtils.isModItem(offHand) ? offHand : null;
            if (heldStack != null){
                AItem constructor = ItemUtils.getItemConstructor(heldStack);
                if (constructor instanceof FCGun){
                    FCGun heldGun = (FCGun)constructor;
                    int count = -1;
                    if (heldGun.getProperties().usingAmmo()) {
                        count = 0;
                        for (ItemStack itemStack : playerEntity.inventory.mainInventory) {
                            if (heldGun.getInventoryAmmoPredicate().test(itemStack)) {
                                count += itemStack.getCount();
                            }
                        }
                    }
                    String infinite = "∞";
                    float windowWidth = Minecraft.getInstance().getMainWindow().getScaledWidth();
                    float windowHeight = Minecraft.getInstance().getMainWindow().getScaledHeight();
                    int index = heldStack == offHand ? -1 : playerEntity.inventory.currentItem;
                    float offsetX = 20;
                    if (index == -1){
                        offsetX = 29;
                    }
                    Vector2 scale = new Vector2(0.75f,0.75f);
                    if (count > 999 && !playerEntity.abilities.isCreativeMode){
                        scale = new Vector2(0.5f,0.5f);
                    }
                    RenderUtils.drawTextZIndex(TextUtils.text(count != -1 && !playerEntity.abilities.isCreativeMode ? count+"" : infinite, TextFormatting.WHITE),new Vector2((index*offsetX),-20),new Vector2((windowWidth/2)-80,windowHeight),scale,0,true,1000);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onRenderWorld(RenderGameOverlayEvent.Pre e) {
        PlayerEntity playerEntity = Minecraft.getInstance().player;
        if (e.getType() == RenderGameOverlayEvent.ElementType.ALL){
            if (playerEntity.isPotionActive(FCEffects.SLEEPING.get())){
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                Minecraft.getInstance().getTextureManager().bindTexture(SLEEPING_SCREEN);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0, 0); GL11.glVertex3f(0, 0, 0);
                GL11.glTexCoord2f(0, 1); GL11.glVertex3f(0, Minecraft.getInstance().getMainWindow().getScaledHeight(), 0);
                GL11.glTexCoord2f(1, 1); GL11.glVertex3f(Minecraft.getInstance().getMainWindow().getScaledWidth(), Minecraft.getInstance().getMainWindow().getScaledHeight(), 0);
                GL11.glTexCoord2f(1, 0); GL11.glVertex3f(Minecraft.getInstance().getMainWindow().getScaledWidth(), 0, 0);
                GL11.glEnd();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }



}
