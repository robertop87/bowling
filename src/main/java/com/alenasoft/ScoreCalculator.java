package com.alenasoft;

import com.alenasoft.application.PlayerGame;

public interface ScoreCalculator {

  void calculateScore(PlayerGame playerGame);

  static ScoreCalculator defaultScoreCalculator() {
    return new DefaultScoreCalculator();
  }
}
