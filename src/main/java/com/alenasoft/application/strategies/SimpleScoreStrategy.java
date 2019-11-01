package com.alenasoft.application.strategies;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.ScoreStrategy;
import com.alenasoft.commons.Constants;
import java.util.List;

class SimpleScoreStrategy implements ScoreStrategy {

  private final FrameOrganizer frameOrganizer = FrameOrganizer.defaultFrameOrganizer();

  @Override
  public void score(int frameIndex, List<Frame> frames) {
    int score =
        (frameIndex == 1)
            ? Constants.minPinfall
            : this.frameOrganizer.getByIndex(frameIndex - 1, frames).getScore();

    Frame currentFrame = this.frameOrganizer.getByIndex(frameIndex, frames);
    score += currentFrame.sumOfPoints();

    currentFrame.setScore(score);
  }
}
