package com.alenasoft.commons;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import java.util.Objects;

public interface ScoreParser {

  String warningTemplate = "WARNING: Invalid Score found [%s], this invalidates the PlayerGame";

  static int parseToNumericScore(String inputScore)
      throws InvalidInputScoreException {
    if (Objects.isNull(inputScore)) {
      fireInvalidInputException("null");
      return Constants.minPinfall;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (Constants.faultChar.equals(sanitizedInputScore)) {
      return Constants.minPinfall;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < Constants.minPinfall || score > Constants.maxPinfall) {
        fireInvalidInputException(sanitizedInputScore);
      }
      return score;
    } catch (NumberFormatException exception) {
      fireInvalidInputException(sanitizedInputScore);
    }
    return Constants.minPinfall;
  }

  static void fireInvalidInputException(String inputScore)
      throws InvalidInputScoreException {
    System.err.println(String.format(warningTemplate, inputScore));
    throw new InvalidInputScoreException(String.format(warningTemplate, inputScore));
  }
}
