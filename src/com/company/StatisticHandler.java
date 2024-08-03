package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, обрабатывающий статистику по каждому типу отфильтрованных данных
 */
public class StatisticHandler {

    /**
     * Выводит статистику по данным типа float в консоль
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     */
    public void printFloatStatisticInConsoleFromFile(List<String> options, String pathToSaveFile, String prefixOutFiles) {
        int countFl = 0;
        if (options.contains("-s")) {
            countFl = getLinesOfFile(pathToSaveFile + prefixOutFiles + "floats.txt").size();
            System.out.println("Количество строк в файле floats: " + countFl);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countFl = getLinesOfFile(pathToSaveFile + prefixOutFiles + "floats.txt").size();
                System.out.println("Количество строк в файле floats: " + countFl);
            }

            List<String> arrayOfString = getLinesOfFile(pathToSaveFile + prefixOutFiles + "floats.txt");
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

    /**
     * Выводит статистику по данным типа long в консоль
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     */
    public void printLongStatisticInConsoleFromFile(List<String> options, String pathToSaveFile, String prefixOutFiles) {
        int countInt = 0;
        if (options.contains("-s")) {
            countInt = getLinesOfFile(pathToSaveFile + prefixOutFiles + "integers.txt").size();
            System.out.println("Количество строк в файле integers: " + countInt);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countInt = getLinesOfFile(pathToSaveFile + prefixOutFiles + "integers.txt").size();
                System.out.println("Количество строк в файле integers: " + countInt);
            }

            List<String> arrayOfString = getLinesOfFile(pathToSaveFile + prefixOutFiles + "integers.txt");
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

    /**
     * Выводит статистику по данным типа string в консоль
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfString - список данных типа string
     */
    public void printStringStatisticInConsoleFromFile(List<String> options, String pathToSaveFile, String prefixOutFiles, List<String> listOfString) {
        int countSt = 0;
        if (options.contains("-s")) {
            countSt = getLinesOfFile(pathToSaveFile + prefixOutFiles + "strings.txt").size();
            System.out.println("Количество строк в файле strings: " + countSt);
        }

        if (options.contains("-f")) {
            if (!options.contains("-s")) {
                countSt = getLinesOfFile(pathToSaveFile + prefixOutFiles + "strings.txt").size();
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

    /**
     * Возвращает массив строк файла
     * @param path - путь до файла
     * @return - массив строк файла
     */
    public List<String> getLinesOfFile(String path) {

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
}
