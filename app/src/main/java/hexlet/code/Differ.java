package hexlet.code;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Diffs.ADDED;
import static hexlet.code.Diffs.CHANGED;
import static hexlet.code.Diffs.DELETED;
import static hexlet.code.Diffs.UNCHANGED;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String format = "stylish";
        return generate(filePath1, filePath2, format);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> oldMap = getData(filePath1);
        Map<String, Object> newMap = getData(filePath2);
        Map<String, Diffs> diff = genDiff(oldMap, newMap);
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

    private static Map<String, Diffs> genDiff(Map<String, Object> oldMap, Map<String, Object> newMap) throws Exception {
        Map<String, Diffs> result = new TreeMap<>();
        Set<String> overallKeys = new TreeSet<>();

        Set<String> oldKeySet = oldMap.keySet();
        Set<String> newKeySet = newMap.keySet();

        overallKeys.addAll(oldKeySet);
        overallKeys.addAll(newKeySet);

        for (String key : overallKeys) {
            Object oldValue = oldMap.get(key);
            Object newValue = newMap.get(key);

            if ((oldKeySet.contains(key)) && !(newKeySet.contains(key))) {
                result.put(key, new Diffs(DELETED, oldValue));

            } else if (!(oldKeySet.contains(key)) && (newKeySet.contains(key))) {
                result.put(key, new Diffs(ADDED, newValue));

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key))
                    && !(Objects.deepEquals(oldValue, newValue))) {
                result.put(key, new Diffs(CHANGED, oldValue, newValue));

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key))
                    && (Objects.deepEquals(oldValue, newValue)))  {
                result.put(key, new Diffs(UNCHANGED, oldValue));
            }
        }

        return result;
    }

}
