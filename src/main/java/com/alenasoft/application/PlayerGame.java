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

  public static Logger log = LogManager.getLogger();

  public static final String invalidNumberOfAttemptsTemplate = "Invalid Number of Attempts [%d] for [%s] player";

  private final FrameOrganizer frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  private final String name;
  private final List<String> inputScores;
  private List<Frame> frames;

  public PlayerGame(String name) throws InvalidInputScoreException {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) throws InvalidInputScoreException {
    this.name = name;
    this.inputScores = inputScores;
    this.calculateScores();
  }

  public void calculateScores() throws InvalidInputScoreException {
    this.frames = this.frameOrganizer.organize(this.inputScores);
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
    return String.join("\n", this.pinfallsRowToPrint(), this.scoresRowToPrint());
  }

  public void validateAttempts() throws InvalidInputScoreException {
    if (this.frames.size() != GameConstants.maxFramesLength) {
      throw new InvalidInputScoreException(
          String.format(invalidNumberOfAttemptsTemplate, this.frames.size(), this.name));
    }
  }

  public void computeScore() throws InvalidInputScoreException {
    this.validateAttempts();
    this.frames.forEach(f -> ScoreStrategyProvider.provideFor(f).score(f.getIndex(), this.frames));
  }
}
