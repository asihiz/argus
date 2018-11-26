package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by asih on 7/10/2017.
 */
public class JsonUtils {

    protected static final Gson GSON = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();

    private final static Logger logger = Logger.getLogger(JsonUtils.class);

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (Throwable t) {
            return null;
        }
    }

    public static JSONObject toJsonObject(String fileName) {

        return new JSONObject(Objects.requireNonNull(toJsonString(fileName)));

    }

    public static String toJsonString(String fileName) {

        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ioe){
            GeneralUtils.handleError("Error reading Gson file ", ioe);
            return null;
        }

    }

    public static void printGsonData(JSONObject gson) {

        for (Object key : gson.keySet()) {
            String keyStr = (String) key;
            Object keyvalue = gson.get(keyStr);
            logger.info("key: "+ keyStr + " value: " + keyvalue + "/n");
            if (keyvalue instanceof JSONObject)
                // polymorphic call
                printGsonData((JSONObject) keyvalue);
        }

    }

}


