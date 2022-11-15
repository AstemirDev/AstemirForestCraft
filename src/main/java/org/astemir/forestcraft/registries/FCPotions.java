package org.astemir.forestcraft.registries;

import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import org.astemir.forestcraft.ForestCraft;


public class FCPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Potion.class, ForestCraft.MOD_ID);
    public static final RegistryObject<Potion> BROKEN_ARMOR = POTIONS.register("broken_armor",()->new Potion(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),2200,0)));
    public static final RegistryObject<Potion> BROKEN_ARMOR_LONG = POTIONS.register("broken_armor_long",()->new Potion(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),4200,0)));
    public static final RegistryObject<Potion> BROKEN_ARMOR_STRONG = POTIONS.register("broken_armor_strong",()->new Potion(new EffectInstance(FCEffects.BROKEN_ARMOR.get(),2000,1)));
    public static final RegistryObject<Potion> THORNS = POTIONS.register("thorns",()->new Potion(new EffectInstance(FCEffects.THORNS.get(),1200,1)));
    public static final RegistryObject<Potion> THORNS_LONG = POTIONS.register("thorns_long",()->new Potion(new EffectInstance(FCEffects.THORNS.get(),2200,1)));
    public static final RegistryObject<Potion> THORNS_STRONG = POTIONS.register("thorns_strong",()->new Potion(new EffectInstance(FCEffects.THORNS.get(),1200,2)));
    public static final RegistryObject<Potion> BAT_VISION = POTIONS.register("bat_vision",()->new Potion(new EffectInstance(Effects.NIGHT_VISION,1800,0),new EffectInstance(Effects.HUNGER,1200,0)));
    public static final RegistryObject<Potion> BAT_VISION_LONG = POTIONS.register("bat_vision_long",()->new Potion(new EffectInstance(Effects.NIGHT_VISION,3900,0),new EffectInstance(Effects.HUNGER,2400,0)));
    public static final RegistryObject<Potion> ELECTRO_RESISTANCE = POTIONS.register("electro_resistance",()->new Potion(new EffectInstance(FCEffects.ELECTRO_RESISTANCE.get(),3800,0)));
    public static final RegistryObject<Potion> ELECTRO_RESISTANCE_LONG = POTIONS.register("electro_resistance_long",()->new Potion(new EffectInstance(FCEffects.ELECTRO_RESISTANCE.get(),9600,0)));
    public static final RegistryObject<Potion> SEA_VISION = POTIONS.register("sea_vision",()->new Potion(new EffectInstance(FCEffects.SEA_VISION.get(),2400,0)));
    public static final RegistryObject<Potion> SEA_VISION_LONG = POTIONS.register("sea_vision_long",()->new Potion(new EffectInstance(FCEffects.SEA_VISION.get(),6600,0)));



    public static void registerPotionRecipes(){
        PotionBrewing.addMix(Potions.AWKWARD, FCItems.SHARPED_LEAF.asItem(), FCPotions.BROKEN_ARMOR.get());
        PotionBrewing.addMix(FCPotions.BROKEN_ARMOR.get(), Items.GLOWSTONE_DUST.asItem(), FCPotions.BROKEN_ARMOR_STRONG.get());
        PotionBrewing.addMix(FCPotions.BROKEN_ARMOR.get(), Items.REDSTONE.asItem(), FCPotions.BROKEN_ARMOR_LONG.get());
        PotionBrewing.addMix(Potions.AWKWARD, FCItems.BAT_WING.asItem(), FCPotions.BAT_VISION.get());
        PotionBrewing.addMix(FCPotions.BAT_VISION.get(), Items.REDSTONE.asItem(), FCPotions.BAT_VISION_LONG.get());
        PotionBrewing.addMix(Potions.AWKWARD, Items.SLIME_BALL.asItem(), FCPotions.ELECTRO_RESISTANCE.get());
        PotionBrewing.addMix(FCPotions.ELECTRO_RESISTANCE.get(), Items.REDSTONE.asItem(), FCPotions.ELECTRO_RESISTANCE_LONG.get());
        PotionBrewing.addMix(Potions.AWKWARD, FCBlocks.SNOWBERRY_BUSH.asItem(),Potions.FIRE_RESISTANCE);
        PotionBrewing.addMix(Potions.AWKWARD, FCItems.ICICLE.asItem(),Potions.SLOWNESS);
        PotionBrewing.addMix(Potions.AWKWARD, Items.TROPICAL_FISH.asItem(), FCPotions.SEA_VISION.get());
        PotionBrewing.addMix(FCPotions.SEA_VISION.get(), Items.REDSTONE.asItem(), FCPotions.SEA_VISION_LONG.get());
        PotionBrewing.addMix(Potions.AWKWARD, FCItems.KROCK_SKIN.asItem(), FCPotions.THORNS.get());
        PotionBrewing.addMix(FCPotions.THORNS.get(), Items.GLOWSTONE_DUST.asItem(), FCPotions.THORNS_STRONG.get());
        PotionBrewing.addMix(FCPotions.THORNS.get(), Items.REDSTONE.asItem(), FCPotions.THORNS_LONG.get());
        PotionBrewing.addMix(Potions.WATER, FCItems.FLESHER.asItem(),Potions.AWKWARD);
    }


}
