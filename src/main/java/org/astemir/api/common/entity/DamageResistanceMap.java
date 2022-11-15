package org.astemir.api.common.entity;


import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import java.util.HashMap;
import java.util.Map;

public class DamageResistanceMap {

    private Map<Source,Float> resistanceMap = new HashMap<>();
    public static final Source EXPLOSION = new Source().explosion();

    public DamageResistanceMap put(Class<?> damagerClass,float resistance){
        this.resistanceMap.put(new Source(damagerClass),resistance);
        return this;
    }

    public DamageResistanceMap put(DamageSource type,float resistance){
        this.resistanceMap.put(new Source(type),resistance);
        return this;
    }

    public DamageResistanceMap put(Source source,float resistance){
        this.resistanceMap.put(source,resistance);
        return this;
    }


    public float getResistance(DamageSource type){
        for (Map.Entry<Source, Float> entry : resistanceMap.entrySet()) {
            if (type.isExplosion()){
                if (entry.getKey().isExplosion()){
                    return entry.getValue();
                }
            }
            if (entry.getKey().getSourceType() != null) {
                if (entry.getKey().getSourceType() == type) {
                    return entry.getValue();
                }
            }
        }
        return 0;
    }

    public float getResistance(Entity entity){
        for (Map.Entry<Source, Float> entry : resistanceMap.entrySet()) {
            Source source = entry.getKey();
            Class<?> sourceClass = source.getSourceClass();
            if (sourceClass != null) {
                if (sourceClass == entity.getClass() || sourceClass.isInstance(entity)) {
                    return entry.getValue();
                }
            }
        }
        return 0;
    }

    public static class Source{

        private DamageSource sourceType;
        private Class<?> sourceClass;
        public boolean explosion = false;

        public boolean isExplosion() {
            return explosion;
        }

        public Source explosion() {
            this.explosion = true;
            return this;
        }

        public Source() {
        }

        public Source(DamageSource sourceType) {
            this.sourceType = sourceType;
        }

        public Source(Class<?> sourceClass) {
            this.sourceClass = sourceClass;
        }

        public DamageSource getSourceType() {
            return sourceType;
        }

        public Class<?> getSourceClass() {
            return sourceClass;
        }
    }

}
