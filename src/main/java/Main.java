import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import utils.CliParam;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========START PROGRAM=========");
        CliParam cliParam = new CliParam();
        CmdLineParser parser = new CmdLineParser(cliParam);

        try {
            parser.parseArgument(args);
            if (cliParam.getInputFiles().isEmpty()) {
                System.err.println("Ошибка: не указаны входные файлы");
                parser.printUsage(System.err);
                return;
            }
            System.out.println("======Start process FILES======");

        } catch (CmdLineException e) {
            System.err.println("Ошибка в параметрах: "+e.getMessage());
            parser.printUsage(System.err);
        }

        System.out.println("=========END PROGRAM=========");


    }



}
