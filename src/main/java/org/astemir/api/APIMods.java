package org.astemir.api;

import net.minecraftforge.fml.ModList;

public enum APIMods {

    CURIOS("curios");

    private String name;
    private boolean loaded;


    APIMods(String name) {
        this.name = name;
        this.loaded = ModList.get() != null && ModList.get().getModContainerById(name).isPresent();
    }

    public boolean isLoaded() {
        return loaded;
    }
}
