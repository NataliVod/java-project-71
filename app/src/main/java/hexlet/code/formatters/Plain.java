package hexlet.code.formatters;

import hexlet.code.Diffs;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import static hexlet.code.Diffs.ADDED;
import static hexlet.code.Diffs.CHANGED;
import static hexlet.code.Diffs.DELETED;
import static hexlet.code.Diffs.UNCHANGED;

public class Plain {
    public static String getPlainString(Map<String, Diffs> diff) throws Exception {
        StringBuilder result =  new StringBuilder();

        for (var pair : diff.entrySet()) {
            Diffs changes = pair.getValue();
            var key = pair.getKey();
            var changeType = changes.getChangeType();

            switch (changeType) {
                case DELETED ->
                        result.append("Property '").append(key).append("' was removed").append("\n");

                case ADDED ->
                        result.append("Property '").append(key).append("' was added with value: ")
                                .append(getPlainValue(changes.getNewValue())).append("\n");

                case CHANGED ->
                        result.append("Property '").append(key).append("' was updated. From ")
                                .append(getPlainValue(changes.getOldValue())).append(" to ")
                                .append(getPlainValue(changes.getNewValue())).append("\n");

                case UNCHANGED -> {
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

