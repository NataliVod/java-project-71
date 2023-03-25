package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;


import java.util.Map;

public class Parser {
    private static final String JSON = "json";
    private static final String YAML = "yml";

    public static Map<String, Object> parse(String content, String fileType) throws Exception {

        ObjectMapper mapper;
        switch (fileType) {
            case (JSON) -> {
                mapper = new ObjectMapper();
                return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            }
            case (YAML) -> {
                mapper = new YAMLMapper();
                return mapper.readValue(content, new TypeReference<Map<String, Object>>() {
                });
            }

            default -> throw new IllegalStateException("Unexpected type: " + fileType);
        }

    }


}
