package com.company;

import java.io.File;

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
}
