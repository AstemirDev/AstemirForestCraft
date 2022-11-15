package org.astemir.forestcraft.common.entities.projectiles.fishing;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.network.IPacket;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import org.astemir.api.common.item.IModItem;
import org.astemir.api.common.item.ItemUtils;
import org.astemir.forestcraft.registries.FCItems;
import org.astemir.forestcraft.common.items.IBait;
import org.astemir.forestcraft.configuration.FCConfigurationValues;
import org.astemir.forestcraft.registries.FCEffects;
import org.astemir.api.utils.PlayerUtils;
import org.astemir.api.math.RandomUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class EntityFCFishingBobber extends FishingBobberEntity {


    private ItemStack catchedFish;

    public EntityFCFishingBobber(World worldIn, PlayerEntity player, double x, double y, double z) {
        super(worldIn, player, x, y, z);
    }

    public EntityFCFishingBobber(PlayerEntity p_i50220_1_, World world, int luckBonus,int speedBonus) {
        super(p_i50220_1_, world, luckBonus,speedBonus);
    }



    private ItemStack getFishingRod() {
        ItemStack mainHand = func_234606_i_().getHeldItemMainhand();
        ItemStack otherHand = func_234606_i_().getHeldItemOffhand();
        boolean flag = mainHand.getItem() == getItem();
        boolean flag1 = otherHand.getItem() == getItem();
        if (flag){
            return mainHand;
        }else
        if (flag1){
            return otherHand;
        }
        return null;
    }

    public Item getItem(){
        return Items.FISHING_ROD;
    }


    public ItemStack getBaitItem(){
        PlayerEntity player = func_234606_i_();
        if (player.isCreative() || !FCConfigurationValues.NEED_BAIT.getValue()){
            return FCItems.DIAMOND_WORM.getDefaultInstance();
        }
        if (player != null) {
            ItemStack bait = PlayerUtils.findItem(player, (item) -> {
                if (ItemUtils.isModItem(item)){
                    return ItemUtils.getItemConstructor(item) instanceof IBait;
                }
                return false;
            });
            return bait;
        }
        return null;
    }

    public List<ItemStack> defaultLootTable(PlayerEntity player,ItemStack stack){
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld)this.world)).withParameter(LootParameters.field_237457_g_, this.getPositionVec()).withParameter(LootParameters.TOOL, stack).withParameter(LootParameters.THIS_ENTITY, this).withRandom(this.rand).withLuck((float)this.luck + player.getLuck());
        builder.withParameter(LootParameters.KILLER_ENTITY, this.func_234616_v_()).withParameter(LootParameters.THIS_ENTITY, this);
        ResourceLocation dir = LootTables.GAMEPLAY_FISHING;
        LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(dir);
        List<ItemStack> list = lootTable.generate(builder.build(LootParameterSets.FISHING));
        return list;
    }


    private boolean func_234615_h_() {
        Entity entity = this.func_234616_v_();
        if (entity != null) {
            for(Entity entity1 : this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (p_234613_0_) -> !p_234613_0_.isSpectator() && p_234613_0_.canBeCollidedWith())) {
                if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity()) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void tick() {
        this.field_234596_b_.setSeed(this.getUniqueID().getLeastSignificantBits() ^ this.world.getGameTime());
        if (!this.field_234611_d_) {
            this.field_234611_d_ = this.func_234615_h_();
        }
        baseTick();
        PlayerEntity playerentity = this.func_234606_i_();
        if (playerentity == null) {
            this.remove();
        } else if (this.world.isRemote || !this.func_234600_a_(playerentity)) {
            if (this.onGround) {
                ++this.ticksInGround;
                if (this.ticksInGround >= 1200) {
                    this.remove();
                    return;
                }
            } else {
                this.ticksInGround = 0;
            }

            float f = 0.0F;
            BlockPos blockpos = this.getPosition();
            FluidState fluidstate = this.world.getFluidState(blockpos);
            if (canFishInLiquid(fluidstate)){
                f = fluidstate.getActualHeight(this.world, blockpos);
            }
            boolean flag = f > 0.0F;
            if (this.currentState == FishingBobberEntity.State.FLYING) {
                if (this.caughtEntity != null) {
                    this.setMotion(Vector3d.ZERO);
                    this.currentState = FishingBobberEntity.State.HOOKED_IN_ENTITY;
                    return;
                }

                if (flag) {
                    this.setMotion(this.getMotion().mul(0.3D, 0.2D, 0.3D));
                    this.currentState = FishingBobberEntity.State.BOBBING;
                    return;
                }

                this.checkCollision();
            } else {
                if (this.currentState == FishingBobberEntity.State.HOOKED_IN_ENTITY) {
                    if (this.caughtEntity != null) {
                        if (this.caughtEntity.removed) {
                            this.caughtEntity = null;
                            this.currentState = FishingBobberEntity.State.FLYING;
                        } else {
                            this.setPosition(this.caughtEntity.getPosX(), this.caughtEntity.getPosYHeight(0.8D), this.caughtEntity.getPosZ());
                        }
                    }

                    return;
                }

                if (this.currentState == FishingBobberEntity.State.BOBBING) {
                    Vector3d motion = this.getMotion();
                    double d0 = this.getPosY() + motion.y - (double)blockpos.getY() - (double)f;
                    if (Math.abs(d0) < 0.01D) {
                        d0 += Math.signum(d0) * 0.1D;
                    }
                    this.setMotion(motion.x * 0.9D, motion.y - d0 * (double)this.rand.nextFloat() * 0.2D, motion.z * 0.9D);
                    if (this.ticksCatchable <= 0 && this.ticksCatchableDelay <= 0) {
                        this.field_234595_aq_ = true;
                    } else {
                        this.field_234595_aq_ = this.field_234595_aq_ && this.field_234598_d_ < 10 && this.func_234603_b_(blockpos);
                    }
                    if (flag) {
                        this.field_234598_d_ = Math.max(0, this.field_234598_d_ - 1);
                        if (this.field_234597_c_) {
                            this.setMotion(this.getMotion().add(0.0D, -0.1D * (double)this.field_234596_b_.nextFloat() * (double)this.field_234596_b_.nextFloat(), 0.0D));
                        }

                        if (!this.world.isRemote) {
                            this.catchingFish(blockpos);
                        }
                    } else {
                        this.field_234598_d_ = Math.min(10, this.field_234598_d_ + 1);
                    }
                }
            }

            if (!canFishInLiquid(fluidstate)){
                this.setMotion(this.getMotion().add(0.0D, -0.03D, 0.0D));
            }

            this.move(MoverType.SELF, this.getMotion());
            this.func_234617_x_();
            if (this.currentState == FishingBobberEntity.State.FLYING && (this.onGround || this.collidedHorizontally)) {
                this.setMotion(Vector3d.ZERO);
            }

            this.setMotion(this.getMotion().scale(0.92D));
            this.recenterBoundingBox();
        }
    }

    @Override
    public int handleHookRetraction(ItemStack itemStack) {
        PlayerEntity playerentity = this.func_234606_i_();
        FishingLiquid fishingLiquid = getFishingLiquid(world.getBlockState(getPosition().down()));
        if (fishingLiquid == null){
            fishingLiquid = getFishingLiquid(world.getBlockState(getPosition()));
        }
        if (!this.world.isRemote && playerentity != null) {
            int i = 0;
            ItemFishedEvent event = null;
            if (this.caughtEntity != null) {
                this.bringInHookedEntity();
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity)playerentity, itemStack, this, Collections.emptyList());
                this.world.setEntityState(this, (byte)31);
                i = this.caughtEntity instanceof ItemEntity ? 3 : 5;
            } else if (this.ticksCatchable > 0) {
                ItemStack bait = getBaitItem();
                if (bait == null){
                    catchedFish = null;
                }else{
                    int power = ((IBait)((IModItem)bait.getItem()).itemConstructor()).getBaitPower();
                    if (RandomUtils.doWithChance(100-power)){
                        bait.shrink(1);
                    }
                }
                if (catchedFish != null) {
                    event = new ItemFishedEvent(Arrays.asList(catchedFish), this.onGround ? 2 : 1, this);
                    MinecraftForge.EVENT_BUS.post(event);
                    if (event.isCanceled()) {
                        this.remove();
                        return event.getRodDamage();
                    }
                    CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) playerentity, itemStack, this, Arrays.asList(catchedFish));

                    FishingLiquid finalFishingLiquid = fishingLiquid;
                    if (finalFishingLiquid != null) {
                        ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), catchedFish) {
                            @Override
                            public boolean isImmuneToFire() {
                                return finalFishingLiquid.isHot();
                            }
                        };
                        double d0 = playerentity.getPosX() - this.getPosX();
                        double d1 = playerentity.getPosY() - this.getPosY();
                        double d2 = playerentity.getPosZ() - this.getPosZ();
                        itementity.setMotion(d0 * 0.1D, d1 * 0.1D + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08D, d2 * 0.1D);
                        this.world.addEntity(itementity);
                        playerentity.world.addEntity(new ExperienceOrbEntity(playerentity.world, playerentity.getPosX(), playerentity.getPosY() + 0.5D, playerentity.getPosZ() + 0.5D, this.rand.nextInt(6) + 1) {
                            @Override
                            public boolean isImmuneToFire() {
                                return finalFishingLiquid.isHot();
                            }
                        });
                        if (catchedFish.getItem().isIn(ItemTags.FISHES)) {
                            playerentity.addStat(Stats.FISH_CAUGHT, 1);
                        }
                    }
                    i = 1;
                }
            }
            if (this.onGround) {
                i = 2;
            }
            this.remove();
            return event == null ? i : event.getRodDamage();
        } else {
            return 0;
        }
    }

    @Override
    public void catchingFish(BlockPos p_190621_1_) {
        ServerWorld serverworld = (ServerWorld) this.world;
        FishingLiquid fishingLiquid = getFishingLiquid(world.getBlockState(getPosition().down()));
        if (fishingLiquid == null){
            fishingLiquid = getFishingLiquid(world.getBlockState(getPosition()));
        }
        if (getBaitItem() != null && serverworld != null && fishingLiquid != null) {
            int i = 1;
            BlockPos blockpos = p_190621_1_.up();
            if (this.rand.nextFloat() < 0.25F && this.world.isRainingAt(blockpos)) {
                ++i;
            }

            if (this.rand.nextFloat() < 0.5F && !this.world.canSeeSky(blockpos)) {
                --i;
            }

            if (this.ticksCatchable > 0) {
                --this.ticksCatchable;
                if (this.ticksCatchable <= 0) {
                    setCustomNameVisible(false);
                    catchedFish = null;
                    this.ticksCaughtDelay = 0;
                    this.ticksCatchableDelay = 0;
                    this.getDataManager().set(field_234599_f_, false);
                }
            } else if (this.ticksCatchableDelay > 0) {
                this.ticksCatchableDelay -= i;
                if (this.ticksCatchableDelay > 0) {
                    this.fishApproachAngle = (float) ((double) this.fishApproachAngle + this.rand.nextGaussian() * 4.0D);
                    float f = this.fishApproachAngle * ((float) Math.PI / 180F);
                    float f1 = MathHelper.sin(f);
                    float f2 = MathHelper.cos(f);
                    double d0 = this.getPosX() + (double) (f1 * (float) this.ticksCatchableDelay * 0.1F);
                    double d1 = (float) MathHelper.floor(this.getPosY()) + 1.0F;
                    double d2 = this.getPosZ() + (double) (f2 * (float) this.ticksCatchableDelay * 0.1F);
                    BlockState liquidState = serverworld.getBlockState(new BlockPos((int) d0, (int) d1 - 1, (int) d2));
                    if (canFishInLiquid(liquidState) && getFishingLiquid(liquidState) == fishingLiquid) {
                        if (this.rand.nextFloat() < 0.15F) {
                            serverworld.spawnParticle(fishingLiquid.getBubbleParticle(), d0, d1 - (double) 0.1F, d2, 1, f1, 0.1D, f2, 0.0D);
                        }
                        float f3 = f1 * 0.04F;
                        float f4 = f2 * 0.04F;
                        serverworld.spawnParticle(fishingLiquid.getFishingParticle(), d0, d1, d2, 0, f4, 0.01D, -f3, 1.0D);
                        serverworld.spawnParticle(fishingLiquid.getFishingParticle(), d0, d1, d2, 0, -f4, 0.01D, f3, 1.0D);
                    }
                } else {
                    this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_SPLASH, 1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                    double d3 = this.getPosY() + 0.5D;
                    serverworld.spawnParticle(fishingLiquid.getBubbleParticle(), this.getPosX(), d3, this.getPosZ(), (int) (1.0F + this.getWidth() * 20.0F), this.getWidth(), 0.0D, this.getWidth(), 0.2F);
                    serverworld.spawnParticle(fishingLiquid.getFishingParticle(), this.getPosX(), d3, this.getPosZ(), (int) (1.0F + this.getWidth() * 20.0F), this.getWidth(), 0.0D, this.getWidth(), 0.2F);
                    this.catchedFish = fishingLiquid.fish(defaultLootTable(func_234606_i_(),getFishingRod()),func_234606_i_(),this);
                    if (catchedFish != null && func_234606_i_().isPotionActive(FCEffects.SEA_VISION.get())) {
                        setCustomNameVisible(true);
                        setCustomName(catchedFish.getDisplayName());
                    }
                    this.ticksCatchable = MathHelper.nextInt(this.rand, 20, 40);
                    this.getDataManager().set(field_234599_f_, true);
                }
            } else if (this.ticksCaughtDelay > 0) {
                this.ticksCaughtDelay -= i;
                float f5 = 0.15F;
                if (this.ticksCaughtDelay < 20) {
                    f5 = (float) ((double) f5 + (double) (20 - this.ticksCaughtDelay) * 0.05D);
                } else if (this.ticksCaughtDelay < 40) {
                    f5 = (float) ((double) f5 + (double) (40 - this.ticksCaughtDelay) * 0.02D);
                } else if (this.ticksCaughtDelay < 60) {
                    f5 = (float) ((double) f5 + (double) (60 - this.ticksCaughtDelay) * 0.01D);
                }

                if (this.rand.nextFloat() < f5) {
                    float f6 = MathHelper.nextFloat(this.rand, 0.0F, 360.0F) * ((float) Math.PI / 180F);
                    float f7 = MathHelper.nextFloat(this.rand, 25.0F, 60.0F);
                    double d4 = this.getPosX() + (double) (MathHelper.sin(f6) * f7 * 0.1F);
                    double d5 = (float) MathHelper.floor(this.getPosY()) + 1.0F;
                    double d6 = this.getPosZ() + (double) (MathHelper.cos(f6) * f7 * 0.1F);
                    if (canFishInLiquid(serverworld.getBlockState(new BlockPos(d4, d5 - 1.0D, d6)))) {
                        serverworld.spawnParticle(fishingLiquid.getSplashParticle(), d4, d5, d6, 2 + this.rand.nextInt(2), 0.1F, 0.0D, 0.1F, 0.0D);
                    }
                }
                if (this.ticksCaughtDelay <= 0) {
                    this.fishApproachAngle = MathHelper.nextFloat(this.rand, 0.0F, 360.0F);
                    this.ticksCatchableDelay = MathHelper.nextInt(this.rand, 20, 80);
                }
            } else {
                this.ticksCaughtDelay = MathHelper.nextInt(this.rand, 100, 600);
                this.ticksCaughtDelay -= this.lureSpeed * 20 * 5;
            }
        }
    }

    @Override
    public FishingBobberEntity.WaterType func_234604_c_(BlockPos p_234604_1_) {
        BlockState blockstate = this.world.getBlockState(p_234604_1_);
        if (!blockstate.isAir() && !blockstate.isIn(Blocks.LILY_PAD)) {
            FluidState fluidstate = blockstate.getFluidState();
            boolean tagged = false;
            for (FishingLiquid liquid : liquids()) {
                if (fluidstate.isTagged(liquid.getFluidTag())){
                    tagged = true;
                }
            }
            return tagged && fluidstate.isSource() && blockstate.getCollisionShape(this.world, p_234604_1_).isEmpty() ? FishingBobberEntity.WaterType.INSIDE_WATER : FishingBobberEntity.WaterType.INVALID;
        } else {
            return FishingBobberEntity.WaterType.ABOVE_WATER;
        }
    }

    private FishingLiquid getFishingLiquid(BlockState state){
        for (FishingLiquid liquid : liquids()) {
            if (liquid.getFluidMaterial() == state.getMaterial()){
                return liquid;
            }
        }
        return null;
    }

    public boolean canFishInLiquid(BlockState state){
        for (FishingLiquid liquid : liquids()) {
            if (liquid.getFluidMaterial() == state.getMaterial()){
                return true;
            }
        }
        return false;
    }


    public boolean canFishInLiquid(FluidState state){
        for (FishingLiquid liquid : liquids()) {
            if (state.isTagged(liquid.getFluidTag())){
                return true;
            }
        }
        return false;
    }

    public List<FishingLiquid> liquids(){
        return Arrays.asList(FishingLiquid.WATER);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return super.createSpawnPacket();
    }
}
