package hexlet.code;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String format = "stylish";
        Map<String, Object> oldMap = getData(filePath1);
        Map<String, Object> newMap = getData(filePath2);
        List<Map<String, Object>> diff = genDiff(oldMap, newMap);
        return Formatter.getFormatedString(diff, format);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> oldMap = getData(filePath1);
        Map<String, Object> newMap = getData(filePath2);
        List<Map<String, Object>> diff = genDiff(oldMap, newMap);
        return Formatter.getFormatedString(diff, format);

    }

    public static Map<String, Object> getData(String filePath) throws Exception {
        String fileType = filePath.substring(filePath.indexOf(".") + 1);
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new Exception("wrong file path");
        }
        String content = Files.readString(path);
        return Parser.parse(content, fileType);
    }

    private static List<Map<String, Object>> genDiff(Map<String, Object> oldMap, Map<String, Object> newMap) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> generalKeySet = new TreeSet<>();

        Set<String> oldKeySet = oldMap.keySet();
        Set<String> newKeySet = newMap.keySet();

        generalKeySet.addAll(oldKeySet);
        generalKeySet.addAll(newKeySet);

        for (String key : generalKeySet) {
            Map<String, Object> diffMap = new LinkedHashMap<>();
            Object oldValue = oldMap.get(key);
            Object newValue = newMap.get(key);
            diffMap.put("key", key);

            if ((oldKeySet.contains(key)) && !(newKeySet.contains(key))) {
                diffMap.put("oldValue", oldValue);
                diffMap.put("changeType", "deleted");

            } else if (!(oldKeySet.contains(key)) && (newKeySet.contains(key))) {
                diffMap.put("newValue", newValue);
                diffMap.put("changeType", "added");

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key))
                    && !(Objects.deepEquals(oldValue, newValue))) {
                diffMap.put("oldValue", oldValue);
                diffMap.put("newValue", newValue);
                diffMap.put("changeType", "changed");

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key))
                    && (Objects.deepEquals(oldValue, newValue)))  {
                diffMap.put("oldValue", oldValue);
                diffMap.put("changeType", "unchanged");
            }
            result.add(diffMap);
        }

        return result;
    }

}
