package org.astemir.api.io;

import com.google.common.base.Charsets;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;
import org.astemir.api.AstemirAPI;
import org.astemir.forestcraft.ForestCraft;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class LocalizedTextFile {

    private String text;

    public LocalizedTextFile(String path,String folder,String... languageCodes) {
        String langCode = Minecraft.getInstance().getLanguageManager().getCurrentLanguage().getCode();
        if (!Arrays.asList(languageCodes).contains(langCode)){
            langCode = "en_us";
        }
        InputStream stream = AstemirAPI.class.getClassLoader().getResourceAsStream(path+"/"+langCode+"/"+folder);
        text = readInputStream(stream);
    }

    private String readInputStream(InputStream stream){
        String text = "Empty text file";
        if (stream != null) {
            try {
                return String.join("", IOUtils.readLines(stream, Charsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    public String getText() {
        return text;
    }
}
