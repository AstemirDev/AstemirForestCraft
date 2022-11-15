package org.astemir.forestcraft.registries;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.forestcraft.common.entities.FCEntityClassification;
import org.astemir.forestcraft.common.entities.animals.*;
import org.astemir.forestcraft.common.entities.monsters.bosses.*;
import org.astemir.forestcraft.common.entities.monsters.*;
import org.astemir.forestcraft.common.entities.projectiles.arrows.EntityElectriteArrow;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityBlackholeBullet;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityDemonBulletProjectile;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntityMoltenBullet;
import org.astemir.forestcraft.common.entities.projectiles.bullets.EntitySkyBullet;
import org.astemir.forestcraft.common.entities.projectiles.fishing.EntityFCFishingBobber;
import org.astemir.forestcraft.common.entities.projectiles.fishing.EntityFireFishingBobber;
import org.astemir.forestcraft.common.entities.projectiles.other.*;
import org.astemir.forestcraft.common.entities.projectiles.throwable.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ForestCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCEntities {

    public static final EntityType<EntityAknayah> AKNAYAH = register("aknayah",EntityAknayah::new,EntityClassification.MONSTER,0.6f,1.95f);
    public static final EntityType<EntityDandelioneer> DANDELIONEER = register("dandelioneer",EntityDandelioneer::new, EntityClassification.CREATURE,0.5f,0.8f);
    public static final EntityType<EntityCrocusFlower> CROCUS_FLOWER = register("crocus_flower",EntityCrocusFlower::new, EntityClassification.MONSTER,1,1.15f);
    public static final EntityType<EntityPetrifiedRunestoneZombie> PETRIFIED_ZOMBIE = register("petrified_zombie",EntityPetrifiedRunestoneZombie::new,EntityClassification.MONSTER,0.6f,1.95f);
    public static final EntityType<EntityDeer> DEER = register("deer",EntityDeer::new, EntityClassification.CREATURE,0.9f,1.4f);
    public static final EntityType<EntityInsaneDog> INSANE_DOG = register("insane_dog",EntityInsaneDog::new,EntityClassification.MONSTER,0.6f,0.85f);
    public static final EntityType<EntityChangeling> CHANGELING = register("changeling",EntityChangeling::new,EntityClassification.MONSTER,0.6f,0.85f);
    public static final EntityType<EntityAlphaInsaneDog> ALPHA_INSANE_DOG = register("alpha_insane_dog",EntityAlphaInsaneDog::new, EntityClassification.MONSTER,1.2f,1.7f);
    public static final EntityType<EntityGemGolem> GEM_GOLEM = register("gem_golem",EntityGemGolem::new,EntityClassification.CREATURE,0.9f,1.4f);
    public static final EntityType<EntityBeeQueen> BEEQUEEN = register("beequeen",(type, world)->new EntityBeeQueen(type,world),EntityClassification.MONSTER,1.5f,4f);
    public static final EntityType<EntityIguanaKing> IGUANA_KING = register("iguana_king",(type, world)->new EntityIguanaKing(type,world),EntityClassification.MONSTER,1.5f,4f);
    public static final EntityType<EntityThunderScreamer> THUNDER_SCREAMER = register("thunder_screamer",EntityThunderScreamer::new, EntityClassification.CREATURE,0.9f,1.4f);
    public static final EntityType<EntityTerrorBringer> TERROR_BRINGER = register("terror_bringer",EntityTerrorBringer::new, EntityClassification.MONSTER,0.8f,2f);
    public static final EntityType<EntityFCFishingBobber> CUSTOM_FISHING_BOBBER = (EntityType<EntityFCFishingBobber>) EntityType.Builder.<EntityFCFishingBobber>create(EntityClassification.MISC).disableSerialization().disableSummoning().size(0.25F, 0.25F).trackingRange(4).setUpdateInterval(5).build(new ResourceLocation("custom_fishing_bobber").toString()).setRegistryName("custom_fishing_bobber");
    public static final EntityType<EntityFireFishingBobber> FIRE_FISHING_BOBBER = (EntityType<EntityFireFishingBobber>) EntityType.Builder.<EntityFireFishingBobber>create((entity,world)->{
        if (world.isRemote()) {
            return new EntityFireFishingBobber(world);
        } else {
            return new EntityFireFishingBobber(new FakePlayer((ServerWorld) world, new GameProfile(UUID.randomUUID(), "")), world, 0, 0);
        }
    },EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).disableSerialization().disableSummoning().size(0.25F, 0.25F).trackingRange(4).setUpdateInterval(5).build(new ResourceLocation("fire_fishing_bobber").toString()).setRegistryName("fire_fishing_bobber");
    public static final EntityType<EntityInfectedZombie> INFECTED_ZOMBIE = register("infected_zombie",EntityInfectedZombie::new, EntityClassification.MONSTER,0.6f,1.95f);
    public static final EntityType<EntityNightWatcher> NIGHT_WATCHER = register("night_watcher",EntityNightWatcher::new, EntityClassification.MONSTER,0.8f,3f);
    public static final EntityType<EntityCicada> CICADA = register("cicada",EntityCicada::new, EntityClassification.CREATURE,0.5f,0.5f);
    public static final EntityType<EntityEaterOfTheDepths> EATER_OF_THE_DEPTHS = register("eater_of_the_depths",EntityEaterOfTheDepths::new, EntityClassification.MONSTER,1f,1.5f);
    public static final EntityType<EntityDirtProjectile> DIRT_PROJECTILE = register("dirt_chunk",EntityDirtProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityFireLiquidProjectile> FIRE_LIQUID_PROJECTILE = register("fire_liquid",EntityFireLiquidProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityLiquidElectritePotion.EntitySkyFragmentProjectile> SKY_FRAGMENT_PROJECTILE = register("sky_fragment", EntityLiquidElectritePotion.EntitySkyFragmentProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityNoteProjectile> NOTE_PROJECTILE = register("note", EntityNoteProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityKrock> KROCK = register("krock",EntityKrock::new, EntityClassification.MONSTER,1f,1.5f);
    public static final EntityType<EntityBakudan> BAKUDAN = register("bakudan",EntityBakudan::new, EntityClassification.MONSTER,1f,1.25f);
    public static final EntityType<EntityBakudanBall> BAKUDAN_BALL = register("bakudan_ball",EntityBakudanBall::new, EntityClassification.MISC,0.5f,0.5f);
    public static final EntityType<EntityKillerBee> KILLER_BEE = register("killer_bee",EntityKillerBee::new, EntityClassification.MONSTER,0.7f,0.6f);
    public static final EntityType<EntityBeeProjectile> PROJECTILE_BEE = register("projectile_bee",EntityBeeProjectile::new, EntityClassification.AMBIENT,0.7f,0.6f);
    public static final EntityType<EntitySoulBlaze> SOUL_BLAZE = register("soul_blaze",EntitySoulBlaze::new, EntityClassification.MONSTER,0.6f,1.8f,true,8);
    public static final EntityType<EntityAirSucker> AIR_SUCKER = register("air_sucker",EntityAirSucker::new, EntityClassification.MONSTER,0.8f,0.8f,false,8);
    public static final EntityType<EntityWoodAbomination> WOOD_ABOMINATION = register("wood_abomination",EntityWoodAbomination::new, EntityClassification.MONSTER,0.8f,0.8f,false,8);
    public static final EntityType<EntityCrocusPetal> CROCUS_PETAL_PROJECTILE = register("crocus_petal", EntityCrocusPetal::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityElectriteArrow> ELECTRITE_ARROW = register("electrite_arrow", EntityElectriteArrow::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityLiquidElectritePotion> LIQUID_ELECTRITE_FLASK = register("liquid_electrite_flask", EntityLiquidElectritePotion::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityHighlyConcentratedPoison> HC_POISON = register("hc_poison", EntityHighlyConcentratedPoison::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityArchaicSentinel> ARCHAIC_SENTINEL = register("archaic_sentinel", EntityArchaicSentinel::new, EntityClassification.MONSTER,1.25f,3f,false,8);
    public static final EntityType<EntityBubbleProjectile> BUBBLE_PROJECTILE = register("bubble", EntityBubbleProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<DaybreakProjectile> DAYBREAK_PROJECTILE = register("daybreak", DaybreakProjectile::new, EntityClassification.MISC,0.5f,0.5f);
    public static final EntityType<EntityMiniTornadoProjectile> MINI_TORNADO_PROJECTILE = register("mini_tornado_projectile", EntityMiniTornadoProjectile::new, EntityClassification.MISC,0.4f,0.4f);
    public static final EntityType<EntityNightBat> NIGHT_BAT = register("night_bat",EntityNightBat::new, EntityClassification.MONSTER,1f,1.5f,false,8);
    public static final EntityType<EntityKelpy> KELPY = register("kelpy",EntityKelpy::new, EntityClassification.WATER_CREATURE,1.5f,1,false,8);
    public static final EntityType<EntityNetherBat> NETHER_BAT = register("nether_bat",EntityNetherBat::new, EntityClassification.MONSTER,1f,1.5f,true,8);
    public static final EntityType<EntityCosmicFiend> COSMIC_FIEND = register("cosmic_fiend",EntityCosmicFiend::new, EntityClassification.MONSTER,7f,10f,false,100);
    public static final EntityType<EntityCosmicFire> COSMIC_FIRE = register("cosmic_fire", EntityCosmicFire::new, EntityClassification.MISC,0.5f,0.5f);
    public static final EntityType<EntityNetherScourge> NETHER_SCOURGE = register("nether_scourge",EntityNetherScourge::new, EntityClassification.MONSTER,1.75f,3,true,80);
    public static final EntityType<EntityDesecratedSoul> DESECRATED_SOUL = register("desecrated_soul",EntityDesecratedSoul::new, EntityClassification.MONSTER,0.6f,1.95f,true,50);
    public static final EntityType<EntityIguana> IGUANA = register("iguana",EntityIguana::new, EntityClassification.MONSTER,0.6f,1.95f,false,50);
    public static final EntityType<EntityJungleWasp> JUNGLE_WASP = register("jungle_wasp",EntityJungleWasp::new, EntityClassification.MONSTER,0.8f,0.7f,false,50);
    public static final EntityType<EntityCloudRay> CLOUD_RAY = register("cloud_ray",EntityCloudRay::new, EntityClassification.MONSTER,1.25f,0.5f,false,50);
    public static final EntityType<EntitySkyBullet> SKY_BULLET_PROJECTILE = register("sky_bullet", EntitySkyBullet::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityMoltenBullet> MOLTEN_BULLET_PROJECTILE = register("molten_bullet", EntityMoltenBullet::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityDemonBulletProjectile> DEMON_BULLET_PROJECTILE = register("demon_bullet", EntityDemonBulletProjectile::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityBlackholeBullet>  BLACKHOLE_BULLET_PROJECTILE = register("blackhole_bullet",EntityBlackholeBullet::new, EntityClassification.MISC,0.25f,0.25f);
    public static final EntityType<EntityWorm> WORM = register("worm",EntityWorm::new, FCEntityClassification.WORMS,0.25f,0.25f);
    public static final EntityType<EntitySeaDevil> SEA_DEVIL = register("sea_devil",EntitySeaDevil::new, EntityClassification.MONSTER,0.75f,0.75f);
    public static final EntityType<EntityWaterBug> WATER_BUG = register("water_bug",EntityWaterBug::new, EntityClassification.MONSTER,0.75f,0.75f);

    public static void registerPlacements(){
        EntitySpawnPlacementRegistry.register(FCEntities.ALPHA_INSANE_DOG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAlphaInsaneDog::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.INSANE_DOG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsaneDog::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.DEER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityDeer:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.THUNDER_SCREAMER, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityThunderScreamer:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.GEM_GOLEM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityGemGolem:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.TERROR_BRINGER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.INFECTED_ZOMBIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInfectedZombie:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.CICADA, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityCicada:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.KROCK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityKrock:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.BAKUDAN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityBakudan:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.AIR_SUCKER, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAirSucker:: spawnSucker);
        EntitySpawnPlacementRegistry.register(FCEntities.CLOUD_RAY, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityCloudRay::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.EATER_OF_THE_DEPTHS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityEaterOfTheDepths:: spawnEater);
        EntitySpawnPlacementRegistry.register(FCEntities.KELPY, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityKelpy:: spawnKelpy);
        EntitySpawnPlacementRegistry.register(FCEntities.DANDELIONEER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityDandelioneer:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.CROCUS_FLOWER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityCrocusFlower:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.PETRIFIED_ZOMBIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityPetrifiedRunestoneZombie:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.WOOD_ABOMINATION, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityWoodAbomination:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.NIGHT_BAT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityNightBat:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.NETHER_BAT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityNetherBat:: canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.DESECRATED_SOUL, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(FCEntities.IGUANA, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, EntityIguana::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.JUNGLE_WASP, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, EntityIguana::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.WORM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, EntityWorm::canSpawnOn);
        EntitySpawnPlacementRegistry.register(FCEntities.CHANGELING, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, EntityChangeling::canSpawn);
        EntitySpawnPlacementRegistry.register(FCEntities.SEA_DEVIL, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySeaDevil:: canSpawn);
        EntitySpawnPlacementRegistry.register(FCEntities.WATER_BUG, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityWaterBug::canSpawn);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent e) {
        if (((RangedAttribute) Attributes.MAX_HEALTH).maximumValue < 10000) {
            ((RangedAttribute) Attributes.MAX_HEALTH).maximumValue = 10000;
        }
        e.put(FCEntities.CROCUS_FLOWER, EntityCrocusFlower.setCustomAttributes().create());
        e.put(FCEntities.DEER, EntityDeer.setCustomAttributes().create());
        e.put(FCEntities.WORM, EntityWorm.setCustomAttributes().create());
        e.put(FCEntities.BEEQUEEN, EntityBeeQueen.setCustomAttributes().create());
        e.put(FCEntities.INSANE_DOG, EntityInsaneDog.setCustomAttributes().create());
        e.put(FCEntities.CHANGELING, EntityChangeling.setCustomAttributes().create());
        e.put(FCEntities.ALPHA_INSANE_DOG, EntityAlphaInsaneDog.setCustomAttributes().create());
        e.put(FCEntities.GEM_GOLEM, EntityGemGolem.setCustomAttributes().create());
        e.put(FCEntities.THUNDER_SCREAMER, EntityThunderScreamer.setCustomAttributes().create());
        e.put(FCEntities.TERROR_BRINGER, EntityTerrorBringer.setCustomAttributes().create());
        e.put(FCEntities.INFECTED_ZOMBIE, EntityInfectedZombie.setCustomAttributes().create());
        e.put(FCEntities.NIGHT_WATCHER, EntityNightWatcher.setCustomAttributes().create());
        e.put(FCEntities.CICADA, EntityCicada.setCustomAttributes().create());
        e.put(FCEntities.EATER_OF_THE_DEPTHS, EntityEaterOfTheDepths.setCustomAttributes().create());
        e.put(FCEntities.KROCK, EntityKrock.setCustomAttributes().create());
        e.put(FCEntities.KILLER_BEE, BeeEntity.func_234182_eX_().create());
        e.put(FCEntities.PROJECTILE_BEE, BeeEntity.func_234182_eX_().create());
        e.put(FCEntities.BAKUDAN, EntityBakudan.setCustomAttributes().create());
        e.put(FCEntities.IGUANA_KING, EntityIguanaKing.setCustomAttributes().create());
        e.put(FCEntities.SOUL_BLAZE, EntitySoulBlaze.registerAttributes().create());
        e.put(FCEntities.AIR_SUCKER, EntityAirSucker.createAttributes().create());
        e.put(FCEntities.DANDELIONEER, EntityDandelioneer.setCustomAttributes().create());
        e.put(FCEntities.PETRIFIED_ZOMBIE, EntityPetrifiedRunestoneZombie.setCustomAttributes().create());
        e.put(FCEntities.WOOD_ABOMINATION, EntityWoodAbomination.setCustomAttributes().create());
        e.put(FCEntities.ARCHAIC_SENTINEL, EntityArchaicSentinel.setCustomAttributes().create());
        e.put(FCEntities.KELPY, EntityKelpy.setCustomAttributes().create());
        e.put(FCEntities.NIGHT_BAT, EntityNightBat.setCustomAttributes().create());
        e.put(FCEntities.NETHER_BAT, EntityNetherBat.setCustomAttributes().create());
        e.put(FCEntities.COSMIC_FIEND, EntityCosmicFiend.setCustomAttributes().create());
        e.put(FCEntities.NETHER_SCOURGE, EntityNetherScourge.setCustomAttributes().create());
        e.put(FCEntities.DESECRATED_SOUL, EntityDesecratedSoul.setCustomAttributes().create());
        e.put(FCEntities.IGUANA,EntityIguana.setCustomAttributes().create());
        e.put(FCEntities.JUNGLE_WASP,EntityJungleWasp.setCustomAttributes().create());
        e.put(FCEntities.CLOUD_RAY,EntityCloudRay.setCustomAttributes().create());
        e.put(FCEntities.SEA_DEVIL,EntitySeaDevil.setCustomAttributes().create());
        e.put(FCEntities.WATER_BUG,EntityWaterBug.setCustomAttributes().create());
        e.put(FCEntities.AKNAYAH,EntityAknayah.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(EntityType.TURTLE,MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE,2).create());
        registerPlacements();
    }


    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        try {
            for (Field f : FCEntities.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof EntityType) {
                    event.getRegistry().register((EntityType) obj);
                } else if (obj instanceof EntityType[]) {
                    for (EntityType type : (EntityType[]) obj) {
                        event.getRegistry().register(type);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



    private static EntityType.Builder build(EntityType.IFactory entity, EntityClassification classification){
        try {
            Constructor cons = EntityType.Builder.class.getDeclaredConstructor(EntityType.IFactory.class,EntityClassification.class);
            cons.setAccessible(true);
            return (EntityType.Builder)cons.newInstance(entity,classification);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final EntityType register(String name,EntityType.IFactory entity, EntityClassification classification, float width, float height){
        EntityType<?> type = build(entity, classification).size(width,height).build(new ResourceLocation(name).toString());
        type.setRegistryName(name);
        return type;
    }


    public static final EntityType register(String name,EntityType.IFactory entity, EntityClassification classification, float width, float height, boolean fireImmune,int trackingRange){
        EntityType.Builder builder = build(entity,classification).size(width,height).trackingRange(trackingRange);
        if (fireImmune){
            builder = builder.immuneToFire();
        }
        EntityType<?> type = builder.build(new ResourceLocation(name).toString());
        type.setRegistryName(name);
        return type;
    }

}
