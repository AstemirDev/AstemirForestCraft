package org.astemir.forestcraft.client.screens.guidebook;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.astemir.api.math.Vector2;
import org.astemir.forestcraft.ForestCraft;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StructureSettings {

    private List<StructureSetting> settings;

    public StructureSettings() {
        settings = new ArrayList<>();
        JsonParser parser = new JsonParser();
        InputStream stream = ForestCraft.class.getClassLoader().getResourceAsStream("assets/forestcraft/guidebook/structures.json");
        JsonElement parsed = parser.parse(new InputStreamReader(stream));
        for (Map.Entry<String, JsonElement> mobTypeElement : parsed.getAsJsonObject().entrySet()) {
            JsonObject contents = mobTypeElement.getValue().getAsJsonObject();
            String path = contents.get("path").getAsString();
            String imagePath = contents.get("image").getAsString();
            String name = contents.get("name").getAsString();
            double scale = contents.get("scale").getAsDouble();
            int[] sizeArray = new Gson().fromJson(contents.get("size").getAsJsonArray(),int[].class);
            Vector2 size = new Vector2(sizeArray[0],sizeArray[1]);
            settings.add(new StructureSetting(path,imagePath,name,scale,size));
        }
    }

    public List<StructureSetting> getSettings() {
        return settings;
    }

    public class StructureSetting{

        private String imagePath;
        private String path;
        private String name;
        private double scale;
        private Vector2 size;

        public StructureSetting(String path, String imagePath,String name, double scale,Vector2 size) {
            this.imagePath = imagePath;
            this.path = path;
            this.name = name;
            this.scale = scale;
            this.size = size;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public double getScale() {
            return scale;
        }

        public Vector2 getSize() {
            return size;
        }
    }
}
