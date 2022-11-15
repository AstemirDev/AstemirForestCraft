package org.astemir.forestcraft.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.astemir.forestcraft.ForestCraft;
import org.astemir.api.math.RandomUtils;
import org.astemir.forestcraft.common.sounds.FCSoundType;

public class FCSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,ForestCraft.MOD_ID);

    public static final RegistryObject<SoundEvent> ANCIENT_RUNESTONE_HERO_EQUINOX = registerSound("entity.ancient_runestone_hero.equinox");
    public static final RegistryObject<SoundEvent> DANDELIONEER_AMBIENT = registerSound("entity.dandelioneer.ambient");
    public static final RegistryObject<SoundEvent> DANDELIONEER_HURT = registerSound("entity.dandelioneer.hurt");
    public static final RegistryObject<SoundEvent> DANDELIONEER_DEATH = registerSound("entity.dandelioneer.death");
    public static final RegistryObject<SoundEvent> CROCUS_FLOWER_SHOOT = registerSound("entity.crocus_flower.shoot");
    public static final RegistryObject<SoundEvent> CICADA_BLOCK = registerSound("entity.cicada.block");
    public static final RegistryObject<SoundEvent> PETRIFIED_ZOMBIE_AMBIENT = registerSound("entity.petrified_runestone_zombie.ambient");
    public static final RegistryObject<SoundEvent> PETRIFIED_ZOMBIE_HURT = registerSound("entity.petrified_runestone_zombie.hurt");
    public static final RegistryObject<SoundEvent> PETRIFIED_ZOMBIE_DEATH = registerSound("entity.petrified_runestone_zombie.death");
    public static final RegistryObject<SoundEvent> COSMIC_FIEND_AMBIENT = registerSound("entity.cosmic_fiend.ambient");
    public static final RegistryObject<SoundEvent> COSMIC_FIEND_HURT = registerSound("entity.cosmic_fiend.hurt");
    public static final RegistryObject<SoundEvent> COSMIC_FIEND_ATTACK = registerSound("entity.cosmic_fiend.attack");
    public static final RegistryObject<SoundEvent> DEER_AMBIENT = registerSound("entity.deer.ambient");
    public static final RegistryObject<SoundEvent> DEER_HURT = registerSound("entity.deer.hurt");
    public static final RegistryObject<SoundEvent> DEER_DEATH = registerSound("entity.deer.death");
    public static final RegistryObject<SoundEvent> DEER_HORNS = registerSound("entity.deer.horns");
    public static final RegistryObject<SoundEvent> GHOST_AMBIENT = registerSound("entity.ghost.ambient");
    public static final RegistryObject<SoundEvent> GHOST_HURT = registerSound("entity.ghost.hurt");
    public static final RegistryObject<SoundEvent> GHOST_DEATH = registerSound("entity.ghost.death");
    public static final RegistryObject<SoundEvent> BEEQUEEN_SCREECH = registerSound("entity.beequeen.screech");
    public static final RegistryObject<SoundEvent> BEEQUEEN_MUSIC = registerSound("entity.beequeen.music");
    public static final RegistryObject<SoundEvent> BEEQUEEN_SPAWN = registerSound("entity.beequeen.spawn");
    public static final RegistryObject<SoundEvent> SCYTHE_WHOOSH = registerSound("item.scythe.whoosh");
    public static final RegistryObject<SoundEvent> BULLET_IMPACT = registerSound("item.bullet.impact");
    public static final RegistryObject<SoundEvent> SCYTHE_WHOOSH_2 = registerSound("item.scythe.whoosh_2");
    public static final RegistryObject<SoundEvent> MEDIUM_SWING = registerSound("item.random.medium_swing");
    public static final RegistryObject<SoundEvent> INSANE_DOG_AMBIENT = registerSound("entity.insane_dog.ambient");
    public static final RegistryObject<SoundEvent> INSANE_DOG_HURT = registerSound("entity.insane_dog.hurt");
    public static final RegistryObject<SoundEvent> LAZERBEAM = registerSound("entity.insane_dog.lazer");
    public static final RegistryObject<SoundEvent> ALPHA_INSANE_DOG_AMBIENT = registerSound("entity.alpha_insane_dog.ambient");
    public static final RegistryObject<SoundEvent> ALPHA_INSANE_DOG_HOWL = registerSound("entity.alpha_insane_dog.howl");
    public static final RegistryObject<SoundEvent> ALPHA_INSANE_DOG_HURT = registerSound("entity.alpha_insane_dog.hurt");
    public static final RegistryObject<SoundEvent> GEM_GOLEM_AMBIENT = registerSound("entity.gem_golem.ambient");
    public static final RegistryObject<SoundEvent> GEM_GOLEM_HURT = registerSound("entity.gem_golem.hurt");
    public static final RegistryObject<SoundEvent> GEM_GOLEM_DEATH = registerSound("entity.gem_golem.death");
    public static final RegistryObject<SoundEvent> KROCK_HURT = registerSound("entity.krock.hurt");
    public static final RegistryObject<SoundEvent> KROCK_DEATH = registerSound("entity.krock.death");
    public static final RegistryObject<SoundEvent> KROCK_AMBIENT = registerSound("entity.krock.ambient");
    public static final RegistryObject<SoundEvent> KROCK_STEP = registerSound("entity.krock.rolling");
    public static final RegistryObject<SoundEvent> BAKUDAN_AMBIENT = registerSound("entity.bakudan.ambient");
    public static final RegistryObject<SoundEvent> BAKUDAN_THROW = registerSound("entity.bakudan.throw");
    public static final RegistryObject<SoundEvent> BAKUDAN_HURT = registerSound("entity.bakudan.hurt");
    public static final RegistryObject<SoundEvent> BAKUDAN_DEATH = registerSound("entity.bakudan.death");
    public static final RegistryObject<SoundEvent> BAKUDAN_EXPLODE = registerSound("entity.bakudan.explode");
    public static final RegistryObject<SoundEvent> BAKUDAN_RECHARGE = registerSound("entity.bakudan.recharge");
    public static final RegistryObject<SoundEvent> TUBULAR_BELLS = registerSound("note.tubular_bell");
    public static final RegistryObject<SoundEvent> WATERPHONE = registerSound("note.waterphone");
    public static final RegistryObject<SoundEvent> ELECTRO_GUITAR = registerSound("note.electro_guitar");
    public static final RegistryObject<SoundEvent> COSMOS_ECHO = registerSound("block.cosmic_beacon.cosmos_echo");
    public static final RegistryObject<SoundEvent> COSMIC_BEACON_ACTIVATE = registerSound("block.cosmic_beacon.activate");
    public static final RegistryObject<SoundEvent> IGUANA_KING_HURT = registerSound("entity.iguana_king.hurt");
    public static final RegistryObject<SoundEvent> IGUANA_KING_LAUGH = registerSound("entity.iguana_king.laugh");
    public static final RegistryObject<SoundEvent> IGUANA_KING_STEP = registerSound("entity.iguana_king.step");
    public static final RegistryObject<SoundEvent> IGUANA_KING_ATTACK = registerSound("entity.iguana_king.attack");
    public static final RegistryObject<SoundEvent> IGUANA_KING_SCREAM = registerSound("entity.iguana_king.scream");
    public static final RegistryObject<SoundEvent> IGUANA_KING_MUSIC = registerSound("entity.iguana_king.music");

    public static final RegistryObject<SoundEvent> EGG_SHELL = registerSound("entity.iguana_king.egg_shell");
    public static final RegistryObject<SoundEvent> SKY_SHOOTER_USE = registerSound("item.sky_shooter.use");
    public static final RegistryObject<SoundEvent> SKY_SHOOTER_READY = registerSound("item.shy_shooter.ready");
    public static final RegistryObject<SoundEvent> DEMON_BUSTER_USE = registerSound("item.demon_buster.use");
    public static final RegistryObject<SoundEvent> MINIGUN_START = registerSound("item.minigun.start");
    public static final RegistryObject<SoundEvent> MINIGUN_LOOP = registerSound("item.minigun.loop");
    public static final RegistryObject<SoundEvent> MINIGUN_STOP = registerSound("item.minigun.stop");
    public static final RegistryObject<SoundEvent> SNEEZE = registerSound("effect.sneeze");
    public static final RegistryObject<SoundEvent> EXPLOSION_LARGE = registerSound("effect.explosion.large");
    public static final RegistryObject<SoundEvent> EXPLOSION_GIANT = registerSound("effect.explosion.giant");
    public static final RegistryObject<SoundEvent> DAYBREAK = registerSound("item.daybreak.use");
    public static final RegistryObject<SoundEvent> ELECTRON_HIT = registerSound("item.electron.hit");
    public static final RegistryObject<SoundEvent> ELECTRON_ELECTROCUT = registerSound("item.electron.electrocut");
    public static final RegistryObject<SoundEvent> THUNDER_SCREAMER_DEATH = registerSound("entity.thunder_screamer.thunder_screamer_death");
    public static final RegistryObject<SoundEvent> THUNDER_SCREAMER_DISTANT = registerSound("entity.thunder_screamer.thunder_screamer_distant");
    public static final RegistryObject<SoundEvent> THUNDER_SCREAMER_HURT = registerSound("entity.thunder_screamer.thunder_screamer_hurt");
    public static final RegistryObject<SoundEvent> TERROR_BRINGER_AMBIENT = registerSound("entity.terror_bringer.ambient");
    public static final RegistryObject<SoundEvent> TERROR_BRINGER_HURT = registerSound("entity.terror_bringer.hurt");
    public static final RegistryObject<SoundEvent> TERROR_BRINGER_DEATH = registerSound("entity.terror_bringer.death");
    public static final RegistryObject<SoundEvent> SIEVE_USE = registerSound("item.gold_panning_sieve.use");
    public static final RegistryObject<SoundEvent> MUSIC_LATINA = registerSound("music.records.latina");
    public static final RegistryObject<SoundEvent> MUSIC_I_SAY_YES = registerSound("music.records.i_say_yes");
    public static final RegistryObject<SoundEvent> MUSIC_WE_TOOK_EACH_OTHERS_HAND = registerSound("music.records.we_took_eachothers_hand");
    public static final RegistryObject<SoundEvent> CRYSTAL_LOOP = registerSound("block.crystal.loop");
    public static final RegistryObject<SoundEvent> CRYSTAL_BREAK = registerSound("block.crystal.break");
    public static final RegistryObject<SoundEvent> INFECTED_ZOMBIE_AMBIENT = registerSound("entity.infected_zombie.ambient");
    public static final RegistryObject<SoundEvent> INFECTED_ZOMBIE_HURT = registerSound("entity.infected_zombie.hurt");
    public static final RegistryObject<SoundEvent> INFECTED_ZOMBIE_DEATH = registerSound("entity.infected_zombie.death");
    public static final RegistryObject<SoundEvent> EATER_OF_THE_DEPTHS_AMBIENT = registerSound("entity.eater_of_the_depths.ambient");
    public static final RegistryObject<SoundEvent> EATER_OF_THE_DEPTHS_HURT = registerSound("entity.eater_of_the_depths.hurt");
    public static final RegistryObject<SoundEvent> EATER_OF_THE_DEPTHS_DEATH = registerSound("entity.eater_of_the_depths.death");
    public static final RegistryObject<SoundEvent> CICADA_AMBIENT = registerSound("entity.cicada.ambient");
    public static final RegistryObject<SoundEvent> CICADA_HURT = registerSound("entity.cicada.hurt");
    public static final RegistryObject<SoundEvent> CICADA_DEATH = registerSound("entity.cicada.death");
    public static final RegistryObject<SoundEvent> WOOD_ABOMINATION_AMBIENT = registerSound("entity.wood_abomination.ambient");
    public static final RegistryObject<SoundEvent> WOOD_ABOMINATION_SCREAM = registerSound("entity.wood_abomination.scream");
    public static final RegistryObject<SoundEvent> NIGHT_WATCHER_SCREAM = registerSound("entity.night_watcher.scream");
    public static final RegistryObject<SoundEvent> CREAM_USE = registerSound("item.cream.use");
    public static final RegistryObject<SoundEvent> WHISTLE_LOOP = registerSound("item.whistle.loop");
    public static final RegistryObject<SoundEvent> WHISTLE_USE = registerSound("item.whistle.use");
    public static final RegistryObject<SoundEvent> EQUINOX_USE = registerSound("item.equinox.use");
    public static final RegistryObject<SoundEvent> NETHER_SCOURGE_HURT = registerSound("entity.nether_scourge.hurt");
    public static final RegistryObject<SoundEvent> NETHER_SCOURGE_DEATH = registerSound("entity.nether_scourge.death");
    public static final RegistryObject<SoundEvent> NETHER_SCOURGE_AMBIENT = registerSound("entity.nether_scourge.ambient");
    public static final RegistryObject<SoundEvent> NETHER_SCOURGE_SCREAM = registerSound("entity.nether_scourge.scream");
    public static final RegistryObject<SoundEvent> CASE_OPEN = registerSound("item.case.open");
    public static final RegistryObject<SoundEvent> IGUANA_AMBIENT = registerSound("entity.iguana.ambient");
    public static final RegistryObject<SoundEvent> IGUANA_HURT = registerSound("entity.iguana.hurt");
    public static final RegistryObject<SoundEvent> IGUANA_DEATH = registerSound("entity.iguana.death");
    public static final RegistryObject<SoundEvent> CHANGELING_AMBIENT = registerSound("entity.changeling.ambient");
    public static final RegistryObject<SoundEvent> CHANGELING_HURT = registerSound("entity.changeling.hurt");
    public static final RegistryObject<SoundEvent> CHANGELING_DEATH = registerSound("entity.changeling.death");
    public static final RegistryObject<SoundEvent> CHANGELING_TRANSFORM = registerSound("entity.changeling.transform");
    public static final RegistryObject<SoundEvent> CHANGELING_PARASITE_AMBIENT = registerSound("entity.changeling.parasite.ambient");
    public static final RegistryObject<SoundEvent> CHANGELING_PARASITE_ATTACK = registerSound("entity.changeling.parasite.attack");
    public static final RegistryObject<SoundEvent> CHANGELING_PARASITE_HURT = registerSound("entity.changeling.parasite.hurt");
    public static final RegistryObject<SoundEvent> CHANGELING_PARASITE_DEATH = registerSound("entity.changeling.parasite.death");
    public static final RegistryObject<SoundEvent> SEA_DEVIL_DEATH = registerSound("entity.sea_devil.death");
    public static final RegistryObject<SoundEvent> SEA_DEVIL_AMBIENT = registerSound("entity.sea_devil.ambient");
    public static final RegistryObject<SoundEvent> WINGS_FLAP = registerSound("item.wings.flap");
    public static final RegistryObject<SoundEvent> WATER_BUG_AMBIENT = registerSound("entity.water_bug.ambient");
    public static final RegistryObject<SoundEvent> WATER_BUG_HURT = registerSound("entity.water_bug.hurt");
    public static final RegistryObject<SoundEvent> WATER_BUG_DEATH = registerSound("entity.water_bug.death");


    public static final RegistryObject<SoundEvent> CLOUD_RAY_AMBIENT = registerSound("entity.cloud_ray.ambient");
    public static final RegistryObject<SoundEvent> CLOUD_RAY_HURT = registerSound("entity.cloud_ray.hurt");
    public static final RegistryObject<SoundEvent> CLOUD_RAY_DEATH = registerSound("entity.cloud_ray.death");
    public static final RegistryObject<SoundEvent> CLOUD_RAY_ATTACK = registerSound("entity.cloud_ray.attack");


    public static final FCSoundType CRYSTAL = new FCSoundType(1, RandomUtils.randomFloat(0.9f,1.1f),()->CRYSTAL_BREAK.get(), ()->SoundEvents.BLOCK_GLASS_STEP,()->SoundEvents.BLOCK_GLASS_PLACE,()->SoundEvents.BLOCK_GLASS_HIT,()->SoundEvents.BLOCK_GLASS_FALL);
    public static final FCSoundType IGUANA_KING_EGG = new FCSoundType(1, 1,()->EGG_SHELL.get(), ()->SoundEvents.BLOCK_BONE_BLOCK_STEP,()->SoundEvents.BLOCK_BONE_BLOCK_PLACE,()->SoundEvents.BLOCK_BONE_BLOCK_HIT,()->SoundEvents.BLOCK_BONE_BLOCK_FALL);


    public static RegistryObject<SoundEvent> registerSound(String name){
        return SOUNDS.register(name,()->new SoundEvent(new ResourceLocation(ForestCraft.MOD_ID,name)));
    }



}
