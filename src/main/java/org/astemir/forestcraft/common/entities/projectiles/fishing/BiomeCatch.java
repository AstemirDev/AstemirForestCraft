package org.astemir.forestcraft.common.entities.projectiles.fishing;

import org.astemir.api.loot.ItemDrop;
import org.astemir.api.loot.ItemDropPool;

import java.util.function.Predicate;


public class BiomeCatch extends ItemDropPool {

    private final Predicate<FishPredicate> predicate;

    public BiomeCatch(Predicate<FishPredicate> predicate){
        this.predicate = predicate;
    }

    @Override
    public BiomeCatch canHasNoResult() {
        return (BiomeCatch)super.canHasNoResult();
    }

    @Override
    public BiomeCatch add(double chance, ItemDrop element) {
        return (BiomeCatch)super.add(chance, element);
    }

    @Override
    public BiomeCatch build() {
        return (BiomeCatch)super.build();
    }

    public Predicate<FishPredicate> getPredicate() {
        return predicate;
    }
}
