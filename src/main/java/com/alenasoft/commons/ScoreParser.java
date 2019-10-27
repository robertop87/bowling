package com.alenasoft.commons;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import java.util.Objects;

public interface ScoreParser {

  String warningTemplate = "WARNING: Invalid Score found [%s], this invalidates the PlayerGame";
  int minScore = 0;
  int maxScore = 10;
  String fault = "F";

  static int parseToNumericScore(String inputScore)
      throws InvalidInputScoreException {
    if (Objects.isNull(inputScore)) {
      fireInvalidInputException("null");
      return minScore;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (fault.equals(sanitizedInputScore)) {
      return minScore;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < minScore || score > maxScore) {
        fireInvalidInputException(sanitizedInputScore);
      }
      return score;
    } catch (NumberFormatException exception) {
      fireInvalidInputException(sanitizedInputScore);
    }
    return minScore;
  }

  static void fireInvalidInputException(String inputScore)
      throws InvalidInputScoreException {
    System.err.println(String.format(warningTemplate, inputScore));
    throw new InvalidInputScoreException(String.format(warningTemplate, inputScore));
  }
}
