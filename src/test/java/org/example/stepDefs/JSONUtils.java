package org.example.stepDefs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class JSONUtils {
    public static List<Map<String, String>> readJSON(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(Paths.get(filePath).toFile(), new TypeReference<List<Map<String, String>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


