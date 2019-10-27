package com.alenasoft;

import java.util.Objects;

public interface ScoreUtils {

  String warningTemplate = "WARNING: Invalid Score found [%s], assign [0] as score";
  int minScore = 0;
  int maxScore = 10;
  String fault = "F";

  static int parseToNumericScore(String inputScore) {
    if (Objects.isNull(inputScore)) {
      return minScore;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if ("F".equals(sanitizedInputScore)) {
      return minScore;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      return (score < minScore || score > maxScore) ? 0 : score;
    } catch (NumberFormatException exception) {
      System.err.println(String.format(warningTemplate, sanitizedInputScore));
      return 0;
    }
  }
}
