package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    private final String stylishExpected;
    private final String plainExpected;
    private final String jsonExpected;

    {
        try {
            String stylishPathString = "src/test/resources/fixtures/StylishExpected.txt";
            stylishExpected = getExpectedData(stylishPathString);
            String jsonPathString = "src/test/resources/fixtures/JsonExpected.txt";
            jsonExpected = getExpectedData(jsonPathString);
            String plainPathString = "src/test/resources/fixtures/PlainExpected.txt";
            plainExpected = getExpectedData(plainPathString);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getExpectedData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new Exception("wrong file path");
        }
        return  Files.readString(path);
    }

    @Test
    void testJsonDifferGenerateDefault() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.json";
        String path2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testYmlDifferGenerateDefault() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.yml";
        String path2 = "src/test/resources/fixtures/file2.yml";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testJsonDifferGenerateStylish() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.json";
        String path2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(path1, path2, "stylish");
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testYmlDifferGenerateStylish() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.yml";
        String path2 = "src/test/resources/fixtures/file2.yml";
        String result = Differ.generate(path1, path2, "stylish");
        assertThat(result).isEqualTo(stylishExpected);
    }

    @Test
    void testJsonDifferGeneratePlain() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.json";
        String path2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(path1, path2, "plain");
        assertThat(result).isEqualTo(plainExpected);
    }

    @Test
    void testYmlDifferGeneratePlain() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.yml";
        String path2 = "src/test/resources/fixtures/file2.yml";
        String result = Differ.generate(path1, path2, "plain");
        assertThat(result).isEqualTo(plainExpected);
    }

    @Test
    void testJsonDifferGenerateJson() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.json";
        String path2 = "src/test/resources/fixtures/file2.json";
        String result = Differ.generate(path1, path2, "json");
        assertThat(result).isEqualTo(jsonExpected);
    }

    @Test
    void testYmlDifferGenerateJson() throws Exception {
        String path1 = "src/test/resources/fixtures/file1.yml";
        String path2 = "src/test/resources/fixtures/file2.yml";
        String result = Differ.generate(path1, path2, "json");
        assertThat(result).isEqualTo(jsonExpected);
    }

}
