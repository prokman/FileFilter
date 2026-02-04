package utils;


import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;


public class CliParam {
    @Option(name = "-s", aliases = "--shortStat", usage = "Показать краткую статистику", forbids = {"-f"})
    private boolean shortStat = false;

    @Option(name = "-f", aliases = "--fullStat", usage = "Показать полную статистику", forbids = {"-s"})
    private boolean fullStat = false;

    @Option(name = "-a", aliases = "--append", usage = "Дополнять существующие файлы с результатами")
    private boolean append = false;

    @Option(name = "-o", aliases = "--outputPath", usage = "Путь для результатов", metaVar = "OUTPUT_DIR")
    private String outputPath = ".";

    @Option(name = "-p", aliases = "--prefix", usage = "Префикс имен выходных файлов", metaVar = "PREFIX")
    private String prefix = "";

    @Argument(
            usage = "Входные файлы в текущей папке",
            metaVar = "INPUT_FILES",
            required = true
    )
    private List<String> inputFiles = new ArrayList<>();


    public boolean isShortStat() {
        return shortStat;
    }

    public boolean isFullStat() {
        return fullStat;
    }

    public boolean isAppend() {
        return append;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
