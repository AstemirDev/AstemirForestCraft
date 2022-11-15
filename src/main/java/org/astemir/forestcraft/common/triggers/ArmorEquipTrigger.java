package org.astemir.forestcraft.common.triggers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;
import org.astemir.forestcraft.ForestCraft;
import java.util.Map;
import java.util.Set;

public class ArmorEquipTrigger extends AbstractCriterionTrigger<ArmorEquipTrigger.Instance> {

    public static final ResourceLocation ID = new ResourceLocation(ForestCraft.MOD_ID,"armor_equipped");

    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();


    @Override
    public ResourceLocation getId() {
        return ID;
    }


    @Override
    protected Instance deserializeTrigger(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        EntityPredicate.AndPredicate player = EntityPredicate.AndPredicate.deserializeJSONObject(json, "player", conditionsParser);
        return new ArmorEquipTrigger.Instance(player);
    }


    public void trigger(ServerPlayerEntity player) {
        ArmorEquipTrigger.Listeners listeners = this.listeners.get(player.getAdvancements());
        if (listeners != null) {
            listeners.trigger();
        }
    }

    public static class Instance extends CriterionInstance {
        public Instance(EntityPredicate.AndPredicate player) {
            super(ArmorEquipTrigger.ID, player);
        }
    }

    static class Listeners {

        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(Listener<ArmorEquipTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(Listener<ArmorEquipTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger() {
            for (Listener<ArmorEquipTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
                listener.grantCriterion(this.playerAdvancements);
            }
        }
    }
}

