package org.astemir.api.utils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.*;
import org.astemir.api.io.SafeTranslationComponent;

import java.util.function.Supplier;

public class TextUtils {

    public static TranslationTextComponent translate(String key){
        return new TranslationTextComponent(key);
    }

    public static SafeTranslationComponent safeTranslate(Supplier<String> key){
        return new SafeTranslationComponent(key);
    }

    public static StringTextComponent text(String text){
        return new StringTextComponent(text);
    }

    public static SafeTranslationComponent safeTranslate(Supplier<String> key,TextFormatting... format){
        SafeTranslationComponent component = safeTranslate(key);
        component.mergeStyle(format);
        return component;
    }

    public static TranslationTextComponent translate(String key,TextFormatting... format){
        TranslationTextComponent component = translate(key);
        component.mergeStyle(format);
        return component;
    }

    public static StringTextComponent text(String text,TextFormatting... format){
        StringTextComponent component = text(text);
        component.mergeStyle(format);
        return component;
    }

    public static int getWidth(FontRenderer renderer,TextComponent component){
        return renderer.getStringPropertyWidth(component);
    }

    public static IFormattableTextComponent effectTooltip(Supplier<Effect> effect, int seconds, int power,boolean harmful){
        String powerText = numberToRoman(power+1);
        String timeText = timeToString(seconds);
        IFormattableTextComponent component = safeTranslate(()->effect.get().getName()).
                append(text(" ")).
                append(text(powerText)).
                append(text(" ")).
                append(text(timeText));
        if (harmful) {
            component.mergeStyle(TextFormatting.RED);
        }else{
            component.mergeStyle(TextFormatting.BLUE);
        }
        return component;
    }

    public static String numberToRoman(int number){
        switch (number){
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
        }
        return "X";
    }

    public static String timeToString(int seconds){
        String result = "(";
        int minutes = seconds / 60;
        result+=minutes;
        result+=":";
        if (seconds < 10){
            result+="0";
        }
        result+=seconds%60;
        return result+")";
    }

    public static StringTextComponent empty(){
        return new StringTextComponent("");
    }

    public static TextComponent construct(TextComponent... components){
        TextComponent res = empty();
        for (TextComponent text : components) {
            res.append(text);
        }
        return res;
    }

    public static ITextComponent color(TextComponent text,TextFormatting format){
        text.mergeStyle(format);
        return text;
    }
}
