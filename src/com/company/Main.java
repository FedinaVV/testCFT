package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        List<String> options = new ArrayList<String>(Arrays.asList(args));

        if (options.contains("-o") || options.contains("-о")) {
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
            System.out.println("its work");
        }

        if (options.contains("-p") || options.contains("-р")) {
            int index = options.indexOf("-p");

            if (index == -1) {
                index = options.indexOf("-р");
            }

            prefixOutFiles = options.get(index + 1);

            if (prefixOutFiles.contains("\\") || prefixOutFiles.contains("/") || prefixOutFiles.contains(":") || prefixOutFiles.contains("*")
                || prefixOutFiles.contains("?") || prefixOutFiles.contains("\"") || prefixOutFiles.contains("<") || prefixOutFiles.contains(">")
                    || prefixOutFiles.contains("|") || prefixOutFiles.contains("+")) {
                System.out.println("Символы \\ / : * ? \" < > | + не допускаются для использования в именах файлов");
                throw new IOException ("Недопустимые символы в имени файлов");
            }
        }

        Scanner scanner = new Scanner(new File("file.txt"));


        File oldFileString = new File(prefixOutFiles + "strings.txt");
        File oldFileInt = new File(prefixOutFiles +"integers.txt");
        File oldFileFloat = new File(prefixOutFiles +"floats.txt");
        oldFileString.delete();
        oldFileInt.delete();
        oldFileFloat.delete();

        try {

            List<Long> listOfLong = new ArrayList<>();
            List<String> listOfString = new ArrayList<>();
            List<Float> listOfFloat = new ArrayList<>();

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

            if (listOfLong.size() > 0) {
                FileWriter writerInt = new FileWriter(pathToSaveFile + prefixOutFiles + "integers.txt", true);

                for (Long item : listOfLong) {
                    writerInt.write(item.toString());
                    writerInt.write("\n");
                }
                writerInt.close();
            }

            if (listOfFloat.size() > 0) {
                FileWriter writerFloat = new FileWriter(pathToSaveFile + prefixOutFiles + "floats.txt", true);

                for (Float item : listOfFloat) {
                    writerFloat.write(item.toString());
                    writerFloat.write("\n");
                }

                writerFloat.close();
            }

            if (listOfString.size() > 0) {
                FileWriter writerString = new FileWriter(pathToSaveFile + prefixOutFiles + "strings.txt", true);

                for (String item : listOfString) {
                    writerString.write(item);
                    writerString.write("\n");
                }

                writerString.close();
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }
    }
}
