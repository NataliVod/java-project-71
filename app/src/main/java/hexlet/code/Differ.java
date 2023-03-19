package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> map1 = getData(filePath1);
        Map<String, String> map2 = getData(filePath2);
        List<Map<String, String>> diff = genDiff(map1, map2);
        StringBuilder result =  new StringBuilder();
        result.append("{\n");
        for (var map:diff) {
            switch (map.get("changeType")) {
                case "deleted" -> result.append("  - ").append(map.get("key")).append(": ")
                        .append(map.get("oldValue")).append("\n");
                case "added" -> result.append("  + ").append(map.get("key")).append(": ")
                        .append(map.get("newValue")).append("\n");
                case "changed" -> result.append("  - ").append(map.get("key")).append(": ")
                        .append(map.get("oldValue")).append("\n")
                        .append("  + ").append(map.get("key")).append(": ")
                        .append(map.get("newValue")).append("\n");
                case "unchanged" -> result.append("    ").append(map.get("key")).append(": ")
                        .append(map.get("oldValue")).append("\n");
                default -> throw new Exception("");
            }
        }
        result.append("}");

        return result.toString();
    }

    private static Map<String, String> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String json = Files.readString(path);
        return parse(json);
    }
    private static Map<String, String> parse(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = mapper.readValue(json, new TypeReference<Map<String, String>>() { });
        return data;
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
