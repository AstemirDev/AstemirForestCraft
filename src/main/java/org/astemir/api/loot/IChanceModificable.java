package org.astemir.api.loot;


import java.util.function.Function;

public interface IChanceModificable<T> {

    public Function<T,Double> chanceMultiplier();
}
