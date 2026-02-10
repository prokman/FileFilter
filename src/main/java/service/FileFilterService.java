package service;

import model.TypeOfFile;
import org.kohsuke.args4j.CmdLineException;
import utils.CliParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFilterService {
    private long rowCount;
    private static final int rowBufferSize = 2;
    private List<String> integers = new ArrayList<>();
    private List<String> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private StatService statService;

    public FileFilterService(StatService statService) {
        this.statService = statService;
    }

    public void processFiles(CliParam cliParam) throws CmdLineException {
        showCurrentCliParam(cliParam);
        if (cliParam.getInputFiles().isEmpty() || cliParam.getInputFiles() == null) {
            throw new CmdLineException("не указаны входные файлы");
        }
        if (!cliParam.isAppend()) {
            Path outPutFilePath = cliParam.getOutputPathAsPath().normalize()
                    .resolve(cliParam.getPrefix()+TypeOfFile.INTEGER + ".txt");
            deleteFile(outPutFilePath);
            outPutFilePath = cliParam.getOutputPathAsPath().normalize()
                    .resolve(cliParam.getPrefix()+TypeOfFile.FLOAT + ".txt");
            deleteFile(outPutFilePath);
            outPutFilePath = cliParam.getOutputPathAsPath().normalize()
                    .resolve(cliParam.getPrefix()+TypeOfFile.STRING + ".txt");
            deleteFile(outPutFilePath);
        }
        Path inputPath = Paths.get(".").toAbsolutePath();
        for (String fileName : cliParam.getInputFiles()) {
            Path inputFilePath = inputPath.resolve(fileName).normalize();
            processFile(fileName, inputFilePath, cliParam);
        }
    }

    private void processFile(String fileName, Path inputFilePath, CliParam cliParam) throws CmdLineException {
        rowCount = 0;
        if (!Files.isRegularFile(inputFilePath)) {
            throw new CmdLineException("файл \"" + fileName + "\" не существует проверьте его название и расположение");
        }
        try (Stream<String> lines = Files.lines(inputFilePath)) {
            lines.filter(line -> !line.isEmpty())
                    .forEach(line -> processLine(line, cliParam));

            if (!integers.isEmpty()) {
                writeBufferToFile(cliParam, TypeOfFile.INTEGER);
                integers.clear();
            }
            if (!floats.isEmpty()) {
                writeBufferToFile(cliParam, TypeOfFile.FLOAT);
                floats.clear();
            }
            if (!strings.isEmpty()) {
                writeBufferToFile(cliParam, TypeOfFile.STRING);
                strings.clear();
            }
        } catch (IOException e) {
            System.err.println("ошибка при чтении файла \"" + fileName + "\"" + "в строке № " + rowCount);
            System.err.println("Причина: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private void processLine(String line, CliParam cliParam) {
        try {
            int intValue = Integer.parseInt(line);
            integers.add(Integer.toString(intValue));
            rowCount++;
            statService.addStat(intValue, null,null, null, rowCount);
            if (integers.size() % rowBufferSize == 0) {
                writeBufferToFile(cliParam, TypeOfFile.INTEGER);
                integers.clear();
            }
        } catch (NumberFormatException e1) {
            if (line.matches("-?\\d+")) {
                integers.add(line);
                rowCount++;
                statService.addStat(null, line,null, null, rowCount);
                if (integers.size() % rowBufferSize == 0) {
                    writeBufferToFile(cliParam, TypeOfFile.INTEGER);
                    integers.clear();
                }
            } else {
                try {
                    double floatValue = Double.parseDouble(line);
                    floats.add(Double.toString(floatValue));
                    rowCount++;
                    statService.addStat(null, null,floatValue, null, rowCount);
                    if (floats.size() % rowBufferSize == 0) {
                        writeBufferToFile(cliParam, TypeOfFile.FLOAT);
                        floats.clear();
                    }
                } catch (NumberFormatException e2) {
                    strings.add(line);
                    rowCount++;
                    statService.addStat(null, null,null, line.length(), rowCount);
                    if (strings.size() % rowBufferSize == 0) {
                        writeBufferToFile(cliParam, TypeOfFile.STRING);
                        strings.clear();
                    }
                }
            }
        }
    }

    private void writeBufferToFile(CliParam cliParam, TypeOfFile typeOfFile) {

        if (!Files.exists(cliParam.getOutputPathAsPath().normalize())) {
            try {
                Files.createDirectories(cliParam.getOutputPathAsPath().normalize());
            } catch (IOException e) {
                System.err.println("Ошибка создания директории: " + cliParam.getOutputPathAsPath().normalize());
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
        Path outPutFilePath = cliParam.getOutputPathAsPath().normalize()
                .resolve(cliParam.getPrefix() + typeOfFile.toString() + ".txt");

        switch (typeOfFile) {
            case INTEGER:
                writeFile(outPutFilePath, integers);
                break;
            case FLOAT:
                writeFile(outPutFilePath, floats);
                break;
            case STRING:
                writeFile(outPutFilePath, strings);
                break;
        }
    }

    private void writeFile(Path outPutFilePath, List<String> outPutStringList) {
        try {
            Files.write(outPutFilePath, outPutStringList, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Ошибка записи файла: " + outPutFilePath);
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteFile(Path FilePath) {
        try {
            Files.deleteIfExists(FilePath);
        } catch (IOException e) {
            System.err.println("Ошибка удаления файла: " + FilePath);
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void showCurrentCliParam(CliParam cliParam) {
        System.out.println("==Параметры обработки==");
        System.out.println("Режим статистики: " +
                (cliParam.isShortStat() ? "краткая" :
                        cliParam.isFullStat() ? "полная" : "статистика не выводится"));
        System.out.println("Режим записи: " +
                (cliParam.isAppend() ?
                        "существующие файлы c результатами дополняются" :
                        "существующие файлы c результатами перезаписываются"));
        System.out.println("Выходная директория: " + cliParam.getOutputPathAsPath().normalize());
        System.out.println("Префикс файлов: " + cliParam.getPrefix());
        System.out.println("Входные файлы: " + cliParam.getInputFiles().size() + " шт.");
        System.out.println("==Параметры обработки==");
    }
}