package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


import java.util.Map;

public class Parser {
    public static Map<String, String> parse(String content, String fileType) throws Exception {

        ObjectMapper mapper;
        switch (fileType) {
            case ("json") ->
                    mapper = new ObjectMapper();
            case ("yml") ->
                    mapper = new YAMLMapper();

            default -> throw new IllegalStateException("Unexpected type: " + fileType);
        }
        Map<String, String> data = mapper.readValue(content, new TypeReference<Map<String, String>>() { });
        return data;

    }


}
