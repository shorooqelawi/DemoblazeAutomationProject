package Utilities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONReader {


    private static ObjectMapper mapper = new ObjectMapper();


    public static String getValue(String fileName, String key) {
        try {
            JsonNode root = mapper.readTree(new File("src/main/resources/" + fileName));
            return root.get(key).asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getNestedValue(String fileName, String parentKey, String childKey){
        try {
            JsonNode root = mapper.readTree(new File("src/main/resources/" + fileName));
            return root.get(parentKey).get(childKey).asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
