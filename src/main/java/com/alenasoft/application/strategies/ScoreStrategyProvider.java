package com.alenasoft.application.strategies;

import com.alenasoft.application.Frame;
import com.alenasoft.application.ScoreStrategy;

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
