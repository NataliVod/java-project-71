package hexlet.code.formatters;

import hexlet.code.Diffs;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Plain {
    public static String getPlainString(Map<String, Diffs> diff) throws Exception {
        StringBuilder result =  new StringBuilder();

        for (var pair : diff.entrySet()) {
            Diffs changes = pair.getValue();
            var key = pair.getKey();
            var changeType = changes.getChangeType();

            switch (changeType) {
                case "deleted" ->
                        result.append("Property '").append(key).append("' was removed").append("\n");

                case "added" ->
                        result.append("Property '").append(key).append("' was added with value: ")
                                .append(getPlainValue(changes.getNewValue())).append("\n");

                case "changed" ->
                        result.append("Property '").append(key).append("' was updated. From ")
                                .append(getPlainValue(changes.getOldValue())).append(" to ")
                                .append(getPlainValue(changes.getNewValue())).append("\n");

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

