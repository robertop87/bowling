package com.alenasoft.application;

import com.alenasoft.application.defaults.DefaultScoreParser;
import com.alenasoft.application.exceptions.InvalidInputScoreException;

public interface ScoreParser {

  int parseToNumericScore(String inputScore) throws InvalidInputScoreException;

  static ScoreParser defaultParser() {
    return new DefaultScoreParser();
  }
}
