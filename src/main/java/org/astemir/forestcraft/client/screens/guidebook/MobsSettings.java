package org.astemir.forestcraft.client.screens.guidebook;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.math.vector.Vector2f;
import org.astemir.forestcraft.ForestCraft;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MobsSettings {

    private List<MobSetting> settings;

    public MobsSettings() {
        settings = new ArrayList<>();
        JsonParser parser = new JsonParser();
        InputStream stream = ForestCraft.class.getClassLoader().getResourceAsStream("assets/forestcraft/guidebook/mobs.json");
        JsonElement parsed = parser.parse(new InputStreamReader(stream));
        for (Map.Entry<String, JsonElement> mobTypeElement : parsed.getAsJsonObject().entrySet()) {
            String mobType = mobTypeElement.getKey();
            JsonObject contents = mobTypeElement.getValue().getAsJsonObject();
            String path = contents.get("path").getAsString();
            int[] positionArray = new Gson().fromJson(contents.get("position").getAsJsonArray(),int[].class);
            Vector2f position = new Vector2f(positionArray[0],positionArray[1]);
            int scale = contents.get("scale").getAsInt();
            settings.add(new MobSetting(mobType,path,position,scale));
        }
    }

    public List<MobSetting> getSettings() {
        return settings;
    }

    public class MobSetting{

        private String mobType;
        private String path;
        private Vector2f position;
        private int scale;

        public MobSetting(String mobType, String path, Vector2f position, int scale) {
            this.mobType = mobType;
            this.path = path;
            this.position = position;
            this.scale = scale;
        }

        public String getMobType() {
            return mobType;
        }

        public String getPath() {
            return path;
        }

        public Vector2f getPosition() {
            return position;
        }

        public int getScale() {
            return scale;
        }
    }
}
