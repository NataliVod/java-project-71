package hexlet.code.formatters;

import hexlet.code.Diffs;
import java.util.Map;
import static hexlet.code.Diffs.ADDED;
import static hexlet.code.Diffs.CHANGED;
import static hexlet.code.Diffs.DELETED;
import static hexlet.code.Diffs.UNCHANGED;

public class Stylish {
    public static String getStylishString(Map<String, Diffs> diff) throws Exception {
        StringBuilder result =  new StringBuilder();
        result.append("{\n");

        for (var pair : diff.entrySet()) {
            Diffs changes = pair.getValue();
            var key = pair.getKey();
            var changeType = changes.getChangeType();

            switch (changeType) {
                case DELETED -> result.append("  - ").append(key).append(": ")
                        .append(changes.getOldValue());

                case ADDED -> result.append("  + ").append(key).append(": ")
                        .append(changes.getNewValue());

                case CHANGED -> result.append("  - ").append(key).append(": ")
                        .append(changes.getOldValue()).append("\n")
                        .append("  + ").append(key).append(": ")
                        .append(changes.getNewValue());

                case UNCHANGED -> result.append("    ").append(key).append(": ")
                        .append(changes.getOldValue());

                default -> throw new Exception("wrong change type" + key);
            }
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }

}
