package top.s0uths1de.betterexperience.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ModConfig {

    private static final String CONFIG_FILE_NAME = "better_experience.json";
    private static JSONObject config;
    private static final Logger logger = LoggerFactory.getLogger(Exception.class);

    public static void load() {
        File configFile = new File(CONFIG_FILE_NAME);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                config = new JSONObject();
                setInt("maxSwitches",2);
                save();
            } catch (IOException e) {
               logger.error(String.valueOf(e));
            }
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
                byte[] buffer = new byte[(int) configFile.length()];
                fileInputStream.read(buffer);
                String data = new String(buffer, StandardCharsets.UTF_8);
                config = new JSONObject(data);
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        }
    }

    // 保存配置文件
    public static void save() {
        File configFile = new File(CONFIG_FILE_NAME);
        try (FileWriter fileWriter = new FileWriter(configFile)) {
            fileWriter.write(config.toString(4)); // 使用4个空格进行缩进，便于阅读
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static void setBoolean(String key, boolean value) {
        config.put(key, value);
        save();
    }

    public static void setInt(String key, int value) {
        config.put(key, value);
        save();
    }

    public static void setString(String key, String value) {
        config.put(key, value);
        save();
    }
}
