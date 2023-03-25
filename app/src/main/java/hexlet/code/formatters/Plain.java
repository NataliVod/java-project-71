package hexlet.code.formatters;


import java.util.*;

public class Plain {
    public static String getPlainString(List<Map<String, Object>> diff) throws Exception {
        String result = "";

        for (var map : diff) {
            String key = (String) map.get("key");

            switch (map.get("changeType").toString()) {
                case "deleted" -> result += "Property '" + key + "' was removed" + "\n";
                case "added" ->
                        result += "Property '" + key + "' was added with value: " + getPlainValue(map.get("newValue")) + "\n";
                case "changed" -> result += "Property '" + key + "' was updated. From " + getPlainValue(map.get("oldValue"))
                        + " to " + getPlainValue(map.get("newValue")) + "\n";
                case "unchanged" -> {
                }
                default -> throw new Exception("wrong change type" + key);
            }

        }

        return result.trim();
    }

    private static String getPlainValue(Object value) {
        String result;
        if (value instanceof String) {
            result = "'" + value + "'";
        } else if ((value instanceof Collection<?>) ||
                (value instanceof Arrays) ||
                (value instanceof Map<?,?>)) {
            result = "[complex value]";
        } else
        result = value + "";
        return result;
    }
}

