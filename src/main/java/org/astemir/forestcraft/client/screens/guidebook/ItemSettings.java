package org.astemir.forestcraft.client.screens.guidebook;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.astemir.forestcraft.ForestCraft;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemSettings {

    private List<ItemSetting> settings;

    public ItemSettings() {
        settings = new ArrayList<>();
        JsonParser parser = new JsonParser();
        InputStream stream = ForestCraft.class.getClassLoader().getResourceAsStream("assets/forestcraft/guidebook/items.json");
        JsonElement parsed = parser.parse(new InputStreamReader(stream));
        for (Map.Entry<String, JsonElement> itemTypeElement : parsed.getAsJsonObject().entrySet()) {
            String itemType = itemTypeElement.getKey();
            JsonObject contents = itemTypeElement.getValue().getAsJsonObject();
            String material = contents.get("item").getAsString();
            String path = contents.get("path").getAsString();
            String name = contents.get("name").getAsString();
            int scale = contents.get("scale").getAsInt();
            settings.add(new ItemSetting(material,path,name,scale));
        }
    }

    public List<ItemSetting> getSettings() {
        return settings;
    }

    public class ItemSetting{

        private String itemMaterial;
        private String name;
        private String path;
        private int scale;

        public ItemSetting(String itemMaterial, String path, String name,int scale) {
            this.itemMaterial = itemMaterial;
            this.path = path;
            this.name = name;
            this.scale = scale;
        }

        public String getName() {
            return name;
        }

        public String getItemMaterial() {
            return itemMaterial;
        }

        public String getPath() {
            return path;
        }

        public int getScale() {
            return scale;
        }
    }
}
