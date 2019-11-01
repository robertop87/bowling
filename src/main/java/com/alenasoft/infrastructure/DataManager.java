package com.alenasoft.infrastructure;

import com.alenasoft.application.PlayerGame;
import com.alenasoft.commons.GameConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {

  private List<PlayerGame> playerGames;

  public DataManager() {
    this.playerGames = new ArrayList<>();
  }

  public void processData(List<PlayerGame> playerGames) {
    this.playerGames = playerGames;
    for (PlayerGame playerGame : this.playerGames) {
      playerGame.calculateScores();
    }
  }

  @Override
  public String toString() {
    return String.join(
        "\n",
        this.frameRowToPrint(),
        this.playerGames.stream()
            .sorted((pg1, pg2) -> Boolean.compare(pg2.isValidGame(), pg1.isValidGame()))
            .map(PlayerGame::toString)
            .collect(Collectors.joining("\n")));
  }

  private String frameRowToPrint() {
    String headerRow = "Frame";
    for (int i = 1; i <= GameConstants.maxFramesLength; i++) {
      headerRow = headerRow.concat(String.format("\t\t%d", i));
    }
    return headerRow;
  }
}
