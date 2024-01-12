package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/file.txt"));

        File oldFileString = new File("strings.txt");
        File oldFileInt = new File("integers.txt");
        File oldFileFloat = new File("floats.txt");
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
                    System.out.println("Ошибка при преобразовании строки в число");
                }

                try {
                    Float lineFloat = Float.valueOf(lineInFile);
                    listOfFloat.add(lineFloat);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка при преобразовании строки в число с типом float");
                }

                listOfString.add(lineInFile);
            }

            if (listOfLong.size() > 0) {
                FileWriter writerInt = new FileWriter("integers.txt", true);

                for (Long item : listOfLong) {
                    writerInt.write(item.toString());
                    writerInt.write("\n");
                }
                writerInt.close();
            }

            if (listOfFloat.size() > 0) {
                FileWriter writerFloat = new FileWriter("floats.txt", true);

                for (Float item: listOfFloat) {
                    writerFloat.write(item.toString());
                    writerFloat.write("\n");
                }

                writerFloat.close();
            }

            if (listOfString.size() > 0) {
                FileWriter writerString = new FileWriter("strings.txt", true);

                for (String item: listOfString) {
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
