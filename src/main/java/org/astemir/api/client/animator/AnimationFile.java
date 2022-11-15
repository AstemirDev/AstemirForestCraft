package org.astemir.api.client.animator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.astemir.api.math.Vector3;
import org.astemir.forestcraft.ForestCraft;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class AnimationFile {

    private Map<String, AnimationTrack> animations = new HashMap<>();

    public AnimationFile(String path) {
        JsonParser parser = new JsonParser();
        InputStream stream = ForestCraft.class.getClassLoader().getResourceAsStream("assets/forestcraft/"+path);
        JsonElement parsed = parser.parse(new InputStreamReader(stream));
        for (Map.Entry<String, JsonElement> animationEntry : parsed.getAsJsonObject().get("animations").getAsJsonObject().entrySet()) {
            String animationName = animationEntry.getKey();
            JsonObject animationJsonObject = animationEntry.getValue().getAsJsonObject();
            boolean loop = true;
            if (animationJsonObject.has("loop")){
                loop = animationJsonObject.get("loop").getAsBoolean();
            }
            double animationLength = animationJsonObject.get("animation_length").getAsDouble();
            AnimationTrack animation = new AnimationTrack(loop,animationLength);
            JsonObject bonesJsonObject = animationJsonObject.get("bones").getAsJsonObject();
            for (Map.Entry<String, JsonElement> bonesEntry : bonesJsonObject.entrySet()) {
                String boneName = bonesEntry.getKey();
                JsonObject boneJsonObject = bonesEntry.getValue().getAsJsonObject();
                AnimationBone bone = new AnimationBone(boneName,
                        getAnimationReference(boneJsonObject,"rotation",true),
                        getAnimationReference(boneJsonObject,"scale",false),
                        getAnimationReference(boneJsonObject,"position",false));
                animation.addBone(bone);
            }
            animations.put(animationName,animation);
        }
    }


    @Override
    public String toString() {
        return "AnimationFile{" +
                "animations=" + animations +
                '}';
    }

    public Map<String, AnimationTrack> getAnimations() {
        return animations;
    }


    private AnimationFrame[] getAnimationReference(JsonObject bone, String name, boolean rad){
        if (bone.has(name)) {
            List<AnimationFrame> result = new ArrayList<>();
            if (bone.get(name).isJsonArray()) {
                JsonArray vectorArray = bone.get(name).getAsJsonArray();
                result.add(new AnimationFrame(0.0f, castVector(vectorArray, rad)));
                return result.toArray(new AnimationFrame[result.size()]);
            }else
            if (bone.get(name).isJsonPrimitive()){
                float f = bone.get(name).getAsFloat();
                result.add(new AnimationFrame(0.0f,new Vector3(f,f,f)));
                return result.toArray(new AnimationFrame[result.size()]);
            }

            JsonObject referenceJson = bone.get(name).getAsJsonObject();
            for (Map.Entry<String, JsonElement> timeCodes : referenceJson.entrySet()) {
                double time = Double.parseDouble(timeCodes.getKey());
                if (timeCodes.getValue().isJsonArray()) {
                    JsonArray vectorArray = timeCodes.getValue().getAsJsonArray();
                    result.add(new AnimationFrame((float) time, castVector(vectorArray, rad)));
                }else
                if (timeCodes.getValue().isJsonPrimitive()){
                    float f = timeCodes.getValue().getAsFloat();
                    result.add(new AnimationFrame((float) time,new Vector3(f,f,f)));
                }
            }
            return result.toArray(new AnimationFrame[result.size()]);
        }
        return null;
    }

    private Vector3 castVector(JsonArray vectorArray, boolean rad){
        double x = vectorArray.get(0).getAsDouble();
        double y = vectorArray.get(1).getAsDouble();
        double z = vectorArray.get(2).getAsDouble();
        if (rad){
            x = Math.toRadians(x);
            y = Math.toRadians(y);
            z = Math.toRadians(z);
        }
        return new Vector3((float)x,(float)y,(float)z);
    }
}
