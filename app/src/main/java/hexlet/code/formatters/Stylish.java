package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String getStylishString (List<Map<String, Object>> diff) throws Exception {
        String result =  "{\n";

        for (var map:diff) {
            String key = (String) map.get("key");

            switch (map.get("changeType").toString()) {
                case "deleted" -> result += "  - " + key + ": " + map.get("oldValue");
                case "added" -> result += "  + " + key + ": " + map.get("newValue");
                case "changed" -> result += "  - " + key + ": " + map.get("oldValue") + "\n"
                        + "  + " + key + ": " + map.get("newValue");
                case "unchanged" -> result += "    " + key + ": " + map.get("oldValue");
                default -> throw new Exception("wrong change type" + key);
            }
            result += "\n";
        }
        result += "}";

        return result;
    }
}
