package com.alenasoft;

import com.alenasoft.strategies.SimpleScoreStrategy;
import com.alenasoft.strategies.SpareScoreStrategy;
import com.alenasoft.strategies.StrikeScoreStrategy;

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
