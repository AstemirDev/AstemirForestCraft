package org.astemir.forestcraft.common.event;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.astemir.api.APIMods;
import org.astemir.api.AstemirAPI;
import org.astemir.api.common.capability.CapabilityUtils;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.api.common.particle.Particle3D;
import org.astemir.api.loot.DropPool;
import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;
import org.astemir.api.loot.WeightedRandom;
import org.astemir.api.utils.*;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.entities.animals.EntityWorm;
import org.astemir.forestcraft.common.entities.monsters.EntityChangeling;
import org.astemir.forestcraft.common.items.armor.wings.curios.ModWings;
import org.astemir.forestcraft.configuration.ConfigUtils;
import org.astemir.forestcraft.registries.FCBlocks;
import org.astemir.forestcraft.common.capabilities.*;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.forestcraft.common.entities.monsters.EntityNightWatcher;
import org.astemir.forestcraft.common.entities.projectiles.throwable.EntityLiquidElectritePotion;
import org.astemir.forestcraft.registries.*;
import org.astemir.api.math.RandomUtils;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.block.Block.getStateId;


public class ItemEvents {


    @SubscribeEvent
    public static void onHit(LivingHurtEvent e) {
        if (e.getSource().getTrueSource() != null) {
            if (e.getSource().getTrueSource() instanceof LivingEntity) {
                if (EntityUtils.hasArmorFullSet(e.getEntityLiving(), FCItems.SHARPED_LEAF_ARMOR)){
                    ((LivingEntity) e.getSource().getTrueSource()).addPotionEffect(new EffectInstance(FCEffects.BROKEN_ARMOR.get(), 40, 2));
                }
                if (e.getEntityLiving().isPotionActive(FCEffects.THORNS.get())){
                    EffectInstance effect = e.getEntityLiving().getActivePotionEffect(FCEffects.THORNS.get());
                    if (effect.getAmplifier() == 0){
                        float damage = e.getAmount()*0.5f;
                        e.getSource().getTrueSource().attackEntityFrom(DamageSource.causeMobDamage(e.getEntityLiving()),damage);
                    }else{
                        float damage = e.getAmount()*0.75f;
                        e.getSource().getTrueSource().attackEntityFrom(DamageSource.causeMobDamage(e.getEntityLiving()),damage);
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent e) {
        Entity entity = e.getEntity();
        if (entity == null) {
            return;
        }
        World world = e.getEntity().getEntityWorld();
        CapabilitySleep sleepCap = CapabilityUtils.getCapability(FCCapabilities.SLEEPING_CAP,e.getEntityLiving());
        if (sleepCap != null) {
            if (sleepCap.getSleepingTicks() > 0) {
                sleepCap.setSleepingTicks(sleepCap.getSleepingTicks() - 1);
                if (e.getEntity().ticksExisted % 16 == 0) {
                    if (!world.isRemote) {
                        ((ServerWorld) world).spawnParticle(FCParticles.SLEEPING.get(), entity.getPosX(), entity.getPosYEye(), entity.getPosZ(), 1, 0, 0, 0, 0);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onArmorEquip(LivingEquipmentChangeEvent e){
        if (e.getEntityLiving() instanceof PlayerEntity){
            PlayerEntity entity = (PlayerEntity) e.getEntityLiving();
            if (APIMods.CURIOS.isLoaded()) {
                if (e.getSlot().equals(EquipmentSlotType.CHEST)) {
                    if (e.getTo().getItem() instanceof ElytraItem) {
                        IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(entity).orElse(null);
                        if (itemHandler != null) {
                            for (int i = 0; i < itemHandler.getSlots(); i++) {
                                ItemStack stack = itemHandler.getStackInSlot(i);
                                if (stack.getItem() instanceof ModWings){
                                    ItemStack chestplate = EntityUtils.chestplate(entity).copy();
                                    if (chestplate.getItem() instanceof ElytraItem){
                                        entity.setItemStackToSlot(EquipmentSlotType.CHEST,Items.AIR.getDefaultInstance());
                                        PlayerUtils.giveItem(entity,chestplate);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (e.getSlot().equals(EquipmentSlotType.HEAD)) {
                if (e.getTo().getItem().equals(FCItems.INSANE_HELMET)) {
                    FCTriggers.ARMOR_EQUIPPED.trigger((ServerPlayerEntity) entity);
                }
            }
        }
    }



    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            if (FCConfigurationValues.SPAWN_SKY_FRAGMENTS.getValue()) {
                if (e.player.ticksExisted != 0 && e.player.ticksExisted % 1200 == 0 && RandomUtils.doWithChance(FCConfigurationValues.SKY_FRAGMENT_RATE.getValue()) && e.player.getPosY() >= e.player.world.getSeaLevel() && (!e.player.isInWater()) && e.player.world.getDimensionKey() == World.OVERWORLD) {
                    EntityLiquidElectritePotion.EntitySkyFragmentProjectile skyFragment = new EntityLiquidElectritePotion.EntitySkyFragmentProjectile(FCEntities.SKY_FRAGMENT_PROJECTILE, e.player.world);
                    skyFragment.setLocationAndAngles(e.player.getPosX() + RandomUtils.randomInt(-20, 20), e.player.getPosY() + 60, e.player.getPosZ() + RandomUtils.randomInt(-20, 20), 0, 0);
                    skyFragment.shoot(0, -1, 0, 2, 0);
                    e.player.world.addEntity(skyFragment);
                }
            }
            if (e.player.ticksExisted != 0 && e.player.ticksExisted % 2000 == 0 && RandomUtils.doWithChance(1f) && e.player.getPosY() >= e.player.world.getSeaLevel() && (!e.player.isInWater()) && e.player.world.getDimensionKey() == World.OVERWORLD) {
                if (ConfigUtils.isEnabledInConfig(FCConfigurationValues.CHANGELING_RANDOM_SPAWN)) {
                    EntityChangeling changeling = new EntityChangeling(FCEntities.CHANGELING, e.player.world);
                    BlockPos spawnPos = WorldUtils.randomEmptyBlock(e.player.world, e.player.getPosition(), 10);
                    if (e.player.world.getBlockState(spawnPos.down()).getBlock() == Blocks.GRASS_BLOCK && EntityUtils.canSpawnAtPosition(e.player.world, spawnPos, FCEntities.CHANGELING)) {
                        changeling.moveToBlockPosAndAngles(spawnPos, 0, 0);
                        e.player.world.addEntity(changeling);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPortalLight(BlockEvent.PortalSpawnEvent e){
        if (!FCConfigurationValues.ENABLE_FLINT_AND_STEEL.getValue()) {
            if (!e.getState().getBlock().equals(Blocks.SOUL_FIRE)) {
                e.getWorld().getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(e.getPos()).grow(4,4,4)).forEach((player)->{
                    if (player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.FLINT_AND_STEEL || player.getHeldItem(Hand.OFF_HAND).getItem() == Items.FLINT_AND_STEEL){
                        player.sendStatusMessage(TextUtils.translate(FCStrings.PORTAL_WARN, TextFormatting.RED),false);
                    }
                });
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onHoeUse(UseHoeEvent e) {
        World world = e.getContext().getWorld();
        BlockPos pos = e.getContext().getPos();
        Block block = world.getBlockState(pos).getBlock();
        if (block.getDefaultState().equals(Blocks.GRASS_BLOCK.getDefaultState()) || block.getDefaultState().equals(Blocks.DIRT.getDefaultState())) {
            if (e.getContext().getFace() != Direction.DOWN && world.isAirBlock(pos.up())){
                if (RandomUtils.doWithChance(5)) {
                    EntityWorm worm = FCEntities.WORM.create(e.getEntity().world);
                    world.addEntity(worm);
                    worm.moveToBlockPosAndAngles(pos.up(),0,0);
                    world.playEvent((PlayerEntity) null, 2001, pos.up(), Block.getStateId(block.getDefaultState()));
                }
            }
        }
    }




    @SubscribeEvent
    public static void onClickEntity(PlayerInteractEvent.EntityInteract e){
        if (e.getItemStack().getItem().equals(FCBlocks.GRASS_HAY.asItem())){
            if (e.getTarget() instanceof SheepEntity){
                EntityUtils.breedAnimal(e.getPlayer(),(SheepEntity)e.getTarget(),e.getHand(),e.getItemStack());
            }else
            if (e.getTarget() instanceof CowEntity){
                EntityUtils.breedAnimal(e.getPlayer(),(CowEntity)e.getTarget(),e.getHand(),e.getItemStack());
            }
        }else
        if (e.getItemStack().getItem().equals(Items.WATER_BUCKET)){
            if (FCConfigurationValues.SQUID_BUCKET.getValue()) {
                if (e.getTarget() instanceof SquidEntity) {
                    e.getPlayer().playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
                    e.getItemStack().shrink(1);
                    PlayerUtils.giveItem(e.getPlayer(), FCItems.SQUID_BUCKET);
                    e.getPlayer().swingArm(e.getHand());
                    e.getTarget().remove();
                }
            }
        }else
        if (e.getItemStack().getItem().isIn(ItemTags.FISHES)){
            if (e.getTarget() instanceof DolphinEntity){
                ItemStack bonus = DOLPHINIUM.drop();
                if (bonus != null){
                    PlayerUtils.giveItem(e.getPlayer(), bonus);
                }
            }
        }
    }

    private static ItemDropPool DOLPHINIUM = new ItemDropPool().add(1, ItemDrop.fromItem(()->FCItems.DOLPHINIUM)).canHasNoResult().build();



    @SubscribeEvent
    public static void onBedClick(PlayerInteractEvent.RightClickBlock e) {
        if (!e.getWorld().isRemote) {
            if (FCConfigurationValues.SPAWN_NIGHT_WATCHERS.getValue() && e.getWorld().getBlockState(e.getPos()).getBlock() instanceof BedBlock && WorldUtils.isNight(e.getWorld())) {
                if (!((PlayerEntity) e.getEntityLiving()).getCooldownTracker().hasCooldown(Items.WHITE_BED)) {
                    List<EntityNightWatcher> nightWatchers = e.getEntityLiving().world.getEntitiesWithinAABB(FCEntities.NIGHT_WATCHER, e.getEntityLiving().getBoundingBox().grow(32, 32, 32), new Predicate<EntityNightWatcher>() {
                        @Override
                        public boolean test(EntityNightWatcher entityNightWatcher) {
                            return true;
                        }
                    });
                    if (nightWatchers.isEmpty()) {
                        if (RandomUtils.doWithChance(FCConfigurationValues.NIGHT_WATCHERS_RATE.getValue()*0.1f)) {
                            BlockPos spawnPos = WorldUtils.randomEmptyBlock(e.getWorld(),e.getPlayer().getPosition(),16);
                            if (EntityUtils.canSpawnAtPosition(e.getWorld(),spawnPos,FCEntities.NIGHT_WATCHER)) {
                                EntityNightWatcher nightWatcher = FCEntities.NIGHT_WATCHER.create(e.getWorld());
                                nightWatcher.moveToBlockPosAndAngles(spawnPos, 0, 0);
                                e.getWorld().addEntity(nightWatcher);
                                ((PlayerEntity) e.getEntityLiving()).sendStatusMessage(new TranslationTextComponent("message.forestcraft.warn_night_watcher"), false);
                                e.setCanceled(true);
                            }
                        }
                    } else {
                        ((PlayerEntity) e.getEntityLiving()).getCooldownTracker().setCooldown(Items.WHITE_BED, 20);
                        ((PlayerEntity) e.getEntityLiving()).sendStatusMessage(new TranslationTextComponent("message.forestcraft.warn_night_watcher"), false);
                        e.setCanceled(true);
                    }
                } else {
                    e.setCanceled(true);
                }
            }

        }
    }



}
