package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Методы работы с файлами
 */
public class FileHandler {

    /**
     * Удаляет выходные файлы с учетом префиуса
     * @param prefixOutFiles - префикс имен выходных файлов
     */
    public void deleteOldFiles(String prefixOutFiles) {
        File oldFileString = new File(prefixOutFiles + "strings.txt");
        File oldFileInt = new File(prefixOutFiles + "integers.txt");
        File oldFileFloat = new File(prefixOutFiles + "floats.txt");
        oldFileString.delete();
        oldFileInt.delete();
        oldFileFloat.delete();
    }

    /**
     * Записывает данные типа float в файл
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfFloat - список данных типа float
     * @throws IOException - если файл не удалось создать
     */
    public void writeFloatOutFile(String pathToSaveFile, String prefixOutFiles, List<Float> listOfFloat) throws IOException {
        try (FileWriter writerFloat = new FileWriter(pathToSaveFile + prefixOutFiles + "floats.txt", true)) {
            for (Float item : listOfFloat) {
                writerFloat.write(item.toString());
                writerFloat.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа float в файл");
            e.printStackTrace();
        }
    }

    /**
     * Записывает данные типа long в файл
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfLong - список данных типа long
     * @throws IOException - если файл не удалось создать
     */
    public void writeLongOutFile(String pathToSaveFile, String prefixOutFiles, List<Long> listOfLong) throws IOException {

        try ( FileWriter writerInt = new FileWriter(pathToSaveFile + prefixOutFiles + "integers.txt", true);) {
            for (Long item : listOfLong) {
                writerInt.write(item.toString());
                writerInt.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа long в файл");
            e.printStackTrace();
        }
    }

    /**
     * Записывает данные типа string в файл
     * @param pathToSaveFile - путь сохранения файла
     * @param prefixOutFiles - префикс имен выходных файлов
     * @param listOfString - список данных типа string
     * @throws IOException - если файл не удалось создать
     */
    public void writeStringOutFile(String pathToSaveFile, String prefixOutFiles, List<String> listOfString) throws IOException {

        try(FileWriter writerString = new FileWriter(pathToSaveFile + prefixOutFiles + "strings.txt", true);) {
            for (String item : listOfString) {
                writerString.write(item);
                writerString.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать данные типа string в файл");
            e.printStackTrace();
        }
    }
}
