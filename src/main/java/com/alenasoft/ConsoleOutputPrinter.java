package com.alenasoft;

import com.alenasoft.application.Frame;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import com.alenasoft.domain.PlayerGame;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputPrinter implements OutputPrinter {

  @Override
  public void print(List<PlayerGame> playerGames) {
    System.out.println(
        String.join(
            "\n",
            this.frameRowToPrint(),
            playerGames
                .stream()
                .sorted((pg1, pg2) -> Boolean.compare(pg2.isValidGame(), pg1.isValidGame()))
                .map(this::printablePlayerGame)
                .collect(Collectors.joining("\n"))));
  }

  private String frameRowToPrint() {
    String headerRow = "Frame";
    for (int i = 1; i <= GameConstants.maxFramesLength; i++) {
      headerRow = headerRow.concat(String.format("\t\t%d", i));
    }
    return headerRow;
  }

  private String printablePlayerGame(PlayerGame playerGame) {
    if (!playerGame.isValidGame()) {
      return String.format("[%s does not have a valid game]", playerGame.getName());
    }

    final List<Frame> frames = playerGame.getFrames();
    return String.join(
        "\n", playerGame.getName(), this.pinfallsRowToPrint(frames), this.scoresRowToPrint(frames));
  }

  private String pinfallsRowToPrint(List<Frame> frames) {
    String rowName = "Pinfalls";
    String framePoints =
        frames
            .stream()
            .map(
                f -> {
                  try {
                    return f.pointsToPrint();
                  } catch (InvalidInputScoreException e) {
                    return "Invalid points detected";
                  }
                })
            .collect(Collectors.joining(""));

    return String.join("\t", rowName, framePoints);
  }

  private String scoresRowToPrint(List<Frame> frames) {
    String rowName = "Score";
    String frameScores =
        frames
            .stream()
            .map(f -> String.format("%d", f.getScore()))
            .collect(Collectors.joining("\t\t"));

    return String.join("\t\t", rowName, frameScores);
  }
}
