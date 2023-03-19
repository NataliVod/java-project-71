package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    String expected = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";
    @Test
    void testDifferGenerateJson() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDifferGenerateYml() throws Exception {
        String path1 = "src/test/resources/file1.yml";
        String path2 = "src/test/resources/file2.yml";
        String result = Differ.generate(path1, path2);
        assertThat(result).isEqualTo(expected);
    }

}
