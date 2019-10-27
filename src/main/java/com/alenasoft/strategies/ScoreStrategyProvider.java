package com.alenasoft.strategies;

import com.alenasoft.Frame;
import com.alenasoft.ScoreStrategy;

public interface ScoreStrategyProvider {

  static ScoreStrategy provideFor(Frame frame) {
    if (frame.getPoints().length == 1 && frame.sumOfPoints() == 10) {
      return new StrikeScoreStrategy();
    }

    if (frame.getPoints().length == 2 && frame.sumOfPoints() == 10) {
      return new SpareScoreStrategy();
    }

    return new SimpleScoreStrategy();
  }
}
