package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String stylish (List<Map<String, Object>> diff) throws Exception {
        String result =  "{\n";
        for (var map:diff) {
            String key = (String) map.get("key");
            switch (map.get("changeType").toString()) {
                case "deleted" -> result += "  - " + key + ": " + map.get("oldValue") + "\n";
                case "added" -> result += "  + " + key + ": " + map.get("newValue") + "\n";
                case "changed" -> result += "  - " + key + ": " + map.get("oldValue") + "\n"
                        + "  + " + key + ": " + map.get("newValue") + "\n";
                case "unchanged" -> result += "    " + key + ": " + map.get("oldValue") + "\n";
                default -> throw new Exception("");
            }
        }
        result += "}";

        return result;
    }

}
