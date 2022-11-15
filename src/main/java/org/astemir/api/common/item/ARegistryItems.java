package org.astemir.api.common.item;


import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ARegistryItems {

    public static Class ITEMS_CONTAINER;

    public static List<Item> ITEMS = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Field f : ITEMS_CONTAINER.getDeclaredFields()) {
            Object obj = null;
            try {
                obj = f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj instanceof Item) {
                if (((Item) obj).getRegistryName() != null) {
                    event.getRegistry().register((Item) obj);
                    ITEMS.add((Item)obj);
                }
            }else
            if (obj instanceof Item[]){
                for (Item item : (Item[]) obj){
                    if (item != null) {
                        if (item.getRegistryName() != null) {
                            event.getRegistry().register(item);
                            ITEMS.add(item);
                        }
                    }
                }
            }
        }
    }



}
