package hexlet.code;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        String format = "stylish";
        return generate(filePath1, filePath2, format);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> oldData = getData(filePath1);
        Map<String, Object> newData = getData(filePath2);
        Map<String, Diffs> diff = DiffUtils.genDiff(oldData, newData);
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
}
