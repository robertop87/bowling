package com.alenasoft.infrastructure.console;

import com.alenasoft.application.ScoreDataCenter;
import com.alenasoft.application.ScoreParser;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import com.alenasoft.domain.Frame;
import com.alenasoft.domain.PlayerGame;
import com.alenasoft.infrastructure.OutputPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputPrinter implements OutputPrinter {

  private final ScoreParser scoreParser = ScoreParser.defaultParser();

  @Override
  public void print(ScoreDataCenter scoreDataManager) {
    System.out.println(
        String.join(
            "\n",
            this.frameRowToPrint(),
            scoreDataManager.getPlayerGames()
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
                    return this.pointsToPrint(f);
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

  public String pointsToPrint(Frame frame) throws InvalidInputScoreException {
    if (frame.getStringPoints().length == 1
        && this.scoreParser.parseToNumericScore(frame.getStringPoints()[0]) == GameConstants.strikeValue) {
      return String.format(frame.getIndex() == 1 ? "\t%s" : "\t\t%s", GameConstants.strike);
    }

    if (frame.getStringPoints().length == 2 && frame.sumOfPoints() == GameConstants.maxPinfall) {
      return String.format(frame.getIndex() == 1 ? "%s\t%s" : "\t%s\t%s", frame.getStringPoints()[0], GameConstants.spare);
    }

    List<String> maskValues = new ArrayList<>();

    for (String stringPoint : frame.getStringPoints()) {
      maskValues.add(this.scoreParser.parseToNumericScore(stringPoint) == GameConstants.maxPinfall ? GameConstants.strike : stringPoint);
    }

    String pointsAsString = maskValues.stream().collect(Collectors.joining("\t"));
    return frame.isFirstFrame() ? pointsAsString : "\t".concat(pointsAsString);
  }
}
