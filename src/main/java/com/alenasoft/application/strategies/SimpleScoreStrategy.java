package com.alenasoft.application.strategies;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.ScoreStrategy;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import java.util.List;

class SimpleScoreStrategy implements ScoreStrategy {

  public static final String exceedsMaxScoreTemplate =
      "The current sum of points [%d] in Frame [%s] exceeds the max allowed (%s)";
  private final FrameOrganizer frameOrganizer = FrameOrganizer.defaultFrameOrganizer();

  @Override
  public void score(int frameIndex, List<Frame> frames) throws InvalidInputScoreException {
    int score =
        (frameIndex == 1)
            ? GameConstants.minPinfall
            : this.frameOrganizer.getByIndex(frameIndex - 1, frames).getScore();

    Frame currentFrame = this.frameOrganizer.getByIndex(frameIndex, frames);
    int currentSumOfPoints = currentFrame.sumOfPoints();

    if (frameIndex != GameConstants.maxFramesLength
        && currentSumOfPoints > GameConstants.maxPinfall) {
      throw new InvalidInputScoreException(
          String.format(
              exceedsMaxScoreTemplate, currentSumOfPoints, frameIndex, GameConstants.maxPinfall));
    }

    score += currentFrame.sumOfPoints();
    currentFrame.setScore(score);
  }
}
