package com.alenasoft;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.PlayerGame;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.strategies.ScoreStrategyProvider;
import com.alenasoft.commons.GameConstants;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultScoreCalculator implements ScoreCalculator {

  public static final Logger log = LogManager.getLogger();
  private static final String invalidNumberOfAttemptsTemplate = "Invalid Number of Attempts [%d]";

  private FrameOrganizer frameOrganizer;

  public DefaultScoreCalculator() {
    this.frameOrganizer = FrameOrganizer.defaultFrameOrganizer();
  }

  public DefaultScoreCalculator(
      FrameOrganizer frameOrganizer) {
    this.frameOrganizer = frameOrganizer;
  }

  @Override
  public void calculateScore(PlayerGame playerGame) {
    try {
      this.frameOrganizer.organize(playerGame);
      this.computeFrameScore(playerGame.getFrames());
      playerGame.setValidGame(Boolean.TRUE);
    } catch (InvalidInputScoreException e) {
      playerGame.setValidGame(Boolean.FALSE);
      log.error(e.getMessage());
    }
  }

  private void validateAttempts(List<Frame> frames) throws InvalidInputScoreException {
    if (frames.size() != GameConstants.maxFramesLength) {
      throw new InvalidInputScoreException(
          String.format(invalidNumberOfAttemptsTemplate, frames.size()));
    }
  }

  private void computeFrameScore(List<Frame> frames) throws InvalidInputScoreException {
    this.validateAttempts(frames);
    for (Frame f : frames) {
      ScoreStrategyProvider.provideFor(f).score(f.getIndex(), frames);
    }
  }
}
