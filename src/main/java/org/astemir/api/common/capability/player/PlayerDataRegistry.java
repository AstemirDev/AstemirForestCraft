package org.astemir.api.common.capability.player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataRegistry {

    private static List<PlayerData> registeredDatas = new ArrayList<>();


    public static <T> PlayerData registerData(String key,Class<T> className){
        PlayerData<T> data = new PlayerData<T>(key,className);
        registeredDatas.add(data);
        return data;
    }

    public static List<PlayerData> getRegisteredData() {
        return registeredDatas;
    }

    public static PlayerData getByKey(String name){
        for (PlayerData registeredData : registeredDatas) {
            if (registeredData.getKey().equals(name)){
                return registeredData;
            }
        }
        return null;
    }
}
