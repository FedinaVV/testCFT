package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String pathToSaveFile = "";
        String prefixOutFiles = "";

        Scanner scanner;

        List<String> options = new ArrayList<>(Arrays.asList(args));

        ArrayList<String> listFileNames = new ArrayList<>();


        if (options.contains("-d")) {

            for (int i = options.indexOf("-d") + 1; i < options.size(); i++) {
                listFileNames.add(options.get(i));
            }
        } else {
            listFileNames.add("file.txt");
        }

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
                pathToSaveFile = getPathForResults(options);
            }

            if (options.contains("-p") || options.contains("-р")) {
                prefixOutFiles = getPrefixForResults(options);
            }

            if (!options.contains("-а") && !options.contains("-a")) {
                deleteOldFiles(prefixOutFiles);
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
                    manageLongFile(options, pathToSaveFile, prefixOutFiles, listOfLong);
                }

                if (listOfFloat.size() > 0) {
                    manageFloatFile(options, pathToSaveFile, prefixOutFiles, listOfFloat);
                }

                if (listOfString.size() > 0) {
                    manageStringFile(options, pathToSaveFile, prefixOutFiles, listOfString);
                }

            } catch (IOException e) {
                System.out.println("Возникла ошибка во время записи, проверьте данные.");
            }
        }
    }

    public static List<String> getLinesCount(String path) {

        List<String> listOfString = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = "";

            while ((line = reader.readLine()) != null) {
                listOfString.add(line);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при считывании строк в файле");
        }
        return listOfString;
    }

    public static String getPathForResults(List<String> options) {

        String pathToSaveFile = "";
        int index = options.indexOf("-o");

        if (index == -1) {
            index = options.indexOf("-о");
        }

        pathToSaveFile = options.get(index + 1);
        if (!((Character) pathToSaveFile.charAt(pathToSaveFile.length() - 1)).equals('/')) {
            pathToSaveFile += "/";
        }
        Path path = Paths.get(pathToSaveFile);

        if (!Files.exists(path)) {
            System.out.println("такой папки нет");
        }

        return pathToSaveFile;
    }

    public static String getPrefixForResults(List<String> options) throws IOException {

        String prefixOutFiles = "";
        int index = options.indexOf("-p");

        if (index == -1) {
            index = options.indexOf("-р");
        }

        prefixOutFiles = options.get(index + 1);

        if (prefixOutFiles.contains("\\") || prefixOutFiles.contains("/") || prefixOutFiles.contains(":") || prefixOutFiles.contains("*")
                || prefixOutFiles.contains("?") || prefixOutFiles.contains("\"") || prefixOutFiles.contains("<") || prefixOutFiles.contains(">")
                || prefixOutFiles.contains("|") || prefixOutFiles.contains("+")) {
            System.out.println("Символы \\ / : * ? \" < > | + не допускаются для использования в именах файлов");
            throw new IOException("Недопустимые символы в имени файлов");
        }

        return prefixOutFiles;
    }

    public static void deleteOldFiles(String prefixOutFiles) {

        File oldFileString = new File(prefixOutFiles + "strings.txt");
        File oldFileInt = new File(prefixOutFiles + "integers.txt");
        File oldFileFloat = new File(prefixOutFiles + "floats.txt");
        oldFileString.delete();
        oldFileInt.delete();
        oldFileFloat.delete();
    }

    public static void manageFloatFile(List<String> options, String pathToSaveFile, String prefixOutFiles, List<Float> listOfFloat) throws IOException {
        int countFl = 0;
        FileWriter writerFloat = new FileWriter(pathToSaveFile + prefixOutFiles + "floats.txt", true);

        for (Float item : listOfFloat) {
            writerFloat.write(item.toString());
            writerFloat.write("\n");
        }

        writerFloat.close();

        if (options.contains("-s")) {
            countFl = getLinesCount(pathToSaveFile + prefixOutFiles + "floats.txt").size();
            System.out.println("Количество строк в файле floats: " + countFl);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countFl = getLinesCount(pathToSaveFile + prefixOutFiles + "floats.txt").size();
                System.out.println("Количество строк в файле floats: " + countFl);
            }

            List<String> arrayOfString = getLinesCount(pathToSaveFile + prefixOutFiles + "floats.txt");
            List<Float> list = new ArrayList<>();

            for (String item : arrayOfString) {
                Float itemOfFloat = Float.valueOf(item);
                list.add(itemOfFloat);
            }

            float min = list.get(0);
            float max = list.get(0);
            float sum = 0;
            float average = 0;

            for (float item : list) {
                if (item < min) {
                    min = item;
                }
                if (item > max) {
                    max = item;
                }

                sum = sum + item;
            }

            average = sum / list.size();

            System.out.println("Минимальное значение в файле floats: " + min);
            System.out.println("Максимальное значение в файле floats: " + max);
            System.out.println("Сумма чисел в файле floats: " + sum);
            System.out.println("Среднее число в файле floats: " + average);

        }
    }

    public static void manageLongFile(List<String> options, String pathToSaveFile, String prefixOutFiles, List<Long> listOfLong) throws IOException {
        int countInt = 0;
        FileWriter writerInt = new FileWriter(pathToSaveFile + prefixOutFiles + "integers.txt", true);

        for (Long item : listOfLong) {
            writerInt.write(item.toString());
            writerInt.write("\n");
        }
        writerInt.close();

        if (options.contains("-s")) {
            countInt = getLinesCount(pathToSaveFile + prefixOutFiles + "integers.txt").size();
            System.out.println("Количество строк в файле integers: " + countInt);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countInt = getLinesCount(pathToSaveFile + prefixOutFiles + "integers.txt").size();
                System.out.println("Количество строк в файле integers: " + countInt);
            }

            List<String> arrayOfString = getLinesCount(pathToSaveFile + prefixOutFiles + "integers.txt");
            List<Long> list = new ArrayList<>();

            for (String item : arrayOfString) {
                Long itemOfLong = Long.valueOf(item);
                list.add(itemOfLong);
            }

            long min = list.get(0);
            long max = list.get(0);
            long sum = 0;
            long average = 0;

            for (long item : list) {
                if (item < min) {
                    min = item;
                }
                if (item > max) {
                    max = item;
                }

                sum = sum + item;
            }

            average = sum / list.size();

            System.out.println("Минимальное значение в файле integers: " + min);
            System.out.println("Максимальное значение в файле integers: " + max);
            System.out.println("Сумма чисел в файле integers: " + sum);
            System.out.println("Среднее число в файле integers: " + average);
        }
    }

    public static void manageStringFile(List<String> options, String pathToSaveFile, String prefixOutFiles, List<String> listOfString) throws IOException {
        int countSt = 0;
        FileWriter writerString = new FileWriter(pathToSaveFile + prefixOutFiles + "strings.txt", true);

        for (String item : listOfString) {
            writerString.write(item);
            writerString.write("\n");

        }

        writerString.close();

        if (options.contains("-s")) {
            countSt = getLinesCount(pathToSaveFile + prefixOutFiles + "strings.txt").size();
            System.out.println("Количество строк в файле strings: " + countSt);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countSt = getLinesCount(pathToSaveFile + prefixOutFiles + "strings.txt").size();
                System.out.println("Количество строк в файле strings: " + countSt);
            }

            int longest = listOfString.get(0).length();
            int shortest = listOfString.get(0).length();

            for (String item : listOfString) {
                if (item.length() > longest) {
                    longest = item.length();
                }

                if (item.length() < shortest) {
                    shortest = item.length();
                }
            }

            System.out.println("Размер самой длинной строки: " + longest);
            System.out.println("Размер самой короткой строки: " + shortest);
        }
    }
}