package com.alenasoft;

import com.alenasoft.exceptions.InvalidInputScoreException;
import java.util.Objects;

public interface ScoreParser {

  String warningTemplate = "WARNING: Invalid Score found [%s], assign [0] as score";
  int minScore = 0;
  int maxScore = 10;
  String fault = "F";

  static int parseToNumericScore(String inputScore)
      throws InvalidInputScoreException {
    if (Objects.isNull(inputScore)) {
      return minScore;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (fault.equals(sanitizedInputScore)) {
      return minScore;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < minScore || score > maxScore) {
        System.err.println(String.format(warningTemplate, sanitizedInputScore));
        throw new InvalidInputScoreException(String.format(warningTemplate, sanitizedInputScore));
      }
      return (score < minScore || score > maxScore) ? 0 : score;
    } catch (NumberFormatException exception) {
      System.err.println(String.format(warningTemplate, sanitizedInputScore));
      throw new InvalidInputScoreException(String.format(warningTemplate, sanitizedInputScore));
    }
  }
}
