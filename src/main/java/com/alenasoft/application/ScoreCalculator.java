package com.alenasoft.application;

import com.alenasoft.domain.PlayerGame;

public interface ScoreCalculator {

  void calculateScore(PlayerGame playerGame);

  static ScoreCalculator defaultScoreCalculator() {
    return new DefaultScoreCalculator();
  }
}
