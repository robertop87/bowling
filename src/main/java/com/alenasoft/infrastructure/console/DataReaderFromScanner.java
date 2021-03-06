package com.alenasoft.infrastructure.console;

import com.alenasoft.domain.PlayerGame;
import com.alenasoft.infrastructure.DataReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataReaderFromScanner implements DataReader {

  public static final Logger log = LogManager.getLogger();

  private List<PlayerGame> playerGames;
  private Scanner scanner;

  public DataReaderFromScanner(Scanner scanner) {
    this.playerGames = new ArrayList<>();
    this.scanner = scanner;
  }

  @Override
  public List<PlayerGame> readPlayerGames() {
    while (this.scanner.hasNext()) {
      this.processLine(this.scanner.nextLine());
    }
    return playerGames;
  }

  public void processLine(String nextLine) {
    if (Objects.isNull(nextLine) || nextLine.isEmpty()) {
      log.info("Empty ROW skipped from input data text file");
      return;
    }

    String sanitized =
        nextLine
            .trim()
            .toUpperCase()
            .replaceAll("\\t", " ") // Replace tabs with space
            .replaceAll("(\\s)+", " "); // Normalize spaces

    String[] values = sanitized.split(" ");
    if (values.length < 1 || values.length > 2) {
      log.info(String.format("Invalid row input: [%s] cannot processed", nextLine));
      System.exit(-1);
    }
    this.addOrUpdate(values[0], values[1]);
  }

  private void addOrUpdate(String playerName, String inputPoint) {
    PlayerGame playerGame =
        this.findByPlayerName(playerName)
            .orElseGet(
                () -> {
                  PlayerGame newPlayerGame = new PlayerGame(playerName);
                  this.playerGames.add(newPlayerGame);
                  return newPlayerGame;
                });
    playerGame.getInputScores().add(inputPoint);
  }

  private Optional<PlayerGame> findByPlayerName(String playerName) {
    return this.playerGames.stream().filter(pg -> playerName.equals(pg.getName())).findFirst();
  }
}
