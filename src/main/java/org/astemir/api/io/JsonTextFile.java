package org.astemir.api.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.astemir.api.AstemirAPI;
import org.astemir.forestcraft.ForestCraft;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonTextFile {

    private JsonElement text;

    public JsonTextFile(String path) {
        JsonParser parser = new JsonParser();
        InputStream stream = AstemirAPI.class.getClassLoader().getResourceAsStream(path);
        text = parser.parse(new InputStreamReader(stream));
    }

    public JsonElement content(){
        return text;
    }
}
