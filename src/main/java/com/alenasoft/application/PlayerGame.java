package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.strategies.ScoreStrategyProvider;
import com.alenasoft.commons.GameConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerGame {

  public static final Logger log = LogManager.getLogger();

  private static final String invalidNumberOfAttemptsTemplate = "Invalid Number of Attempts [%d] for [%s] player";

  private final FrameOrganizer frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  private final String name;
  private final List<String> inputScores;
  private List<Frame> frames;
  private boolean validGame;

  public PlayerGame(String name) {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
  }

  public void calculateScores() {
    try {
      this.frames = this.frameOrganizer.organize(this.inputScores);
      this.computeScore();
      this.validGame = Boolean.TRUE;
    } catch (InvalidInputScoreException e) {
      this.validGame = Boolean.FALSE;
      //log.error(e.getMessage());
    }
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
            .collect(Collectors.joining(""));

    return String.join("\t", rowName, framePoints);
  }

  private String scoresRowToPrint() {
    String rowName = "Score";
    String frameScores =
        this.frames
            .stream()
            .map(f -> String.format("%d", f.getScore()))
            .collect(Collectors.joining("\t\t"));

    return String.join("\t\t", rowName, frameScores);
  }

  @Override
  public String toString() {
    if (!this.validGame) {
      return String.format("[%s does not have a valid game]", this.name);
    }

    return String.join("\n", this.name, this.pinfallsRowToPrint(), this.scoresRowToPrint());
  }

  public void validateAttempts() throws InvalidInputScoreException {
    if (this.frames.size() != GameConstants.maxFramesLength) {
      throw new InvalidInputScoreException(
          String.format(invalidNumberOfAttemptsTemplate, this.frames.size(), this.name));
    }
  }

  private void computeScore() throws InvalidInputScoreException {
    this.validateAttempts();
    for (Frame f : this.frames) {
      ScoreStrategyProvider.provideFor(f).score(f.getIndex(), this.frames);
    }
  }

  public boolean isValidGame() {
    return this.validGame;
  }
}
