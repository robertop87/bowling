package com.alenasoft;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.infrastructure.DataManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

  public static Logger log = LogManager.getLogger();

  public static void main(String[] args) {
    if (args.length <= 0) {
      log.info("Provide a valid path. Example: /home/user/file.txt");
      System.exit(-1);
    }

    try {
      Scanner fileScanner = new Scanner(new File(args[0]));
      DataManager dataManager = new DataManager();
      while (fileScanner.hasNext()) {
        dataManager.processLine(fileScanner.nextLine());
      }
      dataManager.processData();
      System.out.println(dataManager);
    } catch (FileNotFoundException | InvalidInputScoreException e) {
      log.error(e.getMessage());
      System.exit(-1);
    } finally{
      System.exit(0);
    }
  }
}
