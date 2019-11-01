package com.alenasoft.commons;

import com.alenasoft.application.exceptions.InvalidInputScoreException;

public interface ScoreParser {

  int parseToNumericScore(String inputScore) throws InvalidInputScoreException;

  static ScoreParser defaultParser() {
    return new ScoreParserImpl();
  }
}
