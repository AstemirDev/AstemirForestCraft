package org.astemir.api.client.item;

import net.minecraft.client.renderer.model.Model;

import java.util.HashMap;
import java.util.Map;

public class ItemModelRegistry {

    public Map<Integer, Class<? extends Model>> models = new HashMap<>();


    public void addModel(int index,Class<? extends Model> model){
        this.models.put(index,model);
    }

    public Class<? extends Model> getModel(int index){
        return models.get(index);
    }
}
