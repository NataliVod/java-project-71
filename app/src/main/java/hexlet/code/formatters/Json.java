package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String getJsonString(List<Map<String, Object>> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();
        for (var map:diff) {
            result.append(mapper.writeValueAsString(map)).append("\n");
        }
        return result.toString().trim();
    }

}
