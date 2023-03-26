package hexlet.code.formatters;

import hexlet.code.Diffs;
import java.util.Map;

public class Stylish {
    public static String getStylishString(Map<String, Diffs> diff) throws Exception {
        StringBuilder result =  new StringBuilder();
        result.append("{\n");

        for (var pair : diff.entrySet()) {
            Diffs changes = pair.getValue();
            var key = pair.getKey();
            var changeType = changes.getChangeType();

            switch (changeType) {
                case "deleted" -> result.append("  - ").append(key).append(": ")
                        .append(changes.getOldValue());

                case "added" -> result.append("  + ").append(key).append(": ")
                        .append(changes.getNewValue());

                case "changed" -> result.append("  - ").append(key).append(": ")
                        .append(changes.getOldValue()).append("\n")
                        .append("  + ").append(key).append(": ")
                        .append(changes.getNewValue());

                case "unchanged" -> result.append("    ").append(key).append(": ")
                        .append(changes.getOldValue());

                default -> throw new Exception("wrong change type" + key);
            }
            result.append("\n");
        }
        result.append("}");

        return result.toString();
    }

}
