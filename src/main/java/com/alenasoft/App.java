package com.alenasoft;

import com.alenasoft.infrastructure.DataManager;
import com.alenasoft.infrastructure.DataReader;
import com.alenasoft.infrastructure.OutputPrinter;
import com.alenasoft.infrastructure.console.ConsoleOutputPrinter;
import com.alenasoft.infrastructure.console.DataReaderFromScanner;
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
      DataManager dataManager = new DataManager();
      DataReader dataReader = new DataReaderFromScanner(new Scanner(new File(args[0])));
      dataManager.processData(dataReader.readPlayerGames());

      OutputPrinter outputPrinter = new ConsoleOutputPrinter();
      outputPrinter.print(dataManager.getPlayerGames());
    } catch (FileNotFoundException e) {
      log.error(e.getMessage());
      System.exit(-1);
    } finally{
      System.exit(0);
    }
  }
}
