package org.astemir.forestcraft.common.items.constructors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.astemir.api.common.item.AItem;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.common.sound.SoundUtils;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.registries.FCItemGroups;
import java.util.function.Supplier;

public class FCMusicInstrument extends AItem {

    private final Supplier<SoundEvent> sound;

    public FCMusicInstrument(String registryName,Supplier<SoundEvent> sound) {
        super(registryName);
        this.sound = sound;
        maxStack(1);
        itemGroup(FCItemGroups.MUSIC_INSTRUMENTS);
        lore(new TranslationTextComponent(FCStrings.INSTRUMENT).mergeStyle(TextFormatting.GRAY));
    }

    public Supplier<SoundEvent> getSound() {
        return sound;
    }


    @Override
    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        if (count % 5 == 0) {
            playSound(livingEntityIn);
            ItemStack offHand = livingEntityIn.getHeldItem(getInactiveHand(livingEntityIn));
            if (!offHand.isEmpty()){
                if (ItemUtils.isModItem(offHand)){
                    AItem constructor = ItemUtils.getItemConstructor(offHand);
                    if (constructor instanceof FCMusicInstrument) {
                        ((FCMusicInstrument) constructor).playSound(livingEntityIn);
                    }
                }
            }
        }
    }

    public void playSound(LivingEntity livingEntityIn){
        int note = SoundUtils.turnToNote(livingEntityIn.rotationPitch);
        livingEntityIn.world.addParticle(ParticleTypes.NOTE, livingEntityIn.getPosX(), livingEntityIn.getPosY() + 2, livingEntityIn.getPosZ(), (double) note / 24.0D, 0.0D, 0.0D);
        SoundUtils.playPianoSound(livingEntityIn.world, sound.get(), livingEntityIn.getPosition(), note);
    }

    public Hand getInactiveHand(LivingEntity e){
        if (e.getActiveHand().equals(Hand.MAIN_HAND)){
            return Hand.OFF_HAND;
        }else{
            return Hand.MAIN_HAND;
        }
    }


    @Override
    public int getUseDuration(ItemStack stack) {
        return 10000;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }



    @Override
    public ActionResult<ItemStack> onRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

}
