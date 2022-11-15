package org.astemir.api.common.capability.player;


public class PlayerData<T> {


    private String key;
    private Class<T> valueClass;


    public PlayerData(String key, Class<T> valueClass) {
        this.key = key;
        this.valueClass = valueClass;
    }

    public String getKey() {
        return key;
    }

    public Class<T> getValueClass() {
        return valueClass;
    }


    @Override
    public String toString() {
        return "PlayerData{" +
                "key='" + key + '\'' +
                ", valueClass=" + valueClass +
                '}';
    }
}
