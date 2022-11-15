package org.astemir.forestcraft;

import net.minecraft.item.Item;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.astemir.api.utils.TextUtils;

import java.util.function.Supplier;

public class FCStrings {

    public static final String PORTAL_WARN = "message.forestcraft.nether_portal_warn";
    public static final String NOT_ENOUGH_SPACE_BOSS = "message.forestcraft.not_enough_space_boss";
    public static final String RANGED_DAMAGE = "tooltip.forestcraft.ranged_damage";
    public static final String CHANCE_TO_NOT_CONSUME = "tooltip.forestcraft.chance_to_not_consume";
    public static final String BAIT = "tooltip.forestcraft.bait_power";
    public static final String BLOOM = "tooltip.forestcraft.bloom";
    public static final String WINGS_POWER = "tooltip.forestcraft.wings_power";
    public static final String WINGS_SPEED = "tooltip.forestcraft.wings_speed";
    public static final String WINGS_FORWARD = "tooltip.forestcraft.wings_forward";
    public static final String WINGS_HORIZONTAL = "tooltip.forestcraft.wings_horizontal";
    public static final String WINGS_VERTICAL = "tooltip.forestcraft.wings_vertical";
    public static final String RESTORES_AIR = "tooltip.forestcraft.air";
    public static final String BATTLER = "tooltip.forestcraft.battler";
    public static final String CAN_BE_OPENED = "tooltip.forestcraft.can_be_opened";
    public static final String WASHABLE = "tooltip.forestcraft.washable";
    public static final String WATER_SPEED = "tooltip.forestcraft.water_speed";
    public static final String RMB_ABILITY = "tooltip.forestcraft.rmb_ability";
    public static final String COOKED_TENTACLE = "tooltip.forestcraft.cooked_tentacle";
    public static final String CROSSER = "tooltip.forestcraft.crosser";
    public static final String DEMON_FOOD = "tooltip.forestcraft.demon_food";
    public static final String DIRTSCALIBUR = "tooltip.forestcraft.dirtscalibur";
    public static final String DOLPHINIUM = "tooltip.forestcraft.dolphinium";
    public static final String EQUINOX_CLOCK = "tooltip.forestcraft.equinox_clock";
    public static final String EQUINOX_TOTEM = "tooltip.forestcraft.equinox_totem";
    public static final String ETERNAL_HUNGER_CHESTPLATE = "tooltip.forestcraft.eternal_hunger_chestplate";
    public static final String FOSSIL_BRUSH = "tooltip.forestcraft.fossil_brush";
    public static final String GLUE_0 = "tooltip.forestcraft.glue_0";
    public static final String GLUE_1 = "tooltip.forestcraft.glue_1";
    public static final String INSANE_HELMET = "tooltip.forestcraft.insane_helmet";
    public static final String INSTRUMENT = "tooltip.forestcraft.instrument";
    public static final String JUMP_BOOST = "tooltip.forestcraft.jump_boost";
    public static final String LIGHT_BOOTS = "tooltip.forestcraft.light_boots";
    public static final String MESARGENSTAXE = "tooltip.forestcraft.mesargenstaxe";
    public static final String MESARGENSTERN = "tooltip.forestcraft.mesargenstern";
    public static final String MOLTEN = "tooltip.forestcraft.molten";
    public static final String NO_FALL_DAMAGE = "tooltip.forestcraft.no_fall_damage";
    public static final String OBSIDIAN_BOOTS = "tooltip.forestcraft.obsidian_boots";
    public static final String RAIN_FLUTE = "tooltip.forestcraft.rain_flute";
    public static final String SCYTHE = "tooltip.forestcraft.scythe";
    public static final String SICKLE = "tooltip.forestcraft.sickle";
    public static final String SIEVE = "tooltip.forestcraft.sieve";
    public static final String DAYBREAK = "tooltip.forestcraft.daybreak";
    public static final String SNOWBERRY_CREAM = "tooltip.forestcraft.snowberry_cream";
    public static final String TRAVELLER = "tooltip.forestcraft.traveller";
    public static final String OCEAN_MONUMENT_BREAKER = "tooltip.forestcraft.ocean_monument_breaker";
    public static final String SET_BONUS = "tooltip.forestcraft.set_bonus";
    public static final String ENDERITE_SET_BONUS = "tooltip.forestcraft.ender";
    public static final String CONSUMES = "tooltip.forestcraft.consumes";
    public static final String SHOT_SPEED = "tooltip.forestcraft.shot_speed";
    public static final String SPEED_VERY_SLOW = "tooltip.forestcraft.speed_0";
    public static final String SPEED_SLOW = "tooltip.forestcraft.speed_1";
    public static final String SPEED_NORMAL = "tooltip.forestcraft.speed_2";
    public static final String SPEED_FAST = "tooltip.forestcraft.speed_3";
    public static final String SPEED_VERY_FAST = "tooltip.forestcraft.speed_4";
    public static final String SPEED_INSANELY_FAST = "tooltip.forestcraft.speed_5";
    public static final String ARROWS = "tooltip.forestcraft.arrows";

    public static IFormattableTextComponent rangedDamageTooltip(float minDamage, float maxDamage){
        return TextUtils.text((int)minDamage+"-"+(int)maxDamage+" ").append(TextUtils.translate(RANGED_DAMAGE)).mergeStyle(TextFormatting.DARK_GREEN);
    }

    public static IFormattableTextComponent rangedDamageTooltip(float damage){
        return TextUtils.text((int)damage+" ").append(TextUtils.translate(RANGED_DAMAGE)).mergeStyle(TextFormatting.DARK_GREEN);
    }

    public static IFormattableTextComponent speedToolTip(int ticks){
        IFormattableTextComponent speedWord = TextUtils.translate(SPEED_INSANELY_FAST,TextFormatting.LIGHT_PURPLE);
        if (ticks >= 3){
            speedWord = TextUtils.translate(SPEED_VERY_FAST,TextFormatting.RED);
        }
        if (ticks >= 10){
            speedWord = TextUtils.translate(SPEED_FAST,TextFormatting.AQUA);
        }
        if (ticks >= 20){
            speedWord = TextUtils.translate(SPEED_NORMAL,TextFormatting.GRAY);
        }
        if (ticks >= 40){
            speedWord = TextUtils.translate(SPEED_SLOW,TextFormatting.DARK_GRAY);
        }
        if (ticks >= 60){
            speedWord = TextUtils.translate(SPEED_VERY_SLOW,TextFormatting.DARK_GRAY);
        }
        return TextUtils.translate(SHOT_SPEED,TextFormatting.YELLOW).append(TextUtils.text(" ")).append(speedWord);
    }

    public static IFormattableTextComponent chanceToNotConsume(int chance){
        return TextUtils.translate(CHANCE_TO_NOT_CONSUME).append(TextUtils.text(chance+"%")).mergeStyle(TextFormatting.GRAY);
    }

    public static IFormattableTextComponent consumes(Supplier<Item> item){
        return TextUtils.translate(CONSUMES).append(TextUtils.text(" ").append(TextUtils.safeTranslate(()->item.get().getTranslationKey()))).mergeStyle(TextFormatting.GRAY);
    }

    public static IFormattableTextComponent consumesArrows(){
        return TextUtils.translate(CONSUMES).append(TextUtils.text(" ").append(TextUtils.translate(ARROWS))).mergeStyle(TextFormatting.GRAY);
    }

    public static String prefix(String name){
        return ForestCraft.MOD_ID+":"+name;
    }
}
