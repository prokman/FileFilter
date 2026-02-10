import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import service.FileFilterService;
import service.StatService;
import utils.CliParam;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("=========START PROGRAM=========");
        CliParam cliParam = new CliParam();
        CmdLineParser parser = new CmdLineParser(cliParam);
        StatService statService = new StatService();
        FileFilterService filterService = new FileFilterService(statService);
        try {
            parser.parseArgument(args);
            if (cliParam.getInputFiles().isEmpty()) {
                System.err.println("Ошибка: не указаны входные файлы");
                parser.printUsage(System.err);
                return;
            }
            System.out.println();
            filterService.processFiles(cliParam);
            System.out.println();
            if (cliParam.isShortStat()) System.out.println(statService.showShrotStat());
            if (cliParam.isFullStat()) System.out.println(statService.showFullStat());
        } catch (CmdLineException e) {
            System.err.println("Ошибка в параметрах: " + e.getMessage());
            parser.printUsage(System.err);
        }
        System.out.println();
        System.out.println("=========END PROGRAM==========");
    }
}