package org.astemir.forestcraft.registries;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.common.item.*;
import org.astemir.api.math.Pair;
import org.astemir.api.utils.TextUtils;
import org.astemir.forestcraft.common.items.FCItemTier;
import org.astemir.forestcraft.common.items.FCRarity;
import org.astemir.forestcraft.common.items.armor.boots.*;
import org.astemir.forestcraft.common.items.armor.chestplates.ItemEternalHungerChestplate;
import org.astemir.forestcraft.common.items.armor.helmets.ItemInsaneHelmet;
import org.astemir.forestcraft.common.items.armor.helmets.ItemPossesedSkull;
import org.astemir.forestcraft.common.items.constructors.*;
import org.astemir.forestcraft.common.items.equipment.*;
import org.astemir.forestcraft.common.items.insect.*;
import org.astemir.forestcraft.common.items.misc.*;
import org.astemir.forestcraft.common.items.shields.ItemArchaicShield;
import org.astemir.forestcraft.common.items.shields.ItemHoneyKeeper;
import org.astemir.forestcraft.common.items.shields.ItemPrehistoricShield;
import org.astemir.forestcraft.common.items.tools.axes.ItemSoulEater;
import org.astemir.forestcraft.common.items.tools.other.*;
import org.astemir.forestcraft.common.items.tools.pickaxes.*;
import org.astemir.forestcraft.common.items.tools.scythes.*;
import org.astemir.forestcraft.common.items.weapons.bows.ItemElectrobow;
import org.astemir.forestcraft.common.items.weapons.bows.ItemInsaneBow;
import org.astemir.forestcraft.common.items.weapons.bows.ItemSoulConqueror;
import org.astemir.forestcraft.common.items.weapons.guns.*;
import org.astemir.forestcraft.common.items.weapons.other.ItemDandelionRod;
import org.astemir.forestcraft.common.items.weapons.other.ItemRoyalStaff;
import org.astemir.forestcraft.common.items.weapons.other.ItemSoulHarp;
import org.astemir.forestcraft.common.items.weapons.other.ItemStaffOfSleeping;
import org.astemir.forestcraft.common.items.weapons.swords.*;
import org.astemir.forestcraft.common.items.weapons.throwable.*;
import org.astemir.forestcraft.FCStrings;
import org.astemir.forestcraft.common.items.armor.*;
import org.astemir.forestcraft.common.items.armor.wings.*;
import org.astemir.forestcraft.common.items.food.*;
import org.astemir.forestcraft.common.items.summon.ItemDemonFood;
import org.astemir.forestcraft.common.items.summon.ItemEquinoxTotem;


public class FCItems {

