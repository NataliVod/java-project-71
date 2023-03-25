package hexlet.code.formatters;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String getPlainString(List<Map<String, Object>> diff) throws Exception {
        StringBuilder result =  new StringBuilder();

        for (var map : diff) {
            String key = (String) map.get("key");

            switch (map.get("changeType").toString()) {
                case "deleted" ->
                        result.append("Property '").append(key).append("' was removed").append("\n");

                case "added" ->
                        result.append("Property '").append(key).append("' was added with value: ")
                                .append(getPlainValue(map.get("newValue"))).append("\n");

                case "changed" ->
                        result.append("Property '").append(key).append("' was updated. From ")
                                .append(getPlainValue(map.get("oldValue"))).append(" to ")
                                .append(getPlainValue(map.get("newValue"))).append("\n");

                case "unchanged" -> {
                }
                default -> throw new Exception("wrong change type" + key);
            }

        }

        return result.toString().trim();
    }

    private static String getPlainValue(Object value) {
        String result;
        if (value instanceof String) {
            result = "'" + value + "'";
        } else if ((value instanceof Collection<?>)
                || (value instanceof Arrays)
                || (value instanceof Map<?, ?>)) {
            result = "[complex value]";
        } else {
            result = value + "";
        }
        return result;
    }
}

