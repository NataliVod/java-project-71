package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diffs;

import java.util.Map;


public class Json {
    public static String getJsonString(Map<String, Diffs> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();
        for (var pair:diff.entrySet()) {
            result.append(mapper.writeValueAsString(pair)).append("\n");
        }
        return result.toString().trim();
    }

}
