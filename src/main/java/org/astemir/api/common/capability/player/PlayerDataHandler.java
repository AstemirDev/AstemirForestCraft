package org.astemir.api.common.capability.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

import java.util.HashMap;
import java.util.Map;

public class PlayerDataHandler {

    private Map<PlayerData,Object> dataMap = new HashMap<>();

    public void set(PlayerData key,Object value){
        this.dataMap.put(key,value);
    }

    public <T> T get(PlayerData<T> key){
        return (T) key.getValueClass().
                cast(dataMap.get(key));
    }

    public boolean has(PlayerData key){
        return dataMap.containsKey(key);
    }

    public void add(PlayerData<Integer> key,int value){
        if (has(key)) {
            set(key, get(key) + value);
        }else{
            set(key,value);
        }
    }

    public void add(PlayerData<Double> key,double value){
        if (has(key)) {
            set(key, get(key) + value);
        }else{
            set(key,value);
        }
    }

    public void add(PlayerData<Float> key,float value){
        if (has(key)) {
            set(key, get(key) + value);
        }else{
            set(key,value);
        }
    }

    public void remove(PlayerData key){
        if (has(key)){
            dataMap.remove(key);
        }
    }

    public CompoundNBT serialize(){
        CompoundNBT nbt = new CompoundNBT();
        dataMap.forEach((key,value)->{
            if (value instanceof Integer){
                nbt.putInt(key.getKey(),(int)value);
            }else
            if (value instanceof String){
                nbt.putString(key.getKey(),(String)value);
            }else
            if (value instanceof Float){
                nbt.putFloat(key.getKey(),(float)value);
            }else
            if (value instanceof Double){
                nbt.putDouble(key.getKey(),(double)value);
            }
            if (value instanceof Byte){
                nbt.putByte(key.getKey(),(byte)value);
            }
        });
        return nbt;
    }

    public void deserialize(INBT nbt){
        CompoundNBT tag = (CompoundNBT)nbt;
        for (String key : tag.keySet()) {
            PlayerData data = PlayerDataRegistry.getByKey(key);
            Object value = null;
            if (data.getValueClass() == Integer.class){
                value = tag.getInt(key);
            }else
            if (data.getValueClass() == String.class){
                value = tag.getString(key);
            }else
            if (data.getValueClass() == Float.class){
                value = tag.getFloat(key);
            }else
            if (data.getValueClass() == Double.class){
                value = tag.getDouble(key);
            }
            if (data.getValueClass() == Byte.class){
                value = tag.getByte(key);
            }
            dataMap.put(data,value);
        }
    }

    @Override
    public String toString() {
        return "PlayerDataHandler{" +
                "dataMap=" + dataMap +
                '}';
    }
}
