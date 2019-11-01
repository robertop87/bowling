package com.alenasoft.commons;

import com.alenasoft.application.exceptions.InvalidInputScoreException;

public interface ScoreParser {

  String warningTemplate = "WARNING: Invalid Score found [%s], this invalidates the PlayerGame";

  int parseToNumericScore(String inputScore) throws InvalidInputScoreException;

  static ScoreParser defaultParser() {
    return new ScoreParserImpl();
  }
}
