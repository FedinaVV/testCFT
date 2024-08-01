package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, обрабатывающий статистику по каждому типу отфильтрованных данных
 */
public class StatisticHandler {

    /**
     * Выводит статистику по данным типа float в консоль и в файл
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfFloat - список данных типа float
     * @throws IOException - если файл не удалось создать
     */
    public void printFloatStatistic(List<String> options, String pathToSaveFile, String prefixOutFiles, List<Float> listOfFloat) throws IOException {
        int countFl = 0;

        try (FileWriter writerFloat = new FileWriter(pathToSaveFile + prefixOutFiles + "floats.txt", true)) {
            for (Float item : listOfFloat) {
                writerFloat.write(item.toString());
                writerFloat.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа float в файл");
            e.printStackTrace();
        }

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

    /**
     * Выводит статистику по данным типа long в консоль и в файл
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfLong - список данных типа long
     * @throws IOException - если файл не удалось создать
     */
    public void printLongStatistic(List<String> options, String pathToSaveFile, String prefixOutFiles, List<Long> listOfLong) throws IOException {
        int countInt = 0;

        try ( FileWriter writerInt = new FileWriter(pathToSaveFile + prefixOutFiles + "integers.txt", true);) {
            for (Long item : listOfLong) {
                writerInt.write(item.toString());
                writerInt.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа long в файл");
            e.printStackTrace();
        }

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

    /**
     * Выводит статистику по данным типа string в консоль и в файл
     * @param options - список параметров
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfString - список данных типа string
     * @throws IOException - если файл не удалось создать
     */
    public void printStringStatistic(List<String> options, String pathToSaveFile, String prefixOutFiles, List<String> listOfString) throws IOException {
        int countSt = 0;

        try(FileWriter writerString = new FileWriter(pathToSaveFile + prefixOutFiles + "strings.txt", true);) {
            for (String item : listOfString) {
                writerString.write(item);
                writerString.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа string в файл");
            e.printStackTrace();
        }

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

    /**
     * Возвращает количество строк в файле
     * @param path - путь до входного файла
     * @return - количество строк в файле
     */
    public List<String> getLinesCount(String path) {

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
