package hexlet.code;

import java.util.*;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);
        List<Map<String, Object>> diff = genDiff(map1, map2);
        return Formatter.stylish(diff);
    }

    public static Map<String, Object> getData(String filePath) throws Exception {
        String fileType = filePath.substring(filePath.indexOf(".")+1);
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

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key)) && !(Objects.deepEquals(oldValue,newValue))) {
                diffMap.put("oldValue", oldValue);
                diffMap.put("newValue", newValue);
                diffMap.put("changeType", "changed");

            } else if ((oldKeySet.contains(key) && newKeySet.contains(key)) && (Objects.deepEquals(oldValue,newValue)))  {
                diffMap.put("oldValue", oldValue);
                diffMap.put("changeType", "unchanged");
            }
            result.add(diffMap);
        }

        return result;
    }

}
