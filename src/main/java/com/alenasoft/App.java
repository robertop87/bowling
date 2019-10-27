package com.alenasoft;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    if (args.length <= 0) {
      System.err.println("Provide a valid path. Example: /home/user/file.txt");
    }

    try {
      Scanner fileScanner = new Scanner(new File(args[0]));
      InputProcessor inputProcessor = new InputProcessor();
      while (fileScanner.hasNext()) {
        inputProcessor.processLine(fileScanner.nextLine());
      }
      inputProcessor.processData();
      System.out.println(inputProcessor);

    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      System.exit(-1);
    }
  }
}
