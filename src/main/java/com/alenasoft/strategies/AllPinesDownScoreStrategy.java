package com.alenasoft.strategies;

import com.alenasoft.Frame;
import com.alenasoft.FrameOrganizer;
import com.alenasoft.ScoreStrategy;
import java.util.List;

public class AllPinesDownScoreStrategy implements ScoreStrategy {

  private int nextPointSize;

  public AllPinesDownScoreStrategy(int nextPointSize) {
    this.nextPointSize = nextPointSize;
  }

  @Override
  public void score(int frameIndex, List<Frame> frames) {
    int score = (frameIndex == 1) ? 0
        : FrameOrganizer.getByIndex(frameIndex - 1, frames).getScore();

    Frame currentFrame = FrameOrganizer.getByIndex(frameIndex, frames);
    score += currentFrame.sumOfPoints();

    int nextValueCounter = 0;
    int nextFrameDistance = 1;

    while (nextValueCounter < this.nextPointSize) {
      final Frame nextFrame = FrameOrganizer.getByIndex(frameIndex + nextFrameDistance, frames);
      final int pointSize = nextFrame.getPoints().length;
      nextFrameDistance++;

      if (pointSize == 3) {
        if (nextValueCounter == 0) {
          score += (nextFrame.getPoints()[0] + nextFrame.getPoints()[1]);
          nextValueCounter += 2;
        }

        if (nextValueCounter == 1) {
          score += nextFrame.getPoints()[0];
          nextValueCounter += 1;
        }

        continue;
      }

      if (nextValueCounter == 0) {
        score += this.nextPointSize == 1 ? nextFrame.getPoints()[0] : nextFrame.sumOfPoints();
        nextValueCounter += pointSize;
        continue;
      }

      if (nextValueCounter == 1) {
        score += nextFrame.getPoints()[0];
        nextValueCounter += pointSize;
      }
    }

    currentFrame.setScore(score);
  }
}
