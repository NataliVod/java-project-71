package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.Diffs.ADDED;
import static hexlet.code.Diffs.CHANGED;
import static hexlet.code.Diffs.DELETED;
import static hexlet.code.Diffs.UNCHANGED;

public class DiffUtils {

    public static Map<String, Diffs> genDiff(Map<String, Object> oldData, Map<String, Object> newData) throws Exception {
        Map<String, Diffs> result = new TreeMap<>();
        Set<String> overallKeys = new TreeSet<>();

        Set<String> oldKeySet = oldData.keySet();
        Set<String> newKeySet = newData.keySet();

        overallKeys.addAll(oldKeySet);
        overallKeys.addAll(newKeySet);

        for (String key : overallKeys) {
            Object oldValue = oldData.get(key);
            Object newValue = newData.get(key);

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
