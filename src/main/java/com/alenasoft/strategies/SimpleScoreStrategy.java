package com.alenasoft.strategies;

import com.alenasoft.Frame;
import com.alenasoft.FrameOrganizer;
import com.alenasoft.ScoreStrategy;
import java.util.List;

public class SimpleScoreStrategy implements ScoreStrategy {

  @Override
  public void score(int frameIndex, List<Frame> frames) {
    int score = (frameIndex == 1) ? 0
        : FrameOrganizer.getByIndex(frameIndex - 1, frames).getScore();

    Frame currentFrame = FrameOrganizer.getByIndex(frameIndex, frames);
    score += currentFrame.sumOfPoints();

    currentFrame.setScore(score);
  }
}
