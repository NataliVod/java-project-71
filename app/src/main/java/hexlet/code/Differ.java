package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> map1 = getData(filePath1);
        Map<String, String> map2 = getData(filePath2);
        List<Map<String, String>> diff = genDiff(map1, map2);
        String result =  "{\n";
        for (var map:diff) {
            switch (map.get("changeType")) {
                case "deleted" -> result += "  - " + map.get("key") + ": " + map.get("oldValue") + "\n";
                case "added" -> result += "  + " + map.get("key") + ": " + map.get("newValue") + "\n";
                case "changed" -> result += "  - " + map.get("key") + ": " + map.get("oldValue") + "\n"
                                          + "  + " + map.get("key") + ": " + map.get("newValue") + "\n";
                case "unchanged" -> result += "    " + map.get("key") + ": " + map.get("oldValue") + "\n";
                default -> throw new Exception("");
            }
        }
        result += "}";

        return result;
    }

    private static Map<String, String> getData(String filePath) throws Exception {
        String fileType = filePath.substring(filePath.indexOf(".")+1);
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String content = Files.readString(path);
        return Parser.parse(content, fileType);
    }

    private static List<Map<String, String>> genDiff(Map<String, String> map1, Map<String, String> map2) {
        List<Map<String, String>> result = new ArrayList<>();
        Set<String> generalKeySet = new TreeSet<>();
        Set<String> keySet1 = map1.keySet();
        Set<String> keySet2 = map2.keySet();
        generalKeySet.addAll(keySet1);
        generalKeySet.addAll(keySet2);
        for (String key : generalKeySet) {
            Map<String, String> diffMap = new LinkedHashMap<>();
            diffMap.put("key", key);
            if ((keySet1.contains(key)) && !(keySet2.contains(key))) {
                diffMap.put("oldValue", map1.get(key));
                diffMap.put("changeType", "deleted");
            } else if (!(keySet1.contains(key)) && (keySet2.contains(key))) {
                diffMap.put("newValue", map2.get(key));
                diffMap.put("changeType", "added");
            } else if ((keySet1.contains(key) && keySet2.contains(key)) && !(map1.get(key).equals(map2.get(key)))) {
                diffMap.put("oldValue", map1.get(key));
                diffMap.put("newValue", map2.get(key));
                diffMap.put("changeType", "changed");
            } else if ((keySet1.contains(key) && keySet2.contains(key)) && (map1.get(key).equals(map2.get(key))))  {
                diffMap.put("oldValue", map1.get(key));
                diffMap.put("changeType", "unchanged");
            }
            result.add(diffMap);
        }

        return result;
    }


}
