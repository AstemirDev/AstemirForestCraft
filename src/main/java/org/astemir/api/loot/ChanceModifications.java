package org.astemir.api.loot;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import org.astemir.api.math.RandomUtils;

import java.util.function.Function;

public class ChanceModifications {

    public static final Function<PlayerEntity,Double> luck(double modifier){
        return (player)-> {
            if (player.getActivePotionEffect(Effects.LUCK) != null) {
                return (player.getActivePotionEffect(Effects.LUCK).getAmplifier()+1)*modifier;
            }
            return null;
        };
    }


    public static final Function<PlayerEntity,Integer> luck(int min,int max){
        return (player)-> {
            if (player.getActivePotionEffect(Effects.LUCK) != null) {
                int lvl = player.getActivePotionEffect(Effects.LUCK).getAmplifier()+1;
                int count = RandomUtils.randomInt(min,max)*lvl;
                if (count > max){
                    return max;
                }else{
                    return count;
                }
            }
            return null;
        };
    }

    public static final Function<PlayerEntity,Double> looting(double modifier){
        return (player)-> {
            if (player.getHeldItemMainhand() != null) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, player.getHeldItemMainhand());
                return lvl*modifier;
            }
            return null;
        };
    }

    public static final Function<PlayerEntity,Double> fortune(double modifier){
        return (player)-> {
            if (player.getHeldItemMainhand() != null) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand());
                return lvl*modifier;
            }
            return null;
        };
    }

    public static final Function<PlayerEntity,Integer> looting(int min,int max){
        return (player)-> {
            if (player.getHeldItemMainhand() != null) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, player.getHeldItemMainhand());
                int count = RandomUtils.randomInt(min,max)*lvl;
                if (count > max){
                    return max;
                }else{
                    return count;
                }
            }
            return null;
        };
    }

    public static final Function<PlayerEntity,Integer> fortune(int min,int max){
        return (player)-> {
            if (player.getHeldItemMainhand() != null) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand());
                int count = RandomUtils.randomInt(min,max)*lvl;
                if (count > max){
                    return max;
                }else{
                    return count;
                }
            }
            return null;
        };
    }
}
