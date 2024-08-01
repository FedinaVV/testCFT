package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        List<String> options = new ArrayList<>(Arrays.asList(args));

        RunApplication runApplication = new RunApplication();

        runApplication.run(options);
    }
}