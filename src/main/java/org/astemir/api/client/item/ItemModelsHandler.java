package org.astemir.api.client.item;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.astemir.api.AstemirAPI;

import java.util.concurrent.CopyOnWriteArrayList;

public class ItemModelsHandler {

    public CopyOnWriteArrayList<ItemModelHolder> modelsToRender = new CopyOnWriteArrayList<>();

    public void update(long ticks){
        for (ItemModelHolder modelHolder : modelsToRender) {
            if (ticks-modelHolder.getLastRenderedTick() >= 20){
                modelsToRender.remove(modelHolder);
            }
        }
    }


    public <T extends Model> T getModelOrCreate(Entity owner, ItemStack stack,Class<T> modelClass) {
        T model = getModel(owner,modelClass);
        if (model != null){
            return model;
        }else{
            try {
                ItemModelHolder<T> holder = new ItemModelHolder(owner.getEntityId(),stack,modelClass.newInstance());
                holder.setLastRenderedTick(AstemirAPI.CLIENT.TICKS);
                modelsToRender.add(holder);
                return holder.getItemModel();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T extends Model> T getModelOrCreate(ItemStack stack,Class<T> modelClass) {
        T model = getModel(stack,modelClass);
        if (model != null){
            return model;
        }else{
            try {
                ItemModelHolder<T> holder = new ItemModelHolder(0,stack,modelClass.newInstance());
                holder.setLastRenderedTick(AstemirAPI.CLIENT.TICKS);
                modelsToRender.add(holder);
                return holder.getItemModel();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T extends Model> T getModel(Entity owner, Class<T> modelClass){
        for (ItemModelHolder modelHolder : modelsToRender) {

            if (modelHolder.getOwnerId() == owner.getEntityId()  && modelClass == modelHolder.getItemModel().getClass()){
                modelHolder.setLastRenderedTick(AstemirAPI.CLIENT.TICKS);
                return (T) modelHolder.getItemModel();
            }
        }
        return null;
    }

    public <T extends Model> T getModel(ItemStack stack, Class<T> modelClass){
        for (ItemModelHolder modelHolder : modelsToRender) {
            if (modelHolder.getItemStack() == stack && modelClass == modelHolder.getItemModel().getClass()){
                modelHolder.setLastRenderedTick(AstemirAPI.CLIENT.TICKS);
                return (T) modelHolder.getItemModel();
            }
        }
        return null;
    }
}
