package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.strategies.ScoreStrategyProvider;
import com.alenasoft.commons.GameConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerGame {

  private final FrameOrganizer frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  private final String name;
  private final List<String> inputScores;
  private List<Frame> frames;

  public PlayerGame(String name) {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
    this.calculateScores();
  }

  public void calculateScores() {
    try {
      this.frames = this.frameOrganizer.organize(this.inputScores);
    } catch (InvalidInputScoreException e) {
      this.frames.forEach(f -> f.setScore(GameConstants.minPinfall));
    }

    this.computeScore();
  }

  public List<String> getInputScores() {
    return this.inputScores;
  }

  public String getName() {
    return this.name;
  }

  private String pinfallsRowToPrint() {
    String rowName = "Pinfalls";
    String framePoints =
        this.frames
            .stream()
            .map(
                f -> {
                  try {
                    return f.pointsToPrint();
                  } catch (InvalidInputScoreException e) {
                    return "Invalid points detected";
                  }
                })
            .collect(Collectors.joining("\t\t"));

    return String.join("\t", rowName, framePoints);
  }

  private String scoresRowToPrint() {
    String rowName = "Score";
    String frameScores =
        this.frames
            .stream()
            .map(f -> String.format("%5d", f.getScore()))
            .collect(Collectors.joining("\t\t"));

    return String.join("\t\t", rowName, frameScores);
  }

  @Override
  public String toString() {
    String nameWithTag = this.name.concat(!this.isValid() ? " [Invalid Game]" : "");
    return String.join("\n", nameWithTag, this.pinfallsRowToPrint(), this.scoresRowToPrint());
  }

  public boolean isValid() {
    return this.frames.size() == GameConstants.maxFramesLength;
  }

  public void computeScore() {
    if (!this.isValid()) {
      this.frames.forEach(f -> f.setScore(GameConstants.minPinfall));
      return;
    }

    this.frames.forEach(f -> ScoreStrategyProvider.provideFor(f).score(f.getIndex(), this.frames));
  }
}
