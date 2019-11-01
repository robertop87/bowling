package com.alenasoft;

import com.alenasoft.application.PlayerGame;
import com.alenasoft.commons.GameConstants;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputPrinter implements OutputPrinter {

  @Override
  public void print(List<PlayerGame> playerGames) {
    System.out.println(String.join(
        "\n",
        this.frameRowToPrint(),
        playerGames.stream()
            .sorted((pg1, pg2) -> Boolean.compare(pg2.isValidGame(), pg1.isValidGame()))
            .map(PlayerGame::toString)
            .collect(Collectors.joining("\n"))));
  }

  private String frameRowToPrint() {
    String headerRow = "Frame";
    for (int i = 1; i <= GameConstants.maxFramesLength; i++) {
      headerRow = headerRow.concat(String.format("\t\t%d", i));
    }
    return headerRow;
  }
}