    //Other
    public static final Item FIRE_LIQUID = new ItemFireLiquid().register();
    public static final Item HIGHLY_CONCENTRATED_POISON = new ItemHighlyConcentratedPoison().register();
    public static final Item BAKUDAN_BALL = new ItemBakudanBall().register();
    public static final Item TRUE_BAKUDAN_BALL = new ItemTrueBakudanBall().register();
    public static final Item KEEPER_OF_CLOUDS = new ItemKeeperOfClouds().register();
    public static final Item NETHERITE_LIGHTER = new ItemNetheriteLighter().register();
    public static final Item TREASURE_CASE = new FCLootBox(FCStrings.prefix("treasure_case"),FCLootTables.TREASURES).register();
    public static final Item VEGETABLE_CASE = new FCLootBox("forestcraft:vegicase",FCLootTables.VEGETABLES).register();
    public static final Item DIRT_WITH_IRON = new FCWashable("forestcraft:dirt_with_iron", ()-> Items.IRON_ORE, 40, 3).register();
    public static final Item DIRT_WITH_WORMS = new FCWashable("forestcraft:dirt_with_worms", ()-> FCItems.WORM, 20, 1,4).register();
    public static final Item DIRT_WITH_BONES = new FCWashable("forestcraft:dirt_with_bones", ()-> Items.BONE, 20, 4,8).register();
    public static final Item DIRT_WITH_DIRTSCALUBUR = new FCWashable("forestcraft:dirt_with_dirtscalibur", ()-> FCItems.DIRTSCALIBUR, 80).register();
    public static final Item DIRT_WITH_SEEDS = new FCWashable("forestcraft:dirt_with_seeds", FCLootTables.SEEDS, 20).register();
    public static final Item GLUE = new AItem("forestcraft:glue").lore(TextUtils.translate(FCStrings.GLUE_0, TextFormatting.GRAY),TextUtils.translate(FCStrings.GLUE_1, TextFormatting.GRAY)).maxStack(16).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC).register();
    public static final Item WATERING_CAN = new FCWateringCan("forestcraft:watering_can",10).register();
    public static final Item IRON_WATERING_CAN = new FCWateringCan("forestcraft:iron_watering_can",20).register();
    public static final Item POSSESED_SKULL = new ItemPossesedSkull().register();
    public static final Item DEMON_FOOD = new ItemDemonFood().register();
    public static final Item EQUINOX_TOTEM = new ItemEquinoxTotem().register();
    public static final Item DANDELION_SEED = new ItemDandelionSeed().register();
    public static final Item GUIDEBOOK = new ItemGuideBook().register();
    public static final Item NITER_FERTILIZER = new ItemFertilizer().register();
    public static final Item BREAD_CHUNK = new FCBaitItem("forestcraft:bread_chunk",10).register();
    public static final Item BEE = new BeeItem().register();
    public static final Item WORM =  new WormItem("forestcraft:worm",40).register();
    public static final Item GOLDEN_WORM =  new WormItem("forestcraft:golden_worm",60).rarity(Rarity.UNCOMMON).register();
    public static final Item DIAMOND_WORM =  new WormItem("forestcraft:diamond_worm",95).rarity(Rarity.RARE).register();
    public static final Item KILLER_BEE = new KillerBeeItem().register();
    public static final Item CICADA = new CicadaItem().register();
    public static final Item ENDERMITE = new EndermiteItem().register();
    public static final Item SILVERFISH = new SilverfishItem().register();
    public static final Item NET = new ItemNet().register();
    public static final Item SOUL_HARP = new ItemSoulHarp().rarity(Rarity.RARE).register();
    public static final Item ROYAL_STAFF = new ItemRoyalStaff().register();
    public static final Item LIQUID_ELECTRITE_FLASK = new ItemFlaskOfElectrite().register();
    public static final Item GOLD_PANNING_SIEVE = new ItemSieve().register();
    public static final Item BLUEBERRY_SEEDS = new AItemBlockNamedItem("forestcraft:blueberry_seeds",FCBlocks.BLUEBERRY_BUSH_EMPTY).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC).register();
    public static final Item POMIDOR_SEEDS = new AItemBlockNamedItem("forestcraft:pomidor_seeds",FCBlocks.POMIDOR_SAPLING).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC).register();
    public static final Item WEIRD_CUCUMBER_SEEDS = new AItemBlockNamedItem("forestcraft:weird_cucumber_seeds",FCBlocks.CUCUMBER_SAPLING).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC).register();
    public static final Item EQUINOX_CLOCK = new ItemEquinoxClock().register();
    public static final Item RAIN_FLUTE = new ItemRainFlute().register();
    public static final Item CROSSER = new ItemCrosser().register();
    public static final Item SNOWBERRY_CREAM = new ItemSnowberryCream().register();
    public static final Item MUSIC_DISC_LATINA = new AItemMusicDisc("forestcraft:music_disc_latina",FCSounds.MUSIC_LATINA).maxStack(1).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MUSIC_DISCS).rarity(Rarity.RARE).register();
    public static final Item MUSIC_DISC_I_SAY_YES = new AItemMusicDisc("forestcraft:music_disc_i_say_yes",FCSounds.MUSIC_I_SAY_YES).maxStack(1).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MUSIC_DISCS).rarity(Rarity.RARE).register();
    public static final Item MUSIC_DISC_WE_TOOK_EACHOTHERS_HAND = new AItemMusicDisc("forestcraft:music_disc_we_took_eachothers_hand",FCSounds.MUSIC_WE_TOOK_EACH_OTHERS_HAND).maxStack(1).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MUSIC_DISCS).rarity(Rarity.RARE).register();
    public static final Item ALCHEMICAL_BAG = new ItemAlchemicalBag().register();
    public static final Item SICKLE = new ItemSickle().register();
    public static final Item MOLTEN_FISHING_ROD = new ItemMoltenFishingRod().register();
    public static final Item SQUID_BUCKET = new ItemSquidBucket().register();
    public static final Item FOSSIL_BRUSH = new ItemFossilBrush().register();
    public static final Item ARCHAIC_KEY = new AItem("forestcraft:archaic_key").maxStack(1).rarity(Rarity.UNCOMMON).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MISC).register();
    public static final Item ELEGANT_BALLOON = new ItemElegantBalloon().register();

    //Food
    public static final Item SWEET_MEGA_MUFFIN = new AItem("forestcraft:sweet_mega_muffin").food(new AItemFoodProperties().eatingTime(50).hungerRestore(5).saturation(1f).effects(new Pair<>(20,()->new EffectInstance(Effects.RESISTANCE,120,0)))).itemGroup(FCItemGroups.FOOD).register();
    public static final Item CHOCOLATE_DONUT = new AItem("forestcraft:chocolate_donut").food(new AItemFoodProperties().eatingTime(30).hungerRestore(3).saturation(0.3f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item DANDELION_WINE = new AItem("forestcraft:dandelion_wine").rarity(Rarity.UNCOMMON).food(new AItemFoodProperties().eatingTime(40).beverage().alwaysEdible().container(()->Items.GLASS_BOTTLE).effects(new Pair<>(100,()->new EffectInstance(Effects.REGENERATION,90,0)),new Pair<>(100,()->new EffectInstance(Effects.SLOW_FALLING,400,0)))).itemGroup(FCItemGroups.FOOD).maxStack(16).register();
    public static final Item BERRY_JAM = new AItem("forestcraft:berry_jam").food(new AItemFoodProperties().eatingTime(60).beverage().container(()->Items.GLASS_BOTTLE).drinkSound(()-> SoundEvents.ITEM_HONEY_BOTTLE_DRINK).effects(new Pair<>(40,()->new EffectInstance(Effects.REGENERATION,120,1))).hungerRestore(4).saturation(0.9f)).itemGroup(FCItemGroups.FOOD).maxStack(16).register();
    public static final Item AIR_SUCKER_TENTACLE = new AItem("air_sucker_tentacle").food(new AItemFoodProperties().meat().hungerRestore(2).saturation(0.4f)).register();
    public static final Item SHISHTAUK = new AItem("forestcraft:shishtauk").rarity(FCRarity.LEGENDARY).food(new AItemFoodProperties().hungerRestore(10).saturation(1).effects(new Pair<>(75,()->new EffectInstance(Effects.STRENGTH,800,0)),new Pair<>(75,()->new EffectInstance(Effects.RESISTANCE,800,0)))).itemGroup(FCItemGroups.FOOD).maxStack(16).register();
    public static final Item ICICLE = new AItem("forestcraft:icicle").food(new AItemFoodProperties().hungerRestore(2).saturation(0.2f).effects(new Pair<>(100,()->new EffectInstance(Effects.SLOWNESS,200,1)))).itemGroup(FCItemGroups.FOOD).register();
    public static final Item COOKED_AIR_SUCKER_TENTACLE = new ItemCookedTentacle().register();
    public static final Item FISHROOM = new AItem("forestcraft:fishroom").food(new AItemFoodProperties().hungerRestore(1).saturation(0.4f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item FLESHER = new AItem("forestcraft:flesher").food(new AItemFoodProperties().hungerRestore(1).saturation(0.4f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item COOKED_FLESHER = new AItem("forestcraft:cooked_flesher").food(new AItemFoodProperties().hungerRestore(8).saturation(0.6f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item RAW_VENISON = new AItem("forestcraft:raw_venison").food(new AItemFoodProperties().hungerRestore(3).saturation(0.3f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item COOKED_VENISON = new AItem("forestcraft:cooked_venison").food(new AItemFoodProperties().hungerRestore(8).saturation(0.8f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item TERROR_BRINGER_MEAT = new AItem("forestcraft:terror_bringer_meat").food(new AItemFoodProperties().hungerRestore(2).saturation(0.5f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item COOKED_TERROR_BRINGER_MEAT = new AItem("forestcraft:cooked_terror_bringer_meat").food(new AItemFoodProperties().hungerRestore(8).saturation(0.5f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item LEAF_MEAT = new AItem("forestcraft:leaf_meat").food(new AItemFoodProperties().hungerRestore(2).saturation(0.5f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item DRIED_LEAF_MEAT = new AItem("forestcraft:dried_leaf_meat").food(new AItemFoodProperties().hungerRestore(7).saturation(1f).meat()).itemGroup(FCItemGroups.FOOD).register();
    public static final Item BAT_WING = new AItem("forestcraft:bat_wing").food(new AItemFoodProperties().hungerRestore(2).saturation(0.2f).meat().effects(new Pair<>(100,()->new EffectInstance(Effects.HUNGER,200,0)))).itemGroup(FCItemGroups.FOOD).register();
    public static final Item MONSTER_STEW = new AItem("forestcraft:monster_stew").food(new AItemFoodProperties().eatingTime(50).hungerRestore(7).saturation(0.5f).effects(new Pair<>(100,()->new EffectInstance(Effects.HUNGER,100,0))).container(()->Items.BOWL)).maxStack(1).itemGroup(FCItemGroups.FOOD).register();
    public static final Item WEIRD_CUCUMBER =  new AItem("forestcraft:weird_cucumber").food(new AItemFoodProperties().hungerRestore(3).saturation(0.3f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item POMIDOR = new AItem("forestcraft:pomidor").food(new AItemFoodProperties().hungerRestore(4).saturation(0.3f)).itemGroup(FCItemGroups.FOOD).register();
    public static final Item SLIME_JELLY = new AItem("forestcraft:slime_jelly").food(new AItemFoodProperties().hungerRestore(3).saturation(1f).effect(100,()->new EffectInstance(Effects.JUMP_BOOST,600,0))).itemGroup(FCItemGroups.FOOD).rarity(Rarity.UNCOMMON).register();
    public static final Item PICKLES = new AItem("forestcraft:pickles").food(new AItemFoodProperties().eatingTime(40).beverage().hungerRestore(3).saturation(1f).container(()->Items.GLASS_BOTTLE).effects(new Pair<>(200,()->new EffectInstance(Effects.RESISTANCE,400,0)))).itemGroup(FCItemGroups.FOOD).maxStack(16).register();
    public static final Item POMIDOR_JUICE = new AItem("forestcraft:pomidor_juice").food(new AItemFoodProperties().eatingTime(40).hungerRestore(2).saturation(1f).beverage().alwaysEdible().container(()->Items.GLASS_BOTTLE).effects(new Pair<>(100,()->new EffectInstance(Effects.REGENERATION,400,0)))).itemGroup(FCItemGroups.FOOD).maxStack(16).register();
    public static final Item SNOWBERRY_PIE = new AItem("forestcraft:snowberry_pie").food(new AItemFoodProperties().hungerRestore(7).saturation(2f).effects(new Pair<>(100,()->new EffectInstance(Effects.FIRE_RESISTANCE,400,0)))).itemGroup(FCItemGroups.FOOD).register();
    public static final Item SNOWBERRY = new AItemBlockNamedItem("forestcraft:snowberry", FCBlocks.SNOWBERRY_BUSH).food(new AItemFoodProperties().eatingTime(16).alwaysEdible().effects(new Pair<>(100,()->new EffectInstance(Effects.NAUSEA,200,1)))).itemGroup(FCItemGroups.FOOD).register();
    public static final Item ENDER_TROUT = new ItemEnderTrout().register();
    public static final Item CACTUSH = new ItemCactush().register();
    public static final Item BOOM_FISH = new ItemBoomFish().register();
    public static final Item BLUEBERRY = new ItemBlueberry().register();
    public static final Item ROYAL_JELLY = new AItem("forestcraft:royal_jelly").itemGroup(FCItemGroups.FOOD).rarity(Rarity.EPIC).food(new AItemFoodProperties().alwaysEdible().eatingTime(10).hungerRestore(1).saturation(2).effects(new Pair<>(100,()->new EffectInstance(Effects.REGENERATION,40,1,true,true)), new Pair<>(50,()->new EffectInstance(Effects.REGENERATION,80,1,true,true)))).register();

    //Scythes
    public static final Item WOODEN_SCYTHE = new FCScytheItem("forestcraft:wooden_scythe", FCItemTier.WOOD, 4, -2.8f, 1).register();
    public static final Item STONE_SCYTHE = new FCScytheItem("forestcraft:stone_scythe", FCItemTier.STONE, 4, -2.8f,2).register();
    public static final Item IRON_SCYTHE = new FCScytheItem("forestcraft:iron_scythe", FCItemTier.IRON,4,-2.8f,3).sound(FCSounds.SCYTHE_WHOOSH).register();
    public static final Item GOLDEN_SCYTHE = new FCScytheItem("forestcraft:golden_scythe", FCItemTier.GOLD, 4, -2.8f,3).sound(FCSounds.SCYTHE_WHOOSH).register();
    public static final Item SHARPED_LEAF_SCYTHE = new ItemSharpedLeafScythe().register();
    public static final Item MOLTEN_SCYTHE = new ItemMoltenScythe().register();
    public static final Item DIAMOND_SCYTHE = new FCScytheItem("forestcraft:diamond_scythe", FCItemTier.DIAMOND, 4, -2.8f,4).sound(FCSounds.SCYTHE_WHOOSH).register();
    public static final Item NETHERITE_SCYTHE = new FCScytheItem("forestcraft:netherite_scythe", FCItemTier.NETHERITE, 4, -2.6f,5).sound(FCSounds.SCYTHE_WHOOSH).register();
    public static final Item ENDERITE_SCYTHE = new ItemEnderiteScythe().register();
    public static final Item SOUL_SCYTHE = new ItemSoulScythe().register();
    public static final Item MYSTERY_SCYTHE = new ItemMysteryScythe().register();
    public static final Item GALAXIA = new ItemGalaxia().register();

    //Equipment
    public static final Item[] MOLTEN_EQUIPMENT = new EquipmentMolten().register();
    public static final Item[] JEWEL_WART_EQUIPMENT = new EquipmentJewelWart().register();
    public static final Item[] ENDERITE_EQUIPMENT = new EquipmentEnderite().register();
    public static final Item[] GEM_EQUIPMENT = new EquipmentGem().register();
    public static final Item[] AQUAMARINE_EQUIPMENT = new EquipmentAquamarine().register();
    public static final Item[] SHARPED_LEAF_EQUIPMENT = new EquipmentSharpedLeaf().register();
    public static final Item[] VITA_EQUIPMENT = new EquipmentVita().register();
    public static final Item[] BLOOMING_EQUIPMENT = new EquipmentBloom().register();

    //Shields
    public static Item PREHISTORIC_SHIELD = new ItemPrehistoricShield().register();
    public static Item HONEY_KEEPER = new ItemHoneyKeeper().register();
    public static Item ARCHAIC_SHIELD = new ItemArchaicShield().register();

    //Guns
    public static final Item SKY_SHOOTER = new ItemSkyShooter().register();
    public static final Item DEMON_BUSTER = new ItemDemonBuster().register();
    public static final Item DAYBREAK = new ItemDaybreak().register();
    public static final Item DARK_MATTER = new ItemDarkMatter().register();
    public static final Item GHAST_CANNON = new ItemGhastCannon().register();
    public static final Item FIRE_FURY = new ItemFireFury().register();

    //Bullets
    public static final Item DEMON_BULLET = new AItem("forestcraft:demon_bullet").itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.BULLETS).maxStack(64).register();
    public static final Item BLACKHOLE_BULLET = new AItem("forestcraft:blackhole_bullet").itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.BULLETS).maxStack(64).register();
    public static final Item SKY_BULLET = new AItem("forestcraft:sky_bullet").maxStack(64).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.BULLETS).register();
    public static final Item MOLTEN_BULLET = new AItem("forestcraft:molten_bullet").maxStack(64).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.BULLETS).register();

    //Swords
    public static final Item EARTH = new ItemEarth().register();
    public static final Item FLESH_EATER = new ItemFleshEater().register();
    public static final Item SPACE_DEVASTATION = new ItemSpaceDevastation().register();
    public static final Item ELECTRON = new ItemElectron().register();
    public static final Item TIDE_DWELLER = new ItemTideDweller().register();
    public static final Item DIRTSCALIBUR =  new ItemDirtscalibur().register();
    public static final Item TRUE_DIRTSCALIBUR = new ItemTrueDirtscalibur().register();
    public static final Item VILE_TENTACLE = new ItemVileTentacle().register();
    public static final Item PIKARINA = new AItemSword("forestcraft:pikarina", FCItemTier.TIERLESS,3,-2.4f).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS).register();
    public static final Item TERROR_BRINGER_CLAW = new AItemSword("forestcraft:terror_bringer_claw",FCItemTier.TERROR_BRINGER,3,-2.6f).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS).register();
    public static final Item SHARP_BONE_JAW =  new AItemSword("forestcraft:sharp_bone_jaw",FCItemTier.TERROR_BRINGER,3,-2.8f).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS).register();
    public static final Item ANCIENT_IRON_SWORD =  new AItemSword("forestcraft:ancient_iron_sword",FCItemTier.IRON,3,-2.2f).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS).register();
    public static final Item ANCIENT_GOLDEN_SWORD =  new AItemSword("forestcraft:ancient_golden_sword",FCItemTier.ANCIENT_GOLD,3,-2.4f).itemGroup(FCItemGroups.WEAPONS,FCItemGroups.Priorities.SWORDS).register();
    public static final Item TRAVELLER = new ItemTraveller().register();
    public static final Item SPORE_DAGGER = new ItemSporeDagger().register();
    public static final Item KEEPER_OF_HEAVEN = new ItemKeeperOfHeaven().register();
    public static final Item DOLPHINIUM = new ItemDolphinium().register();
    public static final Item ANCESTOR = new ItemAncestor().register();
    public static final Item LEVIATHAN = new ItemLeviathan().register();
    public static final Item STAFF_OF_SLEEPING = new ItemStaffOfSleeping().register();
    public static final Item DANDELION_ROD = new ItemDandelionRod().register();
    public static final Item STINGER_SWORD = new ItemStingerSword().register();
    public static final Item IGUANA_KING_CLAW = new ItemIguanaKingClaw().register();
    public static final Item BATTLER = new ItemBattler().register();
    public static final Item INSANITY = new ItemInsanity().register();
    public static final Item MESARGENSTERN = new ItemMesargenstern().register();

    //Bows
    public static final Item ELECTROBOW = new ItemElectrobow().register();
    public static final Item SOUL_CONQUEROR = new ItemSoulConqueror().register();
    public static final Item INSANE_BOW = new ItemInsaneBow().register();

    //Tools
    public static final Item DESTROYER_OF_MOONS = new ItemDestroyerOfMoons().register();
    public static final Item ARCHAXE = new ItemArchaxe().register();
    public static final Item ARCHAIC_HAMMER = new ItemArchaicHammer().register();
    public static final Item OCEAN_MONUMENT_BREAKER = new ItemOceanMonumentBreaker().register();
    public static final Item MESARGENSTAXE = new ItemMesargenstaxe().register();
    public static final Item ORE_EATER = new AItemPickaxe("forestcraft:ore_eater",FCItemTier.TIERLESS,2,-2.8f).itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.PICKAXES).register();
    public static final Item JAWSAW = new AItemPickaxe("forestcraft:jawsaw",FCItemTier.TIERLESS,6,-3.1f).itemGroup(FCItemGroups.EQUIPMENT,FCItemGroups.Priorities.AXES).register();
    public static final Item SOUL_EATER = new ItemSoulEater().register();

    //Wings
    public static final Item COSMIC_TENTACLES = new ItemCosmicTentacles().register();
    public static final Item FLESH_WINGS = new ItemFleshWings().register();
    public static final Item BEE_WINGS = new ItemBeeWings().register();
    public static final Item ANGEL_WINGS = new ItemAngelWings().register();
    public static final Item SCALE_WINGS = new ItemScaleWings().register();
    public static final Item CHICKEN_WINGS = new ItemChickenWings().register();

    //Armor
    public static final Item ETERNAL_HUNGER_CHESTPLATE = new ItemEternalHungerChestplate().register();
    public static final Item INSANE_HELMET = new ItemInsaneHelmet().register();
    public static final Item SKY_WALKERS = new ItemSkyWalkers().register();
    public static final Item SEVEN_LEAGUE_BOOTS = new ItemSevenLeagueBoots().register();
    public static final Item LIGHT_BOOTS = new ItemLightBoots().register();
    public static final Item FIRE_RESISTANT_BOOTS = new ItemFireResistantBoots().register();
    public static final Item TOUGH_NETHERITE_BOOTS = new ItemToughNetheriteBoots().register();
    public static final Item[] GEM_ARMOR = new ArmorSetGem().register();
    public static final Item[] AQUAMARINE_ARMOR = new ArmorSetAquamarine().register();
    public static final Item[] ENDERITE_ARMOR = new ArmorSetEnderite().register();
    public static final Item[] SHARPED_LEAF_ARMOR = new ArmorSetSharpedLeaf().register();
    public static final Item[] JEWEL_WART_ARMOR = new ArmorSetJewel().register();
    public static final Item[] SEA_SCALE_ARMOR  = new ArmorSetSeaScale().register();

    //Materials
    public static final Item ALPHA_INSANE_DOG_EYE = new AItem("forestcraft:alpha_insane_dog_eye").rarity(Rarity.UNCOMMON).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item INSANE_FUR = new AItem("forestcraft:insane_fur").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item KROCK_SKIN = new AItem("forestcraft:krock_skin").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SHARPED_LEAF = new AItem("forestcraft:sharped_leaf").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item NETHERITE_SCRAP_PIECE = new AItem("forestcraft:netherite_scrap_piece").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).fireImmune().register();
    public static final Item NETHERITE_SCRAP_PLATE = new AItem("forestcraft:netherite_scrap_plate").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).fireImmune().register();
    public static final Item OBSIDIAN_GROUPER = new AItem("forestcraft:obsidian_grouper").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).fireImmune().register();
    public static final Item LAVA_DRIFTER = new AItem("forestcraft:lava_drifter").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).fireImmune().register();
    public static final Item CRIMSON_FISH = new AItem("forestcraft:crimson_fish").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).fireImmune().register();
    public static final Item WARPED_FISH = new AItem("forestcraft:warped_fish").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).fireImmune().register();
    public static final Item NETHEROACH = new AItem("forestcraft:netheroach").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).rarity(Rarity.RARE).fireImmune().register();
    public static final Item STONE_BASS = new AItem("forestcraft:stone_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item COAL_BASS = new AItem("forestcraft:coal_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item REDSTONE_BASS = new AItem("forestcraft:redstone_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item LAPIS_BASS = new AItem("forestcraft:lapis_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item IRON_BASS = new AItem("forestcraft:iron_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item GOLDEN_BASS = new AItem("forestcraft:golden_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item EMERALD_BASS = new AItem("forestcraft:emerald_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).rarity(Rarity.RARE).register();
    public static final Item DIAMOND_BASS = new AItem("forestcraft:diamond_bass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).rarity(Rarity.RARE).register();
    public static final Item GIANT_STINGER = new AItem("forestcraft:giant_stinger").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.EPIC).register();
    public static final Item GEM = new AItem("forestcraft:gem").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item JEWEL_WART = new AItem("forestcraft:jewel_wart").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item ELECTRITE = new AItem("forestcraft:electrite").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item THUNDER_FEATHER = new AItem("forestcraft:thunder_feather").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.RARE).fireImmune().register();
    public static final Item FISHING_HOOK = new AItem("forestcraft:fishing_hook").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item MOLTEN_ALLOY = new AItem("forestcraft:molten_alloy").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item MOLTEN_INGOT = new AItem("forestcraft:molten_ingot").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SPORUM_CAP = new AItem("forestcraft:sporum_cap").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item PRISMATIC_DIAMOND = new AItem("forestcraft:prismatic_diamond").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(FCRarity.MYTHICAL).register();
    public static final Item AQUAMARINE = new AItem("forestcraft:aquamarine").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SOUL_ESSENCE = new AItem("forestcraft:soul_essence").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item ANCIENT_SCALE = new AItem("forestcraft:ancient_scale").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item NITER = new AItem("forestcraft:niter").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SULFUR = new AItem("forestcraft:sulfur").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item CROCUS_PETALS = new AItem("forestcraft:crocus_petals").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SAND_PILE = new AItem("forestcraft:sand_pile").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SAND_PILE_WITH_GOLD = new AItem("forestcraft:sand_pile_with_gold").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item ENDERITE_INGOT = new AItem("forestcraft:enderite_ingot").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.EPIC).fireImmune().register();
    public static final Item SKY_FRAGMENT = new AItem("forestcraft:sky_fragment").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item GLUE_MASS = new AItem("forestcraft:glue_mass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SLIMY_CARP = new AItem("forestcraft:slimy_carp").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item FRESHWATER_PHANTOM = new AItem("forestcraft:freshwater_phantom").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item GOLDENFISH = new AItem("forestcraft:golden_fish").rarity(Rarity.UNCOMMON).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).rarity(Rarity.UNCOMMON).register();
    public static final Item BUNCH_OF_GRASS = new AItem("forestcraft:bunch_of_grass").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item DIAMOND_PIECE = new AItem("forestcraft:diamond_piece").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item BROKEN_DIAMOND = new AItem("forestcraft:broken_diamond").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SPONGE_PIECE = new AItem("forestcraft:sponge_piece").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item SQUID = new AItem("forestcraft:squid").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item GEMINNOW = new AItem("forestcraft:geminnow").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.FISHES).register();
    public static final Item TERROR_BRINGER_FEATHER = new AItem("forestcraft:terror_bringer_feather").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item ARCHAIC_FRAGMENT = new AItem("forestcraft:archaic_fragment").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item VITA = new AItem("forestcraft:vita").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item BLOOMING_INGOT = new AItem("forestcraft:blooming_ingot").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item VITA_SHARD = new AItem("forestcraft:vita_shard").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item GEPODIUM_INGOT = new AItem("forestcraft:gepodium_ingot").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.UNCOMMON).register();
    public static final Item SPECTRALUM = new AItem("forestcraft:spectralum").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).rarity(Rarity.RARE).register();
    public static final Item IGUANA_SCALE = new AItem("forestcraft:iguana_scale").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item PHANTOM_FABRIC = new AItem("forestcraft:phantom_fabric").itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.MATERIALS).register();
    public static final Item DIRT_CHUNK = new AItem("forestcraft:dirt_chunk").register();
    public static final Item OLD_COMPASS = new ItemOldCompass().register();

    //Music
    public static final Item BANJO =  new FCMusicInstrument("forestcraft:banjo", ()->SoundEvents.BLOCK_NOTE_BLOCK_BANJO).register();
    public static final Item DIDGERIDOO = new FCMusicInstrument("forestcraft:didgeridoo", ()->SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO).register();
    public static final Item GUITAR =  new FCMusicInstrument("forestcraft:guitar", ()->SoundEvents.BLOCK_NOTE_BLOCK_GUITAR).register();
    public static final Item SYNTHESIZER = new FCMusicInstrument("forestcraft:synthesizer", ()->SoundEvents.BLOCK_NOTE_BLOCK_PLING).register();
    public static final Item BELL = new FCMusicInstrument( "forestcraft:bell", ()->SoundEvents.BLOCK_NOTE_BLOCK_BELL).register();
    public static final Item PIPE = new FCMusicInstrument("forestcraft:pipe", ()->SoundEvents.BLOCK_NOTE_BLOCK_FLUTE).register();
    public static final Item BASS_GUITAR = new FCMusicInstrument("forestcraft:bass_guitar", ()->SoundEvents.BLOCK_NOTE_BLOCK_BASS).register();
    public static final Item COW_BELL = new FCMusicInstrument("forestcraft:cow_bell", ()->SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL).register();
    public static final Item BIT_MACHINE = new FCMusicInstrument("forestcraft:bit_machine", ()->SoundEvents.BLOCK_NOTE_BLOCK_BIT).register();
    public static final Item IRON_XYLOPHONE = new FCMusicInstrument("forestcraft:iron_xylophone", ()->SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE).register();
    public static final Item XYLOPHONE = new FCMusicInstrument("forestcraft:xylophone", ()->SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE).register();
    public static final Item CHIMES = new FCMusicInstrument("forestcraft:chimes", ()->SoundEvents.BLOCK_NOTE_BLOCK_CHIME).register();
    public static final Item TUBULAR_BELLS = new FCMusicInstrument("forestcraft:tubular_bells", FCSounds.TUBULAR_BELLS).register();
    public static final Item WATERPHONE = new FCMusicInstrument("forestcraft:waterphone",FCSounds.WATERPHONE).register();
    public static final Item ELECTRO_GUITAR = new FCMusicInstrument("forestcraft:electro_guitar", FCSounds.ELECTRO_GUITAR).register();
    public static final Item HARP = new FCMusicInstrument("forestcraft:harp", ()->SoundEvents.BLOCK_NOTE_BLOCK_HARP).register();
    public static final Item BASEDRUM = new FCMusicInstrument("forestcraft:basedrum", ()->SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM).register();
    public static final Item SNARE = new FCMusicInstrument("forestcraft:snare", ()->SoundEvents.BLOCK_NOTE_BLOCK_SNARE).register();
    public static final Item KICK = new FCMusicInstrument("forestcraft:kick", ()->SoundEvents.BLOCK_NOTE_BLOCK_HAT).register();


    //Spawn Eggs
    public static final Item DANDELIONEER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:dandelioneer_spawn_egg", ()-> FCEntities.DANDELIONEER,0x52db19,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item CROCUS_FLOWER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:crocus_flower_spawn_egg", ()-> FCEntities.CROCUS_FLOWER,0x8b0075,0xf0ff00).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item PETRIFIED_ZOMBIE_SPAWN_EGG = new AItemSpawnEgg("forestcraft:petrified_zombie_spawn_egg", ()-> FCEntities.PETRIFIED_ZOMBIE,0xff6900,0x550502).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item DEER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:deer_spawn_egg", ()-> FCEntities.DEER,0x4E3005,0xE6BD82).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item INSANE_DOG_SPAWN_EGG = new AItemSpawnEgg("forestcraft:insane_dog_spawn_egg", ()-> FCEntities.INSANE_DOG,0x212121,0xff0000).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item ALPHA_INSANE_DOG_SPAWN_EGG = new AItemSpawnEgg("forestcraft:alpha_insane_dog_spawn_egg", ()-> FCEntities.ALPHA_INSANE_DOG,0xD0D0D0,0xff0000).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item GEM_GOLEM_SPAWN_EGG = new AItemSpawnEgg("forestcraft:gem_golem_spawn_egg", ()-> FCEntities.GEM_GOLEM,0xC6C1C6,0xC219C2).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item BEEQUEEN_SPAWN_EGG = new AItemSpawnEgg("forestcraft:beequeen_spawn_egg", ()-> FCEntities.BEEQUEEN,0xffffff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item IGUANA_KING_SPAWN_EGG = new AItemSpawnEgg("forestcraft:iguana_king_spawn_egg", ()-> FCEntities.IGUANA_KING,0xffffff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item THUNDER_SCREAMER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:thunder_screamer_spawn_egg", ()-> FCEntities.THUNDER_SCREAMER,0xffeb00,0xfff8a4).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item TERROR_BRINGER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:terror_bringer_spawn_egg", ()-> FCEntities.TERROR_BRINGER,0x4c3e30,0xff7f00).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item INFECTED_ZOMBIE_SPAWN_EGG = new AItemSpawnEgg("forestcraft:infected_zombie_spawn_egg", ()-> FCEntities.INFECTED_ZOMBIE,0x25b93c,0xa3cea9).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item NIGHT_WATCHER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:night_watcher_spawn_egg", ()-> FCEntities.NIGHT_WATCHER,0x4c3e30,0xcb09e3).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item CICADA_SPAWN_EGG = new AItemSpawnEgg("forestcraft:cicada_spawn_egg", ()-> FCEntities.CICADA,0xdf6f00,0x653f19).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item EATER_OF_THE_DEPTHS_SPAWN_EGG = new AItemSpawnEgg("forestcraft:eater_of_the_depths_spawn_egg", ()-> FCEntities.EATER_OF_THE_DEPTHS,0x44e1a7,0x196549).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item KROCK_SPAWN_EGG = new AItemSpawnEgg("forestcraft:krock_spawn_egg", ()-> FCEntities.KROCK,0xdf2f00,0x653f19).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item BAKUDAN_SPAWN_EGG = new AItemSpawnEgg("forestcraft:bakudan_spawn_egg", ()-> FCEntities.BAKUDAN,0xf21616,0xf28e16).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item SOUL_BLAZE_SPAWN_EGG = new AItemSpawnEgg("forestcraft:soul_blaze_spawn_egg", ()-> FCEntities.SOUL_BLAZE,0x0095ff,0x2800ec).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item AIR_SUCKER_SPAWN_EGG = new AItemSpawnEgg("forestcraft:air_sucker_spawn_egg", ()-> FCEntities.AIR_SUCKER,0x0b1c88,0x2000ec).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item WOOD_ABOMINATION_SPAWN_EGG = new AItemSpawnEgg("forestcraft:wood_abomination_spawn_egg", ()-> FCEntities.WOOD_ABOMINATION,0x237e09,0x5e3f06).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item ARCHAIC_SENTINEL_SPAWN_EGG = new AItemSpawnEgg("forestcraft:archaic_sentinel_spawn_egg", ()-> FCEntities.ARCHAIC_SENTINEL,0xffffff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item NIGHT_BAT_SPAWN_EGG = new AItemSpawnEgg("forestcraft:night_bat_spawn_egg", ()-> FCEntities.NIGHT_BAT,0x5b544c,0x44413e).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item KELPY_SPAWN_EGG = new AItemSpawnEgg("forestcraft:kelpy_spawn_egg", ()-> FCEntities.KELPY,0x85b334,0x567c15).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item NETHER_BAT_SPAWN_EGG = new AItemSpawnEgg("forestcraft:nether_bat_spawn_egg", ()-> FCEntities.NETHER_BAT,0xf22808,0xf2c008).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item COSMIC_FIEND_SPAWN_EGG = new AItemSpawnEgg("forestcraft:cosmic_fiend_spawn_egg", ()-> FCEntities.COSMIC_FIEND,0xffffff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item NETHER_SCOURGE_SPAWN_EGG = new AItemSpawnEgg("forestcraft:nether_scourge_spawn_egg", ()-> FCEntities.NETHER_SCOURGE,0xffffff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item DESECRATED_SOUL_SPAWN_EGG = new AItemSpawnEgg("forestcraft:desecrated_soul_spawn_egg", ()-> FCEntities.DESECRATED_SOUL,0x00fdff,0x0083ff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item IGUANA_SPAWN_EGG = new AItemSpawnEgg("forestcraft:iguana_spawn_egg", ()-> FCEntities.IGUANA,0x139b00,0x1f9400).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item JUNGLE_WASP_SPAWN_EGG = new AItemSpawnEgg("forestcraft:jungle_wasp_spawn_egg", ()-> FCEntities.JUNGLE_WASP,0x139b00,0x023f12).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item CLOUD_RAY_SPAWN_EGG = new AItemSpawnEgg("forestcraft:cloud_ray_spawn_egg", ()-> FCEntities.CLOUD_RAY,0x00b8ff,0xffffff).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item CHANGELING_SPAWN_EGG = new AItemSpawnEgg("forestcraft:changeling_spawn_egg", ()-> FCEntities.CHANGELING,0xc5813d,0x251506).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item WORM_SPAWN_EGG = new AItemSpawnEgg("forestcraft:worm_spawn_egg", ()-> FCEntities.WORM,0xffe1f1,0xf8afd6).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item SEA_DEVIL_SPAWN_EGG = new AItemSpawnEgg("forestcraft:sea_devil_spawn_egg", ()-> FCEntities.SEA_DEVIL,0x454f59,0x242d37).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item WATER_BUG_SPAWN_EGG = new AItemSpawnEgg("forestcraft:water_bug_spawn_egg", ()-> FCEntities.WATER_BUG,0x154f59,0x642d37).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();
    public static final Item AKNAYAH_SPAWN_EGG = new AItemSpawnEgg("forestcraft:aknayah_spawn_egg", ()-> FCEntities.AKNAYAH,0xffffff,0xffffff).rarity(FCRarity.LEGENDARY).itemGroup(FCItemGroups.MISC,FCItemGroups.Priorities.SPAWN_EGGS).register();

}
