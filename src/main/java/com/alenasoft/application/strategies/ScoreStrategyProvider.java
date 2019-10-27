package com.alenasoft.application.strategies;

import com.alenasoft.application.Frame;
import com.alenasoft.application.ScoreStrategy;
import com.alenasoft.commons.Constants;

public interface ScoreStrategyProvider {

  static ScoreStrategy provideFor(Frame frame) {
    if (frame.getPoints().length == 1 && frame.sumOfPoints() == Constants.strikeValue) {
      return new StrikeScoreStrategy();
    }

    if (frame.getPoints().length == 2 && frame.sumOfPoints() == Constants.maxPinfall) {
      return new SpareScoreStrategy();
    }

    return new SimpleScoreStrategy();
  }
}
