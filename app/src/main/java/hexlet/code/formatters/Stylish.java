package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String getStylishString(List<Map<String, Object>> diff) throws Exception {
        StringBuilder result =  new StringBuilder();
        result.append("{\n");

        for (var map:diff) {
            String key = (String) map.get("key");

            switch (map.get("changeType").toString()) {
                case "deleted" -> result.append("  - ").append(key).append(": ")
                        .append(map.get("oldValue"));

                case "added" -> result.append("  + ").append(key).append(": ")
                        .append(map.get("newValue"));

                case "changed" -> result.append("  - ").append(key).append(": ")
                        .append(map.get("oldValue")).append("\n")
                        .append("  + ").append(key).append(": ")
                        .append(map.get("newValue"));

                case "unchanged" -> result.append("    ").append(key).append(": ")
                        .append(map.get("oldValue"));

                default -> throw new Exception("wrong change type" + key);
            }
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }

}
