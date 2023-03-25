package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    private static final String PLAIN = "plain";
    private static final String STYLISH = "stylish";

    public static String getFormatedString (List<Map<String, Object>> diff, String format) throws Exception {
        String result;
        result = switch (format) {
            case (PLAIN) -> Plain.getPlainString(diff);
            case (STYLISH) -> Stylish.getStylishString(diff);
            default -> throw new Exception("Unknown format" + format);
        };
        return result;
    }
}
