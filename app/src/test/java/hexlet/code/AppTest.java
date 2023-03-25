package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    private final String stylishExpected = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";
    private final String plainExpected = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

    private final String jsonExpected = "{\"key\":\"chars1\""
            + ",\"oldValue\":[\"a\",\"b\",\"c\"],\"changeType\":\"unchanged\"}\n"
            + "{\"key\":\"chars2\",\"oldValue\":[\"d\",\"e\",\"f\"]"
            + ",\"newValue\":false,\"changeType\":\"changed\"}\n"
            + "{\"key\":\"checked\",\"oldValue\":false,\"newValue\":true,\"changeType\":\"changed\"}\n"
            + "{\"key\":\"default\",\"oldValue\":null,\"newValue\":[\"value1\""
            + ",\"value2\"],\"changeType\":\"changed\"}\n"
            + "{\"key\":\"id\",\"oldValue\":45,\"newValue\":null,\"changeType\":\"changed\"}\n"
            + "{\"key\":\"key1\",\"oldValue\":\"value1\",\"changeType\":\"deleted\"}\n"
            + "{\"key\":\"key2\",\"newValue\":\"value2\",\"changeType\":\"added\"}\n"
            + "{\"key\":\"numbers1\",\"oldValue\":[1,2,3,4],\"changeType\":\"unchanged\"}\n"
            + "{\"key\":\"numbers2\",\"oldValue\":[2,3,4,5]"
            + ",\"newValue\":[22,33,44,55],\"changeType\":\"changed\"}\n"
            + "{\"key\":\"numbers3\",\"oldValue\":[3,4,5],\"changeType\":\"deleted\"}\n"
            + "{\"key\":\"numbers4\",\"newValue\":[4,5,6],\"changeType\":\"added\"}\n"
            + "{\"key\":\"obj1\",\"newValue\":{\"nestedKey\":\"value\""
            + ",\"isNested\":true},\"changeType\":\"added\"}\n"
            + "{\"key\":\"setting1\",\"oldValue\":\"Some value\""
            + ",\"newValue\":\"Another value\",\"changeType\":\"changed\"}\n"
            + "{\"key\":\"setting2\",\"oldValue\":200,\"newValue\":300,\"changeType\":\"changed\"}\n"
            + "{\"key\":\"setting3\",\"oldValue\":true,\"newValue\":\"none\",\"changeType\":\"changed\"}";
    @Test
    void testJsonDifferGenerateStylish() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testYmlDifferGenerateStylish() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testJsonDifferGeneratePlain() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result = Differ.generate(path1, path2, "plain");
        assertThat(result).isEqualTo(plainExpected);
    }

    @Test
    void testYmlDifferGeneratePlain() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String result = Differ.generate(path1, path2, "plain");
        assertThat(result).isEqualTo(plainExpected);
    }

    @Test
    void testJsonDifferGenerateJson() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result = Differ.generate(path1, path2, "json");
        assertThat(result).isEqualTo(jsonExpected);
    }

    @Test
    void testYmlDifferGenerateJson() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String result = Differ.generate(path1, path2, "json");
        assertThat(result).isEqualTo(jsonExpected);
    }

}
