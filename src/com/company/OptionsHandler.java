package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Методы работы с переменными среды (опциями)
 */
public class OptionsHandler {

    /**
     * Возвращает имена входных файлов.
     * Если не перечислены в опциях, то возвращает file.txt
     * @param options - переменные среды (опции)
     * @return - имена входных файлов
     */
    public ArrayList<String> getFileNames(List<String> options) {

        ArrayList<String> listFileNames = new ArrayList<>();

        if (options.contains("-d")) {

            for (int i = options.indexOf("-d") + 1; i < options.size(); i++) {
                listFileNames.add(options.get(i));
            }
        } else {
            listFileNames.add("file.txt");
        }

        return listFileNames;
    }

    /**
     * Возвращает путь для сохранения результатов
     * @param options - переменные среды (опции)
     * @return путь для сохранения результатов
     */
    public String getPathForResults(List<String> options) {

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

    /**
     * Возарвщает префикс для названия выходных файлов
     * @param options - переменные среды (опции)
     * @return префикс для названия выходных файлов
     * @throws IOException Если недопустимые символы в имени файлов
     */
    public String getPrefixForResults(List<String> options) throws IOException {

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
}
