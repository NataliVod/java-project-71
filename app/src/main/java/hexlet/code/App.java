package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second file")
    private String filePath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: stylish]")
    private String format;

    @Override
    public  Integer call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2, format));
        return 1;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
