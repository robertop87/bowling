package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.strategies.ScoreStrategyProvider;
import com.alenasoft.commons.GameConstants;
import java.util.ArrayList;
import java.util.List;
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
      log.error(e.getMessage());
    }
  }

  public List<String> getInputScores() {
    return this.inputScores;
  }

  public String getName() {
    return this.name;
  }

  public List<Frame> getFrames() {
    return this.frames;
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
