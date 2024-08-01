package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Запуск приложения
 */
public class RunApplication {

    /**
     * Запускает приложение
     * @param options - переменные среды (опции)
     * @throws IOException Если возникла ошибка во время записи
     */
    public void run(List<String> options) throws IOException {

        String pathToSaveFile = "";
        String prefixOutFiles = "";

        Scanner scanner;

        StatisticHandler statisticHandler = new StatisticHandler();
        OptionsHandler optionsHandler = new OptionsHandler();
        FileHandler fileHandler = new FileHandler();

        List<String> listFileNames = optionsHandler.getFileNames(options);

        for (String item : listFileNames) {

            List<Long> listOfLong = new ArrayList<>();
            List<String> listOfString = new ArrayList<>();
            List<Float> listOfFloat = new ArrayList<>();

            try {
                scanner = new Scanner(new File(item));

            } catch (FileNotFoundException e) {
                System.out.println("Не найден файл " + item + " для считывания!");
                return;
            }

            if (options.contains("-o") || options.contains("-о")) {
                pathToSaveFile = optionsHandler.getPathForResults(options);
            }

            if (options.contains("-p") || options.contains("-р")) {
                prefixOutFiles = optionsHandler.getPrefixForResults(options);
            }

            if (!options.contains("-а") && !options.contains("-a")) {
                fileHandler.deleteOldFiles(prefixOutFiles);
            }

            try {

                while (scanner.hasNextLine()) {
                    String lineInFile = scanner.nextLine();

                    try {
                        Long lineLong = Long.valueOf(lineInFile);
                        listOfLong.add(lineLong);
                        continue;
                    } catch (NumberFormatException e) {
                    }

                    try {
                        Float lineFloat = Float.valueOf(lineInFile);
                        listOfFloat.add(lineFloat);
                        continue;
                    } catch (NumberFormatException e) {
                    }

                    listOfString.add(lineInFile);
                }
                scanner.close();

                if (listOfLong.size() > 0) {
                    statisticHandler.printLongStatistic(options, pathToSaveFile, prefixOutFiles, listOfLong);
                }

                if (listOfFloat.size() > 0) {
                    statisticHandler.printFloatStatistic(options, pathToSaveFile, prefixOutFiles, listOfFloat);
                }

                if (listOfString.size() > 0) {
                    statisticHandler.printStringStatistic(options, pathToSaveFile, prefixOutFiles, listOfString);
                }

            } catch (IOException e) {
                System.out.println("Возникла ошибка во время записи, проверьте данные.");
            }
        }
    }
}
