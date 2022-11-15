package org.astemir.api.loot;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.astemir.api.math.RandomUtils;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemDrop implements IChanceModificable<PlayerEntity>, IDropable<ItemStack>,IDropChance{

    private Supplier<ItemStack> stack;
    private double chance = 100;
    private int minCount = 1;
    private int maxCount = 1;
    private Function<PlayerEntity,Integer> countMultiplier;
    private Function<PlayerEntity,Double> chanceMultiplier;
    private Random random;



    public ItemDrop(Supplier<ItemStack> stack, int minCount, int maxCount) {
        this.stack = stack;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.random = new Random();
    }

    public ItemDrop(Supplier<ItemStack> stack, int maxCount) {
        this(stack,1,maxCount);
    }

    public ItemDrop(Supplier<ItemStack> stack) {
        this(stack,1,1);
    }

    public static ItemDrop fromItem(Supplier<Item> item, int minCount, int maxCount) {
        return new ItemDrop(()->new ItemStack(item.get()),minCount,maxCount);
    }


    public Function<PlayerEntity, Integer> countMultiplier() {
        return countMultiplier;
    }

    public ItemDrop countMultiplier(Function<PlayerEntity, Integer> countMultiplier) {
        this.countMultiplier = countMultiplier;
        return this;
    }


    public ItemDrop chanceMultiplier(Function<PlayerEntity, Double> chanceMultiplier) {
        this.chanceMultiplier = chanceMultiplier;
        return this;
    }


    public static ItemDrop fromItem(Supplier<Item> item, int maxCount) {
        return fromItem(item,1,maxCount);
    }

    public static ItemDrop fromItem(Supplier<Item> item) {
        return fromItem(item,1,1);
    }

    @Override
    public ItemDrop chance(double chance){
        this.chance = chance;
        return this;
    }

    public double chance() {
        return chance;
    }

    @Override
    public ItemStack drop(PlayerEntity player) {
        if (stack != null) {
            if (stack.get() != null) {
                double num = random.nextDouble() * 100;
                if (num <= chance) {
                    ItemStack copy = stack.get().copy();
                    if (maxCount > 1) {
                        int count = RandomUtils.randomInt(minCount, maxCount);
                        if (player != null) {
                            if (countMultiplier != null) {
                                count += countMultiplier.apply(player);
                            }
                        }
                        copy.setCount(count);
                    }
                    return copy;
                }
            }
        }
        return null;
    }

    @Override
    public ItemStack drop() {
        return drop(null);
    }


    @Override
    public Function<PlayerEntity, Double> chanceMultiplier() {
        return chanceMultiplier;
    }

}
