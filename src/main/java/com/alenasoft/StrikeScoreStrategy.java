package com.alenasoft;

import java.util.List;

public class StrikeScoreStrategy implements ScoreStrategy {

  private final int nextPointSize = 2;

  @Override
  public void score(int frameIndex, List<Frame> frames) {
    int score = (frameIndex == 1) ? 0
        : FrameOrganizer.getByIndex(frameIndex - 1, frames).getScore();
    score += 10;

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
        score += nextFrame.sumOfPoints();
        nextValueCounter += pointSize;
        continue;
      }

      if (nextValueCounter == 1) {
        score += nextFrame.getPoints()[0];
        nextValueCounter += pointSize;
      }
    }

    FrameOrganizer.getByIndex(frameIndex, frames).setScore(score);
  }
}
