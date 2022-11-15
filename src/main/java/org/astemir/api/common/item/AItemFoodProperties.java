package org.astemir.api.common.item;

import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import org.astemir.api.math.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AItemFoodProperties{

    public static final int DEFAULT_EATING_TIME = 32;
    public static final int FAST_EATING_TIME = 16;

    private final Map<Supplier<EffectInstance>, Integer> effects = new HashMap<>();
    private Supplier<SoundEvent> eatingSound = ()-> SoundEvents.ENTITY_GENERIC_EAT;
    private Supplier<SoundEvent> drinkSound = ()-> SoundEvents.ENTITY_GENERIC_DRINK;
    private Supplier<Item> containerItem = null;
    private int hungerRestore = 0;
    private float saturation = 0;
    private int eatingTime = DEFAULT_EATING_TIME;
    private boolean meat = false;
    private boolean alwaysEdible = false;
    private boolean beverage = false;

    public int getHungerRestore(){
        return hungerRestore;
    }

    public float getSaturation(){
        return saturation;
    }

    public int getEatingTime(){
        return eatingTime;
    }

    public boolean isMeat(){
        return meat;
    }

    public boolean isBeverage() {
        return beverage;
    }

    public boolean isAlwaysEdible(){return alwaysEdible;}

    public Map<Supplier<EffectInstance>, Integer> getEffects() {
        return effects;
    }

    public Supplier<SoundEvent> getEatingSound() {
        if (!isBeverage()) {
            return eatingSound;
        }else{
            return drinkSound;
        }
    }

    public Supplier<SoundEvent> getDrinkSound() {
        return drinkSound;
    }

    public Supplier<Item> getContainerItem() {
        return containerItem;
    }

    public AItemFoodProperties hungerRestore(int hungerRestore) {
        this.hungerRestore = hungerRestore;
        return this;
    }

    public AItemFoodProperties saturation(float saturation) {
        this.saturation = saturation;
        return this;
    }

    public AItemFoodProperties eatingTime(int time) {
        this.eatingTime = time;
        return this;
    }

    public AItemFoodProperties meat() {
        this.meat = true;
        return this;
    }

    public AItemFoodProperties beverage() {
        this.beverage = true;
        return this;
    }

    public AItemFoodProperties alwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }

    public AItemFoodProperties eatingSound(Supplier<SoundEvent> eatingSound) {
        this.eatingSound = eatingSound;
        return this;
    }

    public AItemFoodProperties drinkSound(Supplier<SoundEvent> drinkSound) {
        this.drinkSound = drinkSound;
        return this;
    }

    public AItemFoodProperties container(Supplier<Item> containerItem) {
        this.containerItem = containerItem;
        return this;
    }

    public AItemFoodProperties effects(Pair<Integer,Supplier<EffectInstance>>... effects){
        for (Pair<Integer, Supplier<EffectInstance>> effect : effects) {
            this.effects.put(effect.getValue(),effect.getKey());
        }
        return this;
    }

    public AItemFoodProperties effect(int chance,Supplier<EffectInstance> effect){
        this.effects.put(effect,chance);
        return this;
    }
}
