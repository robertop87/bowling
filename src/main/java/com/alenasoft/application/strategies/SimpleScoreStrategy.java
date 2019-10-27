package com.alenasoft.application.strategies;

import com.alenasoft.application.Frame;
import com.alenasoft.application.FrameOrganizer;
import com.alenasoft.application.ScoreStrategy;
import java.util.List;

class SimpleScoreStrategy implements ScoreStrategy {

  @Override
  public void score(int frameIndex, List<Frame> frames) {
    int score = (frameIndex == 1) ? 0
        : FrameOrganizer.getByIndex(frameIndex - 1, frames).getScore();

    Frame currentFrame = FrameOrganizer.getByIndex(frameIndex, frames);
    score += currentFrame.sumOfPoints();

    currentFrame.setScore(score);
  }
}
