package org.astemir.forestcraft.configuration;

public class ConfigUtils {

    public static boolean isEnabledInConfig(FCConfigurationValues.ConfigBoolean bool){
        return bool.getValue();
    }
}
