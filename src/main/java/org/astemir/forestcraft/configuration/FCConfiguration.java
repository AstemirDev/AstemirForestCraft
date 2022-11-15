package org.astemir.forestcraft.configuration;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.astemir.forestcraft.ForestCraft;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class FCConfiguration {

    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final FCConfiguration INSTANCE;
    private static final Path CONFIG_PATH = Paths.get("config", ForestCraft.MOD_ID + ".toml");

    public static Map<FCConfigurationValues.ConfigValue,ForgeConfigSpec.ConfigValue> MAP = new HashMap<>();

    static {
        Pair<FCConfiguration, ForgeConfigSpec> specPair =
                new ForgeConfigSpec.Builder().configure(FCConfiguration::new);
        INSTANCE = specPair.getLeft();
        COMMON_CONFIG = specPair.getRight();
        CommentedFileConfig config = CommentedFileConfig.builder(CONFIG_PATH)
                .sync()
                .autoreload()
                .writingMode(WritingMode.REPLACE)
                .build();
        config.load();
        config.save();
        COMMON_CONFIG.setConfig(config);
        MAP.forEach((a,b)->{
            a.setValue(b.get());
        });
    }

    private ForgeConfigSpec.IntValue newInt(ForgeConfigSpec.Builder builder, FCConfigurationValues.ConfigInteger oldInteger){
        ForgeConfigSpec.IntValue intValue = builder
                .comment(oldInteger.getComment())
                .translation(oldInteger.getPath())
                .defineInRange(oldInteger.getPath(),oldInteger.getValue(),oldInteger.getMin(),oldInteger.getMax());
        MAP.put(oldInteger,intValue);
        return intValue;
    }

    private ForgeConfigSpec.BooleanValue newBool(ForgeConfigSpec.Builder builder, FCConfigurationValues.ConfigBoolean oldBool){
        ForgeConfigSpec.BooleanValue booleanValue = builder
                .comment(oldBool.getComment())
                .translation(oldBool.getPath())
                .define(oldBool.getPath(),oldBool.getValue().booleanValue());
        MAP.put(oldBool,booleanValue);
        return booleanValue;
    }

    private FCConfiguration(ForgeConfigSpec.Builder builder) {
        builder.push("ForestCraft Configuration");
        for (FCConfigurationValues.ConfigBoolean booleanValue : FCConfigurationValues.getBooleanValues()) {
            newBool(builder,booleanValue);
        }
        for (FCConfigurationValues.ConfigInteger integerValue : FCConfigurationValues.getIntegerValues()) {
            newInt(builder,integerValue);
        }
        builder.pop();
    }

    public static void set(FCConfigurationValues.ConfigValue configValue, Object value){
        MAP.get(configValue).set(value);
        configValue.setValue(value);
    }


    public static void save(){
        COMMON_CONFIG.save();
    }
}
