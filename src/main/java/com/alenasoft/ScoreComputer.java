package com.alenasoft;

import java.util.List;

public interface ScoreComputer {

  static void computeScore(List<Frame> frames) {
    frames.forEach(f -> ScoreStrategyProvider.provideFor(f).score(f.getIndex(), frames));
  }
}
